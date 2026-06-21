package org.example.lanchonete;

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