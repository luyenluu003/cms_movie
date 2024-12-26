package cms.cms.controller;

import cms.cms.common.Layout;
import cms.cms.common.Pages;
import cms.cms.dto.UserDto;
import cms.cms.model.User;
import cms.cms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user")UserDto userDto){
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        if (userService.emailExists(userDto.getEmail())) {
            model.addAttribute("error", "Email has been registered!");
            return "register";
        }
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfully");
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/cmsmovie")
    public String home(Model model){
        model.addAttribute(Layout.VIEW, Pages.HOME.uri());

        return "index";
    }

//    @PostMapping("/login")
//    public String login(HttpServletRequest request) {
//        String username = request.getParameter("email");  // Lấy email từ form login
//        String password = request.getParameter("password");  // Lấy mật khẩu từ form login
//
//        System.out.println("Username: " + username);
//        System.out.println("Password: " + password);
//
//        return "redirect:/home";  // Hoặc bất kỳ trang nào bạn muốn chuyển hướng
//    }

    @GetMapping("/")
    public String test(){
        return "index";
    }
}
