# Server-Spring

Proyecto de un servicio REST con Spring. Ejemplo de persistencia de datos(conexión con una base relacional, MySQL), seguridad para endpoints y uso jwt.

<h3 align="left">Lenguaje y Tecnologias:</h3>
<p align="left"> 
    <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> 
    <Br/>
    <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a> 
    <Br/>
    <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> 
</p>

## Instalación y ejecución

Ejecutar el proyecto se debe generar el archivo JAR para lo cual se ejecuta el siguiente comando:

```bash
mvn clean
mvn install
```

> **_NOTA:_** Se debe tener una base de datos ya creada a la cual conectarse antes de ejecutar los comandos. Este proceso se describe más adelante.

Esto generar un carpeta llamada target en su directorio en donde se encuentra el archivo JAR.

-   `prueba-0.0.1-SNAPSHOT.jar`

El nombre puede ser cambiado si lo desea. Posteriormente debe ejecutar el siguiente comando:

```bash
java -jar ${jarfilename}.jar
```

## Descripcion del desarrollo e implementación

Este proyecto tiene como objetivo conectar un login, registro y un formulario a una base de datos mediante el uso de un servicio REST.

### Las especificaciones

-   Creación aplicativa, considerar
    1. Pantalla registro, formato: `correo-usuario-contraseña`
        1. Integrar interceptores para cifrado de datos
    2. Pantalla acceso, formato: `usuario-contraseña`
    3. Pantalla formularia, considerar
        1. Captura 3 campos, en tres tablas relacionadas
            - `Estado`
            - `Municipio`
            - `CP`
        2. Solo accesible a través de login
        3. Conexión a base de datos relacional
        4. Servicio REST para persistir los datos del formulario, con método POST en formato JSON
        5. Incluir seguridad para los servicios

A continuación se describe el proceso que se sigue para cumplir con las especificaciones.

### Propiedades y Conexión a la Base de Datos

Se debe crear la base datos

```SQL
CREATE DATABASE ${NOMBRE_BASE};
USE ${NOMBRE_BASE}
```

Posteriormente se debe conectar en el archivo `application.properties`

```BASH
# Se debe usar cambiar a none eventualmente
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/${NOMBRE_BASE}
spring.datasource.username={USER}
spring.datasource.password={PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Más información en el siguiente [enlace](https://spring.io/guides/gs/accessing-data-mysql/).

### Modelos

Usando JPA se crearon las tablas necesarias según las especificaciones.

-   `Usuario`
    -   correo
    -   usuario
    -   contraseña
-   `UsuarioDireccion`
    -   Estado
    -   Municipio
    -   CP
    -   Usuario

### Seguridad

Para garantizar la seguridad de los servicios se uso JSON web Token (JWT) para validar token de cada petición realizada.

Además, se configuro la direccion`/api/auth/**` para permitir el registro y login solicitados. El resto de endpoints necesitan un token valido para ejecutarse.

La configuración de seguridad se realizó en el archivo `SecurityConfig.java`

### Endpoints

Los controladores para las especificaciones son los siguientes:

-   AuthController `/api/auth`

    -   `/login`
        -   POST
        -   AuthCredentialRequest
        -   Regresa el Usuario y un token para las futuras peticiones
    -   `/register`
        -   POST
        -   Usuario
        -   Regresa el Usuario creado y un token para las futuras peticiones

-   UsuarioDireccionController `/api/direccionUsuario`
    -   `/{id}`
        -   GET
        -   Integer
        -   Regresa los datos de direccion de un usuario
    -   `/`
        -   PUT
        -   UsuarioDireccion
        -   Modifica los datos de dirección de un usuario
