package com.example.tdsfirestore;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsultaActivity extends AppCompatActivity {

    private EditText edtPesquisa;
    private ListView listProduto;
    private List<String> produtos;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        edtPesquisa = findViewById(R.id.edtPesquisa);
        listProduto = findViewById(R.id.listProduto);
        produtos = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
        recuperarDados();
    }

    public void recuperarDados(){
        db.collection("Produto")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            produtos.clear();
                            listProduto.setAdapter(null);
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Map<String,Object> objeto = document.getData();
                                Produto produto = new Produto();
                                produto.setNomeProduto(objeto.get("NomeProduto").toString());
                                produto.setUnidadeProduto(objeto.get("UnidadeProduto").toString());
                                produto.setEstoque(objeto.get("Estoque").toString());
                                produto.setPerecivel(objeto.get("Perecivel").toString());
                                produto.setValorUnitario(objeto.get("ValorUnitario").toString());
                                produtos.add(produto.toString());
                            }
                            ArrayAdapter adapter = new ArrayAdapter<String>(ConsultaActivity.this,android.R.layout.simple_list_item_1,produtos);
                            listProduto.setAdapter(adapter);
                        }else {
                            Toast.makeText(ConsultaActivity.this, "Não foi possivel recuperar os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
