/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Jasser
 */
public class Places {
    private int id;
    private String titrePlace;
    private String descriptionPlace ;
    private String location ;
    private int Nbview ;
    private String picturePlace;
    private String show_place;
    private int nbComments;
    private String category;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitrePlace() {
        return titrePlace;
    }

    public void setTitrePlace(String titrePlace) {
        this.titrePlace = titrePlace;
    }

    public String getDescriptionPlace() {
        return descriptionPlace;
    }

    public void setDescriptionPlace(String descriptionPlace) {
        this.descriptionPlace = descriptionPlace;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNbview() {
        return Nbview;
    }

    public void setNbview(int Nbview) {
        this.Nbview = Nbview;
    }

    public String getPicturePlace() {
        return picturePlace;
    }

    public void setPicturePlace(String picturePlace) {
        this.picturePlace = picturePlace;
    }

    public String getShow_place() {
        return show_place;
    }

    public void setShow_place(String show_place) {
        this.show_place = show_place;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Places() {
    }

    @Override
    public String toString() {
        return "Places{" + "id=" + id + ", titrePlace=" + titrePlace + ", descriptionPlace=" + descriptionPlace + ", location=" + location + ", Nbview=" + Nbview + ", picturePlace=" + picturePlace + ", show_place=" + show_place + ", nbComments=" + nbComments + ", category=" + category + ", image=" + image + '}';
    }
    
}
