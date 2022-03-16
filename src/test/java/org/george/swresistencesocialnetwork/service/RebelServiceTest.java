package org.george.swresistencesocialnetwork.service;

import org.george.swresistencesocialnetwork.dto.RebelDTO;
import org.george.swresistencesocialnetwork.repository.RebelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Rebel Service Test")
public class RebelServiceTest {

    @Autowired
    public RebelRepository rebelRepository;

    public RebelService rebelServiceImp;

    @BeforeEach
    void init() {
        rebelServiceImp = new RebelService(rebelRepository);
    }

    @Test
    @DisplayName("rebel service is not null")
    void test() {
        Assertions.assertNotNull(rebelServiceImp);
    }

    /*
    adicionar rebelde
	adicionar rebelde falhando se faltar qualquer valor
	verificar se inventario está presente
	tentar readicionar rebelde falha
	tentar readicionar rebelde com novo inventario deve falhar
	atualizar localização rebelde
	localização inválida deve falhar
	reportar rebelde como traidor
	negociar com traidor deve falhar
	rebelde com pelo menos tres acusações deve ser marcado como traidor
	rebelde com menos de três acusações deve conseguir negociar
	negociar itens
	negociar lista vazia deve falhar
	respeitar tabela de pontos, listas devem contar mesma quantidade de pontos
	verificar se lista é válida (existe no inventário do rebelde)
	verificar se relatório foi gerado corretamente verificando valores de exemplo
	alterar banco e verificar se relatório responde de acordo
	verificar quando rebelde referencia a si mesmo, bloquear
	verificar que um traidor não pode reportar um rebelde
	testar item invalidos
	testar dados invalidos
     */
}
