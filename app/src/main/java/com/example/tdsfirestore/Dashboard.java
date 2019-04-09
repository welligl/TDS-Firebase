package com.example.tdsfirestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    TextView txtWelcome;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtWelcome = findViewById(R.id.txtWelcome);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
        String msg = "Bem-vindo " + mAuth.getCurrentUser().getEmail();
        txtWelcome.setVisibility(View.VISIBLE);
        txtWelcome.setText(msg);
    }

    public void registrarProduto(View view){
        Intent intent = new Intent(Dashboard.this,RegistrarActivity.class);
        startActivity(intent);
    }

    public void consultarProduto(View view){
        Intent intent = new Intent(Dashboard.this,ConsultaActivity.class);
        startActivity(intent);
    }
}
