/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Utils.WebService;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class EventService {
     public  ArrayList<Event> getListEvent(Map m){
        ArrayList<Event> listEvent = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Event");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Event e = new Event();
            
            
            
            
            e.setTitre((String)f.get("titre"));
            Double id = (Double) f.get("id");
            e.setId(id.intValue());
            e.setDescription((String)f.get("description"));
            Double nbPersons = (Double)f.get("nbPersons");
            e.setNb_persons(nbPersons.intValue());
            Double priceEvent = (Double)f.get("priceEvent");
            e.setPrice_event(priceEvent.intValue());
            Double place_disponible = (Double) f.get("placeDisponible");
            e.setPlace_disponible(place_disponible.intValue());
            Double nb_views = (Double)f.get("nbViews");
            e.setNb_views(nb_views.intValue());
            
            Map map1 = ((Map) f.get("startDate"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            
            Map map2 = ((Map) f.get("endDate"));
            Date date2 = new Date((((Double)map2.get("timestamp")).longValue()*1000)); 
            Format formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            String s2 = formatter1.format(date2);
            
            e.setStart_date(s1);
            e.setEnd_date(s2);
            WebService ws = new WebService();
            Map x = ws.getResponse("getEventImage/"+id);
           
            ArrayList<String> listImages = getListImages(x);
           
            e.setImage(listImages.get(0));
            /**
            e.setTitre((String) f.get("titre"));
            e.setDescription((String) f.get("description"));
            e.setCategorie((String) f.get("categorie"));
            e.setPhoto((String) f.get("photo"));
            e.setDateDebut((Date)f.get("dateDebut"));
            e.setDateFin((Date)f.get("dateFin"));
            //e.setCreatedAt((Date)f.get("createdAt"));
            e.setLieu((String) f.get("lieu"));
            
            e.setNb_max(((Double) f.get("nbMax")).intValue());**/
            listEvent.add(e);  
        }        
        return listEvent;
        
    }
    public  ArrayList<String> getListImages(Map m){
        ArrayList<String> listEvent = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Images");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            
            
            
            
            
           
            //e.setDescription();
            
            
            
           
            
           
            listEvent.add((String)f.get("source"));  
        }        
        return listEvent;
        
    }
    public  ArrayList<String> getListNotifications(Map m){
        ArrayList<String> listEvent = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Notifications");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            
            
            
            
            
            
           
            listEvent.add((String)f.get("message"));  
        }        
        return listEvent;
        
    }
}
