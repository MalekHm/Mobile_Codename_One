/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Promotion;

import com.mycompany.myapp.entities.gui.Pub.*;
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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Velo;
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
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.Publicity;
import com.mycompany.myapp.entities.services.ServicePromotion;
import com.mycompany.myapp.entities.services.ServicePublicity;
import java.text.ParseException;


/**
 *
 * @author bhk
 */
public class EditPromo extends Form{
    
   String filePath;
   Form detailsForm;
   String Imagecode;
    ServicePromotion sa= new ServicePromotion();
    public EditPromo(Form previous,Promotion v,User u) {
        setTitle("Modifier Promo");
        setLayout(BoxLayout.y());
                
        ComboBox<String> combo = new ComboBox<>();
        TextComponent ideventtf= new TextComponent().label("Event");
             ideventtf.text(String.valueOf(v.getEvent_id()));
        TextComponent idproduittf= new TextComponent().label("Produit");
                idproduittf.text(String.valueOf(v.getProduct_id()));

        TextComponent desctf= new TextComponent().label("Description");
                desctf.text(v.getDescriptionpromotion());


        TextComponent tfddd= new TextComponent().label("Date deb");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        Picker datePickerr = new Picker();
        datePickerr.setType(Display.PICKER_TYPE_DATE);
       try {  
           Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(v.getDatedeb());
                   datePicker.setDate(date1);
           Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(v.getDatefin());
                   datePickerr.setDate(date2);

       } catch (ParseException ex) {
       }

        
        TextComponent pourcentagetf= new TextComponent().label("Pourcentage");
        pourcentagetf.text(String.valueOf(v.getPourcentage()));


       

        Button btnValider = new Button("Modifer Pub");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (ideventtf.getText().equals("")||(idproduittf.getText().equals("")||(desctf.getText().equals(""))))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {

                    try {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                        System.out.println(datePicker.getDate());
                        SimpleDateFormat formatt = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                        System.out.println(datePickerr.getDate());
                        System.out.println(filePath);

                        Promotion t = new Promotion(Integer.valueOf(ideventtf.getText()),Integer.valueOf(idproduittf.getText()),desctf.getText(),datePicker.getDate(),datePickerr.getDate(),Float.parseFloat(pourcentagetf.getText()));
                            t.setId(v.getId());
                           if( ServicePromotion.getInstance().editPromo(t)){
                               {
                                   Dialog.show("Success","Connection accepted",new Command("OK"));
                                   
                               }
  
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        addAll(ideventtf,idproduittf,desctf,datePicker,datePickerr,pourcentagetf,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListPromo(u).show());
                
    }
     
}
