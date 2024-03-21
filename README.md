# Proyecto BootCamp50 - Final

* El banco desea agregar un servicio P2P con su moneda virtual BootCoin.

* Hugo Oliveros Monti


### Pre-Requisitos

Plugins que deben estar instalados en su IDE:
* [Lombok](http://projectlombok.org/) - *Libreria de Bytecode que genera automaticamente los Getters y Setters*.
* [CheckStyle](http://www.checkstyle.com/) - *Plugin para poder comprobar el estilo del codigo usando las reglas de Google*
* [MongoDB](http://www.checkstyle.com/) - *Base de datos no relacional*
* [Collection]  - Se encuentra en el directorio **resources**


## Funcionalidades obligatorias

* El banco desea implementar un monedero móvil llamado Yanki con las siguientes características:

* No se necesitar ser un cliente del banco para tener un monedero movil, solo se necesita un número de documento de identificación (DNI, CEX, Pasaporte), número de celular, el IMEI del celular y correo electrónico.

* El usuario puede recibir y enviar pagos a su monedero con solo su número de celular.

* Puede asociar su monedero a una tarjeta de débito del banco de manera que el saldo sea cargado o acreditado solo a la cuenta principal asociada a la tarjeta de débito.

## Ejecución de pruebas

    Para la ejecución de pruebas `unitarias` se debe ejecutar lo siguiente:

    mvn test


Actuator
-
http://localhost:8085/actuator/

