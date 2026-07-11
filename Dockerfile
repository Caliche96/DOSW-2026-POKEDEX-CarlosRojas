# ── Stage 1: Build ──────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copiar archivos de Maven primero (cache de dependencias)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente y compilar
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ── Stage 2: Runtime ─────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar solo el JAR del stage anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto expuesto
EXPOSE 8080

# Variables de entorno por defecto (se pueden sobreescribir)
ENV SPRING_PROFILES_ACTIVE=prod

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]