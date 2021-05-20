/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Promotion;

import Form.Pub.MenuPub;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Entities.Promotion;
import Entities.User;
import Form.Pub.EditPub;
import Form.Pub.ListPub;
import Forms.Home;
import Services.ServicePromotion;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author wajih
 */
public class ListPromo extends Form{
        Form current;

        public ListPromo() {
        setTitle("Liste des Promotion");
        
          Container co;
                       //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <Promotion> promos = new ArrayList();
                        ServicePromotion sa =new ServicePromotion();
                            promos=sa.getAllPromos();
                             for (Promotion fi : promos) {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("ID : "+String.valueOf(fi.getId()));
                            m.setTextLine2("Pourcentage :"+String.valueOf(fi.getPourcentage()));
                            String date= fi.getDatedeb();
                            m.setTextLine3("Le: "+fi.getDatedeb());
                            m.setTextLine4("Jusqu'a : "+fi.getDatefin());
                            m.addPointerReleasedListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Supprimer ce Promo ?", "Supprimer", "Annuler")) {
                        if( ServicePromotion.getInstance().deletePromo(fi)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new ListPromo().show();
                            }
                   
                }
            }    
            }
        });
            m.addLongPressListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Modifier ce Promo ?", "Modifier ", "Annuler")) {
                    new EditPromo(current, fi).show();
                }    
            }
        });

                            add(m);
                             }
                     revalidate();
                    });
                });
    getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new Home().show());
        getToolbar().addCommandToSideMenu("Gestions des Publicites", null, e -> new MenuPub().show());
        getToolbar().addCommandToSideMenu("Gestions des Promotions", null, e -> new MenuPromotion().show());

    }

    
}
