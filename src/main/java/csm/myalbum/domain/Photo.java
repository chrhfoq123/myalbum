package csm.myalbum.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Photo {

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long photoId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "thumb_url")
    private String thumbUrl;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "file_size")
    private int fileSize;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;
}
