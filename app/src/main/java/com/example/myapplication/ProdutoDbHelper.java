package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProdutoDbHelper extends SQLiteOpenHelper {

    public ProdutoDbHelper(Context context, String nomeBanco, int versao) {
        super(context, nomeBanco, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS produtos (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT," +
                        "preco REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {

    }

    public boolean inserirProduto(Produto produto) {

        ContentValues valores = new ContentValues();

        valores.put("nome", produto.nome);
        valores.put("preco", produto.preco);

        SQLiteDatabase db = getWritableDatabase();

        long resultado = db.insert("produtos", null, valores);

        return resultado != -1;
    }

    public ArrayList<Produto> listarProdutos() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                "produtos",
                new String[]{"id", "nome", "preco"},
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<Produto> produtos = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            double preco = cursor.getDouble(2);

            produtos.add(new Produto(id, nome, preco));
        }

        cursor.close();

        return produtos;
    }
}