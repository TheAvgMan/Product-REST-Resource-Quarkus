<h1 style="text-align: center">🎨💻 Product REST Service using Quarkus 💻🎨</h1>

---

## 1 - ✨ Introduction ✨

<h3 style="text-align: center">🎉🥳 Welcome to this repo! 🥳🎉</h3>

<p style="text-align: center"><br> This is my implementation of a Product REST resource / endpoint / service <br>
using the latest cloud-native development Java framework - <em><strong>Quarkus</strong></em></p>

<h3 style="text-align: center">The following technologies were used for this task:</h3>

<br> <div style="text-align: center">
<img alt="REST API" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png" style="width: 50px; margin-right:10px;"/>
<img alt="Java" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" style="width: 50px; margin-right:10px;"/>
<img alt="Quarkus" src="https://user-images.githubusercontent.com/25181517/183892781-61ed6416-4a2c-4061-8240-e6a23e1d7b09.png" style="width: 50px; margin-right:10px;"/>
<img alt="Maven" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" style="width: 50px; margin-right:10px;"/>
<img alt="Hibernate" src="https://user-images.githubusercontent.com/25181517/117207493-49665200-adf4-11eb-808e-a9c0fcc2a0a0.png" style="width: 50px; margin-right:10px;"/>
<img alt="IntelliJ" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" style="width: 50px; margin-right:10px;"/>
<img alt="Postman" src="https://user-images.githubusercontent.com/25181517/192109061-e138ca71-337c-4019-8d42-4792fdaa7128.png" style="width: 50px; margin-right:10px;"/>
<img alt="JUnit" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" style="width: 50px; margin-right:10px;"/>
<img alt="PostgreSQL" src="https://user-images.githubusercontent.com/25181517/117208740-bfb78400-adf5-11eb-97bb-09072b6bedfc.png" style="width: 50px; margin-right:10px;"/>
<img alt="Docker" src="https://user-images.githubusercontent.com/25181517/117207330-263ba280-adf4-11eb-9b97-0ac5b40bc3be.png" style="width: 50px; margin-right:10px;"/>
<img alt="Swagger" src="https://user-images.githubusercontent.com/25181517/186711335-a3729606-5a78-4496-9a36-06efcc74f800.png" style="width: 50px; margin-right:10px;"/>
</div>

## 2 - ✨ Running The Database ✨

<p style="text-align: center"><br> Before starting the application itself, you must <em><strong>run the database first</strong></em>.
<br> The database is going to be executed as a <em><strong>docker container</strong></em> from the latest Postgres image.
<br><br> Execute the following command to run this database container:</p>

```shell script
docker run --name products_pgcontainer -e POSTGRES_PASSWORD=secret123 -d -p 5432:5432 -v products_pgdata:/var/lib/postgresql/data postgres
```

## 3 - ✨ Running The Application ✨

<p style="text-align: center"><br> Now you need to clone this repo to run the application.
<br> After cloning, open the project in your IDE by clicking on the <em><strong>pom.xml</strong></em> file.
<br><br> Then you can run the application using one of the following two ways:</p>

<h3 style="margin-left: 40px"> I) - Running the application in dev mode</h3>

<p style="text-align: center">You can run (and re-run) the application in dev mode, which enables live coding using the following command:</p>

```shell script
./mvnw clean compile quarkus:dev
```

<h3 style="margin-left: 40px"> II) - Running the application dockerized</h3>

<p style="text-align: center"> For this method, you need to follow these steps:</p>

<ol style="margin-left: 60px">
  <li>Replace <em><strong>localhost</strong></em> with <em><strong>host.docker.internal</strong></em> in <em><strong>application.properties</strong></em> file
<br> under the src/main/resources directory.</li>

  <li>Package this application using the following command:</li>

```shell script
./mvnw clean package
```

  <li>Build the docker image of this application using the following command:</li>

```shell script
docker build -f src/main/docker/Dockerfile.jvm -t ProductResource-REST-Quarkus-jvm .
```

> **_Note:_**  This step and the two previous ones are performed only once.

  <li>Run a container from this image using the following command:</li>

```shell script
docker run -i --rm -p 8080:8080 ProductResource-REST-Quarkus-jvm
```

> **_Note:_**  This command is needed every time you want to execute the application as a docker container, because the container is automatically removed when stopped.

</ol>

## 4 - ✨ The Endpoints ✨

<p style="text-align: center"><br> Finally, you can start using the API endpoints of the application!! 🥳🎉
<br><br> To know all the endpoints available and start trying them out, click the following link:</p>

<div style="text-align: center">

[Let's try it out !!](http://localhost:8080/q/swagger-ui)

</div>

> **_Note:_** When using the POST and PUT endpoints, take care not to set the value of ID field as it's automatically generated.

---

## 👋 That's it 👋

<h3 style="text-align: center"><br> I hope you enjoyed it 😊</h3>

<h3 style="text-align: center"><br> Thank you for trying it out ❤</h3>

