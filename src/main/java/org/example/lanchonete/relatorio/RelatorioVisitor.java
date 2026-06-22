package org.example.lanchonete.relatorio;

import org.example.lanchonete.combo.ComboComposite;
import org.example.lanchonete.produtos.*;

public interface RelatorioVisitor {
    void visit(Bebida bebida);
    void visit(Sanduiche sanduiche);
    void visit(Salgado salgado);
    void visit(AdicionalDecorator decorado);
    void visit(ComboComposite comboComposite);
    void visit(Acompanhamento acompanhamento);
}