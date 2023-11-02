#language: pt
@Test
Funcionalidade: Cenarios de teste

  Cenario: Criando uma conta
    Dado que quero criar o usuario
      | nome  | Rafael Macabu |
      | senha | raaaafaa123      |
      | email | rafaaaaaa@qa.com|
      | admin | true          |
    Quando eu fizer um POST na API de cadastro
    Entao o status code será 201


