# Karaf Jackson

This project provides support for FasterXML Jackson project. Jackson is pretty popular library, however it does miss dedicated karaf support.
From other hand its rather too early to push such integration into this project itself.

Requires
---
Karaf 3.x

Provides
---
Jackson features for running it inside karaf.

## Developer introduction

To start using jackson in your project you need to install karaf feature set:

```
feature:repo-add mvn:org.code-house.jackson/features/3.0.0-SNAPSHOT/xml
feature:install jackson-core
```