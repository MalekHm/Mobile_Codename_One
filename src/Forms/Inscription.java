/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.CategoriePlace;
import Entities.Places;
import Entities.User;
import Services.PlacesServices;
import Utils.WebService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class Inscription extends BaseForm{
    private String im ;
    public Inscription(){
        
            
             setName("Inscription");
        setTitle("Inscription");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
        FontImage icone = FontImage.createMaterial(FontImage.MATERIAL_IMAGE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            LoginForm lpa = new LoginForm();
            lpa.show();
        });
        
            
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
             
            
            TextField nom = new TextField();
            nom.setHint("Nom");
            TextField marque = new TextField();
            marque.setHint("Prénom");
            TextField prix = new TextField();
            prix.setHint("Email");
           
           TextField Password = new TextField();
            Password.setHint("Password");
            TextField Identity = new TextField();
            Identity.setHint("Identity");
            TextField phone = new TextField();
            phone.setHint("Phone");
            Label l = new Label("Date Naissance");
            Picker datePicker = new Picker();
            datePicker.setType(Display.PICKER_TYPE_DATE);
           
            Button img = new Button("Ajouter une image",icone);
          
            Button b = new Button("Inscription");
            
           
            //Label c = new Label("Nombre d'heures : "+e.getNbheures()+"");
            
            WebService ws = new WebService();
            
           photos.add(nom);
           //photos.add(l);
           photos.add(marque);
           photos.add(prix);
           photos.add(Password);
            photos.add(Identity);
            photos.add(phone);
            photos.add(l);
            photos.add(datePicker);
      
            
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
                User u = new User();
                u.setPassword(Password.getText());
                u.setNom(nom.getText());
                u.setPrenom(marque.getText());
                u.setEmail(prix.getText());
                u.setIdentity(Integer.parseInt(Identity.getText()));
                u.setPhone(Integer.parseInt(phone.getText()));
                u.setImage(im);
                 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               
               Date da = datePicker.getDate();
              
               String st1 = df.format(da);
                u.setBirthday(st1);
                //p.setCategory(c.getSelectedItem());
                
                ws.Inscription(u);
                LoginForm lp = new LoginForm();
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
