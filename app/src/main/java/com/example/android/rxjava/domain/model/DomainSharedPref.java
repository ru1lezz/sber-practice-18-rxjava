package com.example.android.rxjava.domain.model;

import java.util.Objects;

public class DomainSharedPref {
    private String city;
    private int days;

    public DomainSharedPref() {

    }

    public DomainSharedPref(String city, int days) {
        this.city = city;
        this.days = days;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainSharedPref that = (DomainSharedPref) o;
        return days == that.days &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, days);
    }

    @Override
    public String toString() {
        return "DomainSharedPref{" +
                "city='" + city + '\'' +
                ", days=" + days +
                '}';
    }
}
