package movies.com.co.myapplication.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;


@Root(name = "preview", strict = false)
public class PreviewMovie implements Serializable {

    @Attribute(name = "filesize",required=false)
    private long filesize;

    @Element(name = "large")
    private String trailer;

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
