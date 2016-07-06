package br.com.matrixcorp.piadainterna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import domain.Joker;

public class ListarPiadistasActivity extends AppCompatActivity {

    String[] listaDePiadistas;
    RadioGroup lista;
    boolean aPiadaFoiBoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_piadistas);

        Bundle b = getIntent().getExtras();

        aPiadaFoiBoa = b.getBoolean("piadaBoa");
		//Verificando nível da piada
        if (aPiadaFoiBoa) {
            findViewById(R.id.avaliarPiada).setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            ((Button) findViewById(R.id.avaliarPiada)).setText(getResources().getString(R.string.aplaudir));
            setTitle(getResources().getString(R.string.piadaBoa));
        } else {
            findViewById(R.id.avaliarPiada).setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            ((Button) findViewById(R.id.avaliarPiada)).setText(getResources().getString(R.string.vaiar));
            setTitle(getResources().getString(R.string.piadaRuim));
        }
        ((Button) findViewById(R.id.avaliarPiada)).setTextColor(ContextCompat.getColor(this, R.color.white));

        lista = (RadioGroup) findViewById(R.id.listaDepiadistas);


        Firebase db = new Firebase(getResources().getString(R.string.firebase_jokers_url));
        getJokers(db, new Runnable() {
            @Override
            public void run() {
                ViewGroup layout = (ViewGroup) findViewById(R.id.listaDepiadistas);
                for (int i = 0; i < listaDePiadistas.length; i++) {
                    RadioButton button = new RadioButton(ListarPiadistasActivity.this);
                    button.setId(i);
                    button.setText(listaDePiadistas[i]);
                    layout.addView(button);
                }
            }
        });

        addListenerOnButton();
    }

    private void addListenerOnButton() {

        lista = (RadioGroup) findViewById(R.id.listaDepiadistas);

        Button btnDisplay = (Button) findViewById(R.id.avaliarPiada);

        assert btnDisplay != null;
        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = lista.getCheckedRadioButtonId();

                RadioButton radio = (RadioButton) findViewById(selectedId);
                if (radio == null) {
                    Toast.makeText(ListarPiadistasActivity.this, "Piadista inválido!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), RegistrarPiadaActivity.class);
                    Bundle b = new Bundle();

                    b.putCharSequence("nomeDoPiadista", radio.getText());
                    b.putBoolean("piadaBoa", aPiadaFoiBoa);

                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
    }

    public void getJokers(Firebase db, final Runnable runnable) {

        final ArrayList<String> tmpRetorno = new ArrayList<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Joker tmpJoker = child.getValue(Joker.class);

                    tmpRetorno.add(tmpJoker.getName());
                }
                listaDePiadistas = new String[tmpRetorno.size()];
                listaDePiadistas = tmpRetorno.toArray(listaDePiadistas);
                runnable.run();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //Toast.makeText(ListarPiadistasActivity.this, firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
