package org.example.lanchonete;

public class EstadoCancelado extends EstadoPedido {

	@Override
	public void cancelar(Pedido contexto) {
		acaoInvalida("Não é possível cancelar de um pedido com estado \""+ this +"\".");
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
		acaoInvalida("Não é possível enviar a entrega de um pedido com estado \""+ this +"\".");
	}

	@Override
	public void confirmarEntrega(Pedido contexto) {
		acaoInvalida("Não é possível confirmar a entrega de um pedido com estado \""+ this +"\".");
	}

	@Override
	public String toString(){
		return "Cancelado";
	}

}