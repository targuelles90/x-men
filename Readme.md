## X-Men Project
Api that allows to register human Dna to know if they are mutants or not.
It also provides some statistics, to know the ratio between humans and mutants.

### How to start
1. Clone te project
2. Run docker-compose
```sh
docker-compose up -d
```
3. Explore the API
   1. [Local](http://localhost:8080/swagger-ui.html)
   2. [Heroku](https://api-x-men.herokuapp.com/swagger-ui/index.html)
4. Have fun!

### How to stop
```sh
docker-compose down
```

### How to run tests
```sh
mvn clean test
```

### Environment variables
Environment variable | Description | Default value
--- | --- | ---
`SPRING_REDIS_HOST` | The host of the redis. | `localhost`
`SPRING_REDIS_PORT` | The port of the redis. | `6379`
`SPRING_DATASOURCE_URL` | The url of the database. | `jdbc:mysql://localhost:3306/postgres`
`SPRING_DATASOURCE_USERNAME` | The username of the database. | `postgres`
`SPRING_DATASOURCE_PASSWORD` | The password of the database. | `secret`
`XMEN_MUTANT_DNA_SIZE` | The size of the dna to be considered as mutant. | `4`
`XMEN_MUTANT_MIN_SEQUENCES` | Minimum number of sequences to be considered as mutant. | `2`

### Endpoints
- `POST /mutant` : To know if a human is mutant or not.
- `GET /stats` : To know the ratio between humans and mutants.

#### Example
Human check:
```sh
curl --location --request POST 'http://localhost:8080/v1/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}'
```
Get Stats:
```sh
curl --location --request GET 'http://localhost:8080/v1/stats'
```
