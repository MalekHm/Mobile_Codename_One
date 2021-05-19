/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author SBS
 */
public class Velo {
    private int id;
    private String type;
    private String description;
    private String etat;
    private String image;
    private double prix;
    private Date dateMaintenance;
    private int cycleMaintenance;

    public Velo() {
    }

    public Velo(int id) {
        this.id = id;
    }

    public Velo(int id, String type, String description, String etat, String image, double prix, Date dateMaintenance, int cycleMaintenance) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.etat = etat;
        this.image = image;
        this.prix = prix;
        this.dateMaintenance = dateMaintenance;
        this.cycleMaintenance = cycleMaintenance;
    }

    public Velo(String type, String description, String etat, String image, double prix, Date dateMaintenance, int cycleMaintenance) {
        this.type = type;
        this.description = description;
        this.etat = etat;
        this.image = image;
        this.prix = prix;
        this.dateMaintenance = dateMaintenance;
        this.cycleMaintenance = cycleMaintenance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateMaintenance() {
        return dateMaintenance;
    }

    public void setDateMaintenance(Date dateMaintenance) {
        this.dateMaintenance = dateMaintenance;
    }

    public int getCycleMaintenance() {
        return cycleMaintenance;
    }

    public void setCycleMaintenance(int cycleMaintenance) {
        this.cycleMaintenance = cycleMaintenance;
    }

    @Override
    public String toString() {
        return "Velo{" + "id=" + id + ", type=" + type + ", description=" + description + ", etat=" + etat + ", image=" + image + ", prix=" + prix + ", dateMaintenance=" + dateMaintenance + ", cycleMaintenance=" + cycleMaintenance + '}';
    }
    
    
    
}
