/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Product;
import static Forms.EventDetails.e;
import static Forms.ListProduits.p;
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

/**
 *
 * @author Jasser
 */
public class ProductDetails extends BaseForm{
    
    public static Product p;
    public ProductDetails(){
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListProduits lp = new ListProduits();
            lp.show();
        });
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD, ex -> {
               ReservationForm.IdEvent = e.getId();
           ReservationForm ap = new ReservationForm();
            ap.show();
        });
        
           
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            Label ref = new Label("Titre : "+p.getName());
            Label desc = new Label("Description : "+p.getDescription());
            Label classe = new Label("Prix de location : "+p.getPricer());
            Label q = new Label("Prix de vente: "+p.getPrice());
            Label souscat = new Label("Stock : "+p.getQuantity());
           
            
            
           
          
           //Button voir1 = new Button("Delete");
            
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            System.out.println(p);
            img = URLImage.createToStorage(encoded, p.getImage(), "http://127.0.0.1:8000/product/" + p.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(ref);
           
            photos.add(classe);
            photos.add(desc);
            photos.add(q);
            photos.add(souscat);
            
           
            
              //photos.add(voir1);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            add(photos);
            
           
            
           
        
        show();
    }
}
