package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.OptionalDouble;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPrice;
    private Button btnSave;
    private ListView listProducts;

    private ProductDbHelper dbHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        btnSave = findViewById(R.id.btnSave);
        listProducts = findViewById(R.id.listProducts);

        dbHelper = new ProductDbHelper(this, "products.db", 1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listProducts.setAdapter(adapter);
        refreshList();

        btnSave.setOnClickListener(v -> {

            String name = editName.getText().toString().trim();
            if (!ProductValidator.isValidName(name)) {
                editName.setError(getString(R.string.error_invalid_name));
                return;
            }

            OptionalDouble price = ProductValidator.parsePrice(editPrice.getText().toString());
            if (!price.isPresent()) {
                editPrice.setError(getString(R.string.error_invalid_price));
                return;
            }

            if (dbHelper.insertProduct(new Product(0, name, price.getAsDouble()))) {
                Toast.makeText(this, R.string.product_saved, Toast.LENGTH_SHORT).show();
                editName.setText("");
                editPrice.setText("");
                editName.requestFocus();
                refreshList();
            } else {
                Toast.makeText(this, R.string.error_save_failed, Toast.LENGTH_SHORT).show();
            }
        });

        View root = findViewById(R.id.main);
        final int paddingLeft = root.getPaddingLeft();
        final int paddingTop = root.getPaddingTop();
        final int paddingRight = root.getPaddingRight();
        final int paddingBottom = root.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(
                root,
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime()
                    );

                    v.setPadding(
                            paddingLeft + systemBars.left,
                            paddingTop + systemBars.top,
                            paddingRight + systemBars.right,
                            paddingBottom + systemBars.bottom
                    );

                    return insets;
                }
        );
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    private void refreshList() {
        adapter.clear();
        for (Product product : dbHelper.listProducts()) {
            adapter.add(product.toString());
        }
    }
}