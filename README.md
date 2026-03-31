# Librero :closed_book::green_book::blue_book:
Busca tu libro una vez y obtene los precios de multiples lugares!

## Que es Librero? :thinking:
Librero es un recolector web de libros de las librerías de Argentina,
provee busquedas por titulo del libro y/o por autor del libro, 
ofreciendo los precios que se puede encontrar online.

## Instalacion :hammer_and_wrench:
en progreso...

## Como funciona? :gear:

Para realizar una consula necesitamos un buscador de preferencia(Mozilla, Chrome, Brave, etc).

En la barra de busqueda utilizaremos la siguiente URL: 
   `localhost:8080`

Y para especificar los parametros: 
    `localhost:8080/search?name=""&surname=""&title=""`
    `name`: Nombre de autor(Opcional).
    `surname`: Apellido de autor(Opcional).
    `title`: Titulo de la obra(Requerido).

Sin especificar nombre y apellido la busqueda quedaria de la siguiente manera:
    `localhost:8080/search?title=""`

## Aviso :warning:
Librero es una aplicación unicamente con fines educativos.