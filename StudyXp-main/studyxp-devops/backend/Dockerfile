# --- Estágio 1: BUILD ---
# Usamos uma imagem completa do Maven para construir nosso projeto
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Define o diretório de trabalho dentro do container de build
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .

# Baixa todas as dependências do projeto
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Executa o build, pulando os testes para ser mais rápido
RUN mvn package -DskipTests


# --- Estágio 2: RUN ---
# Usamos uma imagem leve, apenas com Java, para rodar a aplicação
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho na imagem final
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio "builder"
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta que o Spring Boot usa
EXPOSE 8080

# Comando que será executado quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
