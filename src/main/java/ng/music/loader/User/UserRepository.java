package ng.music.loader.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Long> {
    /* Find a user that matches the given username and password
     * @params username     String
     * @params password     String
     * @return              User(json)
     */
    Optional<User> findByUsernameAndPassword(String username, String password);
}
