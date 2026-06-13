package org.example.lanchonete;


public abstract class EstadoPedido {


	protected void notificar(Pedido contexto, String notificacao) {
		contexto.notificar("Notificação sobre o pedido "+contexto.toString()+": "+notificacao);
	}

	public abstract void cancelar(Pedido contexto);

	public abstract void iniciarPreparo(Pedido contexto);

	public abstract void finalizarPreparo(Pedido contexto);

	public abstract void enviarEntrega(Pedido contexto);

	public abstract void confirmarEntrega(Pedido contexto);

    @Override
    public abstract String toString();

	protected void acaoInvalida(String message){
		throw new IllegalStateException("A ação é inválida: "+message);
	}
}