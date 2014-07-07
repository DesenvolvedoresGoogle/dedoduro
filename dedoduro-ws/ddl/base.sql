-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 06/07/2014 às 09:26
-- Versão do servidor: 5.5.37-0ubuntu0.14.04.1
-- Versão do PHP: 5.5.9-1ubuntu4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `dedoduro`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `img` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Fazendo dump de dados para tabela `category`
--

INSERT INTO `category` (`id`, `img`, `name`) VALUES
(1, '/var/www/html/dedoduro/img/category/violencia.png', 'Violêcia'),
(2, '/var/www/html/dedoduro/img/category/educacao.png', 'Educação'),
(3, '/var/www/html/dedoduro/img/category/estabelecimentos.png', 'Estabelecimentos'),
(4, '/var/www/html/dedoduro/img/category/servicos.png', 'Serviços'),
(5, '/var/www/html/dedoduro/img/category/transportes.png', 'Transportes'),
(6, '/var/www/html/dedoduro/img/category/condicao_de_vias.png', 'Condição de Vias');

-- --------------------------------------------------------

--
-- Estrutura para tabela `gps_register`
--

CREATE TABLE IF NOT EXISTS `gps_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(30) DEFAULT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Fazendo dump de dados para tabela `gps_register`
--

INSERT INTO `gps_register` (`id`, `description`, `lat`, `lng`, `category_id`, `user_id`) VALUES
(1, 'Teste', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `gps_register_comment`
--

CREATE TABLE IF NOT EXISTS `gps_register_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `gps_register_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gps_register_id` (`gps_register_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `gps_register_image`
--

CREATE TABLE IF NOT EXISTS `gps_register_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(30) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `gps_register_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gps_register_id` (`gps_register_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `gps_register_rate`
--

CREATE TABLE IF NOT EXISTS `gps_register_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rate` int(11) DEFAULT NULL,
  `gps_register_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gps_register_id` (`gps_register_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Fazendo dump de dados para tabela `gps_register_rate`
--

INSERT INTO `gps_register_rate` (`id`, `rate`, `gps_register_id`, `user_id`) VALUES
(2, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `subcategory`
--

CREATE TABLE IF NOT EXISTS `subcategory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(30) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Fazendo dump de dados para tabela `subcategory`
--

INSERT INTO `subcategory` (`id`, `description`, `category_id`) VALUES
(1, 'Violência Contra Terceiros', 1),
(2, 'Violência Contra Animais', 1),
(3, 'Abuso de Poder', 1),
(4, 'Condições', 2),
(5, 'Disponibilidade de Instituiçõe', 2),
(6, 'Qualidade de Atendimento', 3),
(7, 'Qualidade do Produto Servido', 3),
(8, 'Coleta de Lixo', 4),
(9, 'Qualidade da Telefonia Fixa e ', 4),
(10, 'Lotação', 5),
(11, 'Qualidade', 5),
(12, 'Disponibilidade', 5),
(13, 'Iluminação Pública', 6),
(14, 'Buraco', 6),
(15, 'Lixo', 6),
(16, 'Conservação', 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Fazendo dump de dados para tabela `user`
--

INSERT INTO `user` (`id`, `email`, `password`) VALUES
(1, 'user@user.com', 'senha');

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `gps_register`
--
ALTER TABLE `gps_register`
  ADD CONSTRAINT `gps_register_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `gps_register_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Restrições para tabelas `gps_register_comment`
--
ALTER TABLE `gps_register_comment`
  ADD CONSTRAINT `gps_register_comment_ibfk_1` FOREIGN KEY (`gps_register_id`) REFERENCES `gps_register` (`id`),
  ADD CONSTRAINT `gps_register_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Restrições para tabelas `gps_register_image`
--
ALTER TABLE `gps_register_image`
  ADD CONSTRAINT `gps_register_image_ibfk_1` FOREIGN KEY (`gps_register_id`) REFERENCES `gps_register` (`id`);

--
-- Restrições para tabelas `gps_register_rate`
--
ALTER TABLE `gps_register_rate`
  ADD CONSTRAINT `gps_register_rate_ibfk_1` FOREIGN KEY (`gps_register_id`) REFERENCES `gps_register` (`id`),
  ADD CONSTRAINT `gps_register_rate_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Restrições para tabelas `subcategory`
--
ALTER TABLE `subcategory`
  ADD CONSTRAINT `subcategory_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
