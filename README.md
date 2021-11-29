# Mentoring Matcher

An application to solve a classic mentoring challenge: matching! The
application allows uploading a .csv file that contains background information on
employees, and present the ideal matches for the given employees.

Besides, simple matching an applications allow configuring matching and scoring criteria, so
it is possible to easily add new criteria to matching, change scores or peers count in groups.

For now available operations are EQUALS, GTE, GT, LTE, LT.
Combined with field name application will compute pairing score.

An application also gives ability to not only pair objects, but also compute groups(unfortunately it's buggy now).
Also, it is possible to get score b/w multiple objects.

Application includes.
1. An interface for uploading a .csv file as described above
2. A recommendation page that is shown after processing an input file showing the list of
   matches with the highest average match score,
3. Helper API's to get configuration templates, available operations and fields.

## Deployment
You can find an application deployed in AWS EC2 service, and the database deployed
to AWS RDS.

You can test an application using the [Swagger](http://18.185.249.241/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

### To get pairing score
First get available [configuration templates](http://18.185.249.241/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/matcher-config-template-controller/getAll)

Then, using this config, [upload a csv file](http://18.185.249.241/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/employee-controller/uploadFile) and as a response
there available are all available pairings and of course top match !

Here is a simple config for matchers
```
{
    "peersCount": 2,
    "matcherConfigs": [
        {
            "fieldName": "division",
            "diff": 0,
            "score": 30,
            "operationType": "EQUALS"
        },
        {
            "fieldName": "age",
            "diff": 5,
            "score": 30,
            "operationType": "LTE"
        },
        {
            "fieldName": "timezoneOffset",
            "diff": 0,
            "score": 40,
            "operationType": "EQUALS"
        }
    ]
}
```

## Development

To start your application in the dev profile, run:

```
./gradlew
```

### The database

Make sure to create the database and the role for application.
```
create database mentoring_matching;
create user mentoring_matching with encrypted password '123456';
grant all privileges on database mentoring_matching to mentoring_matching;
```

## Building for production

### Packaging as jar

To build the final jar and optimize the cardshow application for production, run:

```
./gradlew -Pprod clean bootJar
```

To ensure everything worked, run:

```
java -jar build/libs/*.jar
```

## Testing

To launch your application's tests, run:

```
./gradlew test
```

[swagger-link]: http://18.185.249.241/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config