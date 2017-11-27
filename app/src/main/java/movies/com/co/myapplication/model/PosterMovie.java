package movies.com.co.myapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "poster", strict = false)
public class PosterMovie {

    @Element(name = "location")
    private String posterSmall;

    @Element(name = "xlarge")
    private String posterLarge;

    public String getPosterSmall() {
        return posterSmall;
    }

    public void setPosterSmall(String posterSmall) {
        this.posterSmall = posterSmall;
    }

    public String getPosterLarge() {
        return posterLarge;
    }

    public void setPosterLarge(String posterLarge) {
        this.posterLarge = posterLarge;
    }
}
