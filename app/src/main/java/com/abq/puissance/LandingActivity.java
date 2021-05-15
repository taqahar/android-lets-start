package com.abq.puissance;

import android.content.Intent;
import android.os.Bundle;

import com.abq.puissance.models.Matiere;
import com.abq.puissance.models.MesMatieres;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import com.abq.puissance.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;
    private Button btn_puissance;
    private Button btn_matiere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        btn_puissance = findViewById(R.id.btn_puissance);
        btn_matiere = findViewById(R.id.btn_matiere);

//        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
//        toolBarLayout.setTitle(getTitle());

//        FloatingActionButton fab = binding.fab;


        btn_puissance.setOnClickListener(v -> {
            toPuissance();
        });

        btn_matiere.setOnClickListener(v -> {
            toMatiereForm();
        });


//        btn_coming.setOnClickListener(v -> {
//            Snackbar.make(v, "A venir...", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//        });
    }

    private void toPuissance() {
        Intent puissance_page = new Intent(this, CalculActivity.class);
        Matiere matiere = new Matiere();
        matiere.setId(1);
        matiere.setLibelle("Développement mobile");

        puissance_page.putExtra("a", 2L);
        puissance_page.putExtra("com.abq.puissance.models.Matiere", matiere);

        startActivity(puissance_page);
    }

    private void toMatiereForm() {
        Intent matiere_page = new Intent(this, MatiereFormActivity.class);
        startActivity(matiere_page);
    }
}