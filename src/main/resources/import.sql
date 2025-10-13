INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('999999999', '00000007', 'Admin', 'Admin', 'admin@gentlemen.com', 'Admin123', 'ADMINISTRADOR');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900222222', '11111111', 'Ramírez', 'Jair', 'jair@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900333333', '22222222', 'Perez', 'Vyro', 'vyro@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900444444', '33333333', 'Torres', 'Christian', 'christian@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900555555', '44444444', 'Gómez', 'Luis', 'luis@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900666666', '55555555', 'Salazar', 'Andrés', 'andres@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900777777', '66666666', 'Rojas', 'Kevin', 'kevin@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900888888', '77777777', 'Quispe', 'Daniel', 'daniel@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('900999999', '88888888', 'Mendoza', 'Carlos', 'carlos@gentlemen.com', 'Barbero123', 'BARBERO');

INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('901000000', '99999999', 'Vargas', 'Marco', 'marco@gentlemen.com', 'Barbero123', 'BARBERO');


INSERT INTO sedes (nombre_sede, direccion) VALUES ('Chorrillos', 'Av.Chorrillos');

INSERT INTO sedes (nombre_sede, direccion) VALUES ('Surco', 'Av.Surco');

INSERT INTO sedes (nombre_sede, direccion) VALUES ('Lince', 'Av.Lince');


INSERT INTO administradores (id_usuario) VALUES (1);


INSERT INTO barberos (id_usuario, id_sede) VALUES (2, 1);

INSERT INTO barberos (id_usuario, id_sede) VALUES (3, 1);

INSERT INTO barberos (id_usuario, id_sede) VALUES (4, 1);

INSERT INTO barberos (id_usuario, id_sede) VALUES (5, 2);

INSERT INTO barberos (id_usuario, id_sede) VALUES (6, 2);

INSERT INTO barberos (id_usuario, id_sede) VALUES (7, 2);

INSERT INTO barberos (id_usuario, id_sede) VALUES (8, 3);

INSERT INTO barberos (id_usuario, id_sede) VALUES (9, 3);

INSERT INTO barberos (id_usuario, id_sede) VALUES (10, 3);


INSERT INTO categorias (nombre) VALUES ('Afeitado');

INSERT INTO categorias (nombre) VALUES ('Tintes');

INSERT INTO categorias (nombre) VALUES ('Lavado y cuidado del cabello');

INSERT INTO categorias (nombre) VALUES ('Crecimiento y cuidado de barba');


INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (1, 80.00, 57, 'MOISTURIZING SHAVE CREAM 150 ML', 'La Crema de afeitar American Crew hidrata,calma y alivia las irritaciones de la piel y permite un afeitado apurado. La crema no espumosa crea una barrera protectora en la piel que permite que la cuchilla se deslize sin secar la piel.');

INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (1, 80.00, 44, 'REVIT TONER 150 ML', 'Suaviza la piel después de afeitar. Tonifica y refresca. Calma la irritación y humecta las áreas irritadas. Cierra los poros con una sensación de frescura.');

INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (2, 85.00, 61, 'DEVELOPER ACTIVATOR (OXIGENTA) 450 ML', 'American Crew Developer es un Activador de 15 vol (4.5%). Una emulsion reveladora para uso en conjunto de la coloración precision blend de American Crew.');

INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (2, 75.00, 76, 'TINTE PRECISION BLEND (CASTAÑO CENIZO)', 'PRESENTAMOS UN NUEVO SISTEMA DE COBERTURA DE CANAS EXCLUSIVO PARA ELLOS. CREA LA COLORACIÓN NATURAL QUE BUSCAN EN TAN SOLO CINCO MINUTOS.');

INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (3, 190.00, 20, 'SHAMPOO 3-EN-1 ENERGIZING GINGER+TEA 1L', 'Shampoo, acondicionador y Body Wash, formulado con aroma de energizante de jengibre y té. Inspirado en la aromacología te ayuda a sentirte despierto, lleno de energía y vigorizado.');

INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (3, 75.00, 34, 'SHAMPOO 3 EN 1 – TEA TREE – 250 ML', 'Shampoo, acondicionador y gel de ducha todo en uno. Para los chicos sencillos, sin complicaciones. El 3 IN 1 más eficaz del mercado Alivia el cuero cabelludo y la piel.');

INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (4, 115.00, 72, 'MR MUK HAIR, BEARD & BODY WASH 250ML', 'De la jungla urbana a un paraíso tropical, este es el único producto que te acompañará a donde quiera que vayas. Adecuado para todo tipo de cabello y piel.');

INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion) VALUES (4, 85.00, 53, 'BEARD SERUM 50 ML', 'Fórmula ligera y de rápida absorción. Una fórmula a base de aceite especialmente diseñada para el cuidado de la barba, con aceites beneficiosos que instantáneamente acondicionan.');


INSERT INTO tipo_servicios (nombre) VALUES ('SERVICIOS INDIVIDUALES');

INSERT INTO tipo_servicios (nombre) VALUES ('PAQUETES DE SERVICIOS');

INSERT INTO tipo_servicios (nombre) VALUES ('TINTURACIÓN');


INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (55.00, 'CORTE + LAVADO + PEINADO', 'Recibe una asesoría previa por parte de nuestro barbero, y mientras te corta el pelo, disfruta de una bebida de cortesía.', 1);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (40.00, 'ARREGLO/AFEITADO DE BARBA', 'El servicio incluye asesoramiento, arreglo, delineado de la barba o afeitado con toalla caliente, finalmente hidratamos.', 1);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (50.00, 'RITUAL DE BARBA', 'El servicio comenzará con una aplicación de pre-shave y colocación de la toalla caliente por 2 ó 3 minutos con masajes incluido para dejar tu piel suave.', 1);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (70.00, 'LIMPIEZA FACIAL', 'Relájese y limpie su rostro con una limpieza facial. Nuestro servicio de incluye vaporización de ozono con masajes en el rostro y cabeza + exfoliación + mascarilla negra.', 1);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (85.00, 'CORTE DE PELO + ARREGLO/AFEITADO DE BARBA.', '', 2);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (95.00, 'CORTE DE PELO + RITUAL DE BARBA.', '', 2);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (99.00, 'CORTE DE PELO + LIMPIEZA FACIAL', '', 2);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (89.00, 'ARREGLO/AFEITADO DE BARBA + LIMPIEZA FACIAL', '', 2);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (99.00, 'RITUAL DE BARBA + LIMPIEZA FACIAL', '', 2);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (124.00, 'CORTE DE PELO + ARREGLO/AFEITADO DE BARBA + LIMPIEZA FACIAL', '', 2);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (134.00, 'CORTE DE PELO + RITUAL DE BARBA + LIMPIEZA FACIAL', '', 2);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (80.00, 'CAMUFLAJE DE CANAS PARA PELO', 'Este tratamiento de color consigue rejuvenecer los pelos blancos de la cana consiguiendo un resultado muy natural', 3);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (40.00, 'CAMUFLAJE DE CANAS PARA BARBA', 'Este tratamiento de color consigue rejuvenecer los pelos blancos de la BARBA consiguiendo un resultado muy natural', 3);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (120.00, 'CORTE + CAMUFLAJE DE CANAS DE PELO', '', 3);

INSERT INTO servicios (tarifa, nombre, detalle, id_tipo_servicio) VALUES (75.00, 'AFEITADO/ARREGLO Y CAMUFLAJE DE CANAS PARA BARBA', '', 3);
