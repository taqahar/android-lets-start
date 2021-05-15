package com.abq.puissance.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Matiere implements Parcelable {
    private int id;
    private String libelle;
    private boolean type;
    private String enseigant;
    private String image;

//    public static ArrayList<Matiere> mesMatieres;

    public Matiere() {
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeString(this.getLibelle());
        dest.writeBoolean(this.isType());
        dest.writeString(this.getEnseigant());
        dest.writeString(this.getImage());
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Matiere(Parcel parcel) {
        id = parcel.readInt();
        libelle = parcel.readString();
        type = parcel.readBoolean();
        enseigant = parcel.readString();
        image = parcel.readString();
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

    public boolean isType() {
        return type;
    }

    public String getEnseigant() {
        return enseigant;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setType(boolean obligatoire) {
        this.type = obligatoire;
    }

    public void setEnseigant(String enseigant) {
        this.enseigant = enseigant;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
