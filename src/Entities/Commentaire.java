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
public class Commentaire {
    private int id;
    private String content;
    private String email;
    private String nickname;
    private String CreatedAt;
    private String titre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Commentaire() {
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", content=" + content + ", email=" + email + ", nickname=" + nickname + ", CreatedAt=" + CreatedAt + ", titre=" + titre + '}';
    }
    
    
    
    
}
