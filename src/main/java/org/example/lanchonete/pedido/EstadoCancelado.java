package org.example.lanchonete.pedido;

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

	public static class FinalizarPreparoCommand implements Command {

		private final Pedido pedido;

		public FinalizarPreparoCommand(Pedido pedido) {
			this.pedido = pedido;
		}

		@Override
		public void executar() {
			pedido.finalizarPreparo();
		}
	}

	public static class IniciarPreparoCommand implements Command {

		private final Pedido pedido;

		public IniciarPreparoCommand(Pedido pedido) {
			this.pedido = pedido;
		}

		@Override
		public void executar() {
			pedido.iniciarPreparo();
		}
	}

	public static class ConfirmarEntregaCommand implements Command {

		private final Pedido pedido;

		public ConfirmarEntregaCommand(Pedido pedido) {
			this.pedido = pedido;
		}

		@Override
		public void executar() {
			pedido.confirmarEntrega();
		}
	}

	public static class CancelarPedidoCommand implements Command {

		private final Pedido pedido;

		public CancelarPedidoCommand(Pedido pedido) {
			this.pedido = pedido;
		}

		@Override
		public void executar() {
			pedido.cancelar();
		}
	}

	public EstadoPedido clonar(){
		return new EstadoCancelado();
	}
}