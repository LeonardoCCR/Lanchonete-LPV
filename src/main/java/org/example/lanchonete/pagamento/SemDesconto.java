package org.example.lanchonete.pagamento;

public class SemDesconto implements EstrategiaDesconto {
    @Override
    public double aplicar(double valorBruto) {
        return valorBruto;
    }
}