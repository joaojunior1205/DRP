# Use a imagem oficial do OpenJDK 17 como imagem base
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o JAR da aplicação para o diretório de trabalho no contêiner
COPY target/drp-0.0.1-SNAPSHOT.jar .

# Defina o comando para executar a aplicação
CMD ["java", "-jar", "drp-0.0.1-SNAPSHOT.jar"]