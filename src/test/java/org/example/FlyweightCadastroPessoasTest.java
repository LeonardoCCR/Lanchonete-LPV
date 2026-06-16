package org.example;

import org.example.lanchonete.Cidade;
import org.example.lanchonete.CidadeFactory;
import org.example.lanchonete.Cliente;
import org.example.lanchonete.PessoaRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlyweightCadastroPessoasTest {

    @Test
        public void deveCompartilharInstanciaDeCidadeUsandoFlyweight() {
            PessoaRepository<Cliente> repoClientes = new PessoaRepository<>();


            Cidade jf1 = CidadeFactory.getCidade("Juiz de Fora", "MG");
            Cliente c1 = new Cliente("1", "Leonardo César", jf1);

            Cidade jf2 = CidadeFactory.getCidade("Juiz de Fora", "MG");
            Cliente c2 = new Cliente("2", "Hugo de Souza", jf2);

            repoClientes.cadastrar(c1);
            repoClientes.cadastrar(c2);

            assertSame(c1.getCidadeResidencia(), c2.getCidadeResidencia(),
                    "O Flyweight falhou: as instâncias de Cidade deveriam ser estritamente a mesma.");

            int totalCidadesAntes = CidadeFactory.getTotalCidades();
            CidadeFactory.getCidade("Juiz de Fora", "MG");
            assertEquals(totalCidadesAntes, CidadeFactory.getTotalCidades(), "A fábrica criou um objeto duplicado no cache.");
        }

        @Test
        public void deveRetornarStringFormatadaCorretamente() {
            Cidade jf = CidadeFactory.getCidade("Juiz de Fora", "MG");
            Cliente cliente = new Cliente("1", "Leonardo César", jf);

            String saida = cliente.obterPessoa();

            assertTrue(saida.contains("id='1'"));
            assertTrue(saida.contains("nome='Leonardo César'"));
            assertTrue(saida.contains("cidade='Juiz de Fora'"));
            assertTrue(saida.contains("uf='MG'"));
        }
}

