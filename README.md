# Library-Spring-Boot Rest API

## Web Application using:
<ul>
<li>Spring-Boot</li>
<li>CRUD</li>
<li>Postgres DataBase - liquibase</li>
<li>Vue JS</li>
</ul>

## Run:

<ol>
<li>docker run --name library -e POSTGRES_PASSWORD=admin -d postgres</li>
<li>docker exec -it library psql -U postgres</li>
<li>CREATE USER libraryUser;</li>
<li>CREATE USER libraryUser;</li>
<li>ALTER USER libraryUser WITH PASSWORD 'admin';</li>
<li>sudo docker exec -it [container] cat /etc/hosts --> zobaczyc na jakim porcie nasluchuje kontener i wprowadzic w application properties w aplikacji</li>
<li>mvn clean package</li>
<li>docker build -f Dockerfile -t library:v1 .</li>
<li>docker run -p 8000:8080 id_image</li>
</ol>
