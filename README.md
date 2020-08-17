
# Web API Produto

Esforço inicial para abordagem de Micro servico da Web Api EcommerceAPI  da Big Corp S/A 

## Pilha tecnológica
Projeto elaborado utilizando Spring Boot versão 2.3.3, utilizando a pilha tecnológica apresentada a baixo:
- __Spring Data__ – Persistência de dados
- __Spring Cache__ - Cache de recursos
- __Spring Security__ - Segurança autorização e autenticação
- __ Jjwt__ - Administração do Token de Autenticação
- __Spring Actuator__ - Fornecimento de dados sobre a saúde da Web Api
- __Boot Admin__ - Envio de dados obtidos do Actuator para o Spring Boot Adimin, que por sua vez fornece  a interface gráfica. Mais informações [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin)
- __H2__ –-    Banco de Dados
- __Spring Fox__ - Documentação da API para Swagger 2
- __Javax Validation__ –    Validação de Beans

### Observações
Consta no projeto o arquivo data.sql, este arquivo é responsável pela criação de dados no arranque da Web API. Caso o banco tenha sido criado anteriormente, será exibido um erro em console que pode ser desprezado, pois refere-se a nova tentativa de criar os dados.
Talvez seja necessário reconhecer o Certificado Digital como seguro. Pode ser feito pelo Browser acessando a URL de documentação da API.

### Teste da WebApi
O teste da API pode ser feito pela interface do Swagger Disponível pela URL  [https://localhost:3001/v1/swagger-ui/](https://localhost:3001/v1/swagger-ui/)
O projeto fui constituído utilizando o Eclipse