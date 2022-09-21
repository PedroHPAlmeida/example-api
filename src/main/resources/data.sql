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

INSERT INTO PRODUTOS(nome, preco) VALUES ('Arroz', 8.0);
INSERT INTO PRODUTOS(nome, preco) VALUES ('Feijão', 5.0);
INSERT INTO PRODUTOS(nome, preco) VALUES ('Macarrão', 7.0);
INSERT INTO PRODUTOS(nome, preco) VALUES ('Óleo', 4.5);
INSERT INTO PRODUTOS(nome, preco) VALUES ('Farinha', 2.5);

INSERT INTO USUARIOS(nome, email, senha) VALUES ('Administrador do Sistema', 'admin@email.com', '$2a$10$877Aw45Gi4kkd.ay119Tme0X2ii49.UMySFBKG4EUN0Lhb7Ue3wgG'); -- Senha 'admin' criptografada com a classe BCryptPasswordEncoder
INSERT INTO USUARIOS(nome, email, senha) VALUES ('Usuário Normal', 'user@email.com', '$2a$10$Nx5UkFMqU78H1HzvwdzbnuJn3GDN.uhnkvhW42J2w1Ji7RPWtTsAa'); -- Senha 'user' criptografada com a classe BCryptPasswordEncoder

INSERT INTO PERFIS(nome) VALUES ('ROLE_ADMIN'); -- Para funcionar corretamente o nome do perfil deve ser salvo no BD com o prefixo 'ROLE'
INSERT INTO PERFIS(nome) VALUES ('ROLE_NORMAL');

INSERT INTO USUARIOS_PERFIS(usuario_id, perfis_id) VALUES (1, 1);
INSERT INTO USUARIOS_PERFIS(usuario_id, perfis_id) VALUES (2, 2);
