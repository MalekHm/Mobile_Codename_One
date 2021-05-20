/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Event;
import Utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;

/**
 *
 * @author Jasser
 */
public class EventDetails extends BaseForm{
    public static Event e ;
    public EventDetails(){
        WebService ws = new WebService();
        ws.addView(e.getId(),"http://127.0.0.1:8000/AddView/"+e.getId());
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListEvents lp = new ListEvents();
            lp.show();
        });
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, ex -> {
               ReservationForm.IdEvent = e.getId();
           ReservationForm ap = new ReservationForm();
            ap.show();
        });
        
           
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            Label ref = new Label("Titre : "+e.getTitre());
            Label desc = new Label("Description : "+e.getDescription());
            Label classe = new Label("Placs disponibles :  : "+e.getPlace_disponible());
            Label q = new Label("Prix : "+e.getPrice_event());
            Label souscat = new Label("Nombre des vus : "+e.getNb_views());
            Label b = new Label("Date Debut - Date fin : "+e.getStart_date() +"-"+e.getEnd_date());
            
            
           
          
            
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/event/" + e.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(ref);
            photos.add(b);
            photos.add(classe);
            photos.add(desc);
            photos.add(q);
            photos.add(souscat);
            
           
            
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            b.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                }
            });
            
            
        
        show();
    }
}
