package csm.myalbum.controller;

import csm.myalbum.domain.User;
import csm.myalbum.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "redirect:/";
    }
}
