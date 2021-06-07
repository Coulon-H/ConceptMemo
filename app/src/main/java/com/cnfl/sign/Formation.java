package com.cnfl.sign;

public class Formation {
    private String titre, description, lien, date_d, date_f, lieu;
    private int id;

    public Formation() {
        id = 0;
        titre = description = lien = date_d = date_f = lieu = "";
    }

    public Formation(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDate_f() {
        return date_f;
    }

    public void setDate_f(String date_f) {
        this.date_f = date_f;
    }

    public String getDate_d() {
        return date_d;
    }

    public void setDate_d(String date_d) {
        this.date_d = date_d;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
