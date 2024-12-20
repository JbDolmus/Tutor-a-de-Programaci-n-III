package model.entities;

import javafx.scene.image.Image;

/**
 *
 * @author JorgeLPR
 */
public class Country {
    
    private int id;
    private String country;
    private String city;
    private Image flag;
    private byte [] flagBytes;

    public Country(int id, String country, String city, Image flag, byte[] flagBytes) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.flag = flag;
        this.flagBytes = flagBytes;
    }
    
    public Country(int id, String country, String city, Image flag) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.flag=flag;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Image getFlag() {
        return flag;
    }

    public void setFlag(Image flag) {
        this.flag = flag;
    }

    public byte[] getFlagBytes() {
        return flagBytes;
    }

    public void setFlagBytes(byte[] flagBytes) {
        this.flagBytes = flagBytes;
    }
                
    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", country=" + country + ", city=" + city + '}';
    }
    
    
    
}