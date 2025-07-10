# Imagen base de Java
FROM openjdk:17-jdk-slim

# Crea el directorio de trabajo
WORKDIR /app

# Copia el jar generado
COPY target/*.jar app.jar

# Expone el puerto (acordate de mapearlo en docker-compose)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]