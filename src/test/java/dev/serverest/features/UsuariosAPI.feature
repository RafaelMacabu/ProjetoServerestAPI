#language: pt
@Test
Funcionalidade: Cenarios de teste de usuarios

  Cenario: Criando uma conta normal
    Dado que quero criar um usuario normal
    Quando eu fizer um POST na API de cadastro
    Entao o status code será 201

  Cenario: Criando uma conta admin
    Dado que quero criar um usuario admin
    Quando eu fizer um POST na API de cadastro
    Entao o status code será 201

  Cenario: Editando uma conta já criada
    Dado tenho uma conta já cadastrada
    Quando eu fizer um PUT na API de cadastro
    Entao o status code será 200




