package org.example.lanchonete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoObservadorHandler {

    private final Map<String, List<PedidoObservador>> canais;

    public PedidoObservadorHandler(){
        canais = new HashMap<>();
        criarCanais();
    }

    public void emitirNotificacao(Notificacao notificacao){

        if(!canais.containsKey(notificacao.canal())){
            throw new IllegalArgumentException("Este canal de notificações não existe");
        }

        for(PedidoObservador observador: getObservadores(notificacao.canal())){
            observador.receberNotificacao(notificacao);
        }
    }

    private List<PedidoObservador> getObservadores(String canal){

        return canais.get(canal);
    }

    public void adicionarCanal(String canal){
        if(canais.containsKey(canal)){
            return;
        }
        canais.put(canal, new ArrayList<>());
    }

    public void adicionarObservador(String canal, PedidoObservador observador){
        adicionarCanal(canal);
        canais.get(canal).add(observador);
    }

    public void aplicarPreferencias(PedidoObservador observador){
        PreferenciasNotificacao preferencias = observador.getPreferencias();

        for(String canal: preferencias.getCanaisInscritos()){
            adicionarObservador(canal, observador);
        }

    }

    private void criarCanais(){
        adicionarCanal("PEDIDO_RECEBIDO");
        adicionarCanal("PEDIDO_EM_PREPARO");
        adicionarCanal("PEDIDO_PREPARADO");
        adicionarCanal("PEDIDO_SAIU_ENTREGA");
        adicionarCanal("PEDIDO_FINALIZADO");
        adicionarCanal("PEDIDO_CANCELADO");
    }

}
