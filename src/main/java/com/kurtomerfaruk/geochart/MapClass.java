/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtomerfaruk.geochart;

/**
 *
 * @author Omer Faruk KURT
 */
public class MapClass {

    private String country;
    private String count;

    public MapClass(String country, String count) {
        this.country = country;
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
