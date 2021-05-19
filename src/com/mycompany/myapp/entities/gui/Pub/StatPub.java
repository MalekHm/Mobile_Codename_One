/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Pub;

import com.mycompany.myapp.entities.gui.Promotion.MenuPromotion;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Publicity;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Velo;
import com.mycompany.myapp.entities.gui.MenuPromotionPub;
import com.mycompany.myapp.entities.services.ServicePublicity;
import com.mycompany.myapp.entities.services.ServiceVelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malek
 */
public class StatPub extends Form{
        Form current;

        public StatPub(User u) {
        setTitle("STATISTIQUE");
             int nbtop=0;
             int nbbot=0;
             int nbnan=0;
             List<Publicity> lista = ServicePublicity.getInstance().getAllPubs();;
             for (Publicity fi : lista) {
             if(fi.getPosition().equals("TOP"))
                 nbtop++;
             if(fi.getPosition().equals("BOTTOM"))
                 nbbot++;
             if(fi.getPosition().equals("NAN"))
                 nbnan++;   
             }

                 double[] values = new double[]{nbtop,nbbot,nbnan};
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(80);
    renderer.setChartTitle("Statistique");
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.CYAN);
    r.setGradientStop(0, ColorUtil.YELLOW);
    r.setHighlighted(true);
    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);
    add(c);
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPromotionPub().show());
        getToolbar().addCommandToSideMenu("Gestions des Publicites", null, e -> new MenuPub(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Promotions", null, e -> new MenuPromotion(u).show());
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(50);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    /*
    int k = 0;
    for (double value : values) {
        series.add("Nombre " + ++k, value);
    }
    */
    series.add("TOP" ,values[0]);
    series.add("BOTTOM" ,values[1]);
    series.add("NAN" ,values[2]);
    return series;
}

}
