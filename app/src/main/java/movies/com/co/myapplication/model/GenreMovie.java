package movies.com.co.myapplication.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


@Root(name = "genre",strict = false)
public class GenreMovie {

    @ElementList(inline = true, name="name")
    private List<String> genreList;

}
