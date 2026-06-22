package org.example.lanchonete.relatorio;

import org.example.lanchonete.combo.ComboComposite;
import org.example.lanchonete.produtos.Acompanhamento;
import org.example.lanchonete.produtos.BaconExtra;
import org.example.lanchonete.produtos.Bebida;
import org.example.lanchonete.produtos.ItemConsumivel;
import org.example.lanchonete.produtos.Salgado;
import org.example.lanchonete.produtos.Sanduiche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtratoItemVisitorTest {

    private ExtratoItemVisitor visitor;

    @BeforeEach
    void setUp() {
        visitor = new ExtratoItemVisitor();
    }

    @Test
    void dadoBebida_quandoVisitada_entaoCalculaICMSELucro() {
        ItemConsumivel bebida = new Bebida("Refrigerante", 10.0);
        bebida.accept(visitor);

        assertTrue(visitor.getExtrato().contains("ICMS (15%): R$ 1,50"));
        assertTrue(visitor.getExtrato().contains("Lucro Líquido: R$ 8,50"));
    }

    @Test
    void dadoSanduiche_quandoVisitado_entaoCalculaCustoInsumosELucro() {
        ItemConsumivel sanduiche = new Sanduiche("X-Salada", 20.0);
        sanduiche.accept(visitor);

        assertTrue(visitor.getExtrato().contains("Insumos (40%): R$ 8,00"));
        assertTrue(visitor.getExtrato().contains("Lucro Bruto: R$ 12,00"));
    }

    @Test
    void dadoSalgado_quandoVisitado_entaoCalculaCustoOperacionalELucro() {
        ItemConsumivel salgado = new Salgado("Coxinha", 8.0);
        salgado.accept(visitor);

        assertTrue(visitor.getExtrato().contains("Op. Fritura (10%): R$ 0,80"));
        assertTrue(visitor.getExtrato().contains("Lucro Bruto: R$ 7,20"));
    }

    @Test
    void dadoSanduicheDecorado_quandoVisitado_entaoCalculaCustoCustomizado() {
        ItemConsumivel sanduicheComBacon = new BaconExtra(new Sanduiche("Hambúrguer", 10.0));
        sanduicheComBacon.accept(visitor);

        assertTrue(visitor.getExtrato().contains("Lanche Customizado: Hambúrguer + Bacon Extra"));
    }

    @Test
    void dadoComboComposite_quandoVisitado_entaoVisitaFilhosECalculaCombo() {
        ComboComposite combo = new ComboComposite("Combo Feliz", 0.0);
        combo.adicionarItem(new Sanduiche("X-Salada", 20.0));
        combo.adicionarItem(new Bebida("Refrigerante", 10.0));

        combo.accept(visitor);

        assertTrue(visitor.getExtrato().contains("Combo: Combo Feliz"));
        assertTrue(visitor.getExtrato().contains("Insumos (40%): R$ 8,00"));
        assertTrue(visitor.getExtrato().contains("ICMS (15%): R$ 1,50"));
    }

    @Test
    void dadoAcompanhamento_quandoVisitado_entaoCalculaCustoEmbalagemELucro() {
        ItemConsumivel acompanhamento = new Acompanhamento("Batata Frita", 12.0);
        acompanhamento.accept(visitor);

        assertTrue(visitor.getExtrato().contains("Custo Embalagem (5%): R$ 0,60"));
        assertTrue(visitor.getExtrato().contains("Lucro Bruto: R$ 11,40"));
    }
}