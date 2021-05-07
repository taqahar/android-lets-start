package com.abq.puissance.classes;

public class Equipe {
    private String nom;
    private String coach;

    public Equipe(String nom, String coach) {
        this.nom = nom;
        this.coach = coach;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
