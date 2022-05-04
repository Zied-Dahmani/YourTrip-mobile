/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.yourtrip.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.esprit.yourtrip.models.CentreCamping;
import com.esprit.yourtrip.models.Tente;
import com.esprit.yourtrip.services.CentreCampingService;
import com.esprit.yourtrip.services.TenteService;
import java.util.ArrayList;

/**
 *
 * @author Amen
 */
public class TentesListForm extends BaseForm 
{

    public TentesListForm() {
        super.addSideMenu();
        setTitle("Tentes");
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
               new AddUpdateTenteForm(this,true,new Tente()).show();
        });
         
        ArrayList<Tente> tentes = TenteService.getInstance().getAllTentes();
        for (Tente t : tentes) {
         this.add(this.addCentresHolder(t));
        }
                
      
    }

    private Container addCentresHolder(Tente t) {
        try {
           
            Container holderContainer = new Container(BoxLayout.x());
            Container detailsContainer = new Container(BoxLayout.y());
            Container buttonContainer = new Container(BoxLayout.x());

            // EncodedImage spinner = EncodedImage.create("/spinner.png");
            //String url = "https://avatars.dicebear.com/api/bottts/" + c.getNom() + ".png";
            //Image gameImage = URLImage.createToStorage(spinner, url, url, URLImage.RESIZE_SCALE);
            //ImageViewer image = new ImageViewer(gameImage);
            
            Label lbTitle = new Label(t.getNom());
            Label lbDescription = new Label(t.getDescription());
            Button btUpdate = new Button("Modifier");
            Button btDelete = new Button("Supprimer");
            
            btUpdate.getAllStyles().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0x1E90FF).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
            btUpdate.getAllStyles().setFgColor(0xffffff);
            
            btDelete.getAllStyles().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xFF0000).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
            btDelete.getAllStyles().setFgColor(0xffffff);
            
            
            buttonContainer.addAll(btUpdate, btDelete);
            detailsContainer.addAll(lbTitle, lbDescription,buttonContainer);
            holderContainer.addAll(detailsContainer);

            btUpdate.addActionListener(e -> {
               new AddUpdateTenteForm(this,false,t).show();
            });
 
            btDelete.addActionListener(e -> {
                System.out.print(t.getId());
                TenteService.getInstance().deleteTente(t.getId());
                new TentesListForm().show();
            });
            
            return holderContainer;
            
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } 
        
        return new Container(BoxLayout.x());
    }

  

}
