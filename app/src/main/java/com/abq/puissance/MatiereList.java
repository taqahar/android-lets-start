package com.abq.puissance;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abq.puissance.adapter.MatiereAdapter;
import com.abq.puissance.models.Matiere;
import com.abq.puissance.models.MesMatieres;

public class MatiereList extends AppCompatActivity {
    private MatiereAdapter matiereAdapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matiere_list);

//        initData();
        initViews();
    }


    private void initViews() {
        matiereAdapter = new MatiereAdapter(MesMatieres.listMatieres, this);
        listView = findViewById(R.id.matiere_list);
        Log.e("INFO", "x0x0");
        listView.setAdapter(matiereAdapter);
        matiereAdapter.notifyDataSetChanged();
    }

    private static void initData() {
        MesMatieres.listMatieres.add(new Matiere(1, "Maths", false, "M. Abalo", "maths"));
        MesMatieres.listMatieres.add(new Matiere(2, "SVT", true, "M. Koffi", "svt"));
        MesMatieres.listMatieres.add(new Matiere(3, "Physique", false, "Mme Reine", "physique"));
        MesMatieres.listMatieres.add(new Matiere(4, "Informatique", false, "M. Paul", "informatique"));
        MesMatieres.listMatieres.add(new Matiere(5, "Physique", true, "Mme Carine", "physique"));
    }
}
