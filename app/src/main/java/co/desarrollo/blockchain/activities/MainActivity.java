package co.desarrollo.blockchain.activities;

import androidx.appcompat.app.AppCompatActivity;
import co.desarrollo.blockchain.R;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitUI();
        InitHandlers();
    }

    private void InitUI() {
        btnContinuar = findViewById(R.id.btnContinuar);
    }

    private void InitHandlers() {
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToDataCapture();
            }
        });
    }

    public void GoToDataCapture() {
        Intent intent = new Intent();
        intent.setClass(this, DataCaptureActivity.class);
        this.startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}