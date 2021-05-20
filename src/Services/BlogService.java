/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Blog;
import Entities.Commentaire;
import Entities.CommentaireBlog;
import Entities.Community;
import Entities.User;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class BlogService {
    public  ArrayList<Blog> getListBlog(Map m){
        ArrayList<Blog> listCommunity = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Blog");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Blog p = new Blog();
            
            
            
            
            
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            p.setTitle((String)f.get("title"));
            
            p.setImage((String)f.get("picture"));
            
            String description = (String) f.get("description");
            p.setDescription(description);
            Map map1 = ((Map) f.get("dateAjout"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            p.setDateAjout(s1);
            
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

public  ArrayList<CommentaireBlog> getListComments(Map m){
        ArrayList<CommentaireBlog> listCommentaire = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Comments");
        //System.out.println("roooooooooot "+d);
        //Map f =  (Map) d.get(0);
        //System.out.println("dddddddddddddd :::::::::"+d.size());

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            CommentaireBlog c = new CommentaireBlog();
            Double id = (Double)f.get("id");
            c.setId(id.intValue());
            
            c.setContenu((String)f.get("contenu"));
            
            
            Map map1 = ((Map) f.get("date"));
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = formatter.format(date1);
            c.setDate(s1);
           
            //e.setDescription();
            
            
            
           
            
           
            listCommentaire.add(c);  
        }        
        return listCommentaire;
        
    }
public  ArrayList<User> getListCategorie(Map m){
        ArrayList<User> listDisponibilite = new ArrayList<>();
        System.out.println(m.get("user"));
        ArrayList d = new ArrayList<>();
        d.add(m.get("user"));
        
        //Map f =  (Map) d.get(0);
        

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            User p = new User();
            String ll = (String) f.get("id");
            p.setId(Integer.parseInt(ll));
            p.setNom((String)f.get("nom"));
            
            p.setPrenom((String)f.get("prenom"));
            p.setUsername((String)f.get("username"));
            p.setEmail((String)f.get("email"));
            
            if(((String)f.get("roles")).length() == 29){
            p.setRoles(getRoleParent((String)f.get("roles")));
            }
            if(((String)f.get("roles")).length() == 32){
            p.setRoles(getRoleApprenantn((String)f.get("roles")));
            }
            
            System.out.println(p);
           
           
            
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
            listDisponibilite.add(p);  
        }        
        return listDisponibilite;
        
    }
    public static String getRoleParent(String array){
        String s = array.substring(15, 26);
        return s ;
    }
    public static String getRoleApprenantn(String array){
        String s = array.substring(15, 29);
        return s ;
    }
}
