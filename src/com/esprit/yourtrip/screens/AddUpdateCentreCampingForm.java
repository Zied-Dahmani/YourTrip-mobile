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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.yourtrip.MyApplication;
import com.esprit.yourtrip.models.CentreCamping;
import com.esprit.yourtrip.models.Tente;
import com.esprit.yourtrip.services.CentreCampingService;
import com.esprit.yourtrip.services.TenteService;
import java.util.ArrayList;

/**
 *
 * @author Amen
 */
public class AddUpdateCentreCampingForm extends Form {
    
private CentreCamping centre;

    public AddUpdateCentreCampingForm(Form previous, boolean add, CentreCamping c) {

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            this.setTitle("Centre De Camping");

            Container nomContainer = new Container(BoxLayout.x());
            Container adresseContainer = new Container(BoxLayout.x());
            Container emailContainer = new Container(BoxLayout.x());
            Container descriptionContainer = new Container(BoxLayout.y());
            
            TextArea descriptionTA = new TextArea("", 10,100);
            descriptionTA.setMaxSize(9999);
            descriptionTA.getAllStyles().setAlignment(TextArea.CENTER);

            TextField nomTF = new TextField();
            nomTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            TextField adresseTF = new TextField();
            adresseTF.getAllStyles().setAlignment(TextArea.CENTER);

            TextField emailTF = new TextField();
            emailTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            Button submitBtn = new Button("");
           
            nomContainer.addAll(new Label("Nom: "),nomTF);
            adresseContainer.addAll(new Label("Adresse: "),adresseTF);
            emailContainer.addAll(new Label("Email: "),emailTF);
            descriptionContainer.addAll(new Label("Description:"), descriptionTA);
            
            
            if(add)
            {
            submitBtn.setText("Ajouter");
            
            submitBtn.addActionListener(e->{
                if(nomTF.getText()==""||adresseTF.getText()==""||emailTF.getText()==""||descriptionTA.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
            CentreCampingService.getInstance().addCentreCamping(nomTF.getText(),adresseTF.getText(),emailTF.getText(),descriptionTA.getText());
            new CentresCampingListForm().show();
            });
                
            }
            else
            {
            submitBtn.setText("Modifier");
            
            nomTF.setText(c.getNom());
            adresseTF.setText(c.getAdresse());
            emailTF.setText(c.getEmail());
            descriptionTA.setText(c.getDescription());

            submitBtn.addActionListener(e->{
                if(nomTF.getText()==""||adresseTF.getText()==""||emailTF.getText()==""||descriptionTA.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
            CentreCampingService.getInstance().updateCentreCamping(c.getId(),nomTF.getText(),adresseTF.getText(),emailTF.getText(),descriptionTA.getText());
            new CentresCampingListForm().show();
            });
            }

            this.addAll(nomContainer, adresseContainer,emailContainer,descriptionContainer, submitBtn);
            this.setLayout(BoxLayout.y());

    }

}