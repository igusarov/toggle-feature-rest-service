## Environment requirements
Java 8<br>
MySQL 8

## Run app
Import db dump file `db_feature_toggle.dump.sql`<br>
Set up db connection in `src/main/resources/application.properties`<br>
Run `mvn spring-boot:run`. API will be available `http://localhost:8080/api/v1/features`<br>

## Run unit tests
Run `mvn test`
