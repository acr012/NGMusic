package ng.music.loader.Album;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface AlbumRepository extends CrudRepository<Album, Long> {
    Optional<Album> findByAlbumName(String name);
}
