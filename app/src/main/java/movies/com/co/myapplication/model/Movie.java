package movies.com.co.myapplication.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "movieinfo" ,strict = false)
public class Movie {

    @Attribute(name = "id")
    private long id;

    @Element(name = "info")
    private InformationMovie movieinfo;

    @Element(name = "cast")
    private CastingMovie cast;

    @Element(name = "genre")
    private GenreMovie genreMovie;

    @Element(name = "poster")
    private PosterMovie posterMovie;

    @Element(name = "preview")
    private PreviewMovie previewMovie;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InformationMovie getMovieinfo() {
        return movieinfo;
    }

    public void setMovieinfo(InformationMovie movieinfo) {
        this.movieinfo = movieinfo;
    }

    public CastingMovie getCast() {
        return cast;
    }

    public void setCast(CastingMovie cast) {
        this.cast = cast;
    }

    public GenreMovie getGenreMovie() {
        return genreMovie;
    }

    public void setGenreMovie(GenreMovie genreMovie) {
        this.genreMovie = genreMovie;
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
