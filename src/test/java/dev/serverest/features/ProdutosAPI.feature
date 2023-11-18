#language: pt
#encoding: utf-8

@Test
@TesteProdutos
Funcionalidade: Cenarios de teste de produtos

Cenario: Cadastrando um produto
Dado que quero criar um produto
Quando eu fizer um POST na API de produtos
Entao o status code da API de produtos sera 201