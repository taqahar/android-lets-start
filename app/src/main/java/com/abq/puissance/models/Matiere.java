package com.abq.puissance.models;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.RequiresApi;

public class Matiere implements Parcelable {
    private int id;
    private String libelle;
    private boolean facultatif;
    private String enseigant;
    private String imageName;
    private Drawable image;

//    public static ArrayList<Matiere> mesMatieres;

    public Matiere() {
    }

    public Matiere(int id, String libelle, boolean type, String enseigant, String imageName) {
        this.id = id;
        this.libelle = libelle;
        this.facultatif = type;
        this.enseigant = enseigant;
        this.imageName = imageName;
//        this.image = new AppCompatActivity().getResources().getDrawable(new AppCompatActivity().getResources().getIdentifier(imageName, null, new AppCompatActivity().getPackageName()));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeString(this.getLibelle());
        dest.writeBoolean(this.isFacultatif());
        dest.writeString(this.getEnseigant());
        dest.writeString(this.getImageName());
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Matiere(Parcel parcel) {
        id = parcel.readInt();
        libelle = parcel.readString();
        facultatif = parcel.readBoolean();
        enseigant = parcel.readString();
        imageName = parcel.readString();
    }

    public static final Creator<Matiere> CREATOR = new Creator<Matiere>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Matiere createFromParcel(Parcel source) {
            return new Matiere(source);
        }

        @Override
        public Matiere[] newArray(int size) {
            return new Matiere[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public boolean isFacultatif() {
        return facultatif;
    }

    public String getEnseigant() {
        return enseigant;
    }

    public String getImageName() {
        return imageName.toLowerCase();
    }

    public Drawable getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setFacultatif(boolean obligatoire) {
        this.facultatif = obligatoire;
    }

    public void setEnseigant(String enseigant) {
        this.enseigant = enseigant;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
