INSERT INTO `usuario` (`id`, `email`, `senha`, `usuario`) VALUES (NULL, 'admin@local', '$2y$12$uSvTTRsf6sBuWMH4nbMeX.ZRHPaNqZUvqTS7J7HS3hfamZaXmcC2O', 'administrador');

INSERT INTO `grupo` (`id`, `nome`) VALUES
(1, 'usuarios'),
(2, 'Admin'),
(3, 'Alunos'),
(4, 'Doadores'),
(5, 'Tecnicos');

INSERT INTO `permissao` (`id`, `descricao`, `nome`) VALUES
(1, 'Permissões de Doador', 'PDJDO01'),
(2, 'Permissoes para todos os usuarios', 'PDJUS01'),
(3, 'Permissoes de Administrador', 'PDJAD01'),
(4, 'Permissões de Aluno', 'PDJAL01'),
(5, 'Permissões de Técnico', 'PDJTE01');

INSERT INTO `grupo_permissao` (`grupo_id`, `permissao_id`) VALUES
(1, 2),
(2, 3),
(3, 4),
(4, 1),
(5, 5);

INSERT INTO `tipo_equipamento` VALUES (null,'Notebook'),(null,'Celular'),(null,'Tablet'),(null,'PC Desktop'),(null,'Impressora');

INSERT INTO `situacao_equipamento` VALUES (null,'Cadastrado'),(null,'Reservado pelo Técnico'),(null,'Em manutenção'),(null,'Pronto para Distribuição'),(null,'Distribuído'),(null,'Entregue ao Aluno');