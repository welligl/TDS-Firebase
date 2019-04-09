package com.example.tdsfirestore;

public class Produto {

    String nomeProduto;
    String unidadeProduto;
    String perecivel;
    String estoque;
    String valorUnitario;

    public Produto() {
    }

    public Produto(String nomeProduto, String unidadeProduto, String perecivel, String estoque, String valorUnitario) {
        this.nomeProduto = nomeProduto;
        this.unidadeProduto = unidadeProduto;
        this.perecivel = perecivel;
        this.estoque = estoque;
        this.valorUnitario = valorUnitario;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getUnidadeProduto() {
        return unidadeProduto;
    }

    public void setUnidadeProduto(String unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
    }

    public String getPerecivel() {
        return perecivel;
    }

    public void setPerecivel(String perecivel) {
        this.perecivel = perecivel;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return "Produto: " + '\n' +
                "Nome Produto: " + nomeProduto + '\n' +
                "Unidade Produto: " + unidadeProduto + '\n' +
                "Perecivel: " + perecivel + '\n' +
                "Estoque: " + estoque + '\n' +
                "Valor Unitario: " + valorUnitario + '\n';
    }
}
