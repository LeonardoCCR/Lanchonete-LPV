package org.example.lanchonete.pedido;


public class EstadoRecebido extends EstadoPedido {



	@Override
	public void cancelar(Pedido contexto) {
		contexto.setEstado(new EstadoCancelado());
		notificar(contexto, "Pedido cancelado", "PEDIDO_CANCELADO");
	}

	@Override
	public void iniciarPreparo(Pedido contexto) {
		contexto.setEstado(new EstadoEmPreparo());
		notificar(contexto, "Pedido em preparo", "PEDIDO_RECEBIDO");
	}

	@Override
	public void finalizarPreparo(Pedido contexto) {
		acaoInvalida("Não é possível finalizar o preparo de um pedido com estado \""+ this +"\".");
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
		return "Recebido";
	}

	public EstadoPedido clonar(){
		return new EstadoRecebido();
	}

}