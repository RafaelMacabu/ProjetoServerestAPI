#language: pt
#encoding: utf-8

@Test
@TesteProdutos
Funcionalidade: Cenarios de teste de produtos

  @CriandoUsuario
  @CriandoProduto
  Cenario: Cadastrando um produto
    Dado que quero criar um produto
    Quando eu fizer um POST na API de produtos
    Entao o status code sera 201

  @CriandoUsuario
  @CriandoProduto
  Cenario: Procurando um produto pelo ID
    Dado que tenho um produto ja cadastrado
    Quando eu fizer um GET na API de produtos com o id
    Entao a quantidade de produtos encontrados sera 1


  Cenario: Procurando um produto
    Quando eu fizer um GET na API de produtos
    Entao o status code sera 200

  @CriandoUsuario
  @CriandoProduto
  Cenario: Editando um produto pre-existente
    Dado que tenho um produto ja cadastrado
    Quando eu fizer um PUT na API de produtos
    Entao o status code sera 200
