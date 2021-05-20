/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.Product;
import Utils.WebService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class ProductService {
    public  ArrayList<Product> getListProducts(Map m){
        ArrayList<Product> listProduct = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Product");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Product p = new Product();
            
            
            
            
            p.setName((String)f.get("name"));
            Double id = (Double) f.get("id");
            System.out.println(id);
            p.setId(id.intValue());
            p.setDescription((String)f.get("description"));
            Double price = (Double)f.get("price");
            p.setPrice(price);
            Double pricer = (Double)f.get("pricer");
            p.setPricer(pricer);
            Double quantity = (Double) f.get("quantity");
            p.setQuantity(quantity);
            
            WebService ws = new WebService();
            Map x = ws.getResponse("getProductImage/"+id.intValue());
           
            ArrayList<String> listImages = getListImages(x);
           
            p.setImage(listImages.get(0));
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
            listProduct.add(p);  
        }        
        return listProduct;
        
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
    public  ArrayList<String> getListCategorie(Map m){
        ArrayList<String> listProduct = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Categories");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            
            
            
            
            
            
            
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
            listProduct.add((String)f.get("nameCategory"));  
        }        
        return listProduct;
        
    }
}
