/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Promotion;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import Entities.User;
import Form.Pub.AddPub;
import Form.Pub.ListPub;
import Form.Pub.MenuPub;
import Form.Pub.StatPub;
import Forms.Home;


/**
 *
 * @author MALEK
 */
public class MenuPromotion extends Form{
    Form current;

    public MenuPromotion() {
                current=this;
        setTitle("Gestions des Promotions");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAddPromo = new Button("Ajouter Promotion");
        Button btnListPromo = new Button("Liste des Promotions");

        
        btnAddPromo.addActionListener(e-> new AddPromo(current).show());
        btnListPromo.addActionListener(e-> new ListPromo().show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new Home().show());
        getToolbar().addCommandToSideMenu("Gestions des Publicites", null, e -> new MenuPub().show());
        getToolbar().addCommandToSideMenu("Gestions des Promotions", null, e -> new MenuPromotion().show());

        addAll(btnAddPromo,btnListPromo);

    }

}

