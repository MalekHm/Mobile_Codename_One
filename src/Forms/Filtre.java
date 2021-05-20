/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.CategoriePlace;
import Entities.Places;
import Entities.Product;
import Services.PlacesServices;
import Services.ProductService;
import Utils.WebService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class Filtre extends BaseForm{
    ComboBox<String> c;
    public Filtre(){
        c = new ComboBox();
        setName("Recherche produit");
        setTitle("Recherche produit");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListProduits lpa = new ListProduits();
            lpa.show();
        });
       
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Catégorie");
            
            TextField prix = new TextField();
            prix.setHint("Prix min");
            TextField prixMax = new TextField();
            prixMax.setHint("Prix max");
           
           
            
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("ListCategorie");
            ProductService ds = new ProductService();
           ArrayList<String> listCategorie = ds.getListCategorie(x);
           for (String e : listCategorie) {
               c.addItem(e);
           }
          
           photos.add(l);
           photos.add(c);
           
           photos.add(prix);
           photos.add(prixMax);
            
            
      
            
            
            photos.add(b);
            add(photos);
            
            b.addActionListener(e->{
               
                WebService w = new WebService();
               
                
                 Map x2  = w.search(c.getSelectedItem(),prix.getText(),prixMax.getText());
                 ArrayList<Product> listproducts = ds.getListProducts(x2);
                 ListProductsSearch.listproducts = listproducts;
                ListProductsSearch lp = new ListProductsSearch();
                lp.show();
                });
            /**c.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    String status = ws.getStatus("check/"+6+"/"+e.getId());
                    if(status.equals("subscribed")){
                        MatiereVideos.ml = e ;
                        System.out.println(e.getId());
                        MatiereVideos m = new MatiereVideos();
                        m.f.show();
                    }else{
                        
                    }

                }
            });**/
           
      
        show();
    }
}
