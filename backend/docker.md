# Fish Stock

### [See preview](https://antoniosgarbi.github.io/GestorPeixaria/)

### This project has features such as:
- ##### Customer Registration
- ##### Employee Registration
- ##### Supplier Registration
- ##### Product Registration
- ##### Register of cash inflows and outflows
- ##### Finance Scheduling
- ##### Report generation

#### and many others, see all features in [here](https://gestor-peixaria.herokuapp.com/swagger-ui/index.html#/)

## Installation

``` 
docker run --net=host -p 8080:8080 -e MAIL_USERNAME={your_email@email.com} -e MAIL_PASSWORD={password} -e MAIL_HOST={smtp-mail.outlook.com} -e GENERATE_KEY={encripted0nBoot} antoniosk/gestor_peixaria:latest
```
#### [docker-compose file](https://github.com/AntonioSgarbi/GestorPeixaria/blob/main/backend/docker-compose.yml): 
```
version: '2'

services:
  app:
    image: 'antoniosk/gestor_peixaria:latest'
    container_name: gestor
    depends_on:
      - db
    links:
      - db
    environment:
      - SERVER_PORT=8080
      - DB_HOST=db
      - DB_PORT=5432
      - DB_USER=postgres
      - DB_PASSWORD=postgres
      - DB_NAME=gestor_peixaria
      - MAIL_HOST=smtp-mail.outlook.com
      - MAIL_PORT=587
      - MAIL_PROTOCOL=smtp
      - MAIL_USERNAME=username
      - MAIL_PASSWORD=password
      - UUID_JWT_SECRET=secret
      - JWT_EXPIRATION_TIME=3600
      - JWT_REFRESH_TIME=3600
      - GENERATE_KEY=senha
      - URL_CORS=http://localhost:4200
  db:
    image: 'postgres:14.1-alpine'
    container_name: db
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
      - POSTGRES_DB=gestor_peixaria
```


```
docker-compose up -d
```
## Environment Variables

- `SERVER_PORT`=8080
- `DB_HOST`=db
- `DB_PORT`=5432
- `DB_USER`=postgres
- `DB_PASSWORD`=postgres
- `DB_NAME`=db_name
- `MAIL_HOST`=smtp-mail.outlook.com
- `MAIL_PORT`=587
- `MAIL_PROTOCOL`=smtp
- `MAIL_USERNAME`=username
- `MAIL_PASSWORD`=password
- `UUID_JWT_SECRET`=secret
- `JWT_EXPIRATION_TIME`=3600
- `JWT_REFRESH_TIME`=3600
- `GENERATE_KEY`=passwordPrintedOnBoot
- `URL_CORS`=
  - http://localhost:4200
  - http://localhost:4201
