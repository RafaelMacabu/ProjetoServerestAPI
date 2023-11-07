#language: pt
@Test
Funcionalidade: Cenarios de teste de usuarios

  @CriandoUsuario
  Cenario: Criando uma conta normal
    Dado que quero criar um usuario normal
    Quando eu fizer um POST na API de cadastro
    Entao o status code será 201

  @CriandoUsuario
  Cenario: Criando uma conta admin
    Dado que quero criar um usuario admin
    Quando eu fizer um POST na API de cadastro
    Entao o status code será 201

  @CriandoUsuario
  Cenario: Editando uma conta já criada
    Dado tenho uma conta já cadastrada
    Quando eu fizer um PUT na API de cadastro
    Entao o status code será 200




