# Bot Network API [![Build Status](https://travis-ci.org/JonoCX/bot-network-api.svg?branch=master)](https://travis-ci.org/JonoCX/bot-network-api)

This project works as the interface between the underlying Postgres database and the simulation program. It provides a set of database entities and repositories to access said entities.

### Requirements
The basic requirement is that you have Java, Git, Maven, and Postgres installed.

There is a presumption that you have setup a database named 'dengue' which contains all of the relevant schema's for the project as a whole (TwitterRank, Data Crawling, etc.). This project requires a separate schema to store it's tables, run the setup file (as shown below) to create it.

`$ psql -f schema_setup.sql -d dengue`

### Installation
Clone the repository to a local directory:

`$ git clone https://github.com/JonoCX/bot-network-api.git`

Move into the directory and use Maven to install the project:

`$ cd bot-network-api`

`$ mvn clean install`

Add the project as a dependency in your project (in the pom.xml)
```xml
<dependency>
    <groupId>uk.ac.ncl.botnetwork</groupId>
    <artifactId>bot-network-api</artifactId>
    <version>0.4</version>
</dependency>
```
