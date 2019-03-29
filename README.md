# Longeco

Den nye sykkelapplikasjonen for Ambita.

Hvorfor dette navnet? Longeco betyr distanse/lengde på esperanto.

Den er foreløpig helt enkel, må inn med brukere og aksjon manuelt med sql før bruk. Se oppskrift lenger ned.

Når brukere registrerer seg vil en se en oppsummering på første siden (home i menyen) og en registrerer i uke bildet (week i menyen)

### Opprett database lokalt:

Logg inn i **psql**:

Windows: ```psql -U postgres``` 

Unix/Mac: ```sudo -u postgres psql```

Opprett database og bruker ved å kjøre dette i **psql**:
```
CREATE USER longeco WITH PASSWORD 'longeco';

CREATE DATABASE longeco WITH ENCODING='UTF8' OWNER=longeco CONNECTION LIMIT=-1;

GRANT ALL PRIVILEGES ON DATABASE "longeco" to longeco;
```

Kjør applikasjonen lokalt for å få opprettet tabeller.

## Kjøre lokalt første gang:
Fra kommandolinjen, gå inn i mappen/modulen longeco: ```mvn spring-boot:run``` eller kjør i IntelliJ (working directory må settes)

### Bruker for å logge seg på:

Før en starter å teste applikasjonen må man legg inn bruker i user tabellen med sql i longeco databasen.
OBS: noen av tabellnavnene og kolonnennavnene må ha anførselstegn rundt fordi dette er reserverte navn.

Denne kan kjøres i feks dbvisualizer, :

```insert into "user" (active, email, name, password, username) values (true, 'testbruker@ambita.com', 'Test Bruker', '$2a$10$wZHQ/NJ35YnqCUTH8OgpqeSK4XzH2FsU9czxktYC.fGQkWnloWAvW', 'test')```

Hvis du kjører fra psql, skift til riktig database først: ```\c longeco```.

Ønsker du å lage et nytt passord så kan man kjøre denne testen **LongecoApplicationTests.encryptPassword()** 
i debug og kopiere det krypterte passordet og så legge det inn i databasen. Eller gjøres i GUI.

### Legge inn et event:
Man trenger også et event i databasen før en kan teste registrering.
```insert into event (active, begin, "end", name, uid) values (true, '2018-04-23', '2018-10-01', 'Sykle til jobben 2018', '9e3153ed-9754-44ee-96a2-df2d9bffc88c')```

Start applikasjonen i IntelliJ (evt på kommandolinjen)

### Url til longeco lokalt:
http://localhost:8080/longeco/

Nå kan du logge på med brukernavn: test   passord: test
og skal få opp mulighet til å registrere deg på aksjonen.

### Rulle ut på ekstern server

### Tips
When created a release branch, check out master and change version to next snapshot (old: 49.0-SNAPSHOT wil be changed to 50.0-SNAPSHOT)
Set version to all pom's:  
```mvn versions:set -DnewVersion=1.1.0-SNAPSHOT```
