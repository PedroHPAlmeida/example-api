FROM openjdk:11
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx512m", "-jar", "-Dserver.port=${PORT}", "-Dspring.profiles.active=${PROFILE}", "/app.jar"]
