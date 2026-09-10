package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrincipalActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        Spinner spCategoria = findViewById(R.id.spCategoria);

        String[] categorias = {"Estudio", "Trabajo", "Personal", "Otro"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categorias
        );

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spCategoria.setAdapter(adaptador);

        EditText etTarea = findViewById(R.id.etTarea);

        Button btnAgregarTarea = findViewById(R.id.btnAgregarTarea);

        RadioGroup rgPrioridad = findViewById(R.id.rgPrioridad);
        CheckBox cbCompletada = findViewById(R.id.cbCompletada);
        RatingBar rbImportancia = findViewById(R.id.rbImportancia);

        LinearLayout layoutTareas = findViewById(R.id.layoutTareas);

        ProgressBar pbProgreso = findViewById(R.id.pbProgreso);

        int[] tareasTotales = {0};

        int[] tareasCompletadas = {0};

        btnAgregarTarea.setOnClickListener(v -> {

            String tarea = etTarea.getText().toString().trim();

            if (tarea.isEmpty()) {

                etTarea.setError("Ingresa una tarea");

            } else {

                String categoria = spCategoria.getSelectedItem().toString();

                int idPrioridad = rgPrioridad.getCheckedRadioButtonId();

                RadioButton rbSeleccionado = findViewById(idPrioridad);

                String prioridad = rbSeleccionado.getText().toString();

                boolean completada = cbCompletada.isChecked();

                float importancia = rbImportancia.getRating();

                tareasTotales[0]++;

                if (completada) {
                    tareasCompletadas[0]++;
                }

                int porcentaje =
                        (tareasCompletadas[0] * 100) / tareasTotales[0];

                pbProgreso.setProgress(porcentaje);

                TextView tvNuevaTarea = new TextView(this);

                tvNuevaTarea.setText(
                        "Tarea: " + tarea +
                                "\nCategoría: " + categoria +
                                "\nPrioridad: " + prioridad +
                                "\nCompletada: " + completada +
                                "\nImportancia: " + importancia +
                                "\n------------------------"
                );

                layoutTareas.addView(tvNuevaTarea);

                etTarea.setText("");

                Toast.makeText(
                        this,
                        "Tarea agregada correctamente",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );
    }
}