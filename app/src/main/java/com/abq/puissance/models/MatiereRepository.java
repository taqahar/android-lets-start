package com.abq.puissance.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MatiereRepository {
    public boolean save(SQLiteDatabase database, Matiere matiere) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", matiere.getId());
        contentValues.put("libelle", matiere.getLibelle());
        contentValues.put("facultatif", matiere.isFacultatif());
        contentValues.put("enseignant", matiere.getEnseigant());
        contentValues.put("image_name", matiere.getImageName());
        long nbLignes = database.insert("matiere", null, contentValues);
        return (nbLignes > 0);
    }

    public List<Matiere> findAll(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("SELECT * FROM matiere", null);
        List<Matiere> maListe = new ArrayList<>();
        Matiere matiere;
        while (cursor.moveToNext()) {
            matiere = new Matiere();
            matiere.setId(cursor.getInt(0));
            matiere.setLibelle(cursor.getString(1));
            matiere.setFacultatif(cursor.getInt(2) == 1);
            matiere.setEnseigant(cursor.getString(3));
            matiere.setImageName(cursor.getString(4));

            maListe.add(matiere);
        }
        return maListe;
    }
}
