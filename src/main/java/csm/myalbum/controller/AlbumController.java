package csm.myalbum.controller;

import csm.myalbum.domain.User;
import csm.myalbum.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/albumList")
    public String albumList(@SessionAttribute(name = "loginUser", required = false) User loginUser){
        if(loginUser == null){
            return "redirect:/users/login";
        }

        return "albums/albumListForm";
    }

//    @PostMapping()
//    public String createAlbum(){
//
//    }

}
