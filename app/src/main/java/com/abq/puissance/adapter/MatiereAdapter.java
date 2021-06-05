package com.abq.puissance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abq.puissance.R;
import com.abq.puissance.models.Matiere;

import java.util.ArrayList;

public class MatiereAdapter extends BaseAdapter {
    private ArrayList<Matiere> listMatiere;
    private Context context;

    public MatiereAdapter() {}

    public MatiereAdapter(ArrayList<Matiere> listMatiere, Context context) {
        this.listMatiere = listMatiere;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listMatiere.size();
    }

    @Override
    public Object getItem(int position) {
        return listMatiere.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Matiere) this.getItem(position)).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MatiereHolder matiereHolder = new MatiereHolder();

        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.matiere_list_item, null);
        else matiereHolder = (MatiereHolder) convertView.getTag();


        matiereHolder.nomMatiere = convertView.findViewById(R.id.item_matiere_nom);
        matiereHolder.nomProfesseur = convertView.findViewById(R.id.item_matiere_professeur);
        matiereHolder.imageMatiere = convertView.findViewById(R.id.item_matiere_image);
        matiereHolder.typeMatiere = convertView.findViewById(R.id.item_matiere_type);

        matiereHolder.nomMatiere.setText(((Matiere) getItem(position)).getLibelle());
        matiereHolder.nomProfesseur.setText(((Matiere) getItem(position)).getEnseigant());
        matiereHolder.imageName = (((Matiere) getItem(position)).getImageName());
//        TODO: Resolve matiere image
//        matiereHolder.imageMatiere.setImageResource(((Matiere) getItem(position)).getImage());
        matiereHolder.typeMatiere.setText(((Matiere) getItem(position)).isFacultatif() ? "Facultatif" : "Obligatoire");

        convertView.setTag(matiereHolder);
        return convertView;
    }

    class MatiereHolder{
        TextView nomProfesseur;
        TextView nomMatiere;
        ImageView imageMatiere;
        TextView typeMatiere;
        String imageName;
    }
}
