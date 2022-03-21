## Star Wars Resistence Social Network

DOCS:
```
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui.html
```

Reports:
```
http://localhost:8080/swapi/records (JSON)
http://localhost:8080/swapi/records/showRecords (Thymeleaf)
```

**Detalhes**
  O inventário do rebeldes deve ser informado item a item, a funcionalidade de quantidade não foi implementada.
  Toda a interface da API está descrita no api-docs ou swagger-ui.
#####TODO
  - [ ] Add item quantity functionality. 
  - [ ] Finish integration testing
  - [ ] Finish data/persistente testing with DataJpaTesting
  - [ ] Add Redis Cache
  - [ ] Add JWT Auth
  - [ ] Improve unit/data testing with Test Containers
#### API REST
- [x] Adicionar rebeldes
  - nome, idade, gênero, localização (latitude, longitude e nome, na galáxia, da base ao qual faz parte).
  - Um rebelde também possui um inventário que deverá ser passado na requisição com os recursos em sua posse.
  - Não podem Adicionar/Remover itens do seu inventário.
  - Seus pertences devem ser declarados quando eles são registrados no sistema.
    Após isso eles só poderão mudar seu inventário através de negociação com os outros rebeldes.  
- [x] Atualizar localização do rebelde 
  - Capacidade de reportar sua última localização, armazenando a nova latitude/longitude/nome
  (não é necessário rastrear as localizações, apenas sobrescrever a última é o suficiente).
- [x] Reportar o rebelde como um traidor
  - Um traidor não pode negociar os recursos com os demais rebeldes, não pode manipular seu inventário,
  nem ser exibido em relatórios.
  - Um rebelde é marcado como traidor quando, ao menos, três outros rebeldes reportarem a traição.
  - Uma vez marcado como traidor, os itens do inventário se tornam inacessíveis.
- [x] Negociar itens
  - Poderão negociar itens entre eles.
  - Respeitar a tabela de pontos.
  - Ambos os lados deverão oferecer a mesma quantidade de pontos.
  - A negociação em si não será armazenada, mas os itens deverão ser transferidos de um rebelde a outro.
  

|Item| Pontos |
|:---:|:------:|
|Arma|   4    |
| Munição  |   3    |
|   Água   |   2    |
|  Comida  |   1    |
 

#### Relatórios

- [x] Porcentagem de traidores.
- [x] Porcentagem de rebeldes.
- [x] Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde).
- [x] Pontos perdidos devido a traidores.

#### Requisitos não funcionais

- [x] Documentar os endpoints da sua API e fornecer uma forma de usá-los. (Swagger Lib)
- [x] Sua API deve estar minimamente coberta por testes (Unitários e/ou integração).

##### Powered By
- Java 17
  - Gradle
  - Lombok
  - [ ] Redis Cache
  - Mockito/JUnit 5
  - Thymeleaf
  - OpenAPI
  - SpringBoot (2.6.4)
    - H2
    - Data JPA
    - DevTools
    - Web
    - Validation