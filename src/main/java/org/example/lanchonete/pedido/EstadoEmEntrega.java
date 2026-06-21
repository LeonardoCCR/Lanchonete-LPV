package org.example.lanchonete.pedido;


import org.example.lanchonete.Command;

public class EstadoEmEntrega extends EstadoPedido {

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
		contexto.setEstado(new EstadoFinalizado());
		notificar(contexto, "Pedido entregue", "PEDIDO_ENTREGUE");
	}

	@Override
	public String toString(){
		return "Saiu para Entrega";
	}

	public static class EnviarEntregaCommand implements Command {

		private final Pedido pedido;

		public EnviarEntregaCommand(Pedido pedido) {
			this.pedido = pedido;
		}

		@Override
		public void executar() {
			pedido.enviarEntrega();
		}
	}

	public EstadoPedido clonar(){
		return new EstadoEmEntrega();
	}
}