# Build
FROM gradle:8.5 AS BUILD
WORKDIR /app
COPY . .
RUN ./gradlew bootJar

# Package
FROM azul/zulu-openjdk:21
ENV BUILT_JAR=/app/webapp/build/libs/*.jar
WORKDIR /app
COPY --from=BUILD ${BUILT_JAR} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]
