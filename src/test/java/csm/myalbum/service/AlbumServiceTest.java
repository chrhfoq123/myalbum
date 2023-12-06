package csm.myalbum.service;

import csm.myalbum.domain.Album;
import csm.myalbum.repository.AlbumRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlbumServiceTest {

    @Autowired AlbumRepository albumRepository;
    @Autowired AlbumService albumService;

    @Test
    public void getAlbum() throws Exception{
        //given
        Album album = new Album();
        album.setAlbumName("테스트");
        albumRepository.save(album);

        //when
        Album resAlbum = albumService.getAlbum(album.getAlbumId());

        //then
        Assertions.assertThat(resAlbum.getAlbumName()).isEqualTo("테스트");
    }
}