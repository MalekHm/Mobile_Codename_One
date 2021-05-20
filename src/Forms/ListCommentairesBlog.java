/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Commentaire;
import Entities.CommentaireBlog;
import static Forms.ListCommentaires.id;
import Services.BlogService;
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
public class ListCommentairesBlog extends BaseForm{
    public static int id;
    public ListCommentairesBlog(){
       setName("Liste des commentaires");
    
        setTitle("Liste des commentaires");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
           
            ListBlog ap = new ListBlog();
            ap.show();
        });
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
            AddCommentBlog.id = id;
            AddCommentBlog ap = new AddCommentBlog();
            ap.show();
        });
        
           
             WebService ws = new WebService();
    BlogService ds = new BlogService();
    Map x = ws.getResponse("Blogcomments/List/"+id);
        System.out.println(x);
    ArrayList<CommentaireBlog> listevents = ds.getListComments(x);
             for (CommentaireBlog e : listevents) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           
            
            
            Label b = new Label("Content : "+e.getContenu());
            
           Label Content = new Label("Date : "+e.getDate());
           
           Button voir = new Button("Delete");
            
           
            
            photos.add(b);
            photos.add(Content);
            
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
