package br.com.matrixcorp.piadainterna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(final View v) {

            boolean piadaBoa = false;

            switch (v.getId()) {
                case R.id.button1:
                    piadaBoa = false;
                    break;
                case R.id.button2:
                    piadaBoa = true;
                    break;
            }

            Intent intent = new Intent(getApplicationContext(), ListarPiadistasActivity.class);
            Bundle b = new Bundle();

            b.putBoolean("piadaBoa", piadaBoa);

            intent.putExtras(b);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        setTitle(getResources().getString(R.string.main_activity_name));

        (findViewById(R.id.button1)).setOnClickListener(onClickListener);
        (findViewById(R.id.button2)).setOnClickListener(onClickListener);
    }
}
