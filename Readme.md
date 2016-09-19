# Karaf Jackson

This project provides support for FasterXML Jackson project. Jackson is pretty popular library, however it does miss dedicated karaf support.
From other hand its rather too early to push such integration into this project itself.

Requires
---
Karaf 4.x
Karaf 3.x

Feature descriptor uses Karaf Features schema v1.2.1 which is compatible with both Karaf 3 and 4.

Provides
---
Jackson features for running it inside karaf. With support for datatypes and dataformats.

## Developer introduction

To start using jackson in your project you need to install karaf feature set:

```
feature:repo-add mvn:org.code-house.jackson/features/<jackson-version>/xml/features
feature:install jackson-core
```
