package org.example.lanchonete;

import org.example.lanchonete.pedido.Pedido;

public class PagamentoCartao extends Pagamento {

	public PagamentoCartao(ProcessadorPagamento processador) {
		super(processador);
	}

	@Override
	public void efetuarPagamento(Pedido pedido, EstrategiaDesconto estrategia) {
		this.valor = pedido.getValorTotalFinal(estrategia);
		// Paga usando a ponte
		this.confirmado = this.processador.processar(this.valor);
	}
}