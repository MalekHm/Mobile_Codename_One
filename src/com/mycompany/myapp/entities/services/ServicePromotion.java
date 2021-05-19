/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.entities.Location;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Velo;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SIHEM
 */
public class ServicePromotion {

    public ArrayList<Promotion> promos;
    
    public static ServicePromotion instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceUser su = new ServiceUser();
    public ServicePromotion() {
         req = new ConnectionRequest();
    }

    public static ServicePromotion getInstance() {
        if (instance == null) {
            instance = new ServicePromotion();
        }
        return instance;
    }
    


    public boolean addPromotion(Promotion t) {
        String url = Statics.BASE_URL + "/addmobpromo/new?event="+t.getEvent_id()+"&produit="+t.getProduct_id()+"&desc="+t.getDescriptionpromotion()+ "&datedeb="+t.getStart_date_promotion()+"&datefin=" + t.getEnd_date_promotion()+ "&pourcentage="+ t.getPourcentage(); //création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean editPromo(Promotion t) {
        String url = Statics.BASE_URL + "/promo/edit?id="+t.getId()+"&event="+t.getEvent_id()+"&produit="+t.getProduct_id()+"&desc="+t.getDescriptionpromotion()+ "&datedeb="+t.getStart_date_promotion()+"&datefin=" + t.getEnd_date_promotion()+ "&pourcentage="+ t.getPourcentage(); //création de l'URL
               req.setUrl(url);
               System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean deletePromo(Promotion t) {
        String url = Statics.BASE_URL + "/prom/del?id=" + t.getId();
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Promotion> parsePromotion(String jsonText){
                try {
            promos=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Promotion a = new Promotion();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                String test=obj.get("event").toString();
                test=test.substring((test).indexOf("id=")+3 ,(test).indexOf(", titre"));
                float idevnt = Float.parseFloat(test);
                a.setEvent_id((int)idevnt);

                String testt=obj.get("product").toString();
                testt=testt.substring((testt).indexOf("id=")+3 ,(testt).indexOf(", name"));
                float idprod = Float.parseFloat(testt);
                a.setProduct_id((int)idprod);
                a.setDescriptionpromotion(obj.get("descriptionpromotion").toString());
                
                float prix = Float.parseFloat(obj.get("pourcentage").toString());
                a.setPourcentage(prix);
                String datee = obj.get("startDatePromotion").toString();

                a.setDatedeb(datee.substring(0,4)+'/'+datee.substring(5,7)+'/'+datee.substring(8,10));
                String dateee = obj.get("endDatePromotion").toString();
                a.setDatefin(dateee.substring(0,4)+'/'+dateee.substring(5,7)+'/'+dateee.substring(8,10));
                

                promos.add(a);

            }
            
            
        } catch (IOException ex) {
            
        }
        return promos;
    }
              
                    
    public ArrayList<Promotion> getAllPromos(){
        String url = Statics.BASE_URL+"/affmobPromo";
                System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                promos = parsePromotion(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return promos;
    }

    public ArrayList<Promotion> getLocation(int id){
        String url = Statics.BASE_URL+"/location/Location/findLocation/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                promos = parsePromotion(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return promos;
    }    
    public ArrayList<Promotion> getMyLocation(int id){
        String url = Statics.BASE_URL+"/location/Location/findmyLocation/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                promos = parsePromotion(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return promos;
    }
    
        public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    }

        
}
