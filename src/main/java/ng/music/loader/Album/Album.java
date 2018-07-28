package ng.music.loader.Album;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity // Hibernate creates an artist table
@Table(name="ng_albums", uniqueConstraints={
        @UniqueConstraint(columnNames = {"album_name", "release_year", "record_company" })
})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ng_albums_id")
    private Integer albumId;

    @Column(name = "ng_artist_id")
    private Integer artistId;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "release_year")
    private String releaseYear;

    @Column(name = "record_company")
    private String recordCompany;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer id) {
        this.albumId = id;
    }

    @OneToOne(mappedBy = "ng_albums")
    public Integer getArtistId() {
        return artistId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String name) {
        this.albumName = name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String year) {
        this.releaseYear = year;
    }

    public String getRecordCompany() {
        return recordCompany;
    }

    public void setRecordCompany(String recordCompany) {
        this.recordCompany = recordCompany;
    }
}
