package ng.music.loader.loader;


import ng.music.loader.NGMusicController;
import ng.music.loader.ExceptionHandlers.FileLoadingException;
import ng.music.loader.ExceptionHandlers.InvalidFileHeaderException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class NGResourceLoader {

    private final String USER_HEADER = "USER";
    private final String ARTIST_HEADER = "NAME";
    private final String ALBUM_HEADER = "SINGER";
    private final String INVALID = "0";

    public void readFileLinesFromFilePath(String filePath) throws FileLoadingException {

        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource(filePath).toURI());
            StringBuilder data = new StringBuilder();
            Stream<String> lines = Files.lines(path);

            //remove header
            Optional<String> header = lines.findFirst();
            String[] headerArr = header.get().replace(" ", "").split("|");
            lines = lines.skip(1);

            //validate header
            String headerType = isValidHeader(headerArr);
            if(headerType == INVALID){
                throw new InvalidFileHeaderException("header:" + header.get());
            }

            //save each line in the database
            for(int i = 0; i < (int) lines.count(); i++){
                Optional<String> line = lines.findFirst();
                String[] lineArr = line.get().replace(" ", "").split("|");
                saveResourceLine(lineArr,headerType);
                lines = lines.skip(1);
            }

            lines.close();

        } catch (URISyntaxException e){
            throw new FileLoadingException("filepath: " + filePath + "/n" + e.toString());
        } catch (IOException e){
            throw new FileLoadingException("filepath: " + filePath + "/n" + e.toString());
        }
    }



    private void saveResourceLine(String[] line, String header){
        //determine the type of file
        NGMusicController musicController = new NGMusicController();
        switch (header){
            case USER_HEADER:
                //USER|PASS
                musicController.addNewUser(line[0],line[1]);
                break;


            case ARTIST_HEADER:
                try {
                    //format date of birth
                    DateFormat formatter = new SimpleDateFormat("yyymmdd", Locale.ENGLISH);
                    Date dob = formatter.parse(line[1]);
                    //NAME|DOB(YYYYMMDD)|SEX
                    musicController.addNewArtist(line[0],dob,line[2]);
                } catch (ParseException e) {
                    throw new FileLoadingException("dob: " + line[1] + "/n" + e.toString());
                }
                break;


            case ALBUM_HEADER:
                //SINGER|ALBUM|YEAR(YYYY)|COMPANY
                musicController.addNewAlbum(line[0],line[1],line[2]);
                break;


            default:
                throw new InvalidFileHeaderException("header: " + header.toString());
        }

    }



    private String isValidHeader(String[] header){
        //create a set for each header type
        Set<String> userHeader = new LinkedHashSet<>();
        Set<String> artistHeader = new LinkedHashSet<>();
        Set<String> albumHeader = new LinkedHashSet<>();

        //USER|PASS
        userHeader.add(USER_HEADER);
        userHeader.add("PASS");

        //NAME|DOB(YYYYMMDD)|SEX
        artistHeader.add(ARTIST_HEADER);
        artistHeader.add("DOB(YYYYMMDD)");
        artistHeader.add("SEX");

        //SINGER|ALBUM|YEAR(YYYY)|COMPANY
        albumHeader.add(ALBUM_HEADER);
        albumHeader.add("ALBUM");
        albumHeader.add("YEAR(YYYY)");
        albumHeader.add("COMPANY");

        /* Deterimine if header contains all options for a header type
         * (1) check length
         * (2) check content
         */
        if(header.length == 2 && userHeader.containsAll(Arrays.asList(header))) {
            return USER_HEADER;
        }
        if(header.length == 3 && artistHeader.containsAll(Arrays.asList(header))) {
            return ARTIST_HEADER;
        }
        if(header.length == 4 && artistHeader.containsAll(Arrays.asList(header))) {
            return ALBUM_HEADER;
        }

        return INVALID;
    }
}
