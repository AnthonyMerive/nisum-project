# **NISUM-USER-API**

![Java Version](https://img.shields.io/badge/Java-19-red?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring&nbsp;Boot-3.1.2-success?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-7.6-success?logo=Gradle)

# Tabla de contenido

- [Descripción de la necesidad](#Descripción de la necesidad)
- [Requsitos](#Requsitos)

## Descripción de la necesidad

**¿Qué?** Desarrollar una aplicación tipo worker que lea los mensajes del domainEvents para almacenarlos en la base de datos</br>

## Requsitos

- [x] Verificar el repositorio y políticas de acceso

| Nombre         | Ruta                                                                | Rama   |
|----------------|---------------------------------------------------------------------|--------|
| nisum-user-api | <https://github.com/AnthonyMerive/nisum-project/nisum-trace-worker> | Master |

- [x] Contar con el JDK y JRE debidamente instalados en el sistema y configurados en las variables de entorno correctamente:
  - **JDK:**
    - **Variable name:** JAVA_HOME
    - **Variable value:** C:\Program Files\Java\jdk-19 (ruta de ejemplo, validar ruta de instalación)

- [x] Contar con un Entorno de Desarrollo Integrado (IDE) IntelliJ IDEA (preferiblemente).

### Ejecución del servicio:
- [x] Descargar el proyecto desde el repositorio como zip.
- [x] Ejecutar el servicio con las configuraciones correspondientes al JDK de Java 19.
  - Al ejecutarse la aplicación, se genera la tabla de **events** a partir de las anotaciones de jakarta.persistence existentes en las entidades UserData y PhoneData.
- [x] El seguimiento a los cambios en la base de datos pueden ser visualizados en la ruta <http://localhost:8080/h2-console>.
  - Datos de conexión:
    - **Driver Class:** org.h2.Driver
    - **JDBC URL:** jdbc:h2:mem:eventsdb
    - **User Name:** admin
    - **Password:** root
- [x] Descargar e instalar los binarios de kafka o en su defecto desplegar a traves de una imagen de docker kafka, al hacerlo se debe crear un topico de nombre: 'nisum.domain.events'
