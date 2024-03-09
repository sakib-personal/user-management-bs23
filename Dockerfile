FROM amazoncorretto:17.0.10@sha256:8c61828e295c1c6089367e8588ba54e31b6a490485c2a5180edbef2b256b2d80

COPY ./target/user-management*.jar /app/user_management.jar

WORKDIR /app

EXPOSE 8080

CMD java -jar user_management.jar