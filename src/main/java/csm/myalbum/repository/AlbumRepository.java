package csm.myalbum.repository;

import csm.myalbum.domain.Album;
import csm.myalbum.domain.Photo;
import csm.myalbum.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlbumRepository {

    private final EntityManager em;

    public void save(Album album){
        em.persist(album);
    }

    public List<Album> findAll(){
        return em.createQuery("select a from Album a", Album.class)
                .getResultList();
    }
}
