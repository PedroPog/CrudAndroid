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
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS unidade " +
                    "(idunidade INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao varchar(50) NOT NULL, " +
                    "sigla varchar(2), " +
                    "precisao integer " +
                    ");");
            Log.i("OnCreateTable","Tabela Unidade criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnCreateTable","Tabela Unidade: "+e.getLocalizedMessage());
            return false;
        }
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS categoria " +
                    "(idcategoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao varchar(50) NOT NULL, " +
                    "cor varchar(20), " +
                    "status boolean " +
                    ");");
            Log.i("OnCreateTable","Tabela Categoria criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnCreateTable","Tabela Categoria: "+e.getLocalizedMessage());
            return false;
        }
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS produto " +
                    "(idproduto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao varchar(50) NOT NULL, " +
                    "preco numeric(12,2) NOT NULL, " +
                    "quantestoque double NOT NULL DEFAULT 0, " +
                    "codigoean varchar(14), " +
                    "precovariavel boolean DEFAULT true, " +
                    "idunidade INTEGER NOT NULL, " +
                    "imagem varchar default 0, " +
                    "idcategoria INTEGER NOT NULL, " +
                    "status boolean, " +
                    "FOREIGN KEY (idunidade) REFERENCES unidade (idunidade), " +
                    "FOREIGN KEY (idcategoria) REFERENCES categoria (idcategoria)" +
                    ");");
            Log.i("OnCreateTable","Tabela Produto criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnCreateTable","Tabela Produto: "+e.getLocalizedMessage());
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
        try {
            dados = new ContentValues();
            dados.put("descricao", "Unidade");
            dados.put("sigla", "UN");
            dados.put("precisao", 0);
            db.insert("unidade",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Unidade criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Unidade: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("descricao", "Quilograma");
            dados.put("sigla", "KG");
            dados.put("precisao", 3);
            db.insert("unidade",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Unidade criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Unidade: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("descricao", "Metro");
            dados.put("sigla", "M");
            dados.put("precisao", 2);
            db.insert("unidade",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Unidade criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Unidade: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("descricao", "CATEGORIA TESTE");
            dados.put("cor", "#FF4500");
            dados.put("status", true);
            db.insert("categoria",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Categoria criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Categoria: "+e.getLocalizedMessage());
            return false;
        }
        try {
            dados = new ContentValues();
            dados.put("descricao", "PRODUTO TESTE");
            dados.put("preco", "5");
            dados.put("quantestoque", "0");
            dados.put("codigoean", "");
            dados.put("precovariavel", true);
            dados.put("idunidade", 1);
            dados.put("imagem", "");
            dados.put("idcategoria", 1);
            dados.put("status", true);
            db.insert("produto",null,dados);
            dados.clear();
            Log.i("OnInsert","Insert Produto criada com sucesso!");
        }catch (SQLException e){
            Log.e("OnInsert","Insert Produto: "+e.getLocalizedMessage());
            return false;
        }
        return true;
    }
}
