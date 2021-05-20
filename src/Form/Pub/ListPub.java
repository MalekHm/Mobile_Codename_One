/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Pub;

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
import Entities.Publicity;
import Entities.User;
import Form.Promotion.MenuPromotion;
import Forms.Home;
import Services.ServicePublicity;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author malek
 */
public class ListPub extends Form{
        Form current;

        public ListPub() {
        setTitle("Liste des Pubs");
        
          Container co;
                       //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <Publicity> pubs = new ArrayList();
                        ServicePublicity sa =new ServicePublicity();
                    pubs=sa.getAllPubs();
                             for (Publicity fi : pubs) {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("ID :"+String.valueOf(fi.getId()+" Type : "+fi.getTitle_pub()));
                            m.setTextLine2("Description :"+fi.getDescription_pub());
                            m.setTextLine3("de: "+fi.getDatedeb()+" jusequ'a : "+fi.getDatefin());
                            String url = "file://C:/Users/ousse/Desktop/New folder/Prog-In-master/public/uploads/images/"+ fi.getPicture_pub();
                                 System.out.println(url);
                            int deviceWidth = Display.getInstance().getDisplayWidth();
                            Image placeholder = Image.createImage( deviceWidth/3,  deviceWidth/3, 0xbfc9d2);
                            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + fi.getId(),url, URLImage.RESIZE_SCALE);
                            System.out.println(i);
                            m.setIcon(i);
                            m.setTextLine4("Link : "+fi.getLink());
                            m.addPointerReleasedListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette Pub ?", "Supprimer", "Annuler")) {
                Publicity t = new Publicity();
                t.setId(fi.getId());
                        if( ServicePublicity.getInstance().deletePub(t)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new ListPub().show();
                            }
                   
                }
            }    
            }
        });
            m.addLongPressListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette Pub ?", "Modifier ", "Annuler")) {
                    new EditPub(current, fi).show();
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
