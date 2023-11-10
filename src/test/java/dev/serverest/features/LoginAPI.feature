#language: pt
#encoding: utf-8
@Test
@TestLogin
Funcionalidade: Cenarios de teste de login

  @CriandoUsuario
  Cenario: Fazendo login
    Dado que eu tenho uma conta ja cadastrada
    Quando eu fizer um POST na API de login
    Entao o status code sera 200

  @CriandoUsuario
  Cenario: Falhando login com email/senha invalidos
    Dado que eu tenho uma conta nao cadastrada
    Quando eu fizer um POST na API de login
    Entao o status code sera 401