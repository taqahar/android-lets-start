package com.abq.puissance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.abq.puissance.models.Matiere;
import com.abq.puissance.models.MesMatieres;
import com.google.android.material.snackbar.Snackbar;

public class MatiereFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText id;
    EditText libelle;
    boolean type;
    RadioButton facultatif;
    RadioButton obligatoire;
    Spinner enseignant_spinner;
    String enseignantValue;
    Spinner image_spinner;
    String imageValue;
    Button btn_enregistrer;
    Button btn_effacer;
    Button btn_rechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere_form);

        initViews();
        adaptSpinners();
        initEvents();
    }

    private void initViews() {
        id = findViewById(R.id.matiere_id);
        libelle = findViewById(R.id.matiere_libelle);
        facultatif = findViewById(R.id.radio_facultatif);
        obligatoire = findViewById(R.id.radio_obligatoire);
        enseignant_spinner = findViewById(R.id.enseignant_spinner);
        image_spinner = findViewById(R.id.image_spinner);
        btn_enregistrer = findViewById(R.id.btn_enregistrer);
        btn_effacer = findViewById(R.id.btn_effacer);
        btn_rechercher = findViewById(R.id.btn_rechercher);
    }

    private void adaptSpinners() {
        ArrayAdapter<CharSequence> enseignant_adapter = ArrayAdapter.createFromResource(this, R.array.enseignants_names, android.R.layout.simple_spinner_item);
        enseignant_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enseignant_spinner.setAdapter(enseignant_adapter);

        ArrayAdapter<CharSequence> image_adapter = ArrayAdapter.createFromResource(this, R.array.images_names, android.R.layout.simple_spinner_item);
        image_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        image_spinner.setAdapter(image_adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        enseignantValue = enseignant_spinner.getSelectedItem().toString();
        imageValue = image_spinner.getSelectedItem().toString();
    }

    private void initEvents() {
        enseignant_spinner.setOnItemSelectedListener(this);
        image_spinner.setOnItemSelectedListener(this);
        btn_enregistrer.setOnClickListener(v -> {
//            TODO: faire les controles
            enregistrer();
        });

        btn_effacer.setOnClickListener(v -> {
            effacer();
        });

        btn_rechercher.setOnClickListener(v -> {
            rechercher(Integer.parseInt(id.getText().toString()));
        });

    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_facultatif:
                if (checked)
                    type = false;
                break;
            case R.id.radio_obligatoire:
                if (checked)
                    type = true;
                break;
            default:
                break;
        }
    }

    private void enregistrer() {
        Matiere matiere = new Matiere();
        matiere.setId(Integer.parseInt(id.getText().toString()));
        matiere.setLibelle(libelle.getText().toString());
        matiere.setType(type);
        matiere.setEnseigant(enseignantValue);
        matiere.setImage("@drawable/" + imageValue);

        Log.i("LOGQ", "id: " + matiere.getId() + "\n"
                + " libelle:" + matiere.getLibelle() + "\n"
                + " type:" + matiere.isType() + "\n"
                + " enseignant:" + matiere.getEnseigant() + "\n"
                + " image:" + matiere.getImage() + "\n"
        );

        MesMatieres.listMatieres.add(matiere);
    }
    private void effacer() {
        id.setText("");
        libelle.setText("");
        facultatif.toggle();
        enseignant_spinner.setSelection(0);
        image_spinner.setSelection(0);
    }

    private void rechercher(int id) {
        for (Matiere matiere : MesMatieres.listMatieres) {
            if (matiere.getId() == id) {
                Log.e("xxxxxxxxxx", matiere.getId() + " " + matiere.getLibelle() + " " + matiere.isType() + " " + matiere.getEnseigant() + " " + matiere.getImage());
                Intent details_matiere = new Intent(this, MatiereDetailsActivity.class);
                details_matiere.putExtra("com.abq.puissance.models.Matiere", matiere);
                startActivity(details_matiere);
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e("test", view.getId()+"");
        enseignantValue = enseignant_spinner.getSelectedItem().toString();
        imageValue = image_spinner.getSelectedItem().toString();

//        switch (view.getId()) {
//            case R.id.enseignant_spinner:
//                Log.e("test1", "enseignant");
//                enseignantValue = (String) parent.getItemAtPosition(position);
//                Snackbar.make(view, "E", Snackbar.LENGTH_LONG).show();
//                break;
//            case R.id.image_spinner:
//                Log.e("test2", "image");
//                imageValue = (String) parent.getItemAtPosition(position);
//                Snackbar.make(view, "I", Snackbar.LENGTH_LONG).show();
//                break;
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}