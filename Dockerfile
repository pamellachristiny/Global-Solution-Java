# Imagem base com Java 17
FROM eclipse-temurin:17-jre

# Diretório dentro do container
WORKDIR /app

# Copia todo o conteúdo da pasta quarkus-app gerada pelo Maven
COPY target/quarkus-app /app/

# Porta usada pelo seu Quarkus em produção
EXPOSE 8081

# Comando de inicialização
CMD ["java", "-jar", "quarkus-run.jar"]
