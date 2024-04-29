FROM adoptopenjdk:11-jre-hotspot
ADD build/libs/*.jar qp-assessment.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "qp-assessment.jar"]