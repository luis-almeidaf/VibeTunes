# Vibe Tunes 

## Descrição

- É uma plataforma para criação, gerenciamento e compartilhamento de playlists musicais.
- Os usuários podem criar suas próprias playlists, adicionar músicas a elas e compartilhá-las com outros usuários.
- Usuários comuns podem criar contas, alterar seus dados, criar playlist.
- Usuários administradores podem excluir usuários da plataforma, cadastrar novas músicas, cadastrar artistas.

## Pré-requisito

- Java JDK 21 ou superior
- Maven
- Docker

## Rodando o projeto

**Inicie os containers com o comando**:
```shell
docker-compose up -d
```
Este comando start o PostgreSQL.

**Inicie o projeto com o comando**:

```shell
mvn spring-boot:run 
```
## Instruções de uso
Você pode checkar os endpoints em:
```shell
http://localhost:8080/
```

## Status do projeto

O projeto ainda está em desenvolvimento, serão adicionados testes, documentação de endpoints com Swagger, além da conclusão da criação dos endpoints ainda não finalizados.

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)