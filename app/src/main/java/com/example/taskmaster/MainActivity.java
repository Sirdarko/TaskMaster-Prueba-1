package com.example.taskmaster;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,RegistroActivity.class);
            startActivity(intent);
        });
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etContrasena = findViewById(R.id.etContraseña);

        Button btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            String contrasena = etContrasena.getText().toString().trim();

            SharedPreferences preferencias = getSharedPreferences("Usuario", MODE_PRIVATE);

            String usuarioGuardado = preferencias.getString("usuario","");
            String contrasenaGuardada = preferencias.getString("contrasena","");

            if (usuario.isEmpty()) {
                etUsuario.setError("Ingresa tu nombre de usuario");
            } else if (contrasena.isEmpty()) {
                etContrasena.setError("Ingresa tu contraseña");
            } else if (usuario.equals(usuarioGuardado) && contrasena.equals(contrasenaGuardada)) {
            Toast.makeText(this,"Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,InicioActivity.class);
            startActivity(intent);
            } else {
            Toast.makeText(this,"Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
        }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}