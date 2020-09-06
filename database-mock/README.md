# Liquibase migreringsguide for docker image
Vi kjører opp en lokal docker container for databasen vår. Imaget må først bygges før det kan kjøres opp. Man kan enten bruke ``start-database.bat`` for å bruke en prekonfiguret ``docker run``-kommando, eller så kan man starte opp selv slik som under.

```shell script
# Når man er i denne (database-mock) mappen, bygg imaget slik
docker image build -t cook-book-db .

# Kjør opp containeren slik (antar at cook-book-network allerede finnes)
docker run -p 5432:5432 --name cook-book-db --net cook-book-network -d cook-book-db
```

## pgAdmin mot docker containeren
For at pgadmin skal kunne kommunisere med databasen må man oppgi den lokale ip-adressen som docker gir containeren. Kan finnes ved å kjøre `docker inspect` på den kjørende containeren og se på verdien for `IPAddress`.
```shell script
# Antar at containeren er kjørt opp med --name cook-book-db
docker inspect cook-book-db
>
                      "NetworkID": "9f5003c39b7f39e92949a5f7be5bae05eaf573588ea308db1eae7179364be7df",
                      "EndpointID": "d84c96561cac9d6cb500a946ed35dd543fc90d24d9da99108c62f4a5342ab3d5",
                      "Gateway": "172.21.0.1",
                      "IPAddress": "172.21.0.3",    ## Det er denne ipadressen man vil ha
                      "IPPrefixLen": 16,
                      "IPv6Gateway": "",
                      "GlobalIPv6Address": "",
                      "GlobalIPv6PrefixLen": 0,
                      "MacAddress": "02:42:ac:15:00:03",
                      "DriverOpts": null
                  }
              }
          }
      }
  ]
```

# Oppdatering / endring av databasen
Om man har laget nye changelog-filer så må man også lage et nytt image av databasen. For å få generert nødvendig SQL for databasens oppbygging må man i rotmappen kjøre et maven script mot en helt tom database.
1. Stopp ev. kjørende prosesser på port 5432. Kjør så opp en tom postgres docker container, f.eks ``docker run -p 5432:5432 --net cook-book-network -e POSTGRES_PASSWORD=password -d --name empty-db postgres``
2. I rotmappen, kjør ``mvn liquibase:updateSQL``. Det vil da generes en ny ``migrate.sql`` fil i mappen ``target/liquibase``.
3. Bytt ut den eksisterende ``database-mock/02_migrate.sql`` med ``target/liquibase/migrate.sql``.
4. Bytt ev. script som fyller inn data i databasen med nye script som passer til den nye databasen.
5. Kjør ``docker image build -t cook-book-db .``. 
6. Kjør opp det nye imaget