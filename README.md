# locatecep-app

O `locatecep-app` é um aplicativo de linha de comando que utiliza a biblioteca `locateCep` para determinar se um CEP está dentro da área de entrega de uma empresa. Ele demonstra um exemplo de como a biblioteca `locateCep` pode ser integrada em um sistema para verificar a elegibilidade de entrega com base em CEPs.

## Uso

Para executar o aplicativo, siga os passos abaixo:

1. **Compile o código:**
   ```bash
   mvn compile
   ```

2. **Execute o aplicativo:**
   ```bash
   mvn exec:java -Dexec.mainClass="io.github.loja.entrega.Main" -Dexec.args="<CEP>"
   ```

   Substitua `<CEP>` pelo CEP que você deseja verificar.

   **Exemplo:**

   ```bash
   mvn exec:java -Dexec.mainClass="io.github.loja.entrega.Main" -Dexec.args="01001-000"
   ```

##  Exemplo de Implementação

O código do aplicativo `locatecep-app` está organizado da seguinte forma:

* **`io.github.loja.entrega.Main`:** Classe principal que inicia a aplicação e recebe o CEP como argumento da linha de comando.
* **`io.github.loja.entrega.service.DeliveryAreaChecker`:** Classe responsável por verificar se um CEP está dentro da área de entrega, utilizando a estratégia de entrega definida.
* **`io.github.loja.entrega.strategy.DeliveryAreaStrategy`:** Interface que define a estratégia para verificar a elegibilidade de entrega.
* **`io.github.loja.entrega.strategy.StateDeliveryStrategy`:** Implementação da interface `DeliveryAreaStrategy` que verifica se o estado do CEP está dentro da lista de estados permitidos para entrega.

## Dependências

O projeto depende da biblioteca `locateCep`, que precisa ser adicionada ao classpath do projeto.

## Arquitetura

O aplicativo `locatecep-app` utiliza o padrão de projeto Strategy para definir diferentes estratégias de verificação de área de entrega. Atualmente, a única estratégia implementada é a `StateDeliveryStrategy`, que verifica se o estado do CEP está dentro de uma lista de estados permitidos para entrega.

##  Considerações

* O aplicativo `locatecep-app` é um exemplo simples que demonstra o uso da biblioteca `locateCep`.
* A estratégia de verificação de área de entrega pode ser modificada para atender a diferentes necessidades.
* O aplicativo depende da biblioteca `locateCep`, que deve ser adicionada ao classpath do projeto.

##  Licença

O código fonte do `locatecep-app` está licenciado sob a licença MIT.