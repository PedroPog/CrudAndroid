package app.estudos.crudandroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BdProduto {
    DadosOperHelper dadosOperHelper;
    private Context context;

    public BdProduto(Context context) {
        this.context = context;
        dadosOperHelper = new DadosOperHelper(this.context);
    }

    public Cursor selecionarVendProd(int idcateg) {
        Cursor cursor = null;
        SQLiteDatabase sqLiteDatabase = null;

        try {
            sqLiteDatabase = dadosOperHelper.getReadableDatabase();
            String rawQuery = "SELECT produto.idproduto, produto.descricao, produto.preco, " +
                    "                produto.quantestoque, produto.codigoean, produto.precovariavel, " +
                    "                unidade.sigla, produto.imagem, categoria.cor, produto.status " +
                    "                FROM produto " +
                    "                LEFT JOIN categoria ON categoria.idcategoria  = produto.idcategoria " +
                    "                LEFT JOIN unidade ON unidade.idunidade = produto.idunidade " +
                    "                WHERE produto.status = '1' " +
                    "                and produto.idcategoria = " + idcateg + " " +
                    "                ORDER BY produto.descricao ASC";

            cursor = sqLiteDatabase.rawQuery(rawQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (SQLException e) {
            Log.e("DB_ERROR", "Erro ao executar a consulta: " + e.getMessage());
        } finally {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }

        return cursor;
    }

}
