FROM docker.io/gradle:8.10-jdk21 AS build

WORKDIR /app

COPY . .

ARG SKIP_TESTS=true

RUN if [ "$SKIP_TESTS" = "true" ]; then gradle bootJar --no-daemon -x test; else gradle bootJar --no-daemon; fi

FROM docker.io/openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=local

CMD ["java", "-jar", "/app/order-app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
