/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.CategoriePlace;
import Entities.Places;
import Services.PlacesServices;
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
public class EditPlace extends BaseForm{
    private String im ;
    ComboBox<String> c;
    public static Places place;
    public EditPlace(){
        im = place.getPicturePlace();
        c = new ComboBox();
        setName("Modifier recommendation");
        setTitle("Ajouter recommendation");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListPlaces lpa = new ListPlaces();
            lpa.show();
        });
        getToolbar().addCommandToRightSideMenu("Lite des produits Admin", icon, (e) -> {
           //ListProductsAdmin lpa = new ListProductsAdmin();
           //lpa.show();
           
        });
        
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Catégorie");
            TextField nom = new TextField();
            nom.setText(place.getTitrePlace());
            TextField marque = new TextField();
            marque.setText(place.getDescriptionPlace());
            TextField prix = new TextField();
            prix.setText(place.getLocation());
           
           
            Button img = new Button("Ajouter une image",icone);
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            Map x = ws.getResponse("getcategoriePlace");
            PlacesServices ds = new PlacesServices();
           ArrayList<CategoriePlace> listCategorie = ds.getListCategrorie(x);
           for (CategoriePlace e : listCategorie) {
               c.addItem(e.getNameCategoryPlace()+"");
           }
           photos.add(nom);
           c.setSelectedItem(place.getCategory());
           photos.add(l);
           photos.add(c);
           photos.add(marque);
           photos.add(prix);
           
            
            
      
            
            photos.add(img);
            photos.add(b);
            add(photos);
            img.addActionListener(e->{
                
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/Service/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new com.codename1.l10n.SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want

                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    im =fileNameInServer ;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                }
            });
            b.addActionListener(e->{
                
                place.setPicturePlace(im);
                place.setLocation(prix.getText());
                place.setTitrePlace(nom.getText());
                place.setDescriptionPlace(marque.getText());
                
                place.setCategory(c.getSelectedItem());
                System.out.println(place);
                ws.EditPlace(place);
                ListPlaces lp = new ListPlaces();
                lp.show();
                });
            
           
      
        show();
    }
}