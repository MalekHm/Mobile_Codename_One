/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;


import Entities.User;
import Services.BlogService;
import Utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
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
public class LoginForm extends BaseForm{
    
    public LoginForm(){
    super("Login", BoxLayout.y());
        
    
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            TextField login = new TextField();
            
            login.setHint("Email");
            TextField password = new TextField(TextField.PASSWORD);
            password.setHint("Password");
            
            Button b = new Button("Se connecter");
            Button f = new Button("Inscription");
            Button m = new Button("Mot de passe Oublié ?");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            BlogService as = new BlogService();
            WebService ws = new WebService();
            
           
           
            
            
            photos.add(login);
            photos.add(password);
            photos.add(b);
            photos.add(f);
            photos.add(m);
            add(photos);
            f.addActionListener(e->{
                Inscription p = new Inscription();
                p.show();
            });
            b.addActionListener(e->{
               String st = ws.Login(login.getText(), password.getText());
                System.out.println(login.getText());
               if (st.equals("success")) {
                           
                           ArrayList<User> listevents = as.getListCategorie(ws.getUser("http://localhost/Service/GetUser.php?email="+login.getText()));
                           for (User es : listevents) {
                               MyApplication.u = es ;
                               Home h = new Home();
                               h.show();
                               System.out.println(es.getRoles());
                               
                           }
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
            });
            m.addActionListener(e->{
               PasswordForgottenEmail pf = new PasswordForgottenEmail();
               pf.show();
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
