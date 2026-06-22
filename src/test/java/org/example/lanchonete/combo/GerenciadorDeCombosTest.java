package org.example.lanchonete.combo;

import org.example.lanchonete.produtos.ItemConsumivel;
import org.example.lanchonete.produtos.ItemPedido;
import org.example.lanchonete.produtos.ItemPedidoBuilder;
import org.example.lanchonete.relatorio.RelatorioVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorDeCombosTest {

    private GerenciadorDeCombos gerenciador;
    private PerfilComboFactory perfilTeste;

    static class ItemConsumivelMock implements ItemConsumivel {
        @Override
        public double getPreco() {
            return 10.0;
        }

        @Override
        public boolean isLiquido() {
            return ItemConsumivel.super.isLiquido();
        }

        @Override
        public String getDescricao() {
            return "Mock";
        }

        @Override
        public void accept(RelatorioVisitor visitor) {

        }
    }

    @BeforeEach
    void setUp() {
        gerenciador = new GerenciadorDeCombos();

        gerenciador.adicionarNovasReceitas(gerenciador.registrarReceitasPadrao());

        perfilTeste = new PerfilComboFactory() {
            @Override
            public ItemConsumivel criarSanduichePrincipal() {
                return new ItemConsumivelMock();
            }

            @Override
            public ItemConsumivel criarSanduicheSecundario() {
                return null;
            }

            @Override
            public ItemConsumivel criarBebidaMedia() {
                return new ItemConsumivelMock();
            }

            @Override
            public ItemConsumivel criarBebidaGrande() {
                return null;
            }

            @Override
            public ItemConsumivel criarSalgado() {
                return null;
            }

            @Override
            public ItemConsumivel criarAcompanhamento() {
                return new ItemConsumivelMock();
            }
        };
    }

    @Test
    void dadoReceitaPadraoDuplo_quandoGerarCombo_entaoRetornaTresLinhasDePedido() {
        List<ItemPedido> itens = gerenciador.gerarCombo("DUPLO", perfilTeste);

        assertNotNull(itens);
        assertEquals(3, itens.size());
    }

    @Test
    void dadoReceitaPadraoFamilia_quandoGerarCombo_entaoRetornaTresLinhasDePedido() {
        List<ItemPedido> itens = gerenciador.gerarCombo("FAMILIA", perfilTeste);

        assertNotNull(itens);
        assertEquals(3, itens.size());
    }

    @Test
    void dadoReceitaInexistente_quandoGerarCombo_entaoLancaExcecao() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gerenciador.gerarCombo("INEXISTENTE", perfilTeste);
        });

        assertTrue(exception.getMessage().contains("Combo não encontrado"));
    }

    @Test
    void dadoNovaReceita_quandoAdicionadaEGerada_entaoExecutaNovaEstrutura() {
        gerenciador.adicionarNovaReceita("PROMO", perfil -> {
            List<ItemPedido> itens = new ArrayList<>();
            itens.add(new ItemPedidoBuilder(perfil.criarSanduichePrincipal()).comQuantidade(1).build());
            return itens;
        });

        List<ItemPedido> itens = gerenciador.gerarCombo("PROMO", perfilTeste);

        assertNotNull(itens);
        assertEquals(1, itens.size());
    }
}