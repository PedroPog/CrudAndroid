package app.estudos.crudandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import app.estudos.crudandroid.model.ModelCategoria;

public class BdCategoria {
    DadosOperHelper dadosOperHelper;
    private Context context;

    public BdCategoria(Context context) {
        this.context = context;
        dadosOperHelper = new DadosOperHelper(this.context);
    }

    public void addCategoria(@NonNull ModelCategoria model){
        SQLiteDatabase sqLiteDatabase = dadosOperHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("descricao", model.getDescricao());
        values.put("cor", model.getCor());
        values.put("status", model.isStatus());
        sqLiteDatabase.insert("categoria", null, values);
        sqLiteDatabase.close();
    }

    public Cursor selecionarVendCateg() {
        SQLiteDatabase sqLiteDatabase = dadosOperHelper.getReadableDatabase();
        String rawQuery = " SELECT categoria.idcategoria, categoria.descricao, categoria.cor"
                + " FROM categoria ORDER BY categoria.descricao ASC ";
        Cursor cursor = sqLiteDatabase.rawQuery(rawQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        sqLiteDatabase.close();

        return cursor;
    }
}
