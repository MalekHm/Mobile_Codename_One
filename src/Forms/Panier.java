/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Product;
import Utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Jasser
 */
public class Panier extends BaseForm{
    public ArrayList<Product> p ;
    double qte = 1;
    ComboBox<String> co;
    public Panier(){
        co = new ComboBox();
        co.addItem("Location");
        co.addItem("Achat");
        p = ListProduits.p ;
           setName("Panier");
           setTitle("Panier");
           this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
           Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
           FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
           FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           ListProduits lp = new ListProduits();
           lp.show();
           });
          
           
            
           WebService ws = new WebService();
           //ServiceProduit ds = new ServiceProduit();
           for (Product e : p) {
           
           
           Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           ImageViewer imv = null;
           Image img;
           EncodedImage encoded = null;
           Label a = new Label("Nom : "+e.getName());
           Label c = new Label("Prix Vente : "+e.getPrice());
           Label d = new Label("Prix Location : "+e.getPricer());
           e.setQuantity(qte);
           Label qt = new Label("Quantité : "+qte);
           Container ctn = new Container(new BoxLayout(BoxLayout.X_AXIS));
           Button b = new Button("+");
           Button b1 = new Button("-");
           ctn.add(b);
           ctn.add(b1);
           try {
               encoded = EncodedImage.create("/like.png");
           } catch (IOException ex) {
           }
           img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/Upload/" + e.getImage());
           imv = new ImageViewer(img);
           photos.add(imv);
           photos.add(a);
           photos.add(c);
           photos.add(d);
           photos.add(qt);
           photos.add(ctn);
           try {
               ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                
                
               photos.add(sep);
           } catch (IOException ex) {
           }
           add(photos);
            
           b.addPointerPressedListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent evt) {
                   
                   double f = e.getQuantity() + 1;
                   qt.setText("Quantité : "+f);
                   if(co.getSelectedItem().equals("Achat")){
                       double PrixTotal = e.getPrice() * f ;
                       c.setText("Prix Vente : "+PrixTotal);
                   }else{
                       double PrixTotal = e.getPricer()* f ;
                       d.setText("Prix Location : "+PrixTotal);
                   }
                   e.setQuantity(f);
                   p.remove(e);
                   p.add(e);
                }
            });
            b1.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    double f = e.getQuantity() -1;
                    qt.setText("Quantité : "+f);
                    if(co.getSelectedItem().equals("Achat")){
                       double PrixTotal = e.getPrice() * f ;
                       c.setText("Prix Vente : "+PrixTotal);
                   }else{
                       double PrixTotal = e.getPricer()* f ;
                       d.setText("Prix Location : "+PrixTotal);
                   }
                    e.setQuantity(f);
                    p.remove(e);
                    p.add(e);
                }
            });
            
        }
             Button bou = new Button("Valider la commande");
             add(co);
             add(bou);
             bou.addActionListener(e->{
                  for (Product ev : p) {
                     ev.setType(co.getSelectedItem());
                      System.out.println(co.getSelectedItem());
                     ws.AddCommande(ev);
                     
                 }
                  Image screenshot = Image.createImage(getWidth(), getHeight());
        revalidate();
        setVisible(true);
        paintComponent(screenshot.getGraphics(), true);
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch(IOException err) {
            Log.e(err);
        }
       
                 
             });
        show();
    }
}
