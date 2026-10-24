#obraz bazowy
FROM amazoncorretto:17
#zmienna z jarem
ARG JAR_FILE=target/*.jar
#kopiujemy jara do kontenera
COPY ${JAR_FILE} discovery-service.jar
#w jaki sposob uruchomic nasz serwis
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]