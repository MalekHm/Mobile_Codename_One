/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.Promotion.MenuPromotion;
import com.mycompany.myapp.entities.gui.Pub.MenuPub;

/**
 *
 * @author malek
 */
public class MenuPromotionPub extends Form{
    Form current;

    public MenuPromotionPub() {
                current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
                User u = new User(1, "Malek");

        //BUTTONS
        add(new Label("Choisissez une option"));
        Button btnAide = new Button("Gestions des Publicites");
        Button btnBen = new Button("Gestions des Promotions");
        
        btnAide.addActionListener(e-> new MenuPub(u).show());
        btnBen.addActionListener(e-> new MenuPromotion(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPromotionPub().show());
        getToolbar().addCommandToSideMenu("Gestions des Publicites", null, e -> new MenuPub(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Promotions", null, e -> new MenuPromotion(u).show());

        addAll(btnAide,btnBen);

    }

}

