package org.example.lanchonete.combo;

import org.example.lanchonete.produtos.ItemConsumivel;
import org.example.lanchonete.relatorio.RelatorioVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComboCompositeTest {

    private ItemConsumivel lanche;
    private ItemConsumivel bebida;
    private ComboComposite comboSimples;


    private static class ItemMock implements ItemConsumivel {
        private final String descricao;
        private final double preco;

        public ItemMock(String descricao, double preco) {
            this.descricao = descricao;
            this.preco = preco;
        }

        @Override
        public String getDescricao() { return this.descricao; }

        @Override
        public double getPreco() { return this.preco; }

        @Override
        public boolean isLiquido() {
            return ItemConsumivel.super.isLiquido();
        }

        @Override
        public void accept(RelatorioVisitor visitor) {

        }
    }

    @BeforeEach
    public void setUp() {

        lanche = new ItemMock("Burguer", 20.00);
        bebida = new ItemMock("Suco", 10.00);

        comboSimples = new ComboComposite("Combo Casal", 0.10);
    }

    @Test
    public void testCalcularPrecoComDesconto() {
        comboSimples.adicionarItem(lanche);
        comboSimples.adicionarItem(bebida);

        double precoFinalEsperado = 27.00;

        assertEquals(precoFinalEsperado, comboSimples.getPreco(), 0.001);
    }

    @Test
    public void testGerarDescricaoFormatada() {
        comboSimples.adicionarItem(lanche);
        comboSimples.adicionarItem(bebida);

        String descricaoEsperada = "Combo Casal (Burguer + Suco)";

        assertEquals(descricaoEsperada, comboSimples.getDescricao());
    }

    @Test
    public void testRemoverItemModificaPrecoEDescricao() {
        comboSimples.adicionarItem(lanche);
        comboSimples.adicionarItem(bebida);

        comboSimples.removerItem(bebida);

        assertEquals(18.00, comboSimples.getPreco(), 0.001);
        assertEquals("Combo Casal (Burguer)", comboSimples.getDescricao());
    }

    @Test
    public void testPrecoComboVazioRetornaZero() {
        // Um combo recém-criado sem elementos deve zerar os cálculos e não gerar NullPointerException
        assertEquals(0.00, comboSimples.getPreco(), 0.001);
        assertEquals("Combo Casal ()", comboSimples.getDescricao());
    }

    @Test
    public void testCompositeEmMultiplosNiveis() {

        comboSimples.adicionarItem(lanche);

        // Criae um combo com 20% de desconto (0,20)
        ComboComposite superCombo = new ComboComposite("Super Combo Familia", 0.20);

        ItemConsumivel porcaoBatata = new ItemMock("Batata Frita", 12.00);

        superCombo.adicionarItem(comboSimples);
        superCombo.adicionarItem(porcaoBatata);

        // Combo, resultado esperado :
        // Preço: comboSimples = 18,00
        // Preço  batata = 12,00
        // Soma bruta do super combo = 30,0
        // Desconto --> super combo 20% = 6,00
        // Preço final do super combo esperado = 24,00

        assertEquals(24.00, superCombo.getPreco(), 0.001);

        String descricaoArvoreEsperada = "Super Combo Familia (Combo Casal (Burguer) + Batata Frita)";
        assertEquals(descricaoArvoreEsperada, superCombo.getDescricao());
    }
}