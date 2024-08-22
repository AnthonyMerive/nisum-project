# **NISUM-USER-API**

![Java Version](https://img.shields.io/badge/Java-19-red?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring&nbsp;Boot-3.1.2-success?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-7.6-success?logo=Gradle)

# Tabla de contenido

- [Descripción de la necesidad](#Descripcion-de-la-necesidad)
- [Requsitos](#Requsitos)
- [Escenarios](#Escenarios)

## Descripcion de la necesidad

**¿Qué?** Desarrollar una aplicación que exponga una API para la gestion de usuarios</br>

### *Criterios de aceptación*

- Todos los endpoints deben aceptar y retornar solamente JSON, inclusive para los mensajes de error.
- Todos los mensajes deben seguir el formato:
``` json
{"mensaje": "mensaje de error"}
```

**Registro**

- Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono", respetando el siguiente formato:
``` json
{
	"name":"Juan Rodriguez",
	"email":"juan@rodriguez.org",
	"password":"hunter2",
	"phones":[
		{
			"number":"3104203522",
			"cityCode":"1",
			"countryCode":"57"
		}
	]
}
```

- Responder el código de status HTTP adecuado.
- En caso de éxito, retornar el usuario y los siguientes campos:
  - **id:** id del usuario (del banco de datos o un UUID).
  - **created:** fecha de creación del usuario.
  - **modified:** fecha de la última actualización de usuario.
  - **last_login:** fecha del último ingreso (en caso de ser un nuevo usuario, va a coincidir con la fecha de creación).
  - **token:** token de acceso de la API (puede ser UUID o JWT).
  - **isActive:** indica si el usuario sigue habilitado dentro del sistema.
- En caso de que el correo se encuentre registrado en la base de datos, deberá retornar un mensaje de error "El correo ya se encuentra registrado".
- El correo debe seguir una expresión regular para validar que el formato sea el correcto (aaaaaaa@dominio.cl).
- La clave debe seguir una expresión regular para validar que el formato sea el correcto. (El valor de la expresión regular debe ser configurable)
- El token deberá ser persistido junto con el usuario.

## Requsitos

- [x] Verificar el repositorio y políticas de acceso

| Nombre         | Ruta                                                              | Rama   |
|----------------|-------------------------------------------------------------------|--------|
| nisum-user-api | https://github.com/AnthonyMerive/nisum-project/tree/main/nisum-user-api | Main |

- [x] Contar con el JDK y JRE debidamente instalados en el sistema y configurados en las variables de entorno correctamente:
    - **JDK:**
      - **Variable name:** JAVA_HOME
      - **Variable value:** C:\Program Files\Java\jdk-19 (ruta de ejemplo, validar ruta de instalación)

- [x] Contar con un Entorno de Desarrollo Integrado (IDE) IntelliJ IDEA (preferiblemente).
- [x] Tener instalada la aplicación Postman (https://www.postman.com/downloads/).
- [x] Descargar e instalar los binarios de kafka o en su defecto desplegar a traves de una imagen de docker kafka, al hacerlo se debe crear un topico de nombre: 'nisum.domain.events'


### Ejecución del servicio:
- [x] Descargar el proyecto desde el repositorio como zip.
- [x] Ejecutar el servicio con las configuraciones correspondientes al JDK de Java 19.
  - Al ejecutarse la aplicación, se generan las tablas de **users** y **phones** a partir de las anotaciones de jakarta.persistence existentes en las entidades UserData y PhoneData.
  - Igualmente, posterior a la creación de las tablas en mención, se realiza la inserción de algunos datos relacionados en el script **import.sql** de los recursos de la aplicación.
- [x] Importar la colección y las variables de entorno de Postman anexa a la documentacion.
- [x] La documentación del servicio generada por Swagger se visualizara al iniciar el servicio.
- [x] El seguimiento a los cambios en la base de datos pueden ser visualizados en la ruta <http://localhost:8080/h2-console>.
  - Datos de conexión:
      - **Driver Class:** org.h2.Driver
      - **JDBC URL:** jdbc:h2:mem:usersdb
      - **User Name:** admin
      - **Password:** root

**IMPORTANTE:** Si no se quiere instalar kafka y evitar la funcionalidad de '**nisum-trace-worker**' se puede dejar en '**false**' la propiedad del application.yaml '**traceability.enable**'

## Escenarios

### CREATE

### Endpoint: /user/api/v1/save:

#### Header:
Authorization: con Bearer Token

#### Body:

``` json
  {
	"name":"Miguel Mendez",
	"email":"miguel.mendez@nisum.com",
	"password":"Password123",
	"phones":[
		{
			"number":"3112514785",
			"cityCode":"1",
			"countryCode":"30"
		},
		{
			"number":"3101415785",
			"cityCode":"2",
			"countryCode":"57"
		},
		{
			"number":"3002548578",
			"cityCode":"3",
			"countryCode":"33"
		}
	]
}
```

#### Response:

#### 201:

``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 201,
    "message": "Success creation",
    "data": {
        "name": "Miguel Mendez",
        "email": "miguel.mendez@nisum.com",
        "phones": [
            {
                "number": "3112514785",
                "cityCode": "1",
                "countryCode": "30"
            },
            {
                "number": "3101415785",
                "cityCode": "2",
                "countryCode": "57"
            },
            {
                "number": "3002548578",
                "cityCode": "3",
                "countryCode": "33"
            }
        ]
    }
}
```

#### 400:

1. Email exist in database:
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 400,
    "message": "Email exist in database"
```
- descripcion: El email que se intenta crear ya existe en la base de datos.

2. Invalid email format, example@example.com format required:
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 400,
    "message": "Invalid email format, example@example.com format required"
```
- descripcion: El email que se introdujo tiene un formato invalido.

3. Change the password, it is insecure:
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 400,
    "message": "Change the password, it is insecure"
```
- descripcion: El password no cumple con los criterios de aceptacion ya que es inseguro.

4. One of the phone numbers entered is assigned to another user
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 400,
    "message": "one of the phone numbers entered is assigned to another user"
```
- descripcion: Alguno de los numeros de telefono ya esta asignado a otro usuario.

### READ

### Endpoint: /user/api/v1/search

#### Header:
Authorization: con Bearer Token

Email: con el email que se desea buscar

#### Body: (No se requiere)

#### Response:

#### 200:

``` json
{
    {
    "transactionId": "3cbe7437-5e0b-42f3-aaa3-9e0a3b9b72f6",
    "code": 200,
    "message": "Success get",
    "data": {
        "name": "Miguel Mendez",
        "email": "miguel.mendez@nisum.com",
        "phones": [
            {
                "number": "3112514785",
                "cityCode": "1",
                "countryCode": "30"
            },
            {
                "number": "3101415785",
                "cityCode": "2",
                "countryCode": "57"
            },
            {
                "number": "3002548578",
                "cityCode": "3",
                "countryCode": "33"
            }
        ]
    }
}
```

#### 404:

User not found:
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 404,
    "message": "User not found"
```
- descripcion: El email que se intenta buscar no existe.


### Endpoint: /user/api/v1/search-all

#### Header:

Authorization: con Bearer Token

#### Body: (No se requiere)

#### Response:

#### 200:

``` json
{
    "transactionId": "8af07314-cd2e-456a-9e3a-20a2ec8f5048",
    "code": 200,
    "message": "Success get",
    "data": [
        {
            "name": "Pedro Perez",
            "email": "pedro.perez@nisum.com",
            "phones": [
                {
                    "number": "3103254854",
                    "cityCode": "1",
                    "countryCode": "30"
                }
            ]
        },
        {
            "name": "Juan Rodriguez",
            "email": "juan.rodriguez@nisum.com",
            "phones": [
                {
                    "number": "3102546654",
                    "cityCode": "1",
                    "countryCode": "30"
                },
                {
                    "number": "3112546365",
                    "cityCode": "2",
                    "countryCode": "40"
                },
                {
                    "number": "3123245688",
                    "cityCode": "3",
                    "countryCode": "50"
                }
            ]
        }
    ]
}
```

### UPDATE

### Endpoint: /user/api/v1/edit

#### Header:
Authorization: con Bearer Token

#### Body:

``` json
  {
  "name": "Juan Rodriguez",
  "email": "juan.rodriguez@nisum.com",
  "password": "Example1234",
  "phones": [
        {
            "number": "3101214596",
            "cityCode": "1",
            "countryCode": "30"
        },
        {
            "number": "3112578425",
            "cityCode": "2",
            "countryCode": "40"
        }
    ]
}
```

#### Response:

#### 200:

``` json
{
    "transactionId": "dfe022f8-05cd-4162-9372-9af5d6ee4d1d",
    "code": 200,
    "message": "Success update",
    "data": {
        "name": "Juan Rodriguez",
        "email": "juan.rodriguez@nisum.com",
        "phones": [
            {
                "number": "3101214596",
                "cityCode": "1",
                "countryCode": "30"
            },
            {
                "number": "3112578425",
                "cityCode": "2",
                "countryCode": "40"
            }
        ]
    }
}
```

#### 404:

User not found:
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 404,
    "message": "User not found"
```
- descripcion: El usuario que se intenta actualizar no existe.


#### 400:

1. Change the password, it is insecure:
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 400,
    "message": "Change the password, it is insecure"
```
- descripcion: El password no cumple con los criterios de aceptacion ya que es inseguro.

2. One of the phone numbers entered is assigned to another user
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 400,
    "message": "one of the phone numbers entered is assigned to another user"
```
- descripcion: Alguno de los numeros de telefono ya esta asignado a otro usuario.

### DELETE

### Endpoint: /user/api/v1/search

#### Header:
Authorization: con Bearer Token

Email: con el email que desea eliminar

#### Body: (No se requiere)

#### Response:

#### 200:

``` json
{
    "transactionId": "e9bdc92b-50aa-41a0-a8c8-bf95b8c919fc",
    "code": 200,
    "message": "Success delete",
    "data": {
        "name": "Miguel Mendez",
        "email": "miguel.mendez@nisum.com",
        "phones": [
            {
                "number": "3112514785",
                "cityCode": "1",
                "countryCode": "30"
            },
            {
                "number": "3101415785",
                "cityCode": "2",
                "countryCode": "57"
            },
            {
                "number": "3002548578",
                "cityCode": "3",
                "countryCode": "33"
            }
        ]
    }
}
```

#### 404:

User not found:
``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 404,
    "message": "User not found"
```
- descripcion: El email que se intenta borrar no existe.

### GENERAL

- Todos los endpoints pueden responder con un 500 (Internal server error) cuando exista una excepcion no controlada.
- Todos los endpoints podran responder un 401 cuando el token de autenticacion sea invalido cuya respuesta sera la siguiente:

``` json
{
    "transactionId": "08f392f7-7245-4b90-9db1-6c2ee198c707",
    "code": 401,
    "message": "Invalid Token"
```
