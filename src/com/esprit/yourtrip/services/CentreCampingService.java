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
import com.esprit.yourtrip.MyApplication;
import com.esprit.yourtrip.models.CentreCamping;
import com.esprit.yourtrip.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
/**
 *
 * @author Amen
 */
public class CentreCampingService {
    
    
    public ArrayList<CentreCamping> centres;
    public CentreCamping centre;

    public static CentreCampingService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private CentreCampingService() {
        req = new ConnectionRequest();
    }

    public static CentreCampingService getInstance() {
        if (instance == null) {
            instance = new CentreCampingService();
        }
        return instance;
    }

    public ArrayList<CentreCamping> parseCentres(String jsonText) {
        try {
            centres = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> centresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) centresListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                
                CentreCamping c = new CentreCamping();
     
                c.setId((int) Float.parseFloat(obj.get("id").toString()));
                c.setNom(obj.get("nom").toString());
                c.setAdresse(obj.get("adresse").toString());
                c.setEmail(obj.get("email").toString());
                c.setDescription(obj.get("description").toString());
                centres.add(c);
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return centres;
    }
    
    public ArrayList<CentreCamping> getAllCentresCamping() {
        String url = Statics.BASE_URL + "/api/centres";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                centres = parseCentres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return centres;
    }
    
    
    
    public void addCentreCamping(String nom, String adresse, String email, String description ) {
        String url = Statics.BASE_URL + "/api/centre/add";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        req.addArgument("nom", nom);
        req.addArgument("adresse", adresse);
        req.addArgument("email", email);
        req.addArgument("description", description);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void updateCentreCamping(Integer id,String nom, String adresse, String email, String description) {
        String url = Statics.BASE_URL + "/api/centre/update";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        req.addArgument("id", id.toString());
        req.addArgument("nom", nom);
        req.addArgument("adresse", adresse);
        req.addArgument("email", email);
        req.addArgument("description", description);
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void deleteCentreCamping(Integer id) {
        String url = Statics.BASE_URL + "/api/centre/delete";
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");
        System.out.print(id.toString());

        req.addArgument("id", id.toString());
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
}
