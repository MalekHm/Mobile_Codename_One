/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Community;
import Entities.Places;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class CommunityService {
    public  ArrayList<Community> getListCommunity(Map m){
        ArrayList<Community> listCommunity = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Community");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Community p = new Community();
            
            
            
            
            
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            p.setGroupName((String)f.get("grpName"));
            
            p.setEmail((String)f.get("email"));
            Double phone = (Double)f.get("phone");
            p.setPhone(phone.intValue()+"");
            String description = (String) f.get("description");
            p.setDescription(description);
            
            
            //WebService ws = new WebService();
            //Map x = ws.getResponse("getPlaceImage/"+id);
           
            //ArrayList<String> listImages = getListImages(x);
           
            //p.setImage(listImages.get(0));
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
            listCommunity.add(p);  
        }        
        return listCommunity;
        
    }
}
