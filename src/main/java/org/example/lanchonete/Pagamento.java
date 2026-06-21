package org.example.lanchonete;

public abstract class Pagamento {

	protected ProcessadorPagamento processador; //ponte
	protected double valor;
	protected boolean confirmado;

	// Construtor com a ponte
	protected Pagamento(ProcessadorPagamento processador) {
		this.processador = processador;
		this.confirmado = false;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public abstract void efetuarPagamento(Pedido pedido, EstrategiaDesconto estrategia);
}