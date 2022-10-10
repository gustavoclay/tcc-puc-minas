-- Inserir usuario admin e permissoes, usuario: admin senha: admin
INSERT INTO tb_usuario (id_usuario, username, email, password) values (1, 'admin', 'admin@admin.com', '$2a$10$PUIJUx4LaGS/6JkQIxIcv.DdJVI6w5wvpyw1Q26.GFbyjSnv1qPxG');
INSERT INTO tb_permissao (id_permissao, descricao) values (1, 'ROLE_ADMIN');
INSERT INTO rl_usuario_permissao (id_usuario, id_permissao) values (1, 1);

-- Inserir indices do IMC MASCULINO 2 a 18 anos
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (2,14.9,17.3,19,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (3,14.7,16.9,18.5,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (4,14.3,16.4,17.8,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (5,14.1,16.1,17.6,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (6,13.6,15.9,18.1,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (7,13.8,16.2,18.3,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (8,13.9,16.5,19,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (9,14,16.9,19.9,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (10,14.2,17.4,20.8,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (11,14.5,18.1,21.8,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (12,14.9,18.8,22.8,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (13,15.4,19.5,23.7,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (14,15.9,20.2,24.8,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (15,16.5,21,25.5,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (16,17,21.7,26.2,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (17,17.6,22.4,27,0);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (18,18.2,23.1,27.7,0);

-- Inserir indices do IMC FEMININO 2 a 18 anos
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (2,14.6,17.2,18.8,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (3,14.5,16.7,18.3,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (4,14,16.1,17.8,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (5,13.7,15.9,17.7,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (6,13.6,15.9,18.1,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (7,13.5,16.2,18.7,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (8,13.6,16.6,19.5,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (9,13.8,17.1,20.5,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (10,14,17.8,21.6,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (11,14.4,18.5,22.6,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (12,14.8,19.2,23.7,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (13,15.5,20.3,25.7,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (14,15.8,20.6,25.7,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (15,16.3,21.3,26.5,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (16,16.8,21.9,27.3,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (17,17.2,22.4,28,1);
INSERT INTO db_geduca.tb_indice_imc (idade,minimo,normal,maximo,sexo) VALUES (18,17.6,22.9,28.6,1);
