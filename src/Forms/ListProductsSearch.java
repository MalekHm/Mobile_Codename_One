/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Product;
import static Forms.ListProduits.p;
import Services.ProductService;
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
public class ListProductsSearch extends BaseForm{
    public static  ArrayList<Product> listproducts ;
    
    public ListProductsSearch(){
        setName("Liste des produits");
        setTitle("Liste des produits");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
          
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListProduits pa = new ListProduits();
           pa.show();
        });
         
           
            
    
             for (Product e : listproducts) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            
            Label b = new Label("Nom : "+e.getName());
            
           
           Button voir = new Button("Détails");
           Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Button b1 =  new Button("Ajouter au panier");
            
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/product/" + e.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(b);
            
            photos.add(voir);
            c1.add(b1);
            photos.add(c1);
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
                    System.out.println(e);
                   ProductDetails.p = e ;
                   ProductDetails pd = new ProductDetails();
                   pd.show();
                }
            });
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     p.add(e);
                     b1.setEnabled(false);
                     
                  c1.setVisible(false);
                    System.out.println("hello");
                }
            });
        }
        show();
    }
    
}
