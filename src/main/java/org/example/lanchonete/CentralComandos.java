package org.example.lanchonete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CentralComandos {

    private final List<Command> historico =
            new ArrayList<>();

    public void executar(Command comando) {
        comando.executar();
        historico.add(comando);
    }

    public List<Command> getHistorico() {
        return Collections.unmodifiableList(historico);
    }
}