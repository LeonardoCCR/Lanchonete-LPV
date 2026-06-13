package org.example.lanchonete;


public class EstadoRecebido extends EstadoPedido {



	@Override
	public void cancelar(Pedido contexto) {
		contexto.setEstado(new EstadoCancelado());
		notificar(contexto, "Pedido cancelado");
	}

	@Override
	public void iniciarPreparo(Pedido contexto) {
		contexto.setEstado(new EstadoEmPreparo());
		notificar(contexto, "Pedido em preparo");
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

}