package movies.com.co.myapplication.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(strict = false)
public class CastingMovie implements Serializable {

    @ElementList(entry = "name", inline = true, required = false)
    private List<String> name;

    public List<String> getCastList() {
        return name;
    }

    public void setCastList(List<String> castList) {
        this.name = castList;
    }
}
