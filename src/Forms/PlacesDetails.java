/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Places;
import static Forms.EventDetails.e;
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
public class PlacesDetails extends BaseForm{
    public static Places p;
    public PlacesDetails(){
        WebService ws = new WebService();
        ws.addView(p.getId(),"http://127.0.0.1:8000/AddViewPlace/"+p.getId());
        p.setNbview(p.getNbview()+1);
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListPlaces lp = new ListPlaces();
        });
           
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_COMMENT, ex -> {
               ListCommentaires.id = p.getId();
           ListCommentaires ap = new ListCommentaires();
            ap.show();
        });
       
           
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            Label ref = new Label("Titre : "+p.getTitrePlace());
            Label desc = new Label("Description : "+p.getDescriptionPlace());
            Label classe = new Label("Location : "+p.getLocation());
            Label q = new Label("Nombre des vus : "+p.getNbview());
            Label souscat = new Label("Nombre des commentaires : "+p.getNbComments());
            //Label b = new Label("Date Debut - Date fin : "+e.getStart_date() +"-"+e.getEnd_date());
            
            
           
           Button voir = new Button("Edit");
           
           Button voir1 = new Button("Delete");
            
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, p.getPicturePlace(), "http://127.0.0.1:8000/uploads/" + p.getPicturePlace());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(ref);
            //photos.add(b);
            photos.add(classe);
            photos.add(desc);
            photos.add(q);
            photos.add(souscat);
            
           
             photos.add(voir);
              photos.add(voir1);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
            
            
            voir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   EditPlace.place = p ; 
                   EditPlace ep = new EditPlace();
                   ep.show();

                }
            });
            voir1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  WebService ws = new WebService();
                  ws.DeletePlace(p.getId());
                  ListPlaces lp = new ListPlaces();
                  lp.show();

                }
            });
        
        show();
    }
}
