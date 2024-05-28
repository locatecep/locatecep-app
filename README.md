# locatecep-app

O `locatecep-app` é um aplicativo de linha de comando que utiliza a biblioteca `locateCep` para determinar se um CEP está dentro da área de entrega de uma empresa. Ele demonstra um exemplo de como a biblioteca `locateCep` pode ser integrada em um sistema para verificar a elegibilidade de entrega com base em CEPs.

## Uso

Para executar o aplicativo, siga os passos abaixo:

1. **Execute o aplicativo:**

   Abra o diretório `...\locatecep-app\locateCep-app_jar` no bash
   
   ```bash
   java -jar locatecep.jar <CEP>
   ```

   Substitua `<CEP>` pelo CEP que você deseja verificar.

   **Saída:**

   ```
   "Entregamos no CEP 12345678 " 
   ```

##  Exemplo de Implementação

O código do aplicativo `locatecep-app` está organizado da seguinte forma:

* **`io.github.loja.entrega.Main`:** Classe principal que inicia a aplicação e recebe o CEP como argumento da linha de comando.

``` java
// Classe principal (ponto de entrada do programa)
public class Main {

    // Método principal (executado ao iniciar o programa)
    public static void main(String[] args) {

        // Verificação de argumentos: espera-se que o usuário forneça um CEP como argumento
        if (args.length != 1) {
            System.err.println("Uso: java -jar locateCep-app.jar <CEP>"); // Mensagem de erro se o CEP não for fornecido
            return; // Encerra o programa
        }

        // Obtenção do CEP do argumento fornecido pelo usuário
        String cep = args[0];

        // Criação de uma estratégia de entrega para estados específicos ("SP", "RJ", "MG")
        DeliveryAreaStrategy strategy = new StateDeliveryStrategy("SP", "RJ", "MG"); 

        // Criação de um verificador de área de entrega, utilizando a estratégia definida
        DeliveryAreaChecker checker = new DeliveryAreaChecker(strategy);

        // Verificação se o CEP está na área de entrega
        boolean entrega = checker.isWithinDeliveryArea(cep);

        // Exibição do resultado da verificação
        if (entrega) {
            System.out.println("Entregamos no CEP " + cep); // Mensagem se entrega for possível
        } else {
            System.out.println("Não entregamos no CEP " + cep); // Mensagem se entrega não for possível
        }
    }
}
```

* **`io.github.loja.entrega.service.DeliveryAreaChecker`:** Classe responsável por verificar se um CEP está dentro da área de entrega, utilizando a estratégia de entrega definida.
  
``` java
public class DeliveryAreaChecker {
    private final CepService cepService;     // Serviço para buscar informações de CEP
    private final DeliveryAreaStrategy deliveryAreaStrategy; // Estratégia para definir área de entrega

    // Construtor: Inicializa o serviço de CEP e a estratégia de área de entrega
    public DeliveryAreaChecker(DeliveryAreaStrategy deliveryAreaStrategy) {
        CepRepository repository = new DatabaseCepRepository(); // Repositório de CEPs (banco de dados)
        this.cepService = new CepService(repository);         
        this.deliveryAreaStrategy = deliveryAreaStrategy; 
    }

    // Método principal: Verifica se um CEP está na área de entrega
    public boolean isWithinDeliveryArea(String cep) {
        try {
            Cep cepInfo = cepService.buscar(cep); // Busca informações do CEP (estado, etc.)
            return deliveryAreaStrategy.isWithinDeliveryArea(cepInfo); // Usa a estratégia para verificar a área
        } catch (Exception e) { // Captura erros durante a busca do CEP (CEP inválido, problemas no banco de dados)
            return false; // Se houver erro, assume que não entrega no CEP
        }
    }
}
```

**Outras Classes:**
* **`io.github.loja.entrega.strategy.DeliveryAreaStrategy`:** Interface que define a estratégia para verificar a elegibilidade de entrega.

* **`io.github.loja.entrega.strategy.StateDeliveryStrategy`:** Implementação da interface `DeliveryAreaStrategy` que verifica se o estado do CEP está dentro da lista de estados permitidos para entrega.

## Dependências

1. O projeto depende da biblioteca `locateCep`, que precisa ser adicionada ao classpath do projeto.
2. Java 65.0 (ou seja, Java 19 ou superior).

## Arquitetura

O aplicativo `locatecep-app` utiliza o padrão de projeto Strategy para definir diferentes estratégias de verificação de área de entrega. Atualmente, a única estratégia implementada é a `StateDeliveryStrategy`, que verifica se o estado do CEP está dentro de uma lista de estados permitidos para entrega.

##  Considerações

* O aplicativo `locatecep-app` é um exemplo simples que demonstra o uso da biblioteca `locateCep`.
* A estratégia de verificação de área de entrega pode ser modificada para atender a diferentes necessidades.
* O aplicativo depende da biblioteca `locateCep`, que deve ser adicionada ao classpath do projeto.

##  Licença

O código fonte do `locatecep-app` está licenciado sob a licença MIT.
