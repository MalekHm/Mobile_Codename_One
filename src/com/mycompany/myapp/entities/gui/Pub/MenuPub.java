/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Pub;

import com.mycompany.myapp.entities.gui.Promotion.MenuPromotion;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.MenuPromotionPub;

/**
 *
 * @author malek
 */
public class MenuPub extends Form{
    Form current;

    public MenuPub(User u) {
                current=this;
        setTitle("Gestions des Publicites");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAjoutAide = new Button("Ajouter Pub");
        Button btnListVelos = new Button("Liste des Publicites");
        Button btnStat= new Button("Stat");
        
        btnAjoutAide.addActionListener(e-> new AddPub(current,u).show());
        btnListVelos.addActionListener(e-> new ListPub(u).show());
        btnStat.addActionListener(e-> new StatPub(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPromotionPub().show());
        getToolbar().addCommandToSideMenu("Gestions des Publicites", null, e -> new MenuPub(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Promotions", null, e -> new MenuPromotion(u).show());

        addAll(btnAjoutAide,btnListVelos,btnStat);

    }

}

