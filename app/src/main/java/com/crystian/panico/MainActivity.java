package com.crystian.panico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crystian.panico.R;
import com.crystian.panico.model.PanicoConfig;

public class MainActivity extends AppCompatActivity {
    Button btnGps;
    TextView txtLatitude, txtLongitude;
    Location minhaLocalizacao ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude = (TextView) findViewById(R.id.txLatitude);
        txtLongitude = (TextView) findViewById(R.id.txLongitude);
        minhaLocalizacao = new Location("");
        btnGps = (Button) findViewById(R.id.btGPS);
        btnGps.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                pedirPermissoes();
            }
        });
    }



    public void EnviaSMS2(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                 ) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        else {
            try {
                EditText telefone = (EditText) findViewById(R.id.txTelefone);
                SmsManager smgr = SmsManager.getDefault();
                smgr.sendTextMessage(telefone.getText().toString(), null, "Teste Envio", null, null);
                Toast.makeText(MainActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void TesteLigar(View v) {
        EditText txTelefone = (EditText) findViewById(R.id.txTelefone);
        String numero = txTelefone.getText().toString();
        Uri uri = Uri.parse("tel:" + numero);

        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE},1);
            return;
        }
        startActivity(intent);

    }

    public void Ligar(String telefone) {
        String numero = telefone;
        Uri uri = Uri.parse("tel:" + numero);

        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE},1);
            return;
        }
        startActivity(intent);

    }


    private void pedirPermissoes() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
            configurarServico();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configurarServico();
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
    public void configurarServico(){
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    atualizar(location);
                    minhaLocalizacao = location;
                }

                public void onStatusChanged(String provider, int status, Bundle extras) { }

                public void onProviderEnabled(String provider) { }

                public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch(SecurityException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void atualizar(Location location)
    {

        Double latPoint = location.getLatitude();
        Double lngPoint = location.getLongitude();

        txtLatitude.setText(latPoint.toString());
        txtLongitude.setText(lngPoint.toString());
    }

    public void chamarConfig(View view){
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);

    }

    public void AcionaBotaoPanico(View v){
        PanicoConfig config = PanicoConfig.findById(PanicoConfig.class,(long) 1);
        Toast.makeText(this, "tel1:"+config.getTelefone1() + "tel2:"+config.getTelefone2() + "tel3:"+config.getTelefone3(), Toast.LENGTH_LONG).show();

        // Habilitar GPS e Pegar Posição
        pedirPermissoes();

        Double latPoint = minhaLocalizacao.getLatitude();
        Double lngPoint = minhaLocalizacao.getLongitude();


        // Enviar SMS
        String telefone1 = config.getTelefone1();
        String telefone2 = config.getTelefone2();
        String telefone3 = config.getTelefone3();

        String MensagemSMS = "ME AJUDEM!!! - Lat: "  + latPoint.toString() + "Log: " +lngPoint.toString();
        SmsManager smgr = SmsManager.getDefault();
        smgr.sendTextMessage(telefone1,null,MensagemSMS,null,null);
        Toast.makeText(this, "SMS tel1:"+config.getTelefone1(), Toast.LENGTH_LONG).show();
        smgr.sendTextMessage(telefone2,null,MensagemSMS,null,null);
        Toast.makeText(this, "SMS tel2:"+config.getTelefone2() , Toast.LENGTH_LONG).show();
        smgr.sendTextMessage(telefone3,null,MensagemSMS,null,null);
        Toast.makeText(this, "SMS tel3:"+config.getTelefone3(), Toast.LENGTH_LONG).show();

        // Ligar
        Ligar(telefone1);
        Toast.makeText(this, "Ligou tel1:"+config.getTelefone1(), Toast.LENGTH_LONG).show();
        Ligar(telefone2);
        Toast.makeText(this, "Ligou tel2:"+config.getTelefone2(), Toast.LENGTH_LONG).show();
        Ligar(telefone3);
        Toast.makeText(this, "Ligou tel3:"+config.getTelefone3(), Toast.LENGTH_LONG).show();

    }


}




