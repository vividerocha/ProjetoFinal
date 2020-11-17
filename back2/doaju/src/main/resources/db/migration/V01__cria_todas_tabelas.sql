-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 10-Nov-2020 às 01:53
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `doaju`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

CREATE TABLE `aluno` (
  `id` bigint(20) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `escola` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  `numero_casa` int(11) NOT NULL,
  `serie` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `termo` bit(1) NOT NULL,
  `turma` varchar(255) DEFAULT NULL,
  `turno` varchar(255) DEFAULT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno_equipamento`
--

CREATE TABLE `aluno_equipamento` (
  `aluno_id` bigint(20) NOT NULL,
  `equipamento_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `doador`
--

CREATE TABLE `doador` (
  `id` bigint(20) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  `numero_casa` int(11) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `termo` bit(1) NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `equipamento`
--

CREATE TABLE `equipamento` (
  `id` bigint(20) NOT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `descricao_equipamento` varchar(255) DEFAULT NULL,
  `funcionando` bit(1) NOT NULL,
  `tipo_equipamento_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupo`
--

CREATE TABLE `grupo` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupo_permissao`
--

CREATE TABLE `grupo_permissao` (
  `grupo_id` bigint(20) NOT NULL,
  `permissao_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `historico_equipamento`
--

CREATE TABLE `historico_equipamento` (
  `id` bigint(20) NOT NULL,
  `data_alteracao` datetime(6) DEFAULT NULL,
  `equipamento_id` bigint(20) NOT NULL,
  `situacao_equipamento_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mensagem`
--

CREATE TABLE `mensagem` (
  `id` bigint(20) NOT NULL,
  `assunto` varchar(255) DEFAULT NULL,
  `destinatario` varchar(255) DEFAULT NULL,
  `mensagem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `permissao`
--

CREATE TABLE `permissao` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `questionario`
--

CREATE TABLE `questionario` (
  `id` bigint(20) NOT NULL,
  `data_alteracao` datetime(6) DEFAULT NULL,
  `perg1` bigint(20) DEFAULT NULL,
  `perg2` bigint(20) DEFAULT NULL,
  `perg3` bigint(20) DEFAULT NULL,
  `perg4` bigint(20) DEFAULT NULL,
  `perg5` bigint(20) DEFAULT NULL,
  `perg6` bigint(20) DEFAULT NULL,
  `perg7` bigint(20) DEFAULT NULL,
  `perg8` bigint(20) DEFAULT NULL,
  `perg9` bigint(20) DEFAULT NULL,
  `perg10` bigint(20) DEFAULT NULL,
  `aluno_id` bigint(20) DEFAULT NULL,
  `pontuacao_total` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `situacao_equipamento`
--

CREATE TABLE `situacao_equipamento` (
  `id` bigint(20) NOT NULL,
  `situacao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tecnico`
--

CREATE TABLE `tecnico` (
  `id` bigint(20) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  `numero_casa` int(11) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `termo` bit(1) NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_equipamento`
--

CREATE TABLE `tipo_equipamento` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_grupo`
--

CREATE TABLE `usuario_grupo` (
  `usuario_id` bigint(20) NOT NULL,
  `grupo_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsgpw6tb2kerkceshx1b10rhkg` (`usuario_id`);

--
-- Índices para tabela `aluno_equipamento`
--
ALTER TABLE `aluno_equipamento`
  ADD PRIMARY KEY (`aluno_id`,`equipamento_id`),
  ADD KEY `FKmwjwn731bdm8vajr48gpbi11m` (`equipamento_id`);

--
-- Índices para tabela `doador`
--
ALTER TABLE `doador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4oti3m4qr8gk4sl0ul56cnhrc` (`usuario_id`);

--
-- Índices para tabela `equipamento`
--
ALTER TABLE `equipamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt3dslogcrxyu672jsdmjplioy` (`tipo_equipamento_id`);

--
-- Índices para tabela `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `grupo_permissao`
--
ALTER TABLE `grupo_permissao`
  ADD PRIMARY KEY (`grupo_id`,`permissao_id`),
  ADD KEY `FKh21kiw0y0hxg6birmdf2ef6vy` (`permissao_id`);

--
-- Índices para tabela `historico_equipamento`
--
ALTER TABLE `historico_equipamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK89v5swr2w05mpug6kflp0kg10` (`equipamento_id`),
  ADD KEY `FKp6j6ksivq5ahnlrlgbua8uq97` (`situacao_equipamento_id`),
  ADD KEY `FKh070j0j55trxgxwk3lok15xlh` (`usuario_id`);

--
-- Índices para tabela `mensagem`
--
ALTER TABLE `mensagem`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `permissao`
--
ALTER TABLE `permissao`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `questionario`
--
ALTER TABLE `questionario`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `situacao_equipamento`
--
ALTER TABLE `situacao_equipamento`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tecnico`
--
ALTER TABLE `tecnico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3k1f8ce4xjsw2bg58bihd4ets` (`usuario_id`);

--
-- Índices para tabela `tipo_equipamento`
--
ALTER TABLE `tipo_equipamento`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuario_grupo`
--
ALTER TABLE `usuario_grupo`
  ADD PRIMARY KEY (`usuario_id`,`grupo_id`),
  ADD KEY `FKk30suuy31cq5u36m9am4om9ju` (`grupo_id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `doador`
--
ALTER TABLE `doador`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `equipamento`
--
ALTER TABLE `equipamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `grupo`
--
ALTER TABLE `grupo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `historico_equipamento`
--
ALTER TABLE `historico_equipamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `mensagem`
--
ALTER TABLE `mensagem`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `permissao`
--
ALTER TABLE `permissao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `situacao_equipamento`
--
ALTER TABLE `situacao_equipamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tecnico`
--
ALTER TABLE `tecnico`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tipo_equipamento`
--
ALTER TABLE `tipo_equipamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `aluno`
--
ALTER TABLE `aluno`
  ADD CONSTRAINT `FKsgpw6tb2kerkceshx1b10rhkg` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `aluno_equipamento`
--
ALTER TABLE `aluno_equipamento`
  ADD CONSTRAINT `FKkbykrnyf79pidfrm988ji4kvf` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`id`),
  ADD CONSTRAINT `FKmwjwn731bdm8vajr48gpbi11m` FOREIGN KEY (`equipamento_id`) REFERENCES `tipo_equipamento` (`id`);

--
-- Limitadores para a tabela `doador`
--
ALTER TABLE `doador`
  ADD CONSTRAINT `FK4oti3m4qr8gk4sl0ul56cnhrc` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `equipamento`
--
ALTER TABLE `equipamento`
  ADD CONSTRAINT `FKt3dslogcrxyu672jsdmjplioy` FOREIGN KEY (`tipo_equipamento_id`) REFERENCES `tipo_equipamento` (`id`);

--
-- Limitadores para a tabela `grupo_permissao`
--
ALTER TABLE `grupo_permissao`
  ADD CONSTRAINT `FKh21kiw0y0hxg6birmdf2ef6vy` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`),
  ADD CONSTRAINT `FKta4si8vh3f4jo3bsslvkscc2m` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`);

--
-- Limitadores para a tabela `historico_equipamento`
--
ALTER TABLE `historico_equipamento`
  ADD CONSTRAINT `FK89v5swr2w05mpug6kflp0kg10` FOREIGN KEY (`equipamento_id`) REFERENCES `equipamento` (`id`),
  ADD CONSTRAINT `FKh070j0j55trxgxwk3lok15xlh` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FKp6j6ksivq5ahnlrlgbua8uq97` FOREIGN KEY (`situacao_equipamento_id`) REFERENCES `situacao_equipamento` (`id`);

--
-- Limitadores para a tabela `questionario`
--
ALTER TABLE `questionario`
  ADD CONSTRAINT `FK31iia34d5pnkaf7h1dg5gfbgq` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`id`);

--
-- Limitadores para a tabela `tecnico`
--
ALTER TABLE `tecnico`
  ADD CONSTRAINT `FK3k1f8ce4xjsw2bg58bihd4ets` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `usuario_grupo`
--
ALTER TABLE `usuario_grupo`
  ADD CONSTRAINT `FKdofo9es0esuiahyw2q467crxw` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FKk30suuy31cq5u36m9am4om9ju` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
