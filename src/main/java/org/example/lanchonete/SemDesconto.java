package org.example.lanchonete;

public class SemDesconto implements EstrategiaDesconto {
    @Override
    public double aplicar(double valorBruto) {
        return valorBruto;
    }
}