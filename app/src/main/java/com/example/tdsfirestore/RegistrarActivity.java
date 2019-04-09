package com.example.tdsfirestore;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {

    FirebaseFirestore db;
    EditText edtNomeProduto;
    Spinner unidadeProduto;
    Spinner perecivel;
    EditText edtEstoque;
    EditText edtValorUnitario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        db = FirebaseFirestore.getInstance();
        edtNomeProduto = findViewById(R.id.edtNomeProduto);
        unidadeProduto = findViewById(R.id.spinnerUnidade);
        perecivel = findViewById(R.id.spinnerPerecivel);
        edtEstoque = findViewById(R.id.edtEstoque);
        edtValorUnitario = findViewById(R.id.edtValor);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void registrarProduto(View view){
        Map<String,Object> produto = new HashMap<>();
        produto.put("NomeProduto",edtNomeProduto.getText().toString());
        produto.put("UnidadeProduto",unidadeProduto.getSelectedItem().toString());
        produto.put("Perecivel", perecivel.getSelectedItem().toString());
        produto.put("Estoque",edtEstoque.getText().toString());
        produto.put("ValorUnitario",edtValorUnitario.getText().toString());
        db.collection("Produto")
                .add(produto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegistrarActivity.this, "Produto cadastrado", Toast.LENGTH_SHORT).show();
                        redirectConsulta();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrarActivity.this, "Produto não cadastrado", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    
    void redirectConsulta(){
        Intent intent = new Intent(RegistrarActivity.this, ConsultaActivity.class);
        startActivity(intent);
    }
}
