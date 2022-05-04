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
public class Tente {
    
    private int id,prix,idCentreCamping;
    private String nom,description;
    
    public Tente(){}
    
    public Tente(int idCentreCamping, String nom, String description, int prix) {
        this.idCentreCamping=idCentreCamping;
        this.nom = nom;
        this.description = description;
        this.prix=prix;
    }

    public Tente(int id,int idCentreCamping, String nom, String description, int nbPlaces, int prix) {
        this.id = id;
        this.idCentreCamping=idCentreCamping;
        this.nom = nom;
        this.description = description;
        this.prix=prix;
    }
    
    
    public void setId(int id) {
         this.id=id;
    }
    
     public void setIdCentreCamping(int idCentreCamping) {
         this.idCentreCamping=idCentreCamping;
    }
    
    public void setNom(String nom) {
        this.nom=nom;
    }
    
    public void setDescription(String description) {
        this.description=description;
    }
    
     public void setPrix(int prix) {
        this.prix=prix;
    }
    
    
    
    
    public int getIdCentreCamping() {
        return idCentreCamping;
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
    
    public int getPrix(){
        return prix;
    }
    
    
    
     @Override
    public String toString() {
        return "Tente{" + "id=" + id + ", idCentreCamping=" + idCentreCamping + ", nom=" + nom + ", description=" + description + ", prix=" + prix + '}';

    }
    
    
}

