/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.CategoriePlace;
import Entities.Places;
import Entities.User;
import Form.Promotion.MenuPromotion;
import Form.Pub.MenuPub;
import Services.PlacesServices;
import Utils.WebService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class Home extends BaseForm{
    public Home(){
        
        setName("Home");
        setTitle("Home");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           
             getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_NOTIFICATIONS_ACTIVE, e -> {
            Notifications lp = new Notifications();
            lp.show();
        });
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           
            Button Products = new Button("Espace Products");
          
            Button Events = new Button("Espace Events");
            
           Button Blog = new Button("Espace Blog");
           Button Commmunity = new Button("Espace Community");
           Button Recommandations = new Button("Espace Recommandations");
           Button Publicity = new Button("Espace Publicity");
           Button Promotion = new Button("Espace Promotion");
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            
            photos.add(Products);
            photos.add(Events);
            photos.add(Blog);
            photos.add(Commmunity);
            photos.add(Recommandations);
            photos.add(Publicity);
            photos.add(Promotion);
            add(photos);
            
            Products.addActionListener(e->{
                ListProduits lp = new ListProduits();
                lp.show();
            });
            Events.addActionListener(e->{
                ListEvents lp = new ListEvents();
                lp.show();
            });
            Blog.addActionListener(e->{
                ListBlog lp = new ListBlog();
                lp.show();
            });
            Commmunity.addActionListener(e->{
                ListCommunity lp = new ListCommunity();
                lp.show();
            });
            Recommandations.addActionListener(e->{
                ListPlaces lp = new ListPlaces();
                lp.show();
            });
            Publicity.addActionListener(e->{
                MenuPub lp = new MenuPub();
                lp.show();
            });
            Promotion.addActionListener(e->{
                MenuPromotion lp = new MenuPromotion();
                lp.show();
            });
            
                
           
      
        show();
    }
    
}
