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
public class Location {
    private int id;
    private int velo_id;
    private double prix;
    private Date date_debut;
    private Date date_fin;
    private Velo velo;
    
    public Location() {
    }


    public Location(int id, int velo_id, double prix, Date date_debut, Date date_fin) {
        this.id = id;
        this.velo_id = velo_id;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Location(int velo_id, double prix, Date date_debut, Date date_fin) {
        this.velo_id = velo_id;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVelo_id() {
        return velo_id;
    }

    public void setVelo_id(int velo_id) {
        this.velo_id = velo_id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    
    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }
    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", velo_id=" + velo_id + ", prix=" + prix + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
}
