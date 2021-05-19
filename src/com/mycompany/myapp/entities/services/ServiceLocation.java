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
public class ServiceLocation {

    public ArrayList<Location> velos;
    
    public static ServiceLocation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceUser su = new ServiceUser();
    public ServiceVelo sv = new ServiceVelo();
    public ServiceLocation() {
         req = new ConnectionRequest();
    }

    public static ServiceLocation getInstance() {
        if (instance == null) {
            instance = new ServiceLocation();
        }
        return instance;
    }
    


    public boolean addLocation(Location t) {
        String url = Statics.BASE_URL + "location/location/add?velo_id="+t.getVelo_id()+ "&prix="+t.getPrix()+"&date_debut=" + t.getDate_debut()+ "&date_fin="+ t.getDate_fin(); //création de l'URL
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
    public boolean editLocation(Location t) {
        String url = Statics.BASE_URL + "location/location/edit?id="+t.getId()+"&velo_id="+t.getVelo_id()+ "&prix="+t.getPrix()+"&date_debut=" + t.getDate_debut()+ "&date_fin="+ t.getDate_fin(); //création de l'URL
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
    public boolean deleteLocation(Location t) {
        String url = Statics.BASE_URL + "location/location/del?id=" + t.getId();
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

    public ArrayList<Location> parseLocation(String jsonText){
                try {
            velos=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Location a = new Location();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                String test=obj.get("velo").toString();
                test=test.substring((test).indexOf("id=")+3 ,(test).indexOf(", type"));

                System.out.println(test);

                float idv = Float.parseFloat(test);
                a.setVelo_id((int)idv);
                ArrayList <Velo> velo = new ArrayList();
                velo=sv.getVelo(a.getVelo_id());
                for (Velo e : velo)
                {
                    a.setVelo(e);
                }

                float prix = Float.parseFloat(obj.get("prix").toString());
                a.setPrix((double)prix);
                      Map<String, Object> date = null;
                       date = (Map<String, Object>) obj.get("dateDebut");
                       try {
                          Date longdate = new Date((long) Float.parseFloat(date.get("timestamp").toString()) * 1000);
                             

                            System.out.println("***********" + longdate);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                            String d = formatter.format(longdate);
                            a.setDate_debut(longdate);
                            System.out.println("");
                        } catch (NumberFormatException ex) {

                        }
                       Map<String, Object> datef = null;
                       datef = (Map<String, Object>) obj.get("dateFin");
                       try {

                          Date longdate = new Date((long) Float.parseFloat(datef.get("timestamp").toString()) * 1000);
                             

                            System.out.println("***********" + longdate);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                            String d = formatter.format(longdate);
                            a.setDate_fin(longdate);
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
              
                    

    
    public ArrayList<Location> getAllLocations(){
        String url = Statics.BASE_URL+"location/affLocationMob";
                System.out.println(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseLocation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
    }

    public ArrayList<Location> getLocation(int id){
        String url = Statics.BASE_URL+"/location/Location/findLocation/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseLocation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
    }    
    public ArrayList<Location> getMyLocation(int id){
        String url = Statics.BASE_URL+"/location/Location/findmyLocation/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseLocation(new String(req.getResponseData()));
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
