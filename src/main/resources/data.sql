
insert into rol (id, rol, descripcion) values (1, 'GERENTE', 'Gerente');
insert into rol (id, rol, descripcion) values (2, 'RECEPCIONISTA', 'Recepcionista');
insert into rol (id, rol, descripcion) values (3, 'CLIENTE', 'Cliente');

insert into estado_habitacion (codigo, descripcion) values ('LIB', 'Libre');
insert into estado_habitacion (codigo, descripcion) values ('OCU', 'Ocupada');
insert into estado_habitacion (codigo, descripcion) values ('MAN', 'Mantenimiento');
insert into estado_habitacion (codigo, descripcion) values ('LIM', 'Limpieza');

insert into tipo_habitacion (codigo, descripcion, capacidad) values ('EST', 'Estandar', 3);
insert into tipo_habitacion (codigo, descripcion, capacidad) values ('NOR', 'Normal', 4);
insert into tipo_habitacion (codigo, descripcion, capacidad) values ('SUI', 'Suite', 6);

insert into transicion_estado (origen, fin) values ('LIB','OCU');
insert into transicion_estado (origen, fin) values ('LIB','MAN');
insert into transicion_estado (origen, fin) values ('OCU','MAN');
insert into transicion_estado (origen, fin) values ('OCU','LIM');
insert into transicion_estado (origen, fin) values ('LIM','LIB');
insert into transicion_estado (origen, fin) values ('LIM','MAN');
insert into transicion_estado (origen, fin) values ('MAN','LIB');
insert into transicion_estado (origen, fin) values ('MAN','LIM');

insert into habitacion (puerta, piso, tipo, estado) values ('201', '2', 'EST', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('202', '2', 'NOR', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('203', '2', 'SUI', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('301', '3', 'EST', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('302', '3', 'NOR', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('303', '3', 'SUI', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('401', '4', 'EST', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('402', '4', 'NOR', 'LIB');
insert into habitacion (puerta, piso, tipo, estado) values ('403', '4', 'SUI', 'LIB');

insert into usuario (username, password, nombres, apellidos, rol) values ('harry', '123', 'harry', 'mamani', 1);
insert into usuario (username, password, nombres, apellidos, rol) values ('mayra', '123', 'mayra', 'cuellar', 2);