package com.example.testing;

public class mapPin
{
    private String pinName;
    private double lat;
    private double lon;
    private long reviewNote;
    private String reviewText;

    public mapPin()
    {
        pinName = "";
        lat = 0;
        lon = 0;
        reviewNote = 0;
        reviewText = "";
    }

    public mapPin(String name, double lat, double lon, long note, String text)
    {
        this.pinName = name;
        this.lat = lat;
        this.lon = lon;
        this.reviewNote = note;
        this.reviewText = text;
    }

    public String getPinName()
    {
        return pinName;
    }

    public void setPinName(String pinName)
    {
        this.pinName = pinName;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public long getReviewNote()
    {
        return reviewNote;
    }

    public void setReviewNote(long reviewNote)
    {
        this.reviewNote = reviewNote;
    }

    public String getReviewText()
    {
        return reviewText;
    }

    public void setReviewText(String reviewText)
    {
        this.reviewText = reviewText;
    }
}
