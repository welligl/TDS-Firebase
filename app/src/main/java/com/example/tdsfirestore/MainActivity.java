package com.example.tdsfirestore;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edtLogin;
    EditText edtSenha;
    FirebaseAuth mAuth;
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        txtStatus = findViewById(R.id.txtStatus);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    void updateUI(FirebaseUser currentUser){
        if (currentUser != null){
            dashRedirect();
        }else{
            Toast.makeText(this, "Usuário não logado", Toast.LENGTH_SHORT).show();
        }
    }

    public void fazerLogin(View view){
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();
        txtStatus.setVisibility(View.VISIBLE);
        txtStatus.setText("Buscando informações do usuário");
        mAuth.signInWithEmailAndPassword(login,senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                txtStatus.setVisibility(View.GONE);
                updateUI(mAuth.getCurrentUser());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                txtStatus.setText("Usuário/Senha não conferem");
            }
        });
    }

    void dashRedirect(){
        Intent intent = new Intent(MainActivity.this, Dashboard.class);
        startActivity(intent);
    }
}
