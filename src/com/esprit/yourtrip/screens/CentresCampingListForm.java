/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.yourtrip.screens;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.esprit.yourtrip.MyApplication;
import com.esprit.yourtrip.models.CentreCamping;
import com.esprit.yourtrip.services.CentreCampingService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Amen
 */
public class CentresCampingListForm extends Form 
{

    public CentresCampingListForm() {
        setTitle("Centres De Camping");
        getToolbar().addMaterialCommandToSideMenu("Centres De Camping", FontImage.MATERIAL_NATURE, e -> new CentresCampingListForm().show());
        getToolbar().addMaterialCommandToSideMenu("Tentes", FontImage.MATERIAL_HOME, e -> new TentesListForm().show());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
                new AddUpdateCentreCampingForm(this,true,new CentreCamping()).show();
            
        });
        
        Container searchContainer = new Container(BoxLayout.x());
        TextField searchTF = new TextField("", "Search");
        searchContainer.add(searchTF);
        
        Container centresContainer = new Container();

        ArrayList<CentreCamping> centres = CentreCampingService.getInstance().getAllCentresCamping();

        searchTF.addDataChangedListener((i1,i2) -> {
            centresContainer.removeAll();

            for (CentreCamping c : centres) {
                if (c.getNom().toLowerCase().contains(searchTF.getText().toLowerCase()) || searchTF.getText().equals("")) {
                    centresContainer.add(this.addCentresHolder(c));
                }
            }
            this.refreshTheme();

        });
        
        
        for (CentreCamping c : centres) {
         centresContainer.add(this.addCentresHolder(c));
        }
                
        this.addAll(searchContainer,centresContainer);

    }

    private Container addCentresHolder(CentreCamping c) {
        try {
           
            Container holderContainer = new Container(BoxLayout.x());
            Container detailsContainer = new Container(BoxLayout.y());
            Container buttonContainer = new Container(BoxLayout.x());

            Label lbTitle = new Label(c.getNom());
                    lbTitle.getAllStyles().setFgColor(0xFFFFFF);
            Label lbAdresse = new Label(c.getAdresse());
                    lbAdresse.getAllStyles().setFgColor(0xFFFFFF);

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
            detailsContainer.addAll(lbTitle, lbAdresse,buttonContainer);
            holderContainer.addAll(detailsContainer);

            btUpdate.addActionListener(e -> {
               new AddUpdateCentreCampingForm(this,false,c).show();
            });
 
            btDelete.addActionListener(e -> {
                System.out.print(c.getId());
                CentreCampingService.getInstance().deleteCentreCamping(c.getId());
                new CentresCampingListForm().show();
            });
            
            return holderContainer;
            
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } 
        
        return new Container(BoxLayout.x());
    }

  

}
