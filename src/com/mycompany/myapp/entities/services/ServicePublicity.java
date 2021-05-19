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
import com.mycompany.myapp.entities.Publicity;
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
public class ServicePublicity {

    public ArrayList<Publicity> pubs;
    
    public static ServicePublicity instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceUser su = new ServiceUser();
    public ServicePublicity() {
         req = new ConnectionRequest();
    }

    public static ServicePublicity getInstance() {
        if (instance == null) {
            instance = new ServicePublicity();
        }
        return instance;
    }
    


    public boolean addPub(Publicity t) {
        String url = Statics.BASE_URL + "/addmobpub/new?datedeb="+t.getStart_date_pub()+ "&datefin="+t.getEnd_date_pub()+"&desc=" + t.getDescription_pub()+ "&image="+ t.getPicture_pub()+ "&titre="+ t.getTitle_pub()+ "&position="+ t.getPosition()+ "&link="+ t.getLink(); //création de l'URL
        System.out.println(url);       
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
    public boolean editPub(Publicity t) {
        String url = Statics.BASE_URL + "/editmobpub/edit?id="+t.getId()+"&datedeb="+t.getStart_date_pub()+ "&datefin="+t.getEnd_date_pub()+"&desc=" + t.getDescription_pub()+ "&image="+ t.getPicture_pub()+ "&titre="+ t.getTitle_pub()+ "&position="+ t.getPosition()+ "&link="+ t.getLink(); //création de l'URL
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
    public boolean deletePub(Publicity t) {
        String url = Statics.BASE_URL + "/pub/del?id=" + t.getId();
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

    public ArrayList<Publicity> parsepub(String jsonText){
                try {
            pubs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Publicity a = new Publicity();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                
                a.setTitle_pub(obj.get("titlePub").toString());
                a.setDescription_pub(obj.get("descriptionPub").toString());
                a.setPicture_pub(obj.get("picturePub").toString());
                a.setLink(obj.get("link").toString());
                a.setPosition(obj.get("position").toString());
                

                String datee = obj.get("startDatePub").toString();
                a.setDatedeb(datee.substring(0,4)+'/'+datee.substring(5,7)+'/'+datee.substring(8,10));
                
                String dateee = obj.get("endDatePub").toString();
                a.setDatefin(dateee.substring(0,4)+'/'+dateee.substring(5,7)+'/'+dateee.substring(8,10));

                       
                pubs.add(a);
                           //     t.setDateabsence((Date) obj.get("dateabsence"));

            }
            
            
        } catch (IOException ex) {
            
        }
        return pubs;
    }
              
                    

    
    public ArrayList<Publicity> getAllPubs(){
        String url = Statics.BASE_URL+"/affmobPub";
                System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pubs = parsepub(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return pubs;
    }

    public ArrayList<Publicity> getPub(int id){
        String url = Statics.BASE_URL+"/location/Location/findLocation/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pubs = parsepub(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return pubs;
    }    
    public ArrayList<Publicity> getMyLocation(int id){
        String url = Statics.BASE_URL+"/location/Location/findmyLocation/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pubs = parsepub(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return pubs;
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
