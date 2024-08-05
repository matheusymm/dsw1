## Instruções para compilar e executar o sistema:

É necessário instalar o Java, Apache Maven e Apache Tomcat na sua máquina.
  
Após a instalação, basta executar os seguintes comandos:
- Subir o servidor tomcat:
``` Bash
# Na pasta bin do tomcat
./catalina.sh run
```

- Criar e popular o banco de dados:
``` Bash
# No diretório do projeto
psql

# Dentro do terminal interativo do PostgreSQL
\i db/PostgresSQL/create.sql
```

- Compilar e subir o projeto:
``` Bash
mvn package
mvn tomcat7:deploy
```

Em um navegador, abrir o localhost:8080, acessar o Manager App e selecionar a aplicação Bicicleta. Ou abrir em um navegador a URL localhost:8080/Bicicleta.