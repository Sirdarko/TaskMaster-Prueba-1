package com.example.taskmaster;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> {
            finish();
        });
        Button btnRegistro = findViewById(R.id.btnRegistro);
        EditText etUsuarioRegistro = findViewById(R.id.etUsuarioRegistro);
        EditText etContrasenaRegistro = findViewById(R.id.etContrasenaRegistro);

        btnRegistro.setOnClickListener(v -> {
            String usuario = etUsuarioRegistro.getText().toString().trim();
            String contrasena = etContrasenaRegistro.getText().toString().trim();

            if (usuario.isEmpty()) {
                etUsuarioRegistro.setError("Ingresa un usuario");
            } else if (contrasena.isEmpty()) {
                etContrasenaRegistro.setError("Ingresa una contraseña");
            } else {
                 //Espacio donde se guardan los datos
                SharedPreferences preferencias = getSharedPreferences("Usuario", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("usuario",usuario);
                editor.putString("contrasena",contrasena);

                editor.apply();

                Toast.makeText(this,"Registrado Correctamente", Toast.LENGTH_SHORT).show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}