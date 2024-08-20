package app.estudos.crudandroid.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import app.estudos.crudandroid.R;
import app.estudos.crudandroid.SplashActivity;

public class LoginActivity extends SplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}