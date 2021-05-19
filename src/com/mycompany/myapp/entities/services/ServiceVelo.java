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
import com.mycompany.myapp.entities.Velo;
import com.mycompany.myapp.entities.User;

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
public class ServiceVelo {

    public ArrayList<Velo> velos;
    
    public static ServiceVelo instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceUser su = new ServiceUser();
    public ServiceVelo() {
         req = new ConnectionRequest();
    }

    public static ServiceVelo getInstance() {
        if (instance == null) {
            instance = new ServiceVelo();
        }
        return instance;
    }
    


    public boolean addVelo(Velo t) {
        String url = Statics.BASE_URL + "location/velo/add?type="+t.getType()+ "&description="+t.getDescription()+"&etat=" + t.getEtat()+ "&image="+ t.getImage()+ "&prix=" + t.getPrix()+ "&dateMaintenance="+t.getDateMaintenance()+ "&cycleMaintenance="+t.getCycleMaintenance(); //création de l'URL
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
    public boolean editVelo(Velo t) {
        String url = Statics.BASE_URL + "location/velo/edit?id="+t.getId()+"&type="+t.getType()+ "&description="+t.getDescription()+"&etat=" + t.getEtat()+ "&image="+ t.getImage()+ "&prix=" + t.getPrix()+ "&dateMaintenance="+t.getDateMaintenance()+ "&cycleMaintenance="+t.getCycleMaintenance(); //création de l'URL
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
    
    public boolean deleteVelo(Velo t) {
        String url = Statics.BASE_URL + "location/velo/del?id=" + t.getId();
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

    public ArrayList<Velo> parseVelo(String jsonText){
                try {
            velos=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Velo a = new Velo();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                a.setType(obj.get("type").toString());
                a.setDescription(obj.get("description").toString());
                a.setEtat(obj.get("etat").toString());
                a.setImage(obj.get("image").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                a.setPrix((double)prix);
                float cm = Float.parseFloat(obj.get("cycleMaintenance").toString());
                a.setCycleMaintenance((int)cm);
                
                      Map<String, Object> date = null;
                       date = (Map<String, Object>) obj.get("dateMaintenance");
                       try {

                          Date longdate = new Date((long) Float.parseFloat(date.get("timestamp").toString()) * 1000);
                             

                            System.out.println("***********" + longdate);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                            String d = formatter.format(longdate);
                            a.setDateMaintenance(longdate);
                            System.out.println("");
                        } catch (NumberFormatException ex) {

                        }
                       
                velos.add(a);
                           //     t.setDateabsence((Date) obj.get("dateabsence"));

            }
            
            
        } catch (IOException ex) {
            
        }
        return velos;
    }
              
                    

    
    public ArrayList<Velo> getAllVelos(){
        String url = Statics.BASE_URL+"location/affVeloMob";
                System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseVelo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
    }

    public ArrayList<Velo> getVelo(int id){
        String url = Statics.BASE_URL+"location/velo/findVeloMob/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseVelo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
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
