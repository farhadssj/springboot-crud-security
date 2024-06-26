<div align="center"><h1 align="center" id="title">Springboot CRUD Security</h1></div>

<p id="description">This is a web application developed using Java SpringBoot with JWT based Authentication & MySQL CRUD operation. This web application API will cover foollowing areas</p>
<ol>
<li>JWT based Authentication and Authorization (username & password)</li>
<li>Role base access in API (public, ADMIN, USER etc.)</li>
<li>MySQL CRUD operation of Employee info</li>
</ol>

<p align="center">
  <img src="https://img.shields.io/badge/Java-SpringBoot-blue" alt="shields"><span> </span>
  <img src="https://img.shields.io/badge/Database-MySQL-green" alt="shields"><span> </span>
  <img src="https://img.shields.io/badge/JWT-Authentication-green" alt="shields"><span> </span>
  <img src="https://img.shields.io/badge/License-MIT-green" alt="shields">
  <img src="https://img.shields.io/badge/Web-Backend-blue" alt="shields"><span> </span>
</p>

<h2>Installation Steps:</h2>

<p>1. Configure Database</p>

```
CREATE SCHEMA `employee_db`;
```

<p>2. Database Credential Setup</p>
<p>Update mysql database credential of each micro service in <b>application.propertires</b> file</p>
  
```
spring.datasource.username = YOUR_USER_NAME
spring.datasource.password = YOUR_PASSWORD
```

<h2>Api Specification:</h2>

All API endpoints are mapped in postman collection. Collection will be found from here... <a href='https://github.com/farhadssj/springboot-crud-security/blob/be1139a3266f2376bcaa557d7a26f4eb3752120c/uploads/SpringbootCRUDSecurityRestApiPostmanCollection.json' target='_blank'>SpringbootCRUDSecurityRestApiCollection</a>


<h2>Web FrontEnd:</h2>

Web front end using angular framework can be found from here... <a href='https://github.com/farhadssj/angular-restapi-authentication' target='_blank'>AngularWebFrontEnd</a>
