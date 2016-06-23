package br.com.matrixcorp.piadaruim;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {

    boolean piadaBoa;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
    }

//    @Override
//    public void onClick(View v){
//
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        Bundle b = new Bundle();
//
////        b.putByte("piadaBoa", );
//
//    }

//    private void alert(String title, String message) {
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//        alertDialog.setTitle(title);
//        alertDialog.setMessage(message);
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alertDialog.show();
//    }

    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.button1:
                    piadaBoa = false;
                    break;
                case R.id.button2:
                    piadaBoa = true;
                    break;
            }
//            alert("botao acionado", piadaBoa? "piada boa":"piada ruim");
        }
    };


}
