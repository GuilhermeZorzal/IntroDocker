# Baixa java 8 do docker hub
FROM openjdk:8

# Seta o diretório padrão para app
WORKDIR /app

# Não é necessário mais nada pois o restante das configurações está no docker-compose.yml