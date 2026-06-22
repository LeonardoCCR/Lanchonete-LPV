package org.example.lanchonete.usuario;


import java.util.HashMap;
import java.util.Map;

//Flyweigth
public class CidadeFactory {
    private static Map<String, Cidade> cidades = new HashMap<>();

    public static Cidade getCidade(String nome, String uf) {

        return cidades.computeIfAbsent(nome, n -> new Cidade(n, uf));
    }

    public static int getTotalCidades() {
        return cidades.size();
    }
}
