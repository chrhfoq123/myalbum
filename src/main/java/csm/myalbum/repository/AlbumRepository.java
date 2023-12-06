package csm.myalbum.repository;

import csm.myalbum.domain.Album;
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

    public Album findOne(Long albumId){
        return em.find(Album.class, albumId);
    }

    public List<Album> findAll(){
        return em.createQuery("select a from Album a", Album.class)
                .getResultList();
    }

    public Album findById(Long albumId){
        return em.createQuery("select a from Album a where a.albumId = :albumId", Album.class)
                .setParameter("albumId", albumId)
                .getSingleResult();
    }
}
