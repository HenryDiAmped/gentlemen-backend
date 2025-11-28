INSERT INTO usuarios (celular, dni, apellidos, nombres, email, contrasena, tipo_usuario) VALUES ('999999999', '77777777', 'Admin', 'Admin', 'admin@gentlemen.com', '$2a$10$3GQgqNi4NukNh5xsxOwdzum51KdnuF7Ei2ik1AFU6Q/3q9GucsVBa', 'ADMINISTRADOR');

INSERT INTO administradores (id_usuario) VALUES (1);

INSERT INTO sedes (nombre_sede, direccion) VALUES ('Chorrillos', 'Av.Chorrillos');
INSERT INTO sedes (nombre_sede, direccion) VALUES ('Surco', 'Av.Surco');
INSERT INTO sedes (nombre_sede, direccion) VALUES ('Lince', 'Av.Lince');

INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Jair Ramírez', 1, 4.80, 'https://i.pinimg.com/736x/1a/e5/36/1ae5368dd09f30407ca79232155afaa5.jpg', 'Especialista en cortes fade.', TRUE, 'DOMINGO', 'FULL_TIME');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Vyro Perez', 1, 4.50, 'https://i.pinimg.com/1200x/8e/e6/cd/8ee6cd37b5634e87277f5f43ada8cb44.jpg', 'El mejor en afeitados clásicos.', TRUE, 'LUNES', 'PART_TIME_TARDE');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Christian Torres', 2, 4.90, 'https://i.pinimg.com/736x/d7/a0/ed/d7a0ed7bc228614bcfbb079e38331699.jpg', 'Maestro del cabello rizado.', TRUE, 'NINGUNO', 'FULL_TIME');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Luis Gómez', 1, 4.7, 'https://i.pinimg.com/736x/6d/4e/a5/6d4ea52ccfa18c99927f82025e0f063f.jpg', 'Especialista en cortes modernos y clásicos.', TRUE, 'LUNES', 'FULL_TIME');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Andrés Salazar', 1, 4.5, 'https://i.pinimg.com/736x/04/15/e8/0415e836654dad01e77a2c2515eb8c55.jpg', 'Experto en afeitados clásicos y barba.', TRUE, 'MARTES', 'PART_TIME_MANANA');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Kevin Rojas', 2, 4.6, 'https://i.pinimg.com/736x/35/ec/24/35ec24d35b59a7b981c9fbddebd6bf90.jpg', 'Maestro del cabello rizado y cuidado del cuero cabelludo.', TRUE, 'MIERCOLES', 'FULL_TIME');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Daniel Quispe', 2, 4.8, 'https://i.pinimg.com/1200x/d0/1f/ea/d01feaa13d5e26035a3dae896da47de6.jpg', 'Especialista en fades y peinados modernos.', TRUE, 'JUEVES', 'PART_TIME_TARDE');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Carlos Mendoza', 1, 4.4, 'https://i.pinimg.com/736x/7a/48/42/7a484275610640b98f637b9703bbfc7f.jpg', 'Con experiencia en cortes creativos y afeitados finos.', TRUE, 'VIERNES', 'FULL_TIME');
INSERT INTO barberos (nombre, id_sede, rating, imagen_url, biografia, is_active, day_off, shift) VALUES ('Marco Vargas', 2, 4.9, 'https://i.pinimg.com/736x/22/67/b5/2267b58b5ba7c1eb9a5054162d794f8a.jpg', 'Excelente atención al cliente y precisión en cortes.', TRUE, 'SABADO', 'PART_TIME_MANANA');


INSERT INTO categorias (nombre, descripcion) VALUES ('Afeitado', 'Producto para afeitado');
INSERT INTO categorias (nombre, descripcion) VALUES ('Tintes', 'Producto para tinte');
INSERT INTO categorias (nombre, descripcion) VALUES ('Lavado y cuidado del cabello', 'Producto para lavado');
INSERT INTO categorias (nombre, descripcion) VALUES ('Crecimiento y cuidado de barba', 'Producto para la barba');


