package csm.myalbum.controller;

import csm.myalbum.domain.User;
import csm.myalbum.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user){
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "users/addUserForm";
        }

        userService.join(user);
        return "redirect:/users/login"; //다시 로그인화면으로
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") User user){
        return "users/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "users/loginForm";
        }

        User loginUser = userService.login(user);
        log.info("login? {}", loginUser);

        if(loginUser == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 확인해주세요.");
            return "users/loginForm";
        }

        return "redirect:/albumListForm";
    }
}
