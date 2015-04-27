# YNO
YNO stands for "Yno is Not an ORM", it's a simple wrapper of the sql2o library to help inserting and updating data.

Inserting and updating data with YNO
```java
String url = "jdbc:postgresql://localhost:5432/ynotest";
String user = "postgres";
String password = "postgres";
Yno yno = new Yno(url, user, password);

// Here we teach Yno how to collect data from classes to generate the inserts and updates
yno.registerCollector(Bar.class, (obj, collectedData) -> {
    collectedData.table("Bar");

    Bar bar = (Bar) obj;
    collectedData.data("foo", bar.getFoo());
});

// Inserting
Bar bar = new Bar();
bar.setFoo("Foo");
Long key = yno.insert(bar).insertAndFetchKey();

// Updating
bar.setFoo("Fooooo");
yno.update(bar).where("id", key).execute();
```

## Maven

```xml
<repository>
  <id>erickzanardo-releases</id>
  <url>http://erickzanardo.github.com/maven/releases/</url>
</repository>

<dependency>
  <groupId>org.eck</groupId>
  <artifactId>yno</artifactId>
  <version>1.0.0-ALPHA</version>
</dependency>
```
