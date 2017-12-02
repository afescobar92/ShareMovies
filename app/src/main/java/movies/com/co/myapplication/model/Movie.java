package movies.com.co.myapplication.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(strict = false)
public class Movie implements Serializable {

    @Attribute(name = "id" ,required = false)
    private String id;

    @Element(name = "info" , required = false)
    private InformationMovie info;

    @Element(name = "cast" , required = false)
    private CastingMovie cast;

    @Element(name = "genre", required = false)
    private GenreMovie genre;

    @Element(name = "poster", required = false)
    private PosterMovie posterMovie;

    @Element(name = "preview", required = false)
    private PreviewMovie previewMovie;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InformationMovie getMovieInfo() {
        return info;
    }

    public void setMovieInfo(InformationMovie movieInfo) {
        this.info = movieInfo;
    }

    public CastingMovie getCast() {
        return cast;
    }

    public void setCast(CastingMovie cast) {
        this.cast = cast;
    }

    public GenreMovie getGenreMovie() {
        return genre;
    }

    public void setGenreMovie(GenreMovie genreMovie) {
        this.genre = genreMovie;
    }

    public PosterMovie getPosterMovie() {
        return posterMovie;
    }

    public void setPosterMovie(PosterMovie posterMovie) {
        this.posterMovie = posterMovie;
    }

    public PreviewMovie getPreviewMovie() {
        return previewMovie;
    }

    public void setPreviewMovie(PreviewMovie previewMovie) {
        this.previewMovie = previewMovie;
    }
}
