FROM openjdk:17-jdk
COPY target/RequirementsLookup.jar RequirementsLookup.jar
EXPOSE 8020
ENTRYPOINT ["java", "-jar", "RequirementsLookup.jar"]