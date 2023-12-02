#language: pt
#encoding: utf-8
@Test
@TestCarrinho
Funcionalidade: Cenarios de teste de carrinho

  Cenario: Cadastrando um produto no carrinho
    Dado que quero adicionar um produto a um carrinho
    Quando eu fizer um POST na API de carrinho
    Entao o status code sera 201

  Cenario: Achando todos os carrinhos
    Quando eu fizer um GET na API de carrinho
    Entao o status code sera 200

  Cenario: Procurando um carrinho pelo ID
    Dado que eu tenho um carrinho com um produto já cadastrado
    Quando eu fizer um GET na API de carrinho com o id
    Entao a quantidade de carrinhos encontrados sera 1