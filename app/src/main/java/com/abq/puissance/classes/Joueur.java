package com.abq.puissance.classes;

public class Joueur {
    private String matricule;
    private String nom;
    private String prenom;
    private int numero;
    private String poste;
    private Equipe equipe;

    public Joueur(String matricule, String nom, String prenom, int numero, String poste, Equipe equipe) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.poste = poste;
        this.equipe = equipe;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
