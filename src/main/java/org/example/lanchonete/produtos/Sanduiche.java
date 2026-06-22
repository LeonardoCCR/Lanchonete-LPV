package org.example.lanchonete.produtos;

import org.example.lanchonete.relatorio.RelatorioVisitor;

public class Sanduiche implements ItemPersonalizavel {

	private String descricao;
	private double preco;

	public Sanduiche(String descricao, double precoBase) {
		this.descricao = descricao;
		this.preco = precoBase;
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
	public void accept(RelatorioVisitor visitor) {
		visitor.visit(this);
	}

}