#language: pt
#encoding: utf-8
@Test
@TestCarrinho
Funcionalidade: Cenarios de teste de carrinho

  Cenario: Cadastrando um produto no carrinho
    Dado que quero adicionar um produto a um carrinho
    Quando eu fizer um POST na API de carrinho
    Entao o status code sera 201