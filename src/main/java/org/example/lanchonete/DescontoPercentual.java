package org.example.lanchonete;

public class DescontoPercentual implements EstrategiaDesconto {
    private final double porcentagem;

    public DescontoPercentual(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 100) {
            throw new IllegalArgumentException("A porcentagem deve ser entre 0 e 100.");
        }
        this.porcentagem = porcentagem;
    }

    @Override
    public double aplicar(double valorBruto) {
        return valorBruto - (valorBruto * (porcentagem / 100.0));
    }
}