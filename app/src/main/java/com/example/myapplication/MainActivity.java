package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editPreco;
    private Button btnSalvar;
    private ListView listProdutos;

    private ProdutoDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNome);
        editPreco = findViewById(R.id.editPreco);
        btnSalvar = findViewById(R.id.btnSalvar);
        listProdutos = findViewById(R.id.listProdutos);

        dbHelper = new ProdutoDbHelper(this, "produtos.db", 1);
        btnSalvar.setOnClickListener(v -> {

            String nome = editNome.getText().toString();
            String textoPreco = editPreco.getText().toString();
            if (nome.trim().length() < 3) {
                editNome.setError("Nome inválido. Mínimo de 3 caracteres.");
                return;
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