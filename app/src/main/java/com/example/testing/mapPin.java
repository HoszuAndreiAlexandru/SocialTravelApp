package com.example.testing;

public class mapPin
{
    private String pinName;
    private float lat;
    private float lon;
    private int reviewNote;
    private String reviewText;

    public mapPin()
    {
        pinName = "";
        lat = 0;
        lon = 0;
        reviewNote = 0;
        reviewText = "";
    }

    public mapPin(String name, float lat, float lon, int note, String text)
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

    public float getLat()
    {
        return lat;
    }

    public void setLat(float lat)
    {
        this.lat = lat;
    }

    public float getLon()
    {
        return lon;
    }

    public void setLon(float lon)
    {
        this.lon = lon;
    }

    public int getReviewNote()
    {
        return reviewNote;
    }

    public void setReviewNote(int reviewNote)
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
