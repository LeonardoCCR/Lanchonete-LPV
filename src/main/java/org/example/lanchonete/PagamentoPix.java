package org.example.lanchonete;


import org.example.lanchonete.pedido.Pedido;

public class PagamentoPix extends Pagamento {

	public PagamentoPix(ProcessadorPagamento processador) {
		super(processador);
	}

	@Override
	public void efetuarPagamento(Pedido pedido, EstrategiaDesconto estrategia) {
		this.valor = pedido.getValorTotalFinal(estrategia);
		System.out.println("[Pix] Gerando chave aleatória e QR Code para transferência...");

		// Executa através da ponte
		this.confirmado = this.processador.processar(this.valor);
	}
}