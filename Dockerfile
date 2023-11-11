FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
EXPOSE 8089
CMD ["java", "-jar", "/gestion-station-ski-1.0.jar"]