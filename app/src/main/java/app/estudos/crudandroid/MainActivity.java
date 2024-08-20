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
    Button btn_vender,btn_cadastro,btn_contador,btn_relatorio,
            btn_config,btn_suporte,btn_sair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectarBanc(this);//Criação do banco e captura das informação apos abrir
        findView();
        startFragment(new FragmentVenda());
        setOnClick();
        //fecharBanco();//Fechamento do banco para economia de memoria
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
    private void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame, fragment);
        fragmentTransaction.commit();
    }
    private void findView(){
        btn_vender = findViewById(R.id.btn_vender);
        btn_cadastro = findViewById(R.id.btn_cadastro);
        btn_relatorio = findViewById(R.id.btn_relatorio);
        btn_contador = findViewById(R.id.btn_contador);
        btn_config = findViewById(R.id.btn_config);
        btn_suporte = findViewById(R.id.btn_suporte);
        btn_sair = findViewById(R.id.btn_sair);
    }
    private void setOnClick(){
        btn_vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FragmentVenda());
                selecionarBtnMenu(btn_vender);
            }
        });
        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FragmentEstoque());
                selecionarBtnMenu(btn_cadastro);
            }
        });
        btn_relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FragmentRelatorio());
                selecionarBtnMenu(btn_relatorio);
            }
        });
        btn_contador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FragmentContador());
                selecionarBtnMenu(btn_contador);
            }
        });
        btn_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FragmentConfig());
                selecionarBtnMenu(btn_config);
            }
        });
        btn_suporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FragmentSuporte());
                selecionarBtnMenu(btn_suporte);
            }
        });
        btn_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MainActivity.this,SplashActivity.class);
                startActivity(intent);
                finishAfterTransition();*/
                System.exit(0);
            }
        });
    }
    private void selecionarBtnMenu(Button selectedButton){
        btn_vender.setBackgroundResource(semfoco);
        btn_cadastro.setBackgroundResource(semfoco);
        btn_relatorio.setBackgroundResource(semfoco);
        btn_contador.setBackgroundResource(semfoco);
        btn_config.setBackgroundResource(semfoco);
        btn_suporte.setBackgroundResource(semfoco);
        btn_sair.setBackgroundResource(semfoco);
        selectedButton.setBackgroundResource(selecionado);
    }
}