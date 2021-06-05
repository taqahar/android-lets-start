package com.abq.puissance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.abq.puissance.models.Matiere;
import com.abq.puissance.models.MesMatieres;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abq.puissance.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;
    private Button btn_puissance;
    private Button btn_matiere;
    private Button btn_menu;
    private Button btn_comming;

    private final static int MENU_AJOUTER = Menu.FIRST;
    private final static int MENU_SUPPRIMER = Menu.FIRST + 1;

    private Button btnSharedPrefs;
    private EditText editSharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        registerForContextMenu(btn_menu);

//        FloatingActionButton fab = binding.fab;

        btn_puissance.setOnClickListener(v -> {
            toPuissance();
        });

        btn_matiere.setOnClickListener(v -> {
            toMatiereForm();
        });

        btn_comming.setOnClickListener(v -> {
            Snackbar.make(v, "A venir...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        applySharedPreferences();

        btnSharedPrefs.setOnClickListener(v -> {
            saveMesPrefs();
        });
    }

    private void initViews() {
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        btn_puissance = findViewById(R.id.btn_puissance);
        btn_matiere = findViewById(R.id.btn_matiere);
        btn_comming = findViewById(R.id.btn_comming);
        btn_menu = findViewById(R.id.btn_menu);
        editSharedPrefs = findViewById(R.id.edit_shared_prefs);
        btnSharedPrefs = findViewById(R.id.btn_shared_prefs);

        setTitle("DevMobileApp");
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(menu.NONE, MENU_AJOUTER, Menu.NONE, "Ajouter un élément");
        menu.add(menu.NONE, MENU_SUPPRIMER, Menu.NONE, "Supprimer un élément");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case MENU_AJOUTER:
                Toast.makeText(this, "Ajout...", Toast.LENGTH_SHORT).show();
            case MENU_SUPPRIMER:
                Toast.makeText(this, "Suppression...", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void applySharedPreferences() {
//        SharedPreferences mesPrefs = getSharedPreferences("sharedPreferencesFile", Context.MODE_PRIVATE);
        SharedPreferences mesPrefs = getPreferences(Context.MODE_PRIVATE);
        String leCompteur = mesPrefs.getString("compteur", "0_0");
        if (!leCompteur.equals("0_0")) {
            editSharedPrefs.setText(String.valueOf(leCompteur));
        }
    }

    private void saveMesPrefs() {
        SharedPreferences mesPrefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mesPrefs.edit();
        editor.putString("compteur", editSharedPrefs.getText().toString());
        boolean verif = editor.commit();

        if (verif)
            Toast.makeText(this, "Enregistré", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
    }
}