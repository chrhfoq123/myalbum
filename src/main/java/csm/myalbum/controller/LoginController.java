package csm.myalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//    @GetMapping("login")
    public String login(){
        return "loginForm";
    }

//    @GetMapping("addMember")
    public String addMember(){
        return "addMemberForm";
    }

//    @GetMapping("albumList")
    public String albumList(){
        return "albumListForm";
    }

//    @GetMapping("photoList")
    public String photoList(){
        return "photoListForm";
    }

//    @GetMapping("photoInfo")
    public String photoInfo(){
        return "photoInfoForm";
    }
}
