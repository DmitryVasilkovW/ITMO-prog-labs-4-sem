package Task1_3;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
    private int _id;
    private String _title;
    private String _artist;
    private Date _releaseDate;
    private BigDecimal _prise;
    private double _version;

    public Item(int id, String title, String artist, Date releaseDate, BigDecimal prise, double version) {
        _id = id;
        _title = title;
        _artist = artist;
        _releaseDate = releaseDate;
        _prise = prise;
        _version = version;
    }

    public Item(int id, String title, String artist, Date releaseDate, double version) {
        _id = id;
        _title = title;
        _artist = artist;
        _releaseDate = releaseDate;
        _version = version;
    }

    public Item() { }

    public int getId()
    {
        return _id;
    }

    public void setId(int id)
    {
        _id = id;
    }

    public String getTitle()
    {
        return _title;
    }

    public void setTitle(String title)
    {
        _title = title;
    }

    public String getArtist()
    {
        return _artist;
    }

    public void setArtist(String artist)
    {
        _artist = artist;
    }

    public Date getReleaseDate()
    {
        return _releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        _releaseDate = releaseDate;
    }

    public BigDecimal getPrice()
    {
        return _prise;
    }

    public void setPrice(BigDecimal price)
    {
        _prise = price;
    }

    public double getVersion()
    {
        return _version;
    }

    public void setVersion(double version)
    {
        _version = version;
    }
}
