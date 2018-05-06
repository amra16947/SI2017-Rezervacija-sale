# SI2016_TIM9 - Wise Solutions 1001

##### Deploy aplikacije je na sljedećem linku: https://broad-territory.surge.sh/

### Uputstvo za postavljanje projekta
Instalirati node.js (sa npm)
  - https://nodejs.org/en/

Instalirati Ember CLI
```sh
  $ npm install -g ember-cli
```

Instalirati potrebne pakete za build-anje Ember aplikacije
```shs
  $ cd frontend && npm install
```
Instalirati Eclipse Neon.3

Instalirati MySQL 5.7.18
  - prijaviti se na bazu kao root
  - napraviti bazu  pod nazivom tim9
  - potrebno je kreirati novog korisnika i dodati mu privilegije nad bazom tim9
  - CREATE USER 'EtfSI2016'@'localhost' IDENTIFIED BY '2016SIEtf';
  - GRANT ALL PRIVILEGES ON tim9.* TO 'EtfSI2016'@'localhost';

Kreirati tabele u bazi
  - Tabele se automatski kreiraju ukoliko se iz početenog foldera izvrši naredba (koju je potrebno prilagoditi operativnom sistemu) 

```sh
 $ ./mvnw spring-boot:run 
``` 

  - Tabele se mogu kreirati i ručno tako što će se importovati uključeni dump.sql fajl

Kako pokrenuti Ember aplikaciju?
  - Ember se pokreće upotrebom ember-cli alata, i to izvršenjem naredbe: "cd frontend && ember serve"
  - Ember aplikacija će biti dostupna na http://localhost:4200/

Kako pokrenuti Java backend?
  - Iz početnog foldera izvršiti naredbu (prilagoditi operativnom sistemu) 

```sh
 $ ./mvnw spring-boot:run 
``` 
  - URL Java API-a će pisati u izlazu komande

