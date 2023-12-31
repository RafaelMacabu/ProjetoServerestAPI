#language: pt
#encoding: utf-8

@Test
@TesteProdutos
Funcionalidade: Cenarios de teste de produtos

  Cenario: Cadastrando um produto
    Dado que quero criar um produto
    Quando eu fizer um POST na API de produtos
    Entao o status code sera 201

  Cenario: Procurando um produto pelo ID
    Dado que tenho um produto ja cadastrado
    Quando eu fizer um GET na API de produtos com o id
    Entao a quantidade de produtos encontrados sera 1

  Cenario: Procurando todos os produtos
    Quando eu fizer um GET na API de produtos
    Entao o status code sera 200

  Cenario: Editando um produto pre-existente
    Dado que tenho um produto ja cadastrado
    Quando eu fizer um PUT na API de produtos
    Entao o status code sera 200
