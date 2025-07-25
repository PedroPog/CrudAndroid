package app.estudos.crudandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static app.estudos.crudandroid.R.color.selecionado;
import static app.estudos.crudandroid.R.color.semfoco;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import app.estudos.crudandroid.database.DadosOperHelper;
import app.estudos.crudandroid.fragments.FragmentConfig;
import app.estudos.crudandroid.fragments.FragmentContador;
import app.estudos.crudandroid.fragments.FragmentEstoque;
import app.estudos.crudandroid.fragments.FragmentRelatorio;
import app.estudos.crudandroid.fragments.FragmentSuporte;
import app.estudos.crudandroid.fragments.FragmentVenda;

public class MainActivity extends SplashActivity {
    private static DadosOperHelper operHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectarBanc(this);//Criação do banco e captura das informação apos abrir
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
}