-- USUÁRIOS
INSERT INTO usuario (id, nome, email, senha) VALUES
(1, 'Juliana Ransani', 'juliana@email.com', '1234'),
(2, 'Nathan Alvez', 'nathan@email.com', 'abcd');

-- RECEITAS
INSERT INTO receita (id, descricao, valor, data, usuario_id) VALUES
(1, 'Salário', 3500.00, '2025-05-01', 1),
(2, 'Freelance', 1200.00, '2025-05-10', 1),
(3, 'Bônus', 500.00, '2025-05-05', 2);

-- DESPESAS
INSERT INTO despesa (id, descricao, valor, data, categoria, usuario_id) VALUES
(1, 'Aluguel', 1500.00, '2025-05-03', 'MORADIA', 1),
(2, 'Mercado', 450.00, '2025-05-05', 'ALIMENTACAO', 1),
(3, 'Internet', 120.00, '2025-05-10', 'OUTROS', 1),
(4, 'Academia', 80.00, '2025-05-12', 'SAUDE', 2);

-- CONTAS PARCELADAS
INSERT INTO conta_parcelada (id, descricao, valor_total, quantidade_parcelas, data_inicio, usuario_id) VALUES
(1, 'Notebook', 3000.00, 6, '2025-04-01', 1),
(2, 'Curso Online', 1200.00, 4, '2025-04-15', 2);

-- PARCELAS (relacionadas às contas)
INSERT INTO parcela (id, numero, valor, data_vencimento, paga, conta_parcelada_id) VALUES
(1, 1, 500.00, '2025-04-01', true, 1),
(2, 2, 500.00, '2025-05-01', false, 1),
(3, 3, 500.00, '2025-06-01', false, 1),
(4, 1, 300.00, '2025-04-15', true, 2),
(5, 2, 300.00, '2025-05-15', false, 2);

-- ALERTAS
INSERT INTO alerta (id, mensagem, data_geracao, visualizado, usuario_id) VALUES
(1, 'Parcela nº 2 vence em breve: 2025-05-01', '2025-04-28', false, 1),
(2, 'Parcela nº 2 vence em breve: 2025-05-15', '2025-05-12', true, 2);