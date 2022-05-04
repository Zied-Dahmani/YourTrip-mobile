/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.yourtrip.models;

/**
 *
 * @author Amen
 */
public class CentreCamping {
    
    private int id;
    private String nom,description,adresse,email;
    
    public CentreCamping(){}

    public CentreCamping(String nom, String description, String adresse,String email) {
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.email=email;
    }
    public CentreCamping(int id,String nom, String description, String adresse, String email) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.email=email;
    }
    
    
    public void setId(int id) {
         this.id=id;
    }
    
    public void setNom(String nom) {
        this.nom=nom;
    }
    
    public void setDescription(String description) {
        this.description=description;
    }
    
    public void setAdresse(String adresse) {
        this.adresse=adresse;
    }
    
     public void setEmail(String email) {
        this.email=email;
    }
    
    
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public String getEmail() {
        return email;
    }
    
     @Override
    public String toString() {
        return "Centre Camping{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", adresse=" + adresse + '}';
    }
    
    
}

