package com.crystian.panico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.crystian.panico.model.PanicoConfig;

import java.math.BigDecimal;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        verificaExisteRegistro();
    }

    public void verificaExisteRegistro() {
        Long idLong = new Long(1);
        PanicoConfig conf = PanicoConfig.findById(PanicoConfig.class, idLong);
        if (conf != null) {

            EditText telefone1 = (EditText) findViewById(R.id.txTelefone1);
            telefone1.setText(conf.getTelefone1());
            CheckBox checkBoxTel1Ligar = (CheckBox) findViewById(R.id.checkBoxTel1Ligar);
            checkBoxTel1Ligar.setChecked(conf.getTelefone1_ligar());
            CheckBox checkBoxTel1SMS = (CheckBox) findViewById(R.id.checkBoxTel1Sms);
            checkBoxTel1SMS.setChecked(conf.getTelefone1_sms());

            EditText telefone2 = (EditText) findViewById(R.id.txTelefone2);
            telefone2.setText(conf.getTelefone2());
            CheckBox checkBoxTel2Ligar = (CheckBox) findViewById(R.id.checkBoxTel2Ligar);
            checkBoxTel2Ligar.setChecked(conf.getTelefone2_ligar());
            CheckBox checkBoxTel2SMS = (CheckBox) findViewById(R.id.checkBoxTel2Sms);
            checkBoxTel2SMS.setChecked(conf.getTelefone2_sms());

            EditText telefone3 = (EditText) findViewById(R.id.txTelefone3);
            telefone3.setText(conf.getTelefone3());
            CheckBox checkBoxTel3Ligar = (CheckBox) findViewById(R.id.checkBoxTel3Ligar);
            checkBoxTel3Ligar.setChecked(conf.getTelefone3_ligar());
            CheckBox checkBoxTel3SMS = (CheckBox) findViewById(R.id.checkBoxTel3Sms);
            checkBoxTel3SMS.setChecked(conf.getTelefone3_sms());

            EditText textoMenssagem = (EditText) findViewById(R.id.txMensagem);
            textoMenssagem.setText(conf.getTextoMensagem());

            EditText campoTempo = (EditText) findViewById(R.id.campoTempo);
            campoTempo.setText(conf.getTempoReenvio());


        }
    }

    public void gravarConf(View view) {
        Long idLong = new Long(1);
        PanicoConfig conf = PanicoConfig.findById(PanicoConfig.class, idLong);

        EditText telefone1 = (EditText) findViewById(R.id.txTelefone1);
        CheckBox checkBoxTel1Ligar = (CheckBox) findViewById(R.id.checkBoxTel1Ligar);
        CheckBox checkBoxTel1SMS = (CheckBox) findViewById(R.id.checkBoxTel1Sms);

        EditText telefone2 = (EditText) findViewById(R.id.txTelefone2);
        CheckBox checkBoxTel2Ligar = (CheckBox) findViewById(R.id.checkBoxTel2Ligar);
        CheckBox checkBoxTel2SMS = (CheckBox) findViewById(R.id.checkBoxTel2Sms);

        EditText telefone3 = (EditText) findViewById(R.id.txTelefone3);
        CheckBox checkBoxTel3Ligar = (CheckBox) findViewById(R.id.checkBoxTel3Ligar);
        CheckBox checkBoxTel3SMS = (CheckBox) findViewById(R.id.checkBoxTel3Sms);

        EditText textoMenssagem = (EditText) findViewById(R.id.txMensagem);

        EditText tempoReenvio = (EditText) findViewById(R.id.campoTempo);

        if (conf != null) {

            conf.setTelefone1(telefone1.getText().toString());
            conf.setTelefone1_ligar(checkBoxTel1Ligar.isChecked());
            conf.setTelefone1_sms(checkBoxTel1SMS.isChecked());

            conf.setTelefone2(telefone2.getText().toString());
            conf.setTelefone2_ligar(checkBoxTel2Ligar.isChecked());
            conf.setTelefone2_sms(checkBoxTel2SMS.isChecked());

            conf.setTelefone3(telefone3.getText().toString());
            conf.setTelefone3_ligar(checkBoxTel3Ligar.isChecked());
            conf.setTelefone3_sms(checkBoxTel3SMS.isChecked());

            conf.setTextoMensagem(textoMenssagem.getText().toString());

            conf.setTempoReenvio(tempoReenvio.getText().toString());

            conf.save();
            Toast.makeText(this, "Configurações armazenadas com sucesso!!!" + conf.getId(), Toast.LENGTH_LONG).show();

        } else {

            PanicoConfig config = new PanicoConfig();

            config.setTelefone1(telefone1.getText().toString());
            config.setTelefone1_ligar(checkBoxTel1Ligar.isChecked());
            config.setTelefone1_sms(checkBoxTel1SMS.isChecked());

            config.setTelefone2(telefone2.getText().toString());
            config.setTelefone2_ligar(checkBoxTel2Ligar.isChecked());
            config.setTelefone2_sms(checkBoxTel2SMS.isChecked());

            config.setTelefone3(telefone3.getText().toString());
            config.setTelefone3_ligar(checkBoxTel3Ligar.isChecked());
            config.setTelefone3_sms(checkBoxTel3SMS.isChecked());

            config.setTextoMensagem(textoMenssagem.getText().toString());
            config.setTempoReenvio(tempoReenvio.getText().toString());

            config.save();
            Toast.makeText(this, "Configurações armazenadas com sucesso!!!" + config.getId(), Toast.LENGTH_LONG).show();
        }

        finish();

    }
}