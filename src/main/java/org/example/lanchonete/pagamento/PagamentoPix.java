package org.example.lanchonete.pagamento;


import org.example.lanchonete.pedido.Pedido;

public class PagamentoPix extends Pagamento {

	public PagamentoPix(ProcessadorPagamento processador) {
		super(processador);
	}

	@Override
	public void efetuarPagamento(Pedido pedido, EstrategiaDesconto estrategia) {
		this.valor = pedido.getValorTotalFinal(estrategia);

		this.confirmado = this.processador.processar(this.valor);
	}
}