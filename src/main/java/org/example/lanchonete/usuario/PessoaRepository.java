package org.example.lanchonete.usuario;

import java.util.ArrayList;
import java.util.List;

public class PessoaRepository<T extends Pessoa> {

    private final List<T> registros = new ArrayList<>();

    public void cadastrar(T pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("Não é possível cadastrar um registro nulo.");
        }
        this.registros.add(pessoa);
    }

    public List<String> obterRegistros() {
        List<String> saida = new ArrayList<>();
        for (T registro : this.registros) {
            saida.add(registro.obterPessoa());
        }
        return saida;
    }

    public List<T> listarTodos() {
        return new ArrayList<>(registros);
    }
}
