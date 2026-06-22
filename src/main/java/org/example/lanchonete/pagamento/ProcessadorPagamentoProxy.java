package org.example.lanchonete.pagamento;

public class ProcessadorPagamentoProxy implements ProcessadorPagamento {

    private final ProcessadorPagamento processadorReal;

    public ProcessadorPagamentoProxy(ProcessadorPagamento processadorReal) {

        if (processadorReal == null) {
            throw new IllegalArgumentException("O processador de pagamento não pode ser nulo.");
        }

        this.processadorReal = processadorReal;
    }

    @Override
    public boolean processar(double valor) {

        validarValor(valor);

        return processadorReal.processar(valor);
    }

    private void validarValor(double valor) {

        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }
}