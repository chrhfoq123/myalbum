package csm.myalbum.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "login_at")
    private LocalDateTime loginAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Album> albums;
}
