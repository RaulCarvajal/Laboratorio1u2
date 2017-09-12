package com.example.chapa.laboratorio1u2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nombre,telefono,direccion,fecha,horaini,horafin,platillos,postres;
    private CheckBox manteles;
    private SeekBar meseros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre =(EditText) findViewById(R.id.txtNombre);
        telefono =(EditText) findViewById(R.id.txtTel);
        direccion =(EditText) findViewById(R.id.txtDireccion);
        fecha =(EditText) findViewById(R.id.txtFecha);
        horaini=(EditText) findViewById(R.id.txtHoraIni);
        horafin=(EditText) findViewById(R.id.txtHoraFin);
        platillos=(EditText) findViewById(R.id.txtPlatillos);
        postres=(EditText) findViewById(R.id.txtPostres);
        manteles=(CheckBox) findViewById(R.id.chkBox);
        meseros=(SeekBar) findViewById(R.id.seekBar);

        //Guardar
        findViewById(R.id.btnGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        //Leer
        findViewById(R.id.btnLeer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperar();
            }
        });
    }
    private void guardar(){
        SharedPreferences datos=getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=datos.edit();

        String nom=nombre.getText().toString();
        String tel=telefono.getText().toString();
        String dir=direccion.getText().toString();
        String fec=fecha.getText().toString();
        String hini=horaini.getText().toString();
        String hfin=horafin.getText().toString();
        String plat=platillos.getText().toString();
        String post=postres.getText().toString();
        Boolean mant=manteles.isChecked();
        int mese=meseros.getProgress();


        editor.putString("nombre",nom);
        editor.putString("telefono",tel);
        editor.putString("dir",dir);
        editor.putString("fec",fec);
        editor.putString("hini",hini);
        editor.putString("hfin",hfin);
        editor.putString("plat",plat);
        editor.putString("post",post);
        editor.putBoolean("mant",mant);
        editor.putInt("mese",mese);


        editor.commit();

        nombre.setText("");
        telefono.setText("");
        direccion.setText("");
        Toast.makeText(getApplicationContext(),"Saved"+getEmojiByUnicode(0x1F44D),Toast.LENGTH_SHORT).show();
    }

    private void recuperar(){
        SharedPreferences datos=getSharedPreferences("data", Context.MODE_PRIVATE);

        String nom=datos.getString("nombre","");
        String tel=datos.getString("telefono","");
        String dir=datos.getString("dir","");
        String fec=datos.getString("fec","");
        String hini=datos.getString("hini","");
        String hfin=datos.getString("hfin","");
        String plat=datos.getString("plat","");
        String post=datos.getString("post","");
        Boolean mant=datos.getBoolean("mant",false);
        int mese=datos.getInt("mese",5);

        nombre.setText(nom);
        telefono.setText(tel);
        direccion.setText(dir);
        fecha.setText(fec);
        horaini.setText(hini);
        horafin.setText(hfin);
        platillos.setText(plat);
        postres.setText(post);
        manteles.setChecked(mant);
        meseros.setProgress(mese);

        Toast.makeText(getApplicationContext(),"Recuperado"+getEmojiByUnicode(0x1F44D),Toast.LENGTH_SHORT).show();
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
