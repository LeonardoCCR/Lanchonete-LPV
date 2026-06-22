package org.example.lanchonete.pedido;


public class EstadoEmPreparo extends EstadoPedido {

	@Override
	public void cancelar(Pedido contexto) {
		contexto.setEstado(new EstadoCancelado());
		notificar(contexto, "Pedido cancelado", "PEDIDO_CANCELADO");
	}

	@Override
	public void iniciarPreparo(Pedido contexto) {
		acaoInvalida("Não é possível iniciar o preparo de um pedido com estado \""+ this +"\".");
	}

	@Override
	public void finalizarPreparo(Pedido contexto) {
		contexto.setEstado(new EstadoPronto());
		notificar(contexto, "Pedido preparado", "PEDIDO_FINALIZADO");
	}

	@Override
	public void enviarEntrega(Pedido contexto) {
		acaoInvalida("Não é possível enviar a entrega de um pedido com estado \""+ this +"\".");
	}

	@Override
	public void confirmarEntrega(Pedido contexto) {
		acaoInvalida("Não é possível confirmar a entrega de um pedido com estado \""+ this +"\".");
	}

	@Override
	public String toString(){
		return "Em Preparo";
	}

	public EstadoPedido clonar(){
		return new EstadoEmPreparo();
	}

}