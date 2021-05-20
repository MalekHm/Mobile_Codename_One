/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Community;
import Entities.Places;
import Services.CommunityService;
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
public class ListCommunity extends BaseForm{
    public ListCommunity(){
         setName("Liste des community");
        setTitle("Liste des community");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            Home ap = new Home();
            ap.show();
        });
        
           
             WebService ws = new WebService();
    CommunityService ds = new CommunityService();
    Map x = ws.getResponse("comm/List");
        //System.out.println(x);
    ArrayList<Community> listPlaces = ds.getListCommunity(x);
             for (Community e : listPlaces) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            
            Label b = new Label("Nom du groupe : "+e.getGroupName());
            Label email = new Label("Email : "+e.getEmail());
           
           Button voir = new Button("Détails");
            
            
            photos.add(b);
            photos.add(email);
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
                   CommunityDetails.c = e ;
                   CommunityDetails pd = new CommunityDetails();
                   pd.show();
                }
            });
        }
        show();
    }
    
}
