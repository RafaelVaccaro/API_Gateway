# Usando a imagem oficial do OpenJDK 17
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado do build do Gradle
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

# Expõe a porta do serviço
EXPOSE 8080

# Comando para iniciar o serviço
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
