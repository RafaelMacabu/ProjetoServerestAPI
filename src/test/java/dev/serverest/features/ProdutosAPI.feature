#language: pt
#encoding: utf-8

@Test
@TesteProdutos
Funcionalidade: Cenarios de teste de produtos

  @CriandoUsuario
  Cenario: Cadastrando um produto
    Dado que quero criar um produto
    Quando eu fizer um POST na API de produtos
    Entao o status code sera 201

  @CriandoUsuario
  Cenario: Procurando um produto pelo ID
    Dado que tenho um produto ja criado
    Quando eu fizer um GET na API de produtos com o id
    Entao a quantidade de produtos encontrados sera 1
