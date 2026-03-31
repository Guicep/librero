FROM alpine:latest
COPY . /app
WORKDIR /app
# apk pertenece a alpine, cada imagen base utilizara un comando propio para instalar dependencias
RUN apk add openjdk25  \
    && apk add maven  \
    && mvn clean install
CMD ["mvn", "spring-boot:run"]