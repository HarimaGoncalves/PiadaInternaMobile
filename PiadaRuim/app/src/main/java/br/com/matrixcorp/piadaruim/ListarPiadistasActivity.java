package br.com.matrixcorp.piadaruim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ListarPiadistasActivity extends AppCompatActivity {

    String[] listaDePiadistas;
    RadioGroup lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_piadistas);

        lista = (RadioGroup)findViewById(R.id.listaDepiadistas);
        listaDePiadistas = getResources().getStringArray(R.array.piadistas);

        ViewGroup hourButtonLayout = (ViewGroup)findViewById(R.id.listaDepiadistas);
        for(int i = 0; i< listaDePiadistas.length; i++) {
            RadioButton button = new RadioButton(this);
            button.setId(i);
            button.setText(listaDePiadistas[i]);
            hourButtonLayout.addView(button);
        }
    }

}
