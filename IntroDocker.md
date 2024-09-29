# IntroDocker

## Sumário 

- [IntroDocker](#introdocker)
  - [Sumário](#sumário)
  - [Funcionamento do Docker](#funcionamento-do-docker)
    - [Docker Image](#docker-image)
      - [Listando as imagens via terminal:](#listando-as-imagens-via-terminal)
    - [Container](#container)
      - [Listando os containers:](#listando-os-containers)
    - [Dockerfile](#dockerfile)
    - [.dockerignore](#dockerignore)
    - [Docker Compose](#docker-compose)
  - [Outros comandos úteis](#outros-comandos-úteis)
- [Debbuging](#debbuging)

## Funcionamento do Docker

TODO: Explicar como o docker funciona

Para entender e utilizar o docker, vamos primeiros entender alguns conceitos

### Docker Image

Uma `image` é uma _blueprint_ de um container. A imagem é um arquivo estático (não pode ser modificado) que especifíca instruções de como construir o container.

As imagens são construídas a partir do `DockerFile`, dentro do diretório.

#### Listando as imagens via terminal: 

```bash
docker images
```

### Container

O `Container` é uma instância de uma imagem. Ele quem efetivamente é capaz de rodar uma aplicação sua. Eles são isolados uns dos outros e podem ser dinâmicos: diferente das imagens, podem ser manipulados (podem ser stopped, started, restarted, etc).

#### Listando os containers:

```bash
docker ps // Lista os containers em execução
docker ps -a // Lista os containers (incluindo os parados)
```

### Dockerfile

O DockerFile especifíca o passo a passo para montar a imagem. Exemplo de DockerFile (junto com a explicação de cada comando):
___OBS___.: Antes de mais nada, é necessário ressaltar que são utilizados dois ambientes no DockerFile: a estrutura de diretórios que estará DENTRO da imagem, e a estrutura presente no diretório local. Isso ficará mais claro no comando RUN

```DockerFile
# Seleciona uma pré-construída imagem do Docker Hub (Site do Docker que armazena imagens). No nosso caso, java:
FROM openjdk:8
# Os ":" especificam uma versão específica. Quando não usado, pega a última

# RUN executa comandos dentro da imagem que será construída. Por exemplo, criar uma pasta "/app" DENTRO da imagem
RUN mkdir /app
# Podem existir vários RUN dentro de um DockerFile

# WORKDIR especifíca o diretório principal da imagem, onde os proxímos comandos serão utilizados
WORKDIR /app

# Uma imagem executa um código ou aplicação utilizando as dependências específicas dentro do DockerFile (o que foi específicado no FROM pro exemplo. Para mover a aplicação para dentro do container, usamos o COPY)
COPY . /app 
# Copia tudo do diretório local com o DockerFile (o ".") para a pasta "/app" da imagem.

# Normalmente uma aplicação pode usar várias bibliotecas e linguagens para funcionar (as dependencias). Nesse ponto pode-se executar um RUN para instalar outras dependencias
RUN instalando dependencias(o comando pode variar dependendo da implementação usada)

# EXPOSE expõe uma porta para acessar a imagem (acessar um app web pelo navegador por exemplo). No nosso caso não há necessidade
# EXPOSE 5000 -> container disponível pela porta 5000

# Todos os comandos anteriores dizem respeito a criação da imagem. O CMD é o comando que é executado quando executamos docker run, ou seja, rodamos o container
CMD echo "Hello Docker"

# Outra possibilidade é utilizar o ENTRYPOINT, que executa junto com o CMD. Executando o projeto java agora:
ENTRYPOINT ["java"]
CMD ["App"]
```

Para executar o dockerfile (criar a imagem):

```bash
docker build -t nome .
```
Onde `nome` é o nome da imagem e `.` especifíca a localização do dockerfile (no nosso caso, no root do diretório)


### .dockerignore

Ignora files durante o `COPY` do DockerFile

### Docker Compose

Muitas vezes é necessário executar containers que estão associados entre si. O [docker-compose.yml](./docker-compose.yml) especifica configurações mais avançadas de como configurar os containers.

No nosso caso, ele é util para setar os arquivos do diretório local como sendo os mesmos utilizados pelo docker. Isso faz com que não seja necessário realizar o rebuild da imagem toda vez que os arquivos forem alterados. 

## Outros comandos úteis

Executa o servico especificado dentro do docker-compose.yml
```bash
docker-compose run servico
```

# Debbuging

As vezes é necessário executar uma bash ou shell dentro do container, o que ajuda no debug. Isso pode ser feito com:

```bash
docker run -it container sh

# Ou

docker run -it container bash
```
Ou, usando o docker-compose: 
```bash
docker-compose run service sh
```