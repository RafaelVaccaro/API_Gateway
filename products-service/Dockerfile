# Usando a imagem oficial do OpenJDK 17 como base para a construção do container
# A versão slim é uma imagem mais enxuta do JDK 17, com menos pacotes pré-instalados
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container.
# Todos os comandos subsequentes serão executados a partir deste diretório
WORKDIR /app

# Copia o arquivo JAR gerado pelo Gradle (resultado do build) para o diretório de trabalho do container.
# O arquivo JAR contém o serviço que será executado
COPY build/libs/products-service-0.0.1-SNAPSHOT.jar products-service.jar

# Copia o script 'wait-for-it.sh' para o container. Este script será usado para aguardar o banco de dados ficar disponível
COPY wait-for-it.sh /wait-for-it.sh

# Garante que o script 'wait-for-it.sh' tenha permissão de execução dentro do container
RUN chmod +x /wait-for-it.sh

# Expõe a porta 8082 para que o serviço de produtos fique acessível externamente
# O serviço da aplicação Spring Boot será executado nesta porta
EXPOSE 8082

# Comando para aguardar o banco de dados ficar disponível e depois iniciar o serviço
ENTRYPOINT ["/wait-for-it.sh", "postgres-products-db:5432", "--", "java", "-jar", "products-service.jar"]
