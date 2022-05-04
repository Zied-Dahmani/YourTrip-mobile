/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.yourtrip.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.yourtrip.models.Tente;
import com.esprit.yourtrip.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amen
 */
public class TenteService {
    

    public ArrayList<Tente> tentes;
    public Tente tente;

    public static TenteService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private TenteService() {
        req = new ConnectionRequest();
    }

    public static TenteService getInstance() {
        if (instance == null) {
            instance = new TenteService();
        }
        return instance;
    }

    public ArrayList<Tente> parseTentes(String jsonText) {
        try {
            tentes = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> tentesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tentesListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                Tente t = new Tente();
     
                t.setId((int) Float.parseFloat(obj.get("id").toString()));
                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                t.setPrix((int)Float.parseFloat(obj.get("prix").toString()));

                tentes.add(t);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return tentes;
    }
    
    public ArrayList<Tente> getAllTentes() {
        String url = Statics.BASE_URL + "/api/tentes";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tentes = parseTentes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tentes;
    }
    
        
    public void addTente(String name,String prix, String description, String centre_camping_id) {
        String url = Statics.BASE_URL + "/api/tente/add";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        req.addArgument("nom", name);
        req.addArgument("prix", prix);
        req.addArgument("description", description);
        req.addArgument("centre_camping_id", centre_camping_id);


        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public void updateTente(Integer id,String nom, String prix, String description) {
        String url = Statics.BASE_URL + "/api/tente/update";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        //req.addArgument("username", MyApplication.loggedUser.getUsername());
        req.addArgument("id", id.toString());
        req.addArgument("nom", nom);
        req.addArgument("prix", prix);
        req.addArgument("description", description);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    
    public void deleteTente(Integer id) {
        String url = Statics.BASE_URL + "/api/tente/delete";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        System.out.print(id.toString());
        req.addArgument("id", id.toString());
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}