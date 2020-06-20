package com.crystian.panico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.crystian.panico.model.PanicoConfig;

public class ConfigActivity extends AppCompatActivity {
    private PanicoConfig config;
    private EditText telefone1 ;
    private CheckBox telefone1Ligar ;
    private CheckBox telefone1Sms ;

    private EditText telefone2;
    private CheckBox telefone2Ligar;
    private CheckBox telefone2Sms ;

    private EditText telefone3 ;
    private CheckBox telefone3Ligar ;
    private CheckBox telefone3Sms ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

//        config =  PanicoConfig.findById(PanicoConfig.class, 1);



    }

    public void SetCampos(View v){
        telefone1 = (EditText) findViewById(R.id.txTelefone1);
        telefone1Ligar = (CheckBox) findViewById(R.id.cbTelefone1Ligar);
        telefone1Sms = (CheckBox) findViewById(R.id.cbTelefone1Sms);

        telefone2 = (EditText) findViewById(R.id.txTelefone2);
        telefone2Ligar = (CheckBox) findViewById(R.id.cbTelefone2Ligar);
        telefone2Sms = (CheckBox) findViewById(R.id.cbTelefone2Sms);

        telefone3 = (EditText) findViewById(R.id.txTelefone2);
        telefone3Ligar = (CheckBox) findViewById(R.id.cbTelefone2Ligar);
        telefone3Sms = (CheckBox) findViewById(R.id.cbTelefone2Sms);

    }
        public void GravarConf(View view) {

        config.setTelefone1(telefone1.getText().toString());
        config.setTelefone1_ligar(telefone1Ligar.isChecked());
        config.setTelefone1_sms(telefone1Sms.isChecked());
        config.setTelefone2(telefone2.getText().toString());
        config.setTelefone2_ligar(telefone2Ligar.isChecked());
        config.setTelefone2_sms(telefone2Sms.isChecked());
        config.setTelefone3(telefone3.getText().toString());
        config.setTelefone3_ligar(telefone3Ligar.isChecked());
        config.setTelefone3_sms(telefone3Sms.isChecked());

        config.save();
        Toast.makeText(this,"Configurações armazenadas com sucesso!!!"  + config.getId() , Toast.LENGTH_LONG ).show();
        finish();
    }
}
