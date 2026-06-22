package org.example.lanchonete.notificacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoObservadorHandlerTest {

    private PedidoObservadorHandler handler;
    private SpyObserver spyObserver1;
    private SpyObserver spyObserver2;

    static class SpyObserver implements PedidoObservador {
        public final List<Notificacao> notificacoesRecebidas = new ArrayList<>();

        @Override
        public void receberNotificacao(Notificacao notificacao) {
            notificacoesRecebidas.add(notificacao);
        }

        @Override
        public PreferenciasNotificacao getPreferencias() {
            List<String> lista = new ArrayList<>();

            lista.add("PEDIDO_RECEBIDO");
            lista.add("PEDIDO_EM_PREPARO");
            lista.add("PEDIDO_PREPARADO");
            lista.add("PEDIDO_SAIU_ENTREGA");
            lista.add("PEDIDO_FINALIZADO");
            lista.add("PEDIDO_CANCELADO");
            
            return new PreferenciasNotificacao(lista);
        }
    }

    @BeforeEach
    void setUp() {
        handler = new PedidoObservadorHandler();
        spyObserver1 = new SpyObserver();
        spyObserver2 = new SpyObserver();
    }

    @Test
    void dadoObservadoresInscritosEmCanaisDiferentes_quandoEmitirNotificacao_entaoApenasInscritosRecebem() {
        handler.adicionarObservador("PEDIDO_RECEBIDO", spyObserver1);
        handler.adicionarObservador("PEDIDO_FINALIZADO", spyObserver2);

        Notificacao notificacao = new Notificacao("Pedido chegou", "PEDIDO_RECEBIDO");
        handler.emitirNotificacao(notificacao);

        assertEquals(1, spyObserver1.notificacoesRecebidas.size());
        assertEquals("Pedido chegou", spyObserver1.notificacoesRecebidas.get(0).mensagem());

        assertEquals(0, spyObserver2.notificacoesRecebidas.size());
    }

    @Test
    void dadoCanalInexistente_quandoEmitirNotificacao_entaoLancaExcecao() {
        Notificacao notificacao = new Notificacao("Mensagem", "CANAL_FANTASMA");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            handler.emitirNotificacao(notificacao);
        });

        assertEquals("Este canal de notificações não existe", exception.getMessage());
    }

    @Test
    void dadoNovoCanal_quandoAdicionado_entaoPermiteInscricaoEEmissao() {
        handler.adicionarCanal("CANAL_CUSTOMIZADO");
        handler.adicionarObservador("CANAL_CUSTOMIZADO", spyObserver1);

        Notificacao notificacao = new Notificacao("Teste", "CANAL_CUSTOMIZADO");
        handler.emitirNotificacao(notificacao);

        assertEquals(1, spyObserver1.notificacoesRecebidas.size());
    }
}