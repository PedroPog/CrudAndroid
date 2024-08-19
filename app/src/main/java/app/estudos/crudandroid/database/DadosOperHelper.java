package app.estudos.crudandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DadosOperHelper extends SQLiteOpenHelper {

    public static final String NAME_BASE = "base.db";
    public static final int VERSAO_BASE = 1;
    SQLiteDatabase db;
    DbInicial dbInicial = new DbInicial();

    public DadosOperHelper(Context context){
        super(context,NAME_BASE,null,VERSAO_BASE);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbInicial.criarBancoInicial(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
