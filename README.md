##wmpv-project

Proyecto a dockerizar servicios de movimientos de cuenta

##Catalogos

#Estado
Activo: A
Inactivo: I

#Tipo Cuenta
Ahorro: H
Corriente: C

#Tipo Movimiento
Debito: D
Credito: R

##Endpoint de los servicios

#Consulta clientes: parametro 1: pagina, Parametro 2: Numero de registros

curl --request GET \ --url http://localhost:8080/api/clientes/0/10

#Crear/actualizar cliente: code=null, cliente nuevo, caso contrario actualiza cliente

curl --request POST \
  --url http://localhost:8080/api/clientes \
  --header 'Content-Type: application/json' \
  --data '{
	"code":"",
  "name":"William Patino",
  "id":"1716277874",
  "address":"Las Alondras",
  "gender":"M",
  "phone":"0987020503",
  "age":"39",
  "pass":"54321",
  "status":"A"
}'

#Elimina o desactiva al cliente

curl --request DELETE \
  --url http://localhost:8080/api/clientes \
  --header 'Content-Type: application/json' \
  --data '{
	"code":"1",
  "name":"William Patino",
  "id":"1716277874",
  "address":"Las Alondras",
  "gender":"M",
  "phone":"0987020503",
  "age":"39",
  "pass":"54321",
  "status":"A"
}'

#Consulta las cuentas de un cliente: parametro 1: codigo de cliente

curl --request GET \
  --url http://localhost:8080/api/cuentas/1
 
#Crear/actualizar cuenta: idAccount en nulo el cliente nuevo, caso contrario actualiza

curl --request POST \
  --url http://localhost:8080/api/cuentas \
  --header 'Content-Type: application/json' \
  --data '{
	"idAccount":"",
  "idClient":"1",
  "numberAccount":"210120704",
  "typeAccount":"H",
  "initBalance":"50.00",
  "status":"A"
}'

#Elimina o desactiva la cuenta

curl --request DELETE \
  --url http://localhost:8080/api/cuentas \
  --header 'Content-Type: application/json' \
  --data '{
	"idAccount":"1",
  "idClient":"1",
  "numberAccount":"210120704",
  "typeAccount":"H",
  "initBalance":"50.00",
  "status":"A"
}'

#Consulta los ultimos movimientos: parametro 1: codigo de cuenta, parametro 2: el numero de registros a obtener

curl --request GET \
  --url http://localhost:8080/api/movimientos/1/10
  

#Credito/Debito de la cuenta: type="D" es debito, type="R" es credito

curl --request POST \
  --url http://localhost:8080/api/movimientos/creditoDebito \
  --header 'Content-Type: application/json' \
  --data '{
	"type":"D",
  "value":"5",
  "idAccount":"1"
}'

#Genera reporte: parametro 1: fecha inicio, parametro 2: fecha fin, parametro 3: numero de identificacion del cliente, fecha en formato dd-MM-yyyy

curl --request GET \
  --url http://localhost:8080/api/movimientos/reporte/24-09-2022/27-09-2022/1716277874
  
  
##Crear la base de datos en postgres 10, con las credenciales indicadas en el application.properties

#Compilar el proyecto (Java 11): /> mvn clean install
##Se puede porbar el servicio con click derecho en el proyecto "Run As/Spring Boot App"
