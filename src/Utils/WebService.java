/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;



import Entities.Commentaire;
import Entities.CommentaireBlog;
import Entities.Places;
import Entities.Product;
import Entities.User;
import Forms.LoginForm;
import Forms.UpdatePassword;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Justpro
 */
public class WebService {
    static Map h;
    static String status ="";
    static int c ;
    static String lg ;
    
    public static Map<String, Object> getResponse(String url){
        url = "http://127.0.0.1:8000/"+url;
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
    public void addReservation(int IdEvent,int nbrPlabes){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/evennements/"+IdEvent;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("nbPersonnes", nbrPlabes+"");
     con.addArgument("email", MyApplication.u.getEmail());
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        //if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        //}else{
                        //   Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        //}
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void addPlace(Places p){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/AddPlace";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("titrePlace", p.getTitrePlace());
     con.addArgument("descriptionPlace", p.getDescriptionPlace());
     con.addArgument("Location", p.getLocation());
     con.addArgument("PicturePlace", p.getPicturePlace());
     con.addArgument("category", p.getCategory());
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void EditPlace(Places p){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/editPlace/"+p.getId();
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     con.addArgument("id", p.getId()+"");
     con.addArgument("titrePlace", p.getTitrePlace());
     con.addArgument("descriptionPlace", p.getDescriptionPlace());
     con.addArgument("Location", p.getLocation());
     con.addArgument("PicturePlace", p.getPicturePlace());
     con.addArgument("category", p.getCategory());
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   public void DeletePlace(int id){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/deletePlace";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     con.addArgument("id", id+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   public void DeleteComment(int id){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/comments/delete/"+id;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     con.addArgument("id", id+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   
     
    public void AddCommande(Product p){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/AjouterCommande/"+MyApplication.u.getId() ;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     con.addArgument("id", p.getId()+"");
     con.addArgument("quantite", p.getQuantity()+"");
     con.addArgument("type", p.getType());
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void addComment(Commentaire p, int id){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/AddCommentaire/"+id;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("content", p.getContent());
     con.addArgument("nickname", p.getNickname());
     con.addArgument("email", p.getEmail());
     con.addArgument("title", p.getTitre());
     con.addArgument("id", id+"");
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void addLike(int id){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/blogg/AddLike/"+MyApplication.u.getId()+"/"+id;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
   
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void addDislike(int id){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/blogg/AddDislike/"+MyApplication.u.getId()+"/"+id;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
   
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void addCommentBlog(CommentaireBlog p,int id){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/blogg/AddComment/"+MyApplication.u.getId();
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("content", p.getContenu());
    
     con.addArgument("id", id+"");
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public static Map<String, Object> search(String categorie,String prixMin, String prixMax){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/product/search";
        ConnectionRequest con = new ConnectionRequest();
         
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("categorie", categorie);
    
     con.addArgument("prixmax", prixMax);
     con.addArgument("prixmin", prixMin);
     con.setPost(true);
         InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        con.setDisposeOnCompletion(dlg);
        con.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(con.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return h; 
      
        
    }
    public String Login(String email,String password ){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://localhost/Service/login.php?login="+email+"&password="+password ;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     //con.addArgument("user", user+"");
     //con.addArgument("event", event+"");
     
     
    
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        lg = s ;
                        System.out.println(s);
                       /** if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }**/
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lg ;
    }
        public static Map<String, Object> getUser(String url){
        
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
        public void Inscription(User u){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/API/Inscription";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("password", u.getPassword());
    
     con.addArgument("email", u.getEmail());
     con.addArgument("nom", u.getNom());
     con.addArgument("prenom", u.getPrenom());
     con.addArgument("identity", u.getIdentity()+"");
     con.addArgument("phone", u.getPhone()+"");
     con.addArgument("birthday", u.getBirthday());
     con.addArgument("image", u.getImage());
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public void AddRate(int avis,int blog){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/API/AddRate";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("idUser", MyApplication.u.getId()+"");
    
     con.addArgument("id", blog+"");
     con.addArgument("rate", avis+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public void addView(int id,String link){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = link;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
     
     con.addArgument("id", id+"");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            System.out.println("Success");
                            //Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                            System.out.println("Error");
                           //Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void CheckEmail(String email){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/CheckEmail";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
    
     con.addArgument("email", email);
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }   
     public void CheckToken(String token){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/ChekToken/"+token;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
    
     //con.addArgument("email", token);
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            
                            Dialog.show("Confirmation", "success", "Ok", null);
                            UpdatePassword.token = token;
                            UpdatePassword up = new UpdatePassword();
                            up.show();
                        }else{
                            
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void EditPassword(String token,String Password ){
        
        //String url = "http://127.0.0.1:8000/ajouterjson/"+p.getNom()+ "/" +p.getCategorie()+ "/" +p.getEmail()+ "/" +p.getType()+ "/" +p.getAdresse()+ "/" +p.getDescription()+ "/" +p.getSiteWeb()+ "/" +p.getPageFacebook()+ "/" +p.getPhone();
        String url = "http://127.0.0.1:8000/UpdatePassword/"+token;
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     
    
     con.addArgument("password", Password);
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        //byte[] data = (byte[]) evt.getMetaData();
                        //String s = new String(data);
                        //System.out.println(s);
                        /**if (s.equals("Done")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null);
                        }**/
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                            LoginForm lf = new LoginForm();
                            lf.show();
                        }else{
                           Dialog.show("Erreur", "vous avez déja enregistré un bon d'entrée pour cette commande", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
}
