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
public class Event {
    private int id;
    private Categorie categorie;
    private String titre;
    private String description;
    private String start_date;
    private String end_date;
    private int nb_persons;
    private int place_disponible ;
    private int price_event ;
    private int nb_views ;
    private String image ;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getNb_persons() {
        return nb_persons;
    }

    public void setNb_persons(int nb_persons) {
        this.nb_persons = nb_persons;
    }

    public int getPlace_disponible() {
        return place_disponible;
    }

    public void setPlace_disponible(int place_disponible) {
        this.place_disponible = place_disponible;
    }

    public int getPrice_event() {
        return price_event;
    }

    public void setPrice_event(int price_event) {
        this.price_event = price_event;
    }

    public int getNb_views() {
        return nb_views;
    }

    public void setNb_views(int nb_views) {
        this.nb_views = nb_views;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", categorie=" + categorie + ", titre=" + titre + ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date + ", nb_persons=" + nb_persons + ", place_disponible=" + place_disponible + ", price_event=" + price_event + ", nb_views=" + nb_views + '}';
    }
    
    
}
