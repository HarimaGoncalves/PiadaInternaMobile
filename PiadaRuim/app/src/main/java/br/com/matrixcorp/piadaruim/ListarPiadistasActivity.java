package br.com.matrixcorp.piadaruim;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ListarPiadistasActivity extends AppCompatActivity {

    String[] listaDePiadistas;
    RadioGroup lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_piadistas);

        Bundle b = getIntent().getExtras();

        if(b.getBoolean("piadaBoa")) {
            findViewById(R.id.avaliarPiada).setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            ((Button)findViewById(R.id.avaliarPiada)).setText(getResources().getString(R.string.aplaudir));
            setTitle(getResources().getString(R.string.piadaBoa));
        }else {
            findViewById(R.id.avaliarPiada).setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            ((Button)findViewById(R.id.avaliarPiada)).setText(getResources().getString(R.string.vaiar));
            setTitle(getResources().getString(R.string.piadaRuim));
        }
        ((Button)findViewById(R.id.avaliarPiada)).setTextColor(ContextCompat.getColor(this, R.color.white));
        ((Button)findViewById(R.id.avaliarPiada)).setPadding(5, 0, 5, 0);

        lista = (RadioGroup)findViewById(R.id.listaDepiadistas);
        listaDePiadistas = getResources().getStringArray(R.array.piadistas);

        ViewGroup layout = (ViewGroup)findViewById(R.id.listaDepiadistas);
        for (int i = 0; i < listaDePiadistas.length; i++) {
            RadioButton button = new RadioButton(this);
            button.setId(i);
            button.setText(listaDePiadistas[i]);
            layout.addView(button);
        }

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        lista = (RadioGroup)findViewById(R.id.listaDepiadistas);

        if(lista.getCheckedRadioButtonId() != 0) {
            Button btnDisplay = (Button) findViewById(R.id.avaliarPiada);

            assert btnDisplay != null;
            btnDisplay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    int selectedId = lista.getCheckedRadioButtonId();

                    RadioButton radio = (RadioButton) findViewById(selectedId);

                    Toast.makeText(ListarPiadistasActivity.this, radio.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
