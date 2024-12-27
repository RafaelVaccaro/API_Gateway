# Usando a imagem oficial do OpenJDK 17
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado do build do Gradle
COPY build/libs/products-service-0.0.1-SNAPSHOT.jar products-service.jar

# Copia o script 'wait-for-it.sh' para o container. Este script será usado para aguardar o banco de dados ficar disponível
COPY wait-for-it.sh /wait-for-it.sh

# Garantir que o script tenha permissão de execução
RUN chmod +x /wait-for-it.sh

# Expõe a porta do serviço
EXPOSE 8082

# Comando para aguardar o banco de dados ficar disponível e depois iniciar o serviço
ENTRYPOINT ["/wait-for-it.sh", "postgres-products-db:5432", "--", "java", "-jar", "products-service.jar"]
