package org.example.lanchonete.produtos;


import org.example.lanchonete.relatorio.RelatorioVisitor;

public class Bebida implements ItemConsumivel {

	private String descricao;
	private double preco;

	public Bebida(String nome, double preco) {
		this.descricao = nome;
		this.preco = preco;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public double getPreco() {
		return preco;
	}

	@Override
	public boolean isLiquido() {
		return true;
	}

	@Override
	public void accept(RelatorioVisitor visitor) {
		visitor.visit(this);
	}
}