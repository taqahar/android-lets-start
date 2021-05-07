package com.abq.puissance.classes;

public class Match {
    private Equipe equipe1;
    private Equipe equipe2;
    private String tempsAdditionnel;
    private int butEq1;
    private int butEq2;

    public Match(Equipe equipe1, Equipe equipe2, String tempsAdditionnel, int butEq1, int butEq2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.tempsAdditionnel = tempsAdditionnel;
        this.butEq1 = butEq1;
        this.butEq2 = butEq2;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public String getTempsAdditionnel() {
        return tempsAdditionnel;
    }

    public void setTempsAdditionnel(String tempsAdditionnel) {
        this.tempsAdditionnel = tempsAdditionnel;
    }

    public int getButEq1() {
        return butEq1;
    }

    public void setButEq1(int butEq1) {
        this.butEq1 = butEq1;
    }

    public int getButEq2() {
        return butEq2;
    }

    public void setButEq2(int butEq2) {
        this.butEq2 = butEq2;
    }
}
