# Gestor de información

Se deberá conectar a servicios externos para la obtención/gestión de información. El api que se consume es [LCBO API)](https://lcboapi.com/docs/v1/products) , la cual contiene productos de diferentes licores.

Lo que se realizo en esta app es primeramente conexión al servicio rest para poder extraer información de licores, luego se implemento una base de datos que cumple la función como de cache donde previamente pide información sobre la cerveza "Corona Extra" y la almacena en la base de datos para que este contenido sea una muestra y por ultimo se desarrolla un proveedor de contenido el cual cuando el cliente realice una petición sobre un licor el provedor se encarga de buscar en la base de datos en caso de no existir realiza una petición al API y de esta forma almacena la consulta realizada en la base de datos.


![previamente](https://github.com/rploaiza/FEM.AppRest.RoberthLoaiza/blob/release-1.0/docs/apiRest.png)

