package csm.myalbum.controller;

import jakarta.validation.constraints.NotEmpty;

public class addUserForm {

    @NotEmpty(message = "이름을 작성해주세요.")
    private String userName;

    @NotEmpty(message = "이메일을 작성해주세요.")
    private String userEmail;

    @NotEmpty(message = "비밀번호를 작성해주세요.")
    private String userPassword;
}
