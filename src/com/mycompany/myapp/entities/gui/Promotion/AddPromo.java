/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Promotion;

import com.mycompany.myapp.entities.gui.Pub.ListPub;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import static com.codename1.components.FloatingActionButton.createFAB;
import com.codename1.datatransfer.DropTarget;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Velo;
import com.mycompany.myapp.entities.services.ServiceLocation;
import com.mycompany.myapp.entities.services.ServicePromotion;
import com.mycompany.myapp.entities.services.ServiceVelo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import static com.codename1.components.FloatingActionButton.createFAB;
import static com.codename1.components.FloatingActionButton.createFAB;
import static com.codename1.components.FloatingActionButton.createFAB;


/**
 *
 * @author bhk
 */
public class AddPromo extends Form{
    
   String filePath;
   Form detailsForm;
   String Imagecode;
    ServiceVelo sa= new ServiceVelo();
    public AddPromo(Form previous,User u) {
        setTitle("Ajouter Promotion");
        TextComponent ideventtf= new TextComponent().label("Event");
        TextComponent idproduittf= new TextComponent().label("Produit");
        TextComponent desctf= new TextComponent().label("Description");

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new Date());
        Picker datePickerr = new Picker();
        datePickerr.setType(Display.PICKER_TYPE_DATE);
        datePickerr.setDate(new Date());

        
        TextComponent pourcentagetf= new TextComponent().label("Pourcentage");

        Button btnValider = new Button("Ajouter Promo");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                        Promotion t = new Promotion(Integer.valueOf(ideventtf.getText()),Integer.valueOf(idproduittf.getText()),desctf.getText(),datePicker.getDate(),datePickerr.getDate(),Float.parseFloat(pourcentagetf.getText()));

                           if( ServicePromotion.getInstance().addPromotion(t))
                               {
                                   // sendMail("wajih.mejri@esprit.tn");
                                    Dialog.show("Success","Connection accepted",new Command("OK"));
                                    
                                        new ListPromo(u).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
 
            }
        });
        addAll(ideventtf,idproduittf,desctf,datePicker,datePickerr,pourcentagetf,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListPub(u).show());
                
    }
                  public void sendMail(String Email) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/Email/sendmail.php?email="+ Email);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.err.println("Mail Sent");
            }
        });

        NetworkManager.getInstance().addToQueue(req);
    }
}
