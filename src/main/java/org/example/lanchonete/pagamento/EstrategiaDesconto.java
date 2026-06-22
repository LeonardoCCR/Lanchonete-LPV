package org.example.lanchonete.pagamento;

//Strategy
public interface EstrategiaDesconto {
    double aplicar(double valorBruto);
}