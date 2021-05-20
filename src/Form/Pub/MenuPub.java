/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Pub;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import Entities.User;
import Form.Promotion.MenuPromotion;
import Forms.Home;

/**
 *
 * @author malek
 */
public class MenuPub extends Form{
    Form current;

    public MenuPub() {
                current=this;
        setTitle("Gestions des Publicites");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAjoutAide = new Button("Ajouter Pub");
        Button btnListVelos = new Button("Liste des Publicites");
        Button btnStat= new Button("Stat");
        
        btnAjoutAide.addActionListener(e-> new AddPub(current).show());
        btnListVelos.addActionListener(e-> new ListPub().show());
        btnStat.addActionListener(e-> new StatPub().show());
        
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new Home().show());
        getToolbar().addCommandToSideMenu("Gestions des Publicites", null, e -> new MenuPub().show());
        getToolbar().addCommandToSideMenu("Gestions des Promotions", null, e -> new MenuPromotion().show());



        addAll(btnAjoutAide,btnListVelos,btnStat);

    }

}

