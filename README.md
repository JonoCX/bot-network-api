# Bot Network API [![Build Status](https://travis-ci.org/JonoCX/bot-network-api.svg?branch=master)](https://travis-ci.org/JonoCX/bot-network-api)

This project works as the interface between the underlying Postgres database and the simulation program. It provides a set of database entities and repositories to access said entities.

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
