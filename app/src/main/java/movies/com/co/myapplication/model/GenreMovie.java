package movies.com.co.myapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;


@Root(strict = false)
public class GenreMovie implements Serializable {

    @ElementList(entry = "name", inline = true, required = false)
    private List<String> name;

    public List<String> getGenreList() {
        return name;
    }

    public void setGenreList(List<String> genreList) {
        this.name = genreList;
    }
}
