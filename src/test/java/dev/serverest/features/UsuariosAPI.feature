#language: pt
#encoding: utf-8
@Test
@TesteUsuarios
Funcionalidade: Cenarios de teste de usuarios

  @CriandoUsuario
  Cenario: Criando uma conta normal
    Dado que quero criar um usuario normal
    Quando eu fizer um POST na API de cadastro
    Entao o status code sera 201

  @CriandoUsuario
  Cenario: Criando uma conta admin
    Dado que quero criar um usuario admin
    Quando eu fizer um POST na API de cadastro
    Entao o status code sera 201

  @CriandoUsuario
  Cenario: Editando uma conta pre-existente
    Dado que eu tenho uma conta ja cadastrada
    Quando eu fizer um PUT na API de cadastro
    Entao o status code sera 200

  @CriandoUsuario
  Cenario: Achando um usuario
    Dado que eu tenho uma conta ja cadastrada
    Quando eu fizer um GET na API de cadastro com o id
    Entao a quantidade de usuarios encontrados sera 1






