
### Spring demo by marekll ###

### Uruchomienie przez Spring boot-a ###

```
$ ./gradlew bootRun
```

### Uruchomienia przez java -jar ###

Build i przygotowanie:

```
$ ./gradlew bootJar
```

Uruchomienie z profilem "test":

```
$ java -jar -Dspring.profiles.active=test build/libs/spring-0.0.1-SNAPSHOT.jar
```

Profil można zmienić też w pliku application.properties

Profil można zmienić na jeden z dostępnych w `src/main/resources/application-[profile].yaml`
Aby profil był dostępny, należy dodać / zaktualizować odpowiedni plik przed zbudowaniem aplikacji.

### Przydatne linki ###

* Swagger na Spring Boot: <http://localhost:8080/swagger-ui.html>


### TODO LIST ###
Uporządkować adaptery, porty zgodnie z dobrymi praktykami związanymi z architekturą heksagonalną
Napisać testy
pełna dokumentacja
docker
rozszerzyć funkcjonalność
dodać jakąś bardziej skomplikowaną logikę biznesową, żeby testy miały większy sens

etc
