package co.desarrollo.blockchain.activities;

import androidx.appcompat.app.AppCompatActivity;
import co.desarrollo.blockchain.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class ResumeCaptureActivity extends AppCompatActivity {
    public static ResumeCaptureActivity instance = null;

    private TextView txtNombres, txtApellidos, txtEmail, txtOcupacion, txtSuscribirse, txtAceptaTerminos;
    private Button btnFinalizar;

    private String strNombres, strApellidos, strEmail, strOcupacion;
    private boolean bolSuscribirse, bolAceptaTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_capture);

        InitUI();
        InitVariables();
        InitData();
        InitHandlers();
    }

    private void InitUI() {
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEmail = findViewById(R.id.txtEmail);
        txtOcupacion = findViewById(R.id.txtOcupacion);
        txtSuscribirse = findViewById(R.id.txtSuscribirse);
        txtAceptaTerminos = findViewById(R.id.txtAceptaTerminos);
        btnFinalizar = findViewById(R.id.btnFinalizar);
    }

    private void InitVariables() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            strNombres = extras.getString("NOMBRES_SUSCRIPTOR");
            strApellidos = extras.getString("APELLIDOS_SUSCRIPTOR");
            strEmail = extras.getString("EMAIL_SUSCRIPTOR");
            strOcupacion = extras.getString("OCUPACION_SUSCRIPTOR");
            bolSuscribirse = extras.getBoolean("SUSCRIBIRSE_SUSCRIPTOR");
            bolAceptaTerminos = extras.getBoolean("ACEPTA_TERMINOS_SUSCRIPTOR");
        } else{
            Toast.makeText(getApplicationContext(), "No hay datos para mostrar.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void InitData() {
        txtNombres.setText(strNombres);
        txtApellidos.setText(strApellidos);
        txtEmail.setText(strEmail);
        txtOcupacion.setText(strOcupacion);
        txtSuscribirse.setText(bolSuscribirse ? "Sí" : "No");
        txtAceptaTerminos.setText(bolAceptaTerminos ? "Sí" : "No");
    }

    private void InitHandlers() {
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Muchas gracias por trabajar con LogyChain. Nos veremos pronto!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}