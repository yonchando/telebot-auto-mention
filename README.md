# telebot-auto-mention

## How to start bot

### Step 1
Clone project repo

### Step 2
Set up postgresql database

set up enviroment variable like in .env-example

or run with docker by copy `.env-example` to `.env`

`docker-compose up -d`

## How to deploy to production

`mvn clean package`

Upload jar file in target/${name}-${version}.jar then run

`java -jar ${name}-${version}.jar`
> Don't forget to set up enviroment variable from .env-example
