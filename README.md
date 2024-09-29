# IntroDocker

Explicações mais detalhadas do funcionamento e termos específicos em [IntroDocker](./IntroDocker.md)

## Executando
O jeito mais fácil de executar containers Docker é através do [Docker Desktop](https://docs.docker.com/compose/install/), que já possui tudo que é necessário para execução.

### Usando o make

Na primeira execução é necessário dar o `build` da imagem. Basta executar o `make build` pelo terminal. Pode levar um tempinho para configurar a imagem. 

Após esse primeiro `build`, as demais execuções podem ser feitas apenas com `make`. 
