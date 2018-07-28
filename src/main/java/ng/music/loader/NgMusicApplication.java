package ng.music.loader;

import ng.music.loader.loader.NGResourceLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NgMusicApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NgMusicApplication.class, args);
    }

    /* Load any files given in the commandline to MySql
     * Accesses command line arguments, and executes just before the application starts.
     * Overrides the CommandLineRunner run method.
     * @params  args    String[]    contains filepaths to be uploaded to MySql
     */
    @Override
    public void run(String...args) {

        if(args.length > 0){
            for(String arg : args){
                NGResourceLoader resourceLoader = new NGResourceLoader();
                resourceLoader.readFileLinesFromFilePath(arg);
            }
        }

    }

}
