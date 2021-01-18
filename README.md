# Dockin RM - Dockin Resource Manager

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

English | [中文](README.zh-CN.md)

Dockin container platform resource manager is the core module for application definition 
and container instance management, providing container allocation, recycling, and query functions.

**For more Dockin components, please visit [https://github.com/WeBankFinTech/Dockin](https://github.com/WeBankFinTech/Dockin)**


![Architecture](docs/images/dockin.png)

## Features

* **0.1.0**
    * support Dockin CNI
    * support Dockin Opserver 

## Installation

### Minimum Requirements

* **JDK**
  * ≥ 1.8

* **Gradle**
  * ≥ 4.3

## QuickStart

#### import tables
- login mysql console and execute sql file to import tables 
```
mysql -u <username> -p <password>

mysql> source <The absolute path of the sql file>
```

#### project build

- use gradle build project 

```
gradle build
```

#### server run
- use `java -jar` run server
```
java -jar dockin-rm-v1.0.0.jar --spring.config.location=<path of 'application.properties' and 'application-database.properties'>
```
