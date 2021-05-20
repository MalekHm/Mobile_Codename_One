/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Event;
import Entities.Places;
import Forms.BaseForm;
import Services.EventService;
import Services.PlacesServices;
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
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class ListPlaces extends BaseForm{
    
    public ListPlaces(){
        setName("Liste des Places");
        setTitle("Liste des Places");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            Home ap = new Home();
            ap.show();
        });
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_FILTER, e -> {
            AddPlaces ap = new AddPlaces();
            ap.show();
        });
           
             WebService ws = new WebService();
    PlacesServices ds = new PlacesServices();
    Map x = ws.getResponse("listPlace");
        //System.out.println(x);
    ArrayList<Places> listPlaces = ds.getListPlaces(x);
             for (Places e : listPlaces) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            
            Label b = new Label("Titre : "+e.getTitrePlace());
            
           
           Button voir = new Button("Détails");
            
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getPicturePlace(), "http://127.0.0.1:8000/uploads/" + e.getPicturePlace());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(b);
            
            photos.add(voir);
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
            
            voir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   PlacesDetails.p = e ;
                   PlacesDetails pd = new PlacesDetails();
                   pd.show();
                }
            });
        }
        show();
    }
}
