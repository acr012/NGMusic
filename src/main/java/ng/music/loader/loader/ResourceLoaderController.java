package ng.music.loader.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResourceLoaderController {

    NGResourceLoader resourceLoader = new NGResourceLoader();

    @Autowired
    public ResourceLoaderController(){}

    @PostMapping(path = "load/file/{filepath}")
    public void loadFileByFilePath(@PathVariable String filepath){
        resourceLoader.readFileLinesFromFilePath(filepath);
    }
}
