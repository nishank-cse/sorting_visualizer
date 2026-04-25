FROM eclipse-temurin:17-jdk

WORKDIR /app

# 👇 IMPORTANT: copy backend only
COPY backend /app

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]