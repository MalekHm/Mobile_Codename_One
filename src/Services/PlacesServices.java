/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategoriePlace;
import Entities.Commentaire;
import Entities.Places;
import Entities.Product;
import Entities.Rate;
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
public class PlacesServices {
    public  ArrayList<Places> getListPlaces(Map m){
        ArrayList<Places> listPlaces = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Places");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Places p = new Places();
            
            
            
            
            p.setTitrePlace((String)f.get("titlePlace"));
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            p.setDescriptionPlace((String)f.get("DescriptionPlace"));
            Double nbView = (Double)f.get("nbView");
            p.setNbview(nbView.intValue());
            p.setLocation((String)f.get("location"));
            p.setPicturePlace((String)f.get("picturePlace"));
            String showPlace = (String) f.get("showPlace");
            p.setShow_place(showPlace);
            Double nbComment = (Double)f.get("nbComment");
            p.setNbComments(nbComment.intValue());
            
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
            listPlaces.add(p);  
        }        
        return listPlaces;
        
    }
    public  ArrayList<CategoriePlace> getListCategrorie(Map m){
        ArrayList<CategoriePlace> listCategorie = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Categorie");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            CategoriePlace p = new CategoriePlace();
            
            
            
            
            
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            p.setNameCategoryPlace((String)f.get("nameCategoryPlace"));
            
            
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
            listCategorie.add(p);  
        }        
        return listCategorie;
        
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
    public  ArrayList<Commentaire> getListComments(Map m){
        ArrayList<Commentaire> listCommentaire = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Comments");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Commentaire c = new Commentaire();
            Double id = (Double)f.get("id");
            c.setId(id.intValue());
            
            c.setContent((String)f.get("content"));
            c.setEmail((String)f.get("email"));
            c.setNickname((String)f.get("nickname"));
            c.setTitre((String)f.get("titleComment"));
            
            Map map1 = ((Map) f.get("createdAt"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            c.setCreatedAt(s1);
           
            //e.setDescription();
            
            
            
           
            
           
            listCommentaire.add(c);  
        }        
        return listCommentaire;
        
    }
    public  ArrayList<Rate> getRates(Map m){
        ArrayList<Rate> listCommentaire = new ArrayList<>();
        Map d = (Map)m.get("result");
        System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Rate c = new Rate();
            Double rate = (Double)d.get("Rate");
            Double rates = (Double)d.get("Rates");
            c.setRate(rate);
            c.setRates(rates);
           
            //e.setDescription();
            
            
            
           
            
           
            listCommentaire.add(c);  
        }        
        return listCommentaire;
        
    }
    
}
