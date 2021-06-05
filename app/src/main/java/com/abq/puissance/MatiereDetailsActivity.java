package com.abq.puissance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abq.puissance.models.Matiere;

public class MatiereDetailsActivity extends AppCompatActivity {

    TextView info;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere_details);
        info = findViewById(R.id.matiere_detaillee);
        img = findViewById(R.id.matiere_image);

        setEditExtras();
    }

    @SuppressLint("SetTextI18n")
    private void setEditExtras() {
        Bundle extras = getIntent().getExtras();
        Matiere matiere = extras.getParcelable("com.abq.puissance.models.Matiere");

        info.setText("id: " + matiere.getId() + "\n"
                + " libelle:" + matiere.getLibelle() + "\n"
                + " type:" + (matiere.isFacultatif() ? "obligatoire" : "facultatif") + "\n"
                + " enseignant:" + matiere.getEnseigant() + "\n"
        );

        String uri = matiere.getImageName();

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);

//        img.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(uri, null, getPackageName())));
    }
}