
CREATE TABLE usuarios
(
    id  int auto_increment
        primary key,
    password varchar(10)  null,
    nombre   varchar(255) null
);

INSERT INTO usuarios (id, password, nombre) VALUES (1, '1234', 'raquel');