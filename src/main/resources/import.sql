INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900111111', '77777777', 'Enciso', 'Henry', 'juan.perez@gentlemen.com', 'Admin123', 'ADMINISTRADOR');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900222222', '66666666', 'Garcia', 'Belkin', 'luis.gomez@gentlemen.com', 'Admin123', 'ADMINISTRADOR');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900333333', '11111111', 'Ramírez', 'Carlos', 'carlos.ramirez@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900444444', '22222222', 'Torres', 'Miguel', 'miguel.torres@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO administradores (id_usuario) VALUES (1);

INSERT INTO administradores (id_usuario) VALUES (2);

INSERT INTO barberos (id_usuario) VALUES (3);

INSERT INTO barberos (id_usuario) VALUES (4);

INSERT INTO categorias (nombre) VALUES ('Shampoo');

INSERT INTO categorias (nombre) VALUES ('Cera');

INSERT INTO categorias (nombre) VALUES ('Máquinas de Afeitar');

INSERT INTO productos (id_categoria, precio, stock, nombre) VALUES (1, 25.50, 50, 'Shampoo Anticaspa');

INSERT INTO productos (id_categoria, precio, stock, nombre) VALUES (1, 30.00, 40, 'Shampoo Nutritivo');

INSERT INTO productos (id_categoria, precio, stock, nombre) VALUES (2, 15.75, 60, 'Cera Fijadora Fuerte');

INSERT INTO productos (id_categoria, precio, stock, nombre) VALUES (2, 18.00, 70, 'Cera Mate');

INSERT INTO productos (id_categoria, precio, stock, nombre) VALUES (3, 120.00, 20, 'Máquina Afeitadora Pro');

INSERT INTO servicios (tarifa, nombre, detalle) VALUES (30.00, 'Corte Clásico', 'Corte tradicional con tijera y máquina');

INSERT INTO servicios (tarifa, nombre, detalle) VALUES (40.00, 'Corte + Barba', 'Corte de cabello más diseño de barba');

INSERT INTO servicios (tarifa, nombre, detalle) VALUES (20.00, 'Arreglo de Barba', 'Perfilado y afeitado de barba');

INSERT INTO servicios (tarifa, nombre, detalle) VALUES (50.00, 'Corte Premium', 'Corte con técnicas avanzadas y productos exclusivos');

INSERT INTO horarios_atencion (fecha, hora, id_barbero, estado) VALUES ('2025-09-15', '10:00:00', 1, 'LIBRE');

INSERT INTO horarios_atencion (fecha, hora, id_barbero, estado) VALUES ('2025-09-15', '11:00:00', 1, 'LIBRE');

INSERT INTO horarios_atencion (fecha, hora, id_barbero, estado) VALUES ('2025-09-15', '10:00:00', 2, 'LIBRE');

INSERT INTO horarios_atencion (fecha, hora, id_barbero, estado) VALUES ('2025-09-15', '11:00:00', 2, 'LIBRE');
