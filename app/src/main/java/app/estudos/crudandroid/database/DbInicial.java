package app.estudos.crudandroid.database;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import app.estudos.crudandroid.model.ModelFuncao;
import app.estudos.crudandroid.util.UtilidadesGerais;

public class DbInicial {
    private ModelFuncao funcao;
    public boolean criarBancoInicial(SQLiteDatabase db){
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS cliente " +
                    "(idcliente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL UNIQUE, " +
                    "telefone VARCHAR(14) UNIQUE, " +
                    "estado VARCHAR(2) " +
                    ");");
            Log.i("OnCreateTable","Tabela Cliente criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnCreateTable","Tabela Cliente: "+e.getLocalizedMessage());
            return false;
        }
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS funcionario " +
                    "(idfuncionario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL UNIQUE, " +
                    "funcao INTEGER NOT NULL DEFAULT null " +
                    ");");
            Log.i("OnCreateTable","Tabela Funcionario criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnCreateTable","Tabela Funcionario: "+e.getLocalizedMessage());
            return false;
        }
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario " +
                    "(idusuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user varchar(20) NOT NULL, " +
                    "senha varchar(12) NOT NULL, " +
                    "idfuncionario INTEGER DEFAULT null, " +
                    "FOREIGN KEY (idfuncionario) REFERENCES funcionario (idfuncionario) " +
                    ");");
            Log.i("OnCreateTable","Tabela Usuario criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnCreateTable","Tabela Usuario: "+e.getLocalizedMessage());
            return false;
        }

        return insertDadosInicial(db);
    }
    private boolean insertDadosInicial(SQLiteDatabase db){
        ContentValues dados = new ContentValues();
        try{
            dados.put("name", "Cliente");
            dados.put("email", "cliente@gmail.com");
            dados.put("telefone", "(11)91234-1234");
            dados.put("estado", "SP");
            db.insert("cliente",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Cliente criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Cliente: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("name", "Funcionario");
            dados.put("email", "funcionario@gmail.com");
            dados.put("funcao", ModelFuncao.FUNCIONARIO.ordinal());
            db.insert("funcionario",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Funcionario criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Funcionario: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("name", "Gerente");
            dados.put("email", "gerente@gmail.com");
            dados.put("funcao", ModelFuncao.GERENTE.ordinal());
            db.insert("funcionario",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Funcionario criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Funcionario: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("user", "cliente");
            dados.put("senha", UtilidadesGerais.generatePassword());
            db.insert("usuario",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Usuario criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Usuario: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("user", "funcionario");
            dados.put("senha", UtilidadesGerais.generatePassword());
            dados.put("idfuncionario", 1);
            db.insert("usuario",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Usuario criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Tabela Usuario: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("user", "gerente");
            dados.put("senha", UtilidadesGerais.generatePassword());
            dados.put("idfuncionario", 2);
            db.insert("usuario",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Usuario criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Usuario: "+e.getLocalizedMessage());
            return false;
        }
        return true;
    }
}
