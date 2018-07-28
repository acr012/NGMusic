package ng.music.loader;

import javassist.NotFoundException;
import ng.music.loader.User.User;
import ng.music.loader.User.UserRepository;
import ng.music.loader.Album.Album;
import ng.music.loader.Album.AlbumRepository;
import ng.music.loader.Artist.ArtistRepository;
import ng.music.loader.ExceptionHandlers.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ng.music.loader.Artist.Artist;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping(path="/")
public class NGMusicController {

    /* ========================== REPOSITORIES ==========================
     * Retrieve the auto-generated repository beans,
     * which are used to handle the data
     */
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;




    /* ============================== USER ==============================
     * addNewUser creates a new user record
     * @ResponseBody means the returned String is the response, not a view name
     * @RequestParam means it is a parameter from the GET or POST request
     */
    @PostMapping(path="add/user")   ///tested
    public @ResponseBody ResponseEntity addNewUser (@RequestHeader(value ="name") String name,
                                                    @RequestHeader(value ="password") String password) {
        //Check if user already exists
        if(userRepository.findByUsernameAndPassword(name, password).isPresent()){
            throw new ResourceAlreadyExistsException("User Already Exists" + "/n" +
                    "name: " + name + " password: " + password);
        }

        //Create user
        User newUser = new User();
        newUser.setUsername(name);
        newUser.setPassword(password);
        User savedUser = userRepository.save(newUser);

        //Format response
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
                .buildAndExpand(savedUser.getUsername()).toUri();
        HttpHeaders header = new HttpHeaders();
        header.setLocation(location);

        return new ResponseEntity(header, HttpStatus.CREATED);
    }

    /* getAllUsers returns a JSON of the users
     * Note: used for development
     */
    @GetMapping(path="all/users") //tested
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /* getArtistByName returns a JSON of the artists
     * Note: used to display a single artists
     */
    @GetMapping(path="validate/{username}") //tested
    public @ResponseBody User getUserByNameAndPassword(@PathVariable String username,
                                                       @RequestHeader(value= "password") String password)
                                                       throws NotFoundException {
        Optional<User> user =  userRepository.findByUsernameAndPassword(username, password);

        if(!user.isPresent()){
            throw new NotFoundException("user: " + username);
        }

        return user.get();

    }


    /* ============================== ARTIST ==============================
     * addNewArtist creates a new artist record
     * @ResponseBody means the returned String is the response, not a view name
     * @RequestParam means it is a parameter from the GET or POST request
     */
    @PostMapping(path="add/artist")
    public @ResponseBody String addNewArtist (@RequestParam(value= "name") String name,
                                              @RequestParam(value= "dob") java.util.Date dob,
                                              @RequestParam(value= "gender") String gender) {

        Artist newArtist = new Artist();
        newArtist.setName(name);
        newArtist.setDob(dob);
        newArtist.setGender(gender);
        artistRepository.save(newArtist);
        return "Saved New Artist!";
    }

    /* getAllArtists returns a JSON of the artists
     * Note: used to display all of the artists
     */
    @GetMapping(path="all/artists")
    public @ResponseBody Iterable<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    /* getArtistByName returns a JSON of the artists
     * Note: used to display a single artists
     */
    @GetMapping(path="artist/{name}")
    public @ResponseBody Artist getArtistByName(@PathVariable String name) throws NotFoundException {
        Optional<Artist> artist = artistRepository.findByName(name);

        if(!artist.isPresent()){
            throw new NotFoundException("artist: " + name);
        }

        return artist.get();

    }

    /* ============================== ALBUM ==============================
     * addNewAlbum creates a new album record
     * @ResponseBody means the returned String is the response, not a view name
     * @RequestParam means it is a parameter from the GET or POST request
     */
    @PostMapping(path="add/album")
    public @ResponseBody String addNewAlbum (@RequestParam(value="album") String albumnName, @RequestParam(value= "year") String releaseYear,
                                              @RequestParam(value= "company") String recordCompany) {

        Album newAlbum = new Album();
        newAlbum.setAlbumName(albumnName);
        newAlbum.setReleaseYear(releaseYear);
        newAlbum.setRecordCompany(recordCompany);
        albumRepository.save(newAlbum);
        return "Saved New Album!";
    }

    /* getAllAlbums returns a JSON of the album
     * Note: used to display all of the Album
     */
    @GetMapping(path="all/albums")
    public @ResponseBody Iterable<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    /* getAlbumByName returns a JSON of the Album
     * Note: used to display a single Album
     */
    @GetMapping(path="album/{name}")
    public @ResponseBody Album getAlbumByName(@PathVariable String name) throws NotFoundException {
        Optional<Album> album = albumRepository.findByAlbumName(name);

        if(!album.isPresent()){
            throw new NotFoundException("album: " + name);
        }

        return album.get();
    }

}