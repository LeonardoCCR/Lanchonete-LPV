package org.example.lanchonete;

import org.example.lanchonete.pedido.Pedido;

public abstract class Pagamento {

	protected ProcessadorPagamento processador; // Bridge
	protected double valor;
	protected boolean confirmado;


	protected Pagamento(ProcessadorPagamento processador) {
		this.processador = processador;
		this.confirmado = false;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public abstract void efetuarPagamento(Pedido pedido, EstrategiaDesconto estrategia);
}