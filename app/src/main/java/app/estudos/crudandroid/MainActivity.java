package app.estudos.crudandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import app.estudos.crudandroid.database.DadosOperHelper;

public class MainActivity extends SplashActivity {
    private static DadosOperHelper operHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectarBanc(this);//Criação do banco e captura das informação apos abrir

        fecharBanco();//Fechamento do banco para economia de memoria
    }

    public static void conectarBanc(Context context) {
        try {
            operHelper = new DadosOperHelper(context);
        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle("Falha na conexao Banco");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
    public static void fecharBanco(){
        try {
            operHelper.close();
        }catch (SQLException e){
            Log.w("FechamentoBanco","Tentativa de fechar Banco: "+e.getMessage());
        }
    }
}