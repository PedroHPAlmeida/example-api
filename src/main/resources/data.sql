INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Pedro Henrique', '2001-02-09', 'm');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('José Almeida', '2000-03-16', 'm');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Maria Pereira', '1990-07-29', 'f');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('João Silva', '2002-02-02', 'm');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Ana Marques', '1995-12-31', 'f');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Joana Lacerda', '1978-09-10', 'f');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Lucas Mendes', '1980-01-01', 'm');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Jorge Oliveira', '1982-04-17', 'm');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Mateus Ferreira', '1966-12-22', 'm');
INSERT INTO CLIENTES(nome, data_nascimento, sexo) VALUES ('Gustavo Azevedo', '1969-08-24', 'm');

INSERT INTO USUARIOS(nome, email, senha) VALUES ('Administrador do Sistema', 'admin@email.com', '$2a$10$877Aw45Gi4kkd.ay119Tme0X2ii49.UMySFBKG4EUN0Lhb7Ue3wgG') // Senha 'admin' criptografada com a classe BCryptPasswordEncoder