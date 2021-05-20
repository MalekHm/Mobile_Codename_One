/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Forms.BaseForm;

/**
 *
 * @author Jasser
 */
public class Blog {
    
    private int id ;
    private String title;
    private String description;
    private String image ;
    private String DateAjout;
    private String Community;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateAjout() {
        return DateAjout;
    }

    public void setDateAjout(String DateAjout) {
        this.DateAjout = DateAjout;
    }

    public String getCommunity() {
        return Community;
    }

    public void setCommunity(String Community) {
        this.Community = Community;
    }

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", description=" + description + ", image=" + image + ", DateAjout=" + DateAjout + ", Community=" + Community + '}';
    }
    
}
