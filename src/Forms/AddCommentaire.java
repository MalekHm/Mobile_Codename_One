/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.CategoriePlace;
import Entities.Commentaire;
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
public class AddCommentaire extends BaseForm{
    public static int id;
    public AddCommentaire(){
        
        
        setName("Ajouter Commentaire");
        setTitle("Ajouter Commentaire");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListPlaces lpa = new ListPlaces();
            lpa.show();
        });
        
        
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label l = new Label("Catégorie");
            TextField nom = new TextField();
            nom.setHint("Content");
            TextField marque = new TextField();
            marque.setHint("Email");
            TextField prix = new TextField();
            prix.setHint("NickName");
           
           TextField title = new TextField();
            title.setHint("Titre du commentaire");
            
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            
           photos.add(nom);
           
           
           photos.add(marque);
           photos.add(prix);
           
           photos.add(title);
            
      
            
           
            photos.add(b);
            add(photos);
            
            b.addActionListener(e->{
                Commentaire p = new Commentaire();
               
                p.setContent(nom.getText());
                p.setEmail(marque.getText());
                p.setNickname(prix.getText());
                
                p.setTitre(title.getText());
                WebService ws = new WebService();
                ws.addComment(p,id);
                ListPlaces lp = new ListPlaces();
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
