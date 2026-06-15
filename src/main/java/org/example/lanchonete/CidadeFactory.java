package org.example.lanchonete;


import java.util.HashMap;
import java.util.Map;

//Padrão flyweigth
public class CidadeFactory {
    private static Map<String, Cidade> cidades = new HashMap<>();

    public static Cidade getCidade(String nome, String uf) {
        Cidade cidade = cidades.get(nome);
        if (cidade == null) { //cria caso não exista
            cidade = new Cidade(nome, uf);
            cidades.put(nome, cidade);
        }
        return cidade;
    }

    public static int getTotalCidades() {
        return cidades.size();
    }
}
