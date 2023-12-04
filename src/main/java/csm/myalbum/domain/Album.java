package csm.myalbum.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Album {

    @Id @GeneratedValue
    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
