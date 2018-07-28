package ng.music.loader.Artist;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.*;

@Entity // Hibernate creates an artist table
@Table(name="ng_artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ng_artist_id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "dob")
    private java.sql.Date dob;

    @Column(name = "gender")
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ng_artist_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getDob() {
        return dob;
    }

    public void setDob(java.util.Date dob) {
        this.dob = getSqlDateFromJavaDate(dob);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /* ========================= HELPER METHODS =========================
     */
    //TODO: untested
    private java.sql.Date getSqlDateFromJavaDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
