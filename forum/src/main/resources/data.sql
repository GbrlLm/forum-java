INSERT INTO LIVRO(nome, categoria) VALUES('Harry Potter', 'Aventura');
INSERT INTO LIVRO(nome, categoria) VALUES('It, A Coisa', 'Terror');

INSERT INTO USUARIO(nome, email, senha, livro_id) VALUES('Gabriel', 'gabriel@hotmail.com', '$2a$10$j57M3iEBBG7pRRXKJEbiI.Uui0RjBWfv8kxuUjYzyrQVqG4d8cdqK', 1);

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, livro_id) VALUES('Dúvida', 'Quem matou tal personagem', '2020-06-20 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, livro_id) VALUES('Dúvida 2', 'O livro é bom', '2020-06-20 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, livro_id) VALUES('Dúvida 3', 'É dificil de ler?', '2020-06-20 20:00:00', 'NAO_RESPONDIDO', 1, 2);

