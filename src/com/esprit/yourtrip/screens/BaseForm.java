/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.yourtrip.screens;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
/**
 *
 * @author Amen
 */
    
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu() {
        Toolbar tb = getToolbar();
        tb.setSafeArea(false);
        /*String url = "https://avatars.dicebear.com/api/bottts/" + "b" + ".png";
        try {
            EncodedImage spinner = EncodedImage.create("/spinner.png");
            Image centreImage = URLImage.createToStorage(spinner, url, url, URLImage.RESIZE_SCALE);
            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    FlowLayout.encloseCenterBottom(
                            new Label(centreImage, "PictureWhiteBackgrond"))
            ));
        } catch (Exception e) {

        }*/

        tb.addMaterialCommandToSideMenu("Centres De Camping", FontImage.MATERIAL_NATURE, e -> new CentresCampingListForm().show());
        tb.addMaterialCommandToSideMenu("Tentes", FontImage.MATERIAL_HOME, e -> new TentesListForm().show());

    }
}