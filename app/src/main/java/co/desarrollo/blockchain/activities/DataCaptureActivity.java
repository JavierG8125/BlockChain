package co.desarrollo.blockchain.activities;

import androidx.appcompat.app.AppCompatActivity;
import co.desarrollo.blockchain.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class DataCaptureActivity extends AppCompatActivity {
    private EditText txtNombres, txtApellidos, txtEmail;
    private Spinner ddlOcupacion;
    private ToggleButton chkSuscribirse, chkAceptaTerminos;
    private Button btnSuscribirse;

    private List<String> lstOcupaciones;
    private ArrayAdapter<String> ardOcupaciones;

    private String strNombres, strApellidos, strEmail, strOcupacion;
    private boolean bolSuscribirse, bolAceptaTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_capture);

        InitUI();
        InitVariables();
        InitData();
        InitHandlers();
    }

    private void InitUI() {
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEmail = findViewById(R.id.txtEmail);
        ddlOcupacion = findViewById(R.id.ddlOcupacion);
        chkSuscribirse = findViewById(R.id.chkSuscribirse);
        chkAceptaTerminos = findViewById(R.id.chkAceptaTerminos);
        btnSuscribirse = findViewById(R.id.btnSuscribirse);
    }

    private void InitVariables() {
        lstOcupaciones = new ArrayList<>();
    }

    private void InitData() {
        lstOcupaciones.add("Seleccione");
        lstOcupaciones.add("Estudiante");
        lstOcupaciones.add("Empleado");
        lstOcupaciones.add("Independiente");
        lstOcupaciones.add("Empresario");
        lstOcupaciones.add("Emprendedor");

        ardOcupaciones = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lstOcupaciones);
        ardOcupaciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddlOcupacion.setAdapter(ardOcupaciones);
    }

    private void InitHandlers() {
        btnSuscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidarDatosSuscripcion()){
                    GoToDataResume();
                }
            }
        });
    }

    private boolean ValidarDatosSuscripcion() {
        strNombres = txtNombres.getText().toString();
        strApellidos = txtApellidos.getText().toString();
        strEmail = txtEmail.getText().toString();
        strOcupacion = ddlOcupacion.getSelectedItem().toString();
        bolSuscribirse = chkSuscribirse.isChecked();
        bolAceptaTerminos = chkAceptaTerminos.isChecked();

        if(strNombres.isEmpty()){
            Toast.makeText(getApplicationContext(), "Por favor digite sus nombres.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(strApellidos.isEmpty()){
            Toast.makeText(getApplicationContext(), "Por favor digite sus apellidos.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(strEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Por favor digite su correo electrónico.", Toast.LENGTH_LONG).show();
            return false;
        } else if(!isValidEmail(strEmail)){
            Toast.makeText(getApplicationContext(), "Por favor digite un correo electrónico válido.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(strOcupacion.equals("Seleccione")){
            Toast.makeText(getApplicationContext(), "Por favor seleccione una ocupación.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!bolAceptaTerminos){
            Toast.makeText(getApplicationContext(), "Para continuar debe aceptar los términos y condiciones de LogyChain.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void GoToDataResume() {
        Intent intent = new Intent();
        intent.putExtra("NOMBRES_SUSCRIPTOR", strNombres);
        intent.putExtra("APELLIDOS_SUSCRIPTOR", strApellidos);
        intent.putExtra("EMAIL_SUSCRIPTOR", strEmail);
        intent.putExtra("OCUPACION_SUSCRIPTOR", strOcupacion);
        intent.putExtra("SUSCRIBIRSE_SUSCRIPTOR", bolSuscribirse);
        intent.putExtra("ACEPTA_TERMINOS_SUSCRIPTOR", bolAceptaTerminos);

        intent.setClass(this, ResumeCaptureActivity.class);
        this.startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}