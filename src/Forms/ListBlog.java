/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Blog;
import Entities.Product;
import static Forms.ListProduits.p;
import Services.BlogService;
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
public class ListBlog extends BaseForm{
    
    public ListBlog(){
        setName("Liste des Blogs");
        setTitle("Liste des Blogs");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
          
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               Home h = new Home();
               h.show();
           
        });
        
           
             WebService ws = new WebService();
    BlogService ds = new BlogService();
    Map x = ws.getResponse("blogg/List");
        System.out.println(x);
    ArrayList<Blog> listproducts = ds.getListBlog(x);
             for (Blog e : listproducts) {
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            
            Label b = new Label("Titre : "+e.getTitle());
            
           Label date = new Label("Date de création : "+e.getDateAjout());
           Button voir = new Button("Détails");
           
           
           
            
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, e.getImage(), "http://127.0.0.1:8000/uploads/images/" + e.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(b);
            photos.add(date);
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
                    System.out.println(e);
                   BlogDetails.blog = e ;
                   BlogDetails pd = new BlogDetails();
                   pd.show();
                }
            });
            
        }
        show();
    }
}
