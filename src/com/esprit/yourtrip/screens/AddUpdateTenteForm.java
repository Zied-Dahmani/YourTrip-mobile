/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.yourtrip.screens;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
public class AddUpdateTenteForm extends Form{
    
private CentreCamping centre;

    public AddUpdateTenteForm(Form previous, boolean add, Tente c) {

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            this.setTitle("Tente");

            Container nomContainer = new Container(BoxLayout.x());
            Container prixContainer = new Container(BoxLayout.x());
            Container descriptionContainer = new Container(BoxLayout.y());
            
            TextArea descriptionTA = new TextArea("", 10,100);
            descriptionTA.setMaxSize(9999);
            descriptionTA.getAllStyles().setAlignment(TextArea.CENTER);

            TextField nomTF = new TextField();
            nomTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            //TextField prixTF = new TextField(TextArea.NUMERIC);
            TextField prixTF = new TextField();

            prixTF.getAllStyles().setAlignment(TextArea.CENTER);

           
            Button submitBtn = new Button("");
           
            Label lbNom = new Label("Nom:");
                                lbNom.getAllStyles().setFgColor(0xFFFFFF);
            Label lbPrix = new Label("Prix:");
                                lbPrix.getAllStyles().setFgColor(0xFFFFFF);
             Label lbDescription = new Label("Description:");
                                lbDescription.getAllStyles().setFgColor(0xFFFFFF);
                                
            nomContainer.addAll(lbNom,nomTF);
            prixContainer.addAll(lbPrix,prixTF);
            
            
            if(add)
            {
            submitBtn.setText("Ajouter");
                    
            ComboBox<String> centresCB = new ComboBox<>();
            ArrayList<CentreCamping> centres = CentreCampingService.getInstance().getAllCentresCamping();
            for (CentreCamping centre : centres) {
                centresCB.addItem(Integer.toString(centre.getId()));
            }
            
           
            Label lbCentres = new Label("Centre De Camping:");
                                lbCentres.getAllStyles().setFgColor(0xFFFFFF);
            
                descriptionContainer.addAll(lbDescription, descriptionTA,lbCentres,centresCB);
            
            submitBtn.addActionListener(e->{
                if(nomTF.getText()==""||prixTF.getText()==""||descriptionTA.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
            TenteService.getInstance().addTente(nomTF.getText(),prixTF.getText(),descriptionTA.getText(),centresCB.getSelectedItem());
            new TentesListForm().show();
            });
                
            }
            else
            {
            submitBtn.setText("Modifier");

                descriptionContainer.addAll(lbDescription, descriptionTA);

            nomTF.setText(c.getNom());
            prixTF.setText(String.valueOf(c.getPrix()));
            descriptionTA.setText(c.getDescription());

            submitBtn.addActionListener(e->{
                if(nomTF.getText()==""||prixTF.getText()==""||descriptionTA.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
            TenteService.getInstance().updateTente(c.getId(),nomTF.getText(),prixTF.getText(),descriptionTA.getText());
            new TentesListForm().show();
            });
            
            }
                        
            
            submitBtn.getStyle().setFgColor(0xFFFFFF);
            this.addAll(nomContainer, prixContainer,descriptionContainer, submitBtn);
            this.setLayout(BoxLayout.y());

    }

}
