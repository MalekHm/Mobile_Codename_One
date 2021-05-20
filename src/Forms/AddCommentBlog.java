/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Commentaire;
import Entities.CommentaireBlog;
import static Forms.AddCommentaire.id;
import Utils.WebService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author Jasser
 */
public class AddCommentBlog extends BaseForm{
    public static int id;
    public AddCommentBlog(){
        setName("Ajouter Commentaire");
        setTitle("Ajouter Commentaire");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
               ListCommentairesBlog.id = id;
            ListCommentairesBlog lpa = new ListCommentairesBlog();
            lpa.show();
        });
       
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            TextField nom = new TextField();
            nom.setHint("Content");
            
          
            Button b = new Button("Ajouter");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            
           photos.add(nom);
           
           
          
            
           
            photos.add(b);
            add(photos);
            
            b.addActionListener(e->{
                CommentaireBlog p = new CommentaireBlog();
               
                p.setContenu(nom.getText());
                
                WebService ws = new WebService();
                ws.addCommentBlog(p,id);
                ListBlog lp = new ListBlog();
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
