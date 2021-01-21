## Notas Importantes sobre o projeto

### 1 - CLIENTE
Como o documento descritivo do projeto não define restrições ou regras de como devem ser cadastrados os clientes e conta corrente em relação a agência, foi disponibilizado 2 opções de cadastro de Clientes:

 - 1.1 - Informando a agência, e neste caso será validada;
 - 1.2 - Não informando a agência, neste caso o cliente será cadastrado numa agência previamente cadastrada no banco de dados.

 Obs.: A API disponibiliza o cadastro de Agências.

### 2 - CONTA CORRENTE

Foi utilizado o CPF/CNPJ do cliente como número da conta;

### 3 - O projeto está com os 3 níveis de testes:

 - 3.1 - Testes unitários
 - 3.2 - Testes de integração
 - 3.3 - Testes de API

### 4 - Deploy

O deploy do projeto foi realizado no ambiente da Amazon WS disponível na url abaixo:
- http://ec2-54-207-62-145.sa-east-1.compute.amazonaws.com:8081

###### Obs 1: Caso NÃO esteja disponível no momento de testar na uri acima avise para subir. Não coloquei a tempo o app como serviço.

###### Obs 2: Na AWS está escutando na porta 8081, mas quando for subir o projeto localmente, está na padrão: 8080.
