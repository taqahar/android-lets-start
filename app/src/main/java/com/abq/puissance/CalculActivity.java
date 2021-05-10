package com.abq.puissance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculActivity extends AppCompatActivity {
    Button btCalculer;
    Button btAnnuler;
    Button btnReturn;
    EditText editA;
    EditText editN;
    TextView txtResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        btCalculer = findViewById(R.id.btn_calculer);
        btAnnuler = findViewById(R.id.btn_annuler);
        btnReturn = findViewById(R.id.btn_return);
        editA = findViewById(R.id.nombre_a);
        editN = findViewById(R.id.nombre_n);
        txtResultat = findViewById(R.id.nombre_apn);

        btnReturn.setOnClickListener(v -> {
            finish();
        });
        btCalculer.setOnClickListener(v -> {
            calcul();
        });

        btAnnuler.setOnClickListener(v -> {
            effacer();
        });

        editN.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                calcul();
            }
            return false;
        });
    }

    @SuppressLint("SetTextI18n")
    private void calcul() {
        if (editA.getText().toString().equals("") || editN.getText().toString().equals("")) {
            System.out.println("Erreur");
    //        txtResultat.setText("Veuillez svp definir une valeur");
            return;
        }
        Long a = Long.valueOf(editA.getText().toString());
        Long n = Long.valueOf(editN.getText().toString());
        Long resultat = calcPuissance(a, n);

        System.out.println(resultat.toString());
        txtResultat.setText(resultat.toString());

        editA.setSelected(true);
    }

    private Long calcPuissance(Long a, Long n) {
        Long result = 1L;
        for (int i = 1; i <= n; i++) {
            result = result * a;
        }
        return result;
    }

    @SuppressLint("SetTextI18n")
    private void effacer() {
        editA.setText("");
        editN.setText("");
        txtResultat.setText("RESULTAT");
    }
}
