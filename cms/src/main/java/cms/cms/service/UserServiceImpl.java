package cms.cms.service;

import cms.cms.dto.UserDto;
import cms.cms.model.User;
import cms.cms.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
@Log4j2
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpSession session;

    @Override
    public User save(UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getPhone(),
                userDto.getEmail(),
                new Date(),
                userDto.getUpdated_at(),
                true,
                false
        );
        return userRepository.save(user);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findUserByUsername(email).isPresent();
    }

    @Override
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    @Override
    public User getUserInfo() {
        log.info("getUsername: " + getUserName());
        User user = userRepository.getUserInfoByUsername(getUserName());
        if (user != null) {
            log.info("session user: " + user);
            session.setAttribute("user", user);
        } else {
            log.warn("No user found for username: " + getUserName());
        }
        return user;
    }


}
