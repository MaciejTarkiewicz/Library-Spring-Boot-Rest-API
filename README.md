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
<li>CREATE USER libraryUser;</li>
<li>CREATE DATABASE "libraryBase" OWNER libraryUser;</li>
<li>ALTER USER libraryUser WITH PASSWORD 'admin';</li>
<li>mvn clean spring-boot:run</li>
</ol>

<ol>
 <li> sudo usermod -a -G docker jenkins ----> Dodanie Jenkinsa do grupy dockera</li>
<li> sudo systemctl restart jenkins ---> restart</li>
</ol>
