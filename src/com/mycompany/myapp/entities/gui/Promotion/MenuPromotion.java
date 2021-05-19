/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Promotion;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.MenuPromotionPub;
import com.mycompany.myapp.entities.gui.Pub.AddPub;
import com.mycompany.myapp.entities.gui.Pub.ListPub;
import com.mycompany.myapp.entities.gui.Pub.MenuPub;
import com.mycompany.myapp.entities.gui.Pub.StatPub;


/**
 *
 * @author MALEK
 */
public class MenuPromotion extends Form{
    Form current;

    public MenuPromotion(User u) {
                current=this;
        setTitle("Gestions des Promotions");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAddPromo = new Button("Ajouter Promotion");
        Button btnListPromo = new Button("Liste des Promotions");

        
        btnAddPromo.addActionListener(e-> new AddPromo(current,u).show());
        btnListPromo.addActionListener(e-> new ListPromo(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPromotionPub().show());
        getToolbar().addCommandToSideMenu("Gestions des Publicites", null, e -> new MenuPub(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Promotions", null, e -> new MenuPromotion(u).show());

        addAll(btnAddPromo,btnListPromo);

    }

}

