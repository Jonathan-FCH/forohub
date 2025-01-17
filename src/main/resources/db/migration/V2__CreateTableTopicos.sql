
create table topicos(

    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_de_creacion DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    autor BIGINT NOT NULL,
    curso VARCHAR(255) NOT NULL,
    FOREIGN KEY (autor) REFERENCES usuarios(id)

);