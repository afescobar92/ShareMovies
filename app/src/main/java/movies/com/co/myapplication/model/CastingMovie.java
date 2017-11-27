package movies.com.co.myapplication.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "cast",strict = false)
public class CastingMovie {

    @ElementList(inline = true, name="name")
    private List<String> castList;

    public List<String> getCastList() {
        return castList;
    }

    public void setCastList(List<String> castList) {
        this.castList = castList;
    }
}
