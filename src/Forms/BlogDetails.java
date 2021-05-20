/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Entities.Blog;
import Entities.Commentaire;
import Entities.Rate;
import static Forms.CommunityDetails.c;
import static Forms.ListCommentaires.id;
import Services.PlacesServices;
import Utils.WebService;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jasser
 */
public class BlogDetails extends BaseForm{
    public static Blog blog;
    public BlogDetails(){
        setName("Détails");
        setTitle("Détails");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT_AVAILABLE, s);
         FontImage icons = FontImage.createMaterial(FontImage.MATERIAL_EVENT_NOTE, s);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListBlog lp = new ListBlog();
            lp.show();
        });
           getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_COMMENT, ex -> {
               ListCommentairesBlog.id = blog.getId();
           ListCommentairesBlog ap = new ListCommentairesBlog();
            ap.show();
        });
        
           
            Container photos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer imv = null;
            Image img;
            EncodedImage encoded = null;
            
            WebService w = new WebService();
    PlacesServices ds = new PlacesServices();
    Map x = w.getResponse("API/GetRate/"+blog.getId());
        System.out.println(x);
    ArrayList<Rate> listevents = ds.getRates(x);
             
            Label b = new Label("Titre : "+blog.getTitle());
            Label email = new Label("Date Ajout : "+blog.getDateAjout());
            Label phone = new Label("Description : "+blog.getDescription());
            Label rates = new Label("Nombre des avis : "+listevents.get(0).getRates().intValue()+"");
            Label score = new Label("Avis : "+listevents.get(0).getRate().intValue()+"");
           Slider rate = createStarRankSlider();
           Container CR = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Button R = new Button("Envoyer");
            CR.add(FlowLayout.encloseCenter(rate));
            CR.add(R);
           Button voir = new Button("Like");
            Button Dislike = new Button("Dislike");
            try {
                encoded = EncodedImage.create("/like.png");
            } catch (IOException ex) {
            }
            img = URLImage.createToStorage(encoded, blog.getImage(), "http://127.0.0.1:8000/uploads/images/" + blog.getImage());
            imv = new ImageViewer(img);
            photos.add(imv);
            photos.add(b);
            
            photos.add(email);
            photos.add(phone);
            photos.add(rates);
            photos.add(score);
            photos.add(voir);
            photos.add(Dislike);
            try {
                ScaleImageLabel sep = new ScaleImageLabel(Image.createImage("/Separator.png"));
                photos.add(sep);
            } catch (IOException ex) {
            }
            
            add(photos);
            add(CR);
            voir.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    ws.addLike(blog.getId());
                }
            });
            Dislike.addPointerPressedListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    WebService ws = new WebService();
                    ws.addDislike(blog.getId());
                }
            });
            R.addActionListener((e)-> {
                
                int avis = rate.getProgress() / 2;
                WebService ws = new WebService();
                ws.AddRate(avis,blog.getId());
                System.out.println(avis);
                System.out.println(blog.getId());
                System.out.println(MyApplication.u.getId());
            });
            show();
    }
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
     private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
}
