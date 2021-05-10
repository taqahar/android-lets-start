package com.abq.puissance;

import android.content.Intent;
import android.os.Bundle;

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
    private Button btn_coming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        btn_puissance = findViewById(R.id.btn_puissance);
        btn_coming = findViewById(R.id.btn_coming);

//        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
//        toolBarLayout.setTitle(getTitle());

//        FloatingActionButton fab = binding.fab;


        btn_puissance.setOnClickListener(v -> {
            toPuissance();
        });


        btn_coming.setOnClickListener(v -> {
            Snackbar.make(v, "A venir...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    private void toPuissance() {
        Intent puissance_page = new Intent(this, CalculActivity.class);
        startActivity(puissance_page);
    }
}