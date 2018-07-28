package ng.music.loader.Artist;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface ArtistRepository extends CrudRepository<Artist, Long> {

    Optional<Artist> findByName(String name);
}
