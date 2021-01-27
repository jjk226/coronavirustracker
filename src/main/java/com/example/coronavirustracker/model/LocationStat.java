package com.example.coronavirustracker.model;

public class LocationStat {

    private String state;
    private String country;
    private int latest;
    private int previous;
    private int change;

    public LocationStat() {

    }

    public LocationStat(String state, String country, int latest) {
        this.state = state;
        this.country = country;
        this.latest = latest;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatest() {
        return latest;
    }

    public void setLatest(int latest) {
        this.latest = latest;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getChange() {
        return this.change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "LocationStat{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latest=" + latest +
                '}';
    }
}
