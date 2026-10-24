#obraz bazowy
FROM amazoncorretto:17
#zmienna z jarem
ARG JAR_FILE=target/*.jar
#kopiujemy jara do kontenera
COPY ${JAR_FILE} amount-service.jar
#w jaki sposob uruchomic nasz serwis
ENTRYPOINT ["java", "-jar", "/amount-service.jar"]