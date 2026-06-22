package org.example.lanchonete.pedido;


public class EstadoPronto extends EstadoPedido {



	@Override
	public void cancelar(Pedido contexto) {
		contexto.setEstado(new EstadoCancelado());
		notificar(contexto, "Pedido cancelado, multa aplicada", "PEDIDO_CANCELADO");
		//TODO: implementar multa
	}

	@Override
	public void iniciarPreparo(Pedido contexto) {
		acaoInvalida("Não é possível iniciar o preparo de um pedido com estado \""+ this +"\".");
	}

	@Override
	public void finalizarPreparo(Pedido contexto) {
		acaoInvalida("Não é possível finalizar o preparo de um pedido com estado \""+ this +"\".");
	}

	@Override
	public void enviarEntrega(Pedido contexto) {
		contexto.setEstado(new EstadoEmEntrega());
		contexto.aplicarEmbalagens();
		notificar(contexto, "Pedido em entrega", "PEDIDO_SAIU_ENTREGA");
	}

	@Override
	public void confirmarEntrega(Pedido contexto) {
		acaoInvalida("Não é possível confirmar a entrega de um pedido com estado \""+ this +"\".");
	}

	@Override
	public String toString(){
		return "Pronto para Entrega";
	}

	public EstadoPedido clonar(){
		return new EstadoPronto();
	}

}