INSERT INTO marcas (nombre, descripcion) VALUES ('American Crew', 'Marca líder de productos de cuidado masculino, ofreciendo una amplia gama de artículos para cabello, afeitado, cuerpo y barba, diseñados tanto para uso profesional como personal.');
INSERT INTO marcas (nombre, descripcion) VALUES ('MUK', 'Marca australiana de productos de cuidado y estilo para el cabello, que ofrece una amplia gama de artículos para uso profesional y personal.');
INSERT INTO marcas (nombre, descripcion) VALUES ('Vikingo', 'Marca peruana de cuidado personal masculino, enfocada en productos para el cabello (shampoos anticaída, acondicionadores, ceras) y barba (bálsamos, aceites).');


INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (1, 80.00, 57, 'MOISTURIZING SHAVE CREAM 150 ML', 'La Crema de afeitar American Crew hidrata,calma y alivia las irritaciones de la piel y permite un afeitado apurado. La crema no espumosa crea una barrera protectora en la piel que permite que la cuchilla se deslize sin secar la piel.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/foto-producto-01092020225139-full.jpg', 1);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (1, 80.00, 44, 'REVIT TONER 150 ML', 'Suaviza la piel después de afeitar. Tonifica y refresca. Calma la irritación y humecta las áreas irritadas. Cierra los poros con una sensación de frescura.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/foto-producto-01092020225237-full.jpg', 1);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (2, 85.00, 61, 'DEVELOPER ACTIVATOR (OXIGENTA) 450 ML', 'American Crew Developer es un Activador de 15 vol (4.5%). Una emulsion reveladora para uso en conjunto de la coloración precision blend de American Crew.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/foto-producto-12082020153623-full.jpg', 1);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (2, 75.00, 76, 'TINTE PRECISION BLEND (CASTAÑO CENIZO)', 'PRESENTAMOS UN NUEVO SISTEMA DE COBERTURA DE CANAS EXCLUSIVO PARA ELLOS. CREA LA COLORACIÓN NATURAL QUE BUSCAN EN TAN SOLO CINCO MINUTOS.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/foto-producto-12082020154330-full.jpg', 1);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (3, 190.00, 20, 'SHAMPOO 3-EN-1 ENERGIZING GINGER+TEA 1L', 'Shampoo, acondicionador y Body Wash, formulado con aroma de energizante de jengibre y té. Inspirado en la aromacología te ayuda a sentirte despierto, lleno de energía y vigorizado.', 'https://thebarbercompany.pe/wp-content/uploads/2025/04/ginger.png', 1);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (3, 75.00, 34, 'SHAMPOO 3 EN 1 – TEA TREE – 250 ML', 'Shampoo, acondicionador y gel de ducha todo en uno. Para los chicos sencillos, sin complicaciones. El 3 IN 1 más eficaz del mercado Alivia el cuero cabelludo y la piel.', 'https://thebarbercompany.pe/wp-content/uploads/2024/10/tea.png', 1);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (4, 85.00, 53, 'BEARD SERUM 50 ML', 'Fórmula ligera y de rápida absorción. Una fórmula a base de aceite especialmente diseñada para el cuidado de la barba, con aceites beneficiosos que instantáneamente acondicionan.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/foto-producto-12082020145110-full.jpg', 1);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (4, 115.00, 72, 'MR MUK HAIR, BEARD & BODY WASH 250ML', 'De la jungla urbana a un paraíso tropical, este es el único producto que te acompañará a donde quiera que vayas. Adecuado para todo tipo de cabello y piel.', 'https://thebarbercompany.pe/wp-content/uploads/2024/05/hairbeard.png', 2);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (4, 155.00, 50, 'Aceite de Argán Tratamiento (SPA) 100 ml', 'Una lujosa mezcla de fácil absorción de puro aceite de argán marroquí, aceite de linaza, aceite de almendras dulces y aloe vera.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/foto-producto-14102020172538-full.jpg', 2);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (4, 127.00, 56, 'Minoxidil', 'El único producto que está comprobado que funciona para estimular el crecimiento de la barba es el Minoxidil en bálsamo.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/Cuadradas.png', 3);
INSERT INTO productos (id_categoria, precio, stock, nombre, descripcion, imagen_url, id_marca) VALUES (4, 65.00, 80, 'Shampoo Barba Vikingo', 'Shampoo orgánico que humecta y acondiciona la barba. Contiene aloe vera y es libre de parabenos.', 'https://thebarbercompany.pe/wp-content/uploads/2022/08/foto-producto-17022022151652-full.png', 3);


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
