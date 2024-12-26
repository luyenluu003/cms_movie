package cms.cms.service;

import cms.cms.dto.UserDto;
import cms.cms.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User save(UserDto userDto);
    boolean emailExists(String email);

    User getUserInfo();

    String getUserName();
}
