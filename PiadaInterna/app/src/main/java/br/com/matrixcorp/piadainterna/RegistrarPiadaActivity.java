package br.com.matrixcorp.piadainterna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarPiadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piada_contada);

        Bundle b = getIntent().getExtras();

        if (b.getBoolean("piadaBoa")) {
            setTitle(getResources().getString(R.string.compartilha_boa_piada));

        } else {
            setTitle(getResources().getString(R.string.compartilha_piada_ruim));
        }

        ((TextView) findViewById(R.id.nomeDoPiadista)).setText(String.format("-- %1$s", b.getCharSequence("nomeDoPiadista")));

        addListenerOnButton();
    }

    private void addListenerOnButton() {

        Button cancelarPiada = (Button) findViewById(R.id.button3);
        Button postarPiada = (Button) findViewById(R.id.button4);

        cancelarPiada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        postarPiada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrarPiadaActivity.this, ((TextView) findViewById(R.id.piadaContada)).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
