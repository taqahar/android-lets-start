package com.abq.puissance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.abq.puissance.models.DBHelper;
import com.abq.puissance.models.Matiere;
import com.abq.puissance.models.MatiereRepository;
import com.abq.puissance.models.MesMatieres;

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
    Button btn_toListMatieres;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere_form);

        dbHelper = new DBHelper(this);

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
        btn_toListMatieres = findViewById(R.id.btn_liste_matieres);
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
        loadFromDB();
    }

    private void initEvents() {
        enseignant_spinner.setOnItemSelectedListener(this);
        image_spinner.setOnItemSelectedListener(this);
        btn_enregistrer.setOnClickListener(v -> {
//            TODO: faire les controles
            enregistrer(true);
        });

        btn_effacer.setOnClickListener(v -> {
            effacer();
        });

        btn_rechercher.setOnClickListener(v -> {
            rechercher(Integer.parseInt(id.getText().toString()));
        });

        btn_toListMatieres.setOnClickListener(v -> {
            afficherListe();
        });

    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_facultatif:
                if (checked)
                    type = true;
                break;
            case R.id.radio_obligatoire:
                if (checked)
                    type = false;
                break;
            default:
                break;
        }
    }


    private void enregistrer(boolean isDB) {
        Matiere matiere = new Matiere();
        matiere.setId(Integer.parseInt(id.getText().toString()));
        matiere.setLibelle(libelle.getText().toString());
        matiere.setFacultatif(type);
        matiere.setEnseigant(enseignantValue);
        matiere.setImageName("@drawable/" + imageValue);

        Log.i("LOGQ", "id: " + matiere.getId() + "\n"
                + " libelle:" + matiere.getLibelle() + "\n"
                + " type:" + matiere.isFacultatif() + "\n"
                + " enseignant:" + matiere.getEnseigant() + "\n"
                + " image:" + matiere.getImageName() + "\n"
        );

        if (isDB) {
            MatiereRepository matiereRepository = new MatiereRepository();
            Log.i("SAVING MATIERE", "" + matiereRepository.save(dbHelper.getDatabase(), matiere));
        }

        MesMatieres.listMatieres.add(matiere);

        Toast.makeText(this,"Matiere enregistrée", Toast.LENGTH_SHORT).show();
    }

    private void loadFromDB() {
        MatiereRepository matiereRepository = new MatiereRepository();
        MesMatieres.listMatieres.addAll(matiereRepository.findAll(dbHelper.getDatabase()));
    }

    private void effacer() {
        id.setText("");
        libelle.setText("");
        facultatif.toggle();
        enseignant_spinner.setSelection(0);
        image_spinner.setSelection(0);
        Toast.makeText(this,"Effacé", Toast.LENGTH_SHORT).show();
    }

    private void rechercher(int id) {
        boolean wasFound = false;
        for (Matiere matiere : MesMatieres.listMatieres) {
            if (matiere.getId() == id) {
                wasFound = true;
                Log.e("xxxxxxxxxx", matiere.getId() + " " + matiere.getLibelle() + " " + matiere.isFacultatif() + " " + matiere.getEnseigant() + " " + matiere.getImageName());
                Intent details_matiere = new Intent(this, MatiereDetailsActivity.class);
                details_matiere.putExtra("com.abq.puissance.models.Matiere", matiere);
                startActivity(details_matiere);
            }
        }
        if (!wasFound) {
            Toast.makeText(this,"Matiere non trouvée", Toast.LENGTH_SHORT).show();
        }
    }

    private void afficherListe() {
        Intent listePage = new Intent(this, MatiereList.class);
        startActivity(listePage);
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