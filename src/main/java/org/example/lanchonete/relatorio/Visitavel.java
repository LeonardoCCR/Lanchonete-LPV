package org.example.lanchonete.relatorio;

public interface Visitavel {
    void accept(RelatorioVisitor visitor);
}