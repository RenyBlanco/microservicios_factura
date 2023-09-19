DROP TABLE IF EXISTS facturas;

CREATE TABLE facturas (      id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                numero_factura VARCHAR(25) NOT NULL,
                                descripcion VARCHAR(100),
                                id_cliente BIGINT NOT NULL,
                                created_at TIMESTAMP,
                                estado VARCHAR(20));

DROP TABLE IF EXISTS detalles_facturas;


CREATE TABLE detalles_facturas (     id BIGINT AUTO_INCREMENT  PRIMARY KEY,
								id_factura BIGINT NOT NULL,
                              id_producto BIGINT NOT NULL,
                              cantidad INT,
                              precio DOUBLE);