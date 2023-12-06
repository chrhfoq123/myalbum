package csm.myalbum.service;

import csm.myalbum.domain.Album;
import csm.myalbum.repository.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    public Album getAlbum(Long albumId){
        Album res = albumRepository.findById(albumId);
        if(res != null){
            return res;
        }else{
            throw new EntityNotFoundException(String.format("앨범 아이디 %d로 조회되지 않았습니다.", albumId));
        }
    }
}
