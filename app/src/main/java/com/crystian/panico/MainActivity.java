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
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crystian.panico.R;
import com.crystian.panico.model.PanicoConfig;


public class MainActivity extends AppCompatActivity {
    Button btnGps, btnPanico;
    TextView txtLatitude, txtLongitude;
    Location minhaLocalizacao ;
    String telefone1 ;
    String telefone2 ;
    String telefone3;
    TelephonyManager mTelephonyManager;
    private MyPhoneCallListener mListener;
    public static final int REQUEST_CODE = 1;
    private long inicio = 0;
    private long termino = 0;
    public Boolean botaoAcionado = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude = (TextView) findViewById(R.id.txLatitude);
        txtLongitude = (TextView) findViewById(R.id.txLongitude);
        // minhaLocalizacao = new Location("");
        btnGps = (Button) findViewById(R.id.btGPS);
        btnGps.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                pedirPermissoes();
            }
        });

        btnPanico = (Button) findViewById(R.id.btPanico);


        btnPanico.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Boolean botaoApertado = false;

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //botaoApertado = true;
                    inicio = event.getEventTime();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        //botaoApertado = false;
                        termino = event.getEventTime();

                }
                if (termino - inicio> 15000){
                    Log.d("TOUCH", "Maior que 15'");
                    //AcionaBotaoPanico();
                    // set flag botao apertado
                }else {
                    Log.d("TOUCH", "Menor que 15'");
                    inicio = 0;
                    termino = 0;
                }

                return true;
            }

        });

        // Create a telephony manager.
        mTelephonyManager = (TelephonyManager)  getSystemService(TELEPHONY_SERVICE);
        if (isTelephonyEnabled()) {
            Log.d("TAG", "telephony_enabled");
            checkForPhonePermission();
            // Register the PhoneStateListener to monitor phone activity.
            mListener = new MyPhoneCallListener();
            mTelephonyManager.listen(mListener,
                    PhoneStateListener.LISTEN_CALL_STATE);

        } else {
            Toast.makeText(this,
                    "telephony_not_enabled",
                    Toast.LENGTH_LONG).show();
            Log.d("TAG","telephony_not_enabled");
            // Disable the call button.
           // disableCallButton();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isTelephonyEnabled()) {
            mTelephonyManager.listen(mListener,
                    PhoneStateListener.LISTEN_NONE);
        }
    }

    private boolean isTelephonyEnabled() {
        if (mTelephonyManager != null) {
            if (mTelephonyManager.getSimState() ==
                    TelephonyManager.SIM_STATE_READY) {
                return true;
            }
        }
        return false;
    }

    private void checkForPhonePermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            // Permission not yet granted. Use requestPermissions().
            Log.d("TAG", "permission_not_granted");
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CALL_PHONE},
//                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            // Permission already granted.
        }
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
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            // Permission not yet granted. Use requestPermissions().
            Log.d("TAG", "permission_not_granted");
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.CALL_PHONE},
//                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            String numero = telefone;
            Uri uri = Uri.parse("tel:" + numero);

            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE},1);
                return;
            }
            Integer requestResult;
            //startActivityForResult(intent,requestResult);
            startActivityForResult(intent, REQUEST_CODE);
        }

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

                    Double latPoint = minhaLocalizacao.getLatitude();
                    Double lngPoint = minhaLocalizacao.getLongitude();


                    String MensagemSMS = "ME AJUDEM!!! - Lat: "  + latPoint.toString() + "Log: " +lngPoint.toString();
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(telefone1,null,MensagemSMS,null,null);
                   // Toast.makeText(this, "SMS tel1:"+ telefone1.toString(), Toast.LENGTH_LONG).show();
                    smgr.sendTextMessage(telefone2,null,MensagemSMS,null,null);
                   // Toast.makeText(this, "SMS tel2:" + telefone2.toString() , Toast.LENGTH_LONG).show();
                    smgr.sendTextMessage(telefone3,null,MensagemSMS,null,null);
                   // Toast.makeText(this, "SMS tel3:"+telefone3.toString() , Toast.LENGTH_LONG).show();



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

    public void AcionaBotaoPanico(){

        botaoAcionado = true;

        PanicoConfig config = PanicoConfig.findById(PanicoConfig.class,(long) 1);
        //Toast.makeText(this, "tel1:"+config.getTelefone1() + "tel2:"+config.getTelefone2() + "tel3:"+config.getTelefone3(), Toast.LENGTH_LONG).show();

        // Enviar SMS
        telefone1 = config.getTelefone1();
        telefone2 = config.getTelefone2();
        telefone3 = config.getTelefone3();

        // Habilitar GPS e Pegar Posição
        pedirPermissoes();


        // Ligar
        Ligar(telefone1);
        Toast.makeText(this, "Ligou tel1:"+config.getTelefone1(), Toast.LENGTH_LONG).show();

    }


    private class MyPhoneCallListener extends PhoneStateListener {
        private boolean returningFromOffHook = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            // Define a string for the message to use in a toast.
            String message = "Status Fone";//getString(R.string.phone_status);
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    returningFromOffHook = true;
                    break;
                case TelephonyManager.CALL_STATE_IDLE:

                    if (returningFromOffHook) {
                     if (botaoAcionado){
                         // ligar proximo numero
                     }
//                        // No need to do anything if >= version K
//                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//                            Log.i("TAG", "getString(R.string.restarting_app)");
//                            // Restart the app.
////                            Intent intent = getPackageManager()
////                                    .getLaunchIntentForPackage(
////                                    .getPackageName());
//                            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            //startActivity(i);
//                        }
                    }
                    break;
                default:
                    message = message + "Phone off";
                    Toast.makeText(MainActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                    Log.i("TAG", message);
                    break;
            }
        }
    }




}




