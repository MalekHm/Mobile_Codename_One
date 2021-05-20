/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Commentaire;
import Entities.Event;
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
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class ListCommentaires extends BaseForm{
    public static int id;
    public ListCommentaires(){
        setName("Liste des commentaires");
    
        setTitle("Liste des commentaires");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
               AddCommentaire.id = id;
            AddCommentaire ap = new AddCommentaire();
            ap.show();
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               
            ListPlaces ap = new ListPlaces();
            ap.show();
        });
         
           
             WebService ws = new WebService();
    PlacesServices ds = new PlacesServices();
    Map x = ws.getResponse("comments/List/"+id);
        System.out.println(x);
    ArrayList<Commentaire> listevents = ds.getListComments(x);
             for (Commentaire e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            
            Label b = new Label("Titre : "+e.getTitre());
            
           Label Content = new Label("Content : "+e.getContent());
           Label email = new Label("Created By : "+e.getNickname()+"-"+e.getEmail());
           Label createdAt = new Label("Created At : "+e.getCreatedAt());
           
           Button voir = new Button("Delete");
            
           
            
            photos.add(b);
            photos.add(Content);
            photos.add(email);
            photos.add(createdAt);
            if(e.getEmail().equals(MyApplication.u.getEmail())){
                photos.add(voir);
            }
            
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
                    ws.DeleteComment(e.getId());
                    ListPlaces lp = new ListPlaces();
                    lp.show();
                   //EventDetails.e = e ;
                   //EventDetails pd = new EventDetails();
                   //pd.show();
                }
            });
        }
        show();
    }
}
