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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


    }

        public void GravarConf(View view) {
        EditText telefone1 = (EditText) findViewById(R.id.txTelefone1);
        CheckBox telefone1Ligar = (CheckBox) findViewById(R.id.cbTelefone1Ligar);
        CheckBox telefone1Sms = (CheckBox) findViewById(R.id.cbTelefone1Sms);

        EditText telefone2 = (EditText) findViewById(R.id.txTelefone2);
        CheckBox telefone2Ligar = (CheckBox) findViewById(R.id.cbTelefone2Ligar);
        CheckBox telefone2Sms = (CheckBox) findViewById(R.id.cbTelefone2Sms);

        EditText telefone3 = (EditText) findViewById(R.id.txTelefone2);
        CheckBox telefone3Ligar = (CheckBox) findViewById(R.id.cbTelefone2Ligar);
        CheckBox telefone3Sms = (CheckBox) findViewById(R.id.cbTelefone2Sms);

        PanicoConfig config =  new PanicoConfig();
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
