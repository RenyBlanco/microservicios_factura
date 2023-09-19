INSERT INTO facturas (id, numero_factura, descripcion, id_cliente, created_at, estado) VALUES(1, '0001', 'invoice office items', 1, NOW(),'CREATED');

INSERT INTO detalles_facturas (id_factura, id_producto, cantidad, precio ) VALUES(1, 1 , 1, 178.89);
INSERT INTO detalles_facturas (id_factura, id_producto, cantidad, precio)  VALUES(1, 2 , 2, 12.5);
INSERT INTO detalles_facturas (id_factura, id_producto, cantidad, precio)  VALUES(1, 3 , 1, 40.06);