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
public class CommentaireBlog {
    private int id;
    private String contenu;
    private String Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public CommentaireBlog() {
    }

    @Override
    public String toString() {
        return "CommentaireBlog{" + "id=" + id + ", contenu=" + contenu + ", Date=" + Date + '}';
    }
    
    
    
}
