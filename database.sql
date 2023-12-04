-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.11-MariaDB - Source distribution
-- OS do Servidor:               Linux
-- HeidiSQL Versão:              12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para plataforma_video
DROP DATABASE IF EXISTS `plataforma_video`;
CREATE DATABASE IF NOT EXISTS `plataforma_video` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `plataforma_video`;

-- Copiando estrutura para tabela plataforma_video.assistirvideo
DROP TABLE IF EXISTS `assistirvideo`;
CREATE TABLE IF NOT EXISTS `assistirvideo` (
  `usuario_codusuario` int(11) NOT NULL,
  `video_codVideo` int(11) NOT NULL,
  `videosCurtidos` varchar(1) NOT NULL,
  PRIMARY KEY (`usuario_codusuario`,`video_codVideo`),
  KEY `fk_usuario_has_video_usuario1_idx` (`usuario_codusuario`),
  KEY `fk_usuario_has_video_video1_idx` (`video_codVideo`),
  CONSTRAINT `fk_usuario_has_video_usuario1` FOREIGN KEY (`usuario_codusuario`) REFERENCES `usuario` (`codusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_video_video1` FOREIGN KEY (`video_codVideo`) REFERENCES `video` (`codVideo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.assistirvideo: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela plataforma_video.auditoria
DROP TABLE IF EXISTS `auditoria`;
CREATE TABLE IF NOT EXISTS `auditoria` (
  `codAuditoria` int(11) NOT NULL AUTO_INCREMENT,
  `acao` varchar(800) DEFAULT NULL,
  `tabela` varchar(50) DEFAULT NULL,
  `dataHora` datetime DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codAuditoria`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='Registra todas as informações sobre os processos mais importantes do sistema\r\n';

-- Copiando dados para a tabela plataforma_video.auditoria: ~13 rows (aproximadamente)
INSERT INTO `auditoria` (`codAuditoria`, `acao`, `tabela`, `dataHora`, `usuario`) VALUES
	(1, NULL, 'usuario', '2023-10-06 10:54:54', 'root@localhost'),
	(2, NULL, 'video', '2023-10-06 10:56:47', 'root@localhost'),
	(3, 'Novo usuário registrado (João Bosco) com o email joao.bosco@alunos.ifsuldeminas.edu.br', 'usuario', '2023-10-06 10:57:25', 'root@localhost'),
	(4, 'Usuário GabrielPGonçalves alterado para BIEL_ClubeUniforte', 'usuario', '2023-10-24 08:00:38', 'root@localhost'),
	(5, 'Email do Usuário TESTE alterado para teste404@yahoo.com.br', 'usuario', '2023-10-24 08:01:09', 'root@localhost'),
	(6, 'Senha do Usuário João Bosco alterado para 12345', 'usuario', '2023-10-24 08:01:17', 'root@localhost'),
	(7, 'Vídeo Derrotando o EnderDragon só na cama - Joãozinho Gameplays do canal Joãozinho Gameplays teve o título alterado para Usando a estratégia da cama para matar o ender dragon - Joãozinho Gameplays', 'video', '2023-10-24 08:14:45', 'root@localhost'),
	(8, NULL, 'video', '2023-11-14 07:19:33', 'root@localhost'),
	(9, NULL, 'usuario', '2023-11-15 22:35:42', 'root@localhost'),
	(10, 'Senha do Usuário Taiki Hamasaki alterado para 123456789', 'usuario', '2023-11-15 22:38:17', 'root@localhost'),
	(11, NULL, 'usuario', '2023-11-19 01:12:27', 'root@localhost'),
	(12, NULL, 'usuario', '2023-11-19 11:35:46', 'root@localhost'),
	(14, NULL, 'video', '2023-12-03 16:44:05', 'root@localhost');

-- Copiando estrutura para tabela plataforma_video.canal
DROP TABLE IF EXISTS `canal`;
CREATE TABLE IF NOT EXISTS `canal` (
  `codCanal` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCanal` varchar(200) NOT NULL,
  `descricaoCanal` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`codCanal`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.canal: ~7 rows (aproximadamente)
INSERT INTO `canal` (`codCanal`, `nomeCanal`, `descricaoCanal`) VALUES
	(1, 'Joãozinho Gameplays 6M', 'Sou João Bosco e rumo aos 6 milhões'),
	(2, 'TESTECANAL', NULL),
	(5, 'Joãozinho Gameplays 6M', 'Sou João Bosco e rumo aos 6 milhões'),
	(6, 'Taiki Hamasaki', NULL),
	(7, 'Taiki', NULL),
	(8, 'Teste 002', NULL),
	(9, 'Teste 003', NULL),
	(10, 'T. Hamasaki', NULL);

-- Copiando estrutura para tabela plataforma_video.channelowner
DROP TABLE IF EXISTS `channelowner`;
CREATE TABLE IF NOT EXISTS `channelowner` (
  `canal_codCanal` int(11) NOT NULL,
  `usuario_codusuario` int(11) NOT NULL,
  PRIMARY KEY (`canal_codCanal`,`usuario_codusuario`),
  KEY `fk_canal_has_usuario_usuario1_idx` (`usuario_codusuario`),
  KEY `fk_canal_has_usuario_canal1_idx` (`canal_codCanal`),
  CONSTRAINT `fk_canal_has_usuario_canal1` FOREIGN KEY (`canal_codCanal`) REFERENCES `canal` (`codCanal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_canal_has_usuario_usuario1` FOREIGN KEY (`usuario_codusuario`) REFERENCES `usuario` (`codusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.channelowner: ~5 rows (aproximadamente)
INSERT INTO `channelowner` (`canal_codCanal`, `usuario_codusuario`) VALUES
	(1, 1),
	(2, 3),
	(6, 4),
	(7, 4),
	(10, 4);

-- Copiando estrutura para tabela plataforma_video.comprarfilmes
DROP TABLE IF EXISTS `comprarfilmes`;
CREATE TABLE IF NOT EXISTS `comprarfilmes` (
  `usuario_codusuario` int(11) NOT NULL,
  `video_codVideo` int(11) NOT NULL,
  `precoVideo` decimal(5,2) NOT NULL,
  PRIMARY KEY (`usuario_codusuario`,`video_codVideo`),
  KEY `fk_comprarFilmes_video1_idx` (`video_codVideo`),
  CONSTRAINT `fk_alugarFilmes_usuario1` FOREIGN KEY (`usuario_codusuario`) REFERENCES `usuario` (`codusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comprarFilmes_video1` FOREIGN KEY (`video_codVideo`) REFERENCES `video` (`codVideo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.comprarfilmes: ~0 rows (aproximadamente)
INSERT INTO `comprarfilmes` (`usuario_codusuario`, `video_codVideo`, `precoVideo`) VALUES
	(2, 1, 5.00);

-- Copiando estrutura para tabela plataforma_video.genero
DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `codGenero` int(11) NOT NULL AUTO_INCREMENT,
  `tipoGenero` varchar(200) NOT NULL,
  `tipoPublico` enum('Infantil','InfantoJuvenil','"Adulto"') DEFAULT NULL,
  `idadePublico` enum('04 - 10','12 - 16','+18') DEFAULT NULL,
  PRIMARY KEY (`codGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.genero: ~4 rows (aproximadamente)
INSERT INTO `genero` (`codGenero`, `tipoGenero`, `tipoPublico`, `idadePublico`) VALUES
	(1, 'Gameplays', 'InfantoJuvenil', ''),
	(2, 'Maquiagem', 'InfantoJuvenil', '12 - 16'),
	(3, 'Moda', 'InfantoJuvenil', '12 - 16'),
	(4, 'Jogos Violentos', 'InfantoJuvenil', '12 - 16');

-- Copiando estrutura para tabela plataforma_video.parceria
DROP TABLE IF EXISTS `parceria`;
CREATE TABLE IF NOT EXISTS `parceria` (
  `codParceria` int(11) NOT NULL AUTO_INCREMENT,
  `nomeEmpresa` varchar(150) NOT NULL,
  `inicioContrato` date NOT NULL,
  `fimContrato` date NOT NULL,
  `canal_codCanal` int(11) NOT NULL,
  PRIMARY KEY (`codParceria`,`canal_codCanal`) USING BTREE,
  KEY `fk_parcerias_canal1_idx` (`canal_codCanal`),
  CONSTRAINT `fk_parcerias_canal1` FOREIGN KEY (`canal_codCanal`) REFERENCES `canal` (`codCanal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.parceria: ~7 rows (aproximadamente)
INSERT INTO `parceria` (`codParceria`, `nomeEmpresa`, `inicioContrato`, `fimContrato`, `canal_codCanal`) VALUES
	(1, 'Adidas', '2023-09-11', '2024-09-11', 1),
	(2, 'Nike', '2023-12-03', '2024-12-03', 1),
	(3, 'Puma', '2023-12-06', '2023-12-06', 1),
	(4, 'Kabum', '2024-01-12', '2024-01-12', 6),
	(5, 'Kabum', '2024-01-12', '2024-01-12', 7),
	(6, 'Ubisoft', '2023-02-12', '2023-02-12', 6),
	(7, 'NetShoes', '2024-01-10', '2024-01-10', 1);

-- Copiando estrutura para procedure plataforma_video.proc_alteraCanal
DROP PROCEDURE IF EXISTS `proc_alteraCanal`;
DELIMITER //
CREATE PROCEDURE `proc_alteraCanal`(
	IN `codAlterar` INT,
	IN `campoParaAlterar` INT,
	IN `novoValor` VARCHAR(500)
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM canal WHERE codCanal = codAlterar;
	if(@contador = 0) 
		then SELECT "Usuário não encontrado" AS "ERRO";
		ELSE
			IF(campoParaAlterar = 1)
				then 
					if(novoValor > 200)
						then SELECT "Quantidade de caracteres excedido para o nome do Canal" AS "Valor inválido";
						ELSE UPDATE canal SET nomeCanal = novoValor WHERE codCanal = codAlterar;
							SELECT "Nome do Canal alterado com sucesso!" AS "AVISO";
					END if;
				ELSE if(campoParaAlterar = 2)
					then UPDATE canal SET descricaoCanal = novoValor WHERE codCanal = codAlterar;
						SELECT "Descrição do Canal alterado com sucesso!" AS "AVISO";
					ELSE SELECT "Campo inválido" AS ERRO;
				END if;
			END if;
	END IF;
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_alteraParceriaData
DROP PROCEDURE IF EXISTS `proc_alteraParceriaData`;
DELIMITER //
CREATE PROCEDURE `proc_alteraParceriaData`(
	IN `codAlterar` INT,
	IN `campoParaAlterar` INT,
	IN `novoValor` DATE
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM parcerias WHERE codParceria = codAlterar;
	if(@contador = 0)
		then SELECT "Parceria não encontrada" AS ERRO;
		ELSE
			if(campoParaAlterar = 1)
				then UPDATE parcerias SET inicioContrato = novoValor WHERE codParceria = codAlterar;
				SELECT "Data de Início de parceria alterado com sucesso!" AS AVISO;
				ELSE if(campoParaAlterar = 2)
					then UPDATE parcerias SET fimContrato = novoValor WHERE codParceria = codAlterar;
						SELECT "Data de Fim de parceria alterado com sucesso!" AS AVISO;
					ELSE SELECT "Campo de alteração inválido" AS ERRO;
				END if;
			END if;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_alteraUsuario
DROP PROCEDURE IF EXISTS `proc_alteraUsuario`;
DELIMITER //
CREATE PROCEDURE `proc_alteraUsuario`(
	IN `codAlterar` INT,
	IN `campoParaAlterar` INT,
	IN `novoValor` VARCHAR(200)
)
BEGIN
SELECT COUNT(*) INTO @contador FROM usuario WHERE codUsuario = codAlterar;
if (@contador = 0)
	then SELECT "Usuário não encontrado" AS ERRO;
	ELSE
		if(campoParaAlterar = 1) 
			then UPDATE usuario SET nomeUsuario = novoValor WHERE codUsuario = codAlterar;
			SELECT "Nome de Usuário alterado com sucesso!" AS "AVISO";
			ELSE if(campoParaAlterar = 2)
				then UPDATE usuario SET email = novoValor WHERE codUsuario = codAlterar;
				SELECT "Email do Usuário alterado com sucesso!" AS "AVISO";
				ELSE if(campoParaAlterar = 3)
					then 
					if(LENGTH(novoValor) > 20)
							then SELECT "Quantidade de caracteres excedido para senha" AS "Valor inválido";
							ELSE UPDATE usuario SET senha = novoValor WHERE codUsuario = codAlterar;
								SELECT "Senha do Usuário alterado com sucesso!" AS "AVISO";
					END if;
					
						ELSE SELECT "Campo inválido" AS "ERRO";
				END IF;
			END IF;
		END IF;
END IF;
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_alteraVideo
DROP PROCEDURE IF EXISTS `proc_alteraVideo`;
DELIMITER //
CREATE PROCEDURE `proc_alteraVideo`(
	IN `codAlterar` INT,
	IN `novoValor` VARCHAR(500)
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM video WHERE codVideo = codAlterar;
	if(@contador = 0)
		then SELECT "Vídeo não encontrado" AS ERRO;
		ELSE
			UPDATE video SET tituloVideo = novoValor WHERE codVideo = codAlterar;
			SELECT "Título do Vídeo alterado com sucesso!" AS AVISO;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_excluiVideo
DROP PROCEDURE IF EXISTS `proc_excluiVideo`;
DELIMITER //
CREATE PROCEDURE `proc_excluiVideo`(
	IN `codApagar` INT
)
BEGIN
	SELECT COUNT(*) INTO @contador FROM video WHERE codVideo = codApagar;
	if (@contador = 0)
		then SELECT "Esse código de vídeo não existe." AS Erro;
		ELSE DELETE FROM assistirvideo WHERE video_codVideo = codApagar;
			DELETE FROM video WHERE codVideo = codApagar; 
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_insertCanal
DROP PROCEDURE IF EXISTS `proc_insertCanal`;
DELIMITER //
CREATE PROCEDURE `proc_insertCanal`(
	IN `nomeDoCanal` VARCHAR(200),
	IN `descricaoDoCanal` VARCHAR(500)
)
BEGIN
	INSERT INTO canal(codCanal, nomeCanal, descricaoCanal)
	VALUES(NULL, nomeDoCanal, descricaoDoCanal);
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_insertGenero
DROP PROCEDURE IF EXISTS `proc_insertGenero`;
DELIMITER //
CREATE PROCEDURE `proc_insertGenero`(
	IN `generoDesejado` VARCHAR(200),
	IN `publicoAlvo` ENUM('Infantil', 'InfantoJuvenil', '"Adulto"'),
	IN `idadePublicoAlvo` ENUM('04 - 10', '12 - 16', '+18')
)
BEGIN
	INSERT INTO genero(codGenero, tipoGenero, tipoPublico, idadePublico)
	VALUES(NULL, generoDesejado, publicoAlvo, idadePublicoAlvo);
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_insertParcerias
DROP PROCEDURE IF EXISTS `proc_insertParcerias`;
DELIMITER //
CREATE PROCEDURE `proc_insertParcerias`(
	IN `nomeDaEmpresa` VARCHAR(150),
	IN `inicioDoContrato` DATE,
	IN `fimDoContrato` DATE,
	IN `codCanalParceiro` INT
)
BEGIN
	INSERT INTO parcerias(codParcerias, nomeEmpresa, inicioContrato,
	fimContrato, canal_codCanal) VALUES(NULL, nomeDaEmpresa, inicioDoContrato,
	fimDoContrato, codCanalParceiro);
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_insertUser
DROP PROCEDURE IF EXISTS `proc_insertUser`;
DELIMITER //
CREATE PROCEDURE `proc_insertUser`(
	IN `nomeDesejado` VARCHAR(200),
	IN `emailDesejado` VARCHAR(200),
	IN `senhaDesejada` VARCHAR(20)
)
BEGIN
	INSERT INTO usuario(codUsuario, nomeUsuario, email, senha)
	VALUES(NULL, nomeDesejado, emailDesejado, senhaDesejada);
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_insertVideo
DROP PROCEDURE IF EXISTS `proc_insertVideo`;
DELIMITER //
CREATE PROCEDURE `proc_insertVideo`(
	IN `tituloDesejado` VARCHAR(400),
	IN `codGeneroDesejado` INT,
	IN `codCanalDesejado` INT
)
BEGIN
	INSERT INTO video(codVideo, tituloVideo, genero_codCategoria, canal_codCanal)
	VALUES(NULL, tituloDesejado, codGeneroDesejado, codCanalDesejado);
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_relatoriosContas
DROP PROCEDURE IF EXISTS `proc_relatoriosContas`;
DELIMITER //
CREATE PROCEDURE `proc_relatoriosContas`(
	IN `tipo` CHAR(1)
)
BEGIN
	if(tipo = 'U')
		then SELECT codUsuario AS "Código do Usuário", nomeUsuario AS "Nome do Usuario",
			email AS "Email" FROM usuario;
		ELSE if(tipo = 'C')
			then SELECT c.codCanal AS "Código do Canal", c.nomeCanal AS "Canal",
				u.email AS "Email" FROM usuario AS u INNER JOIN canal AS c INNER JOIN channelowner AS co
				WHERE co.canal_codCanal = c.codCanal AND co.usuario_codusuario = u.codusuario;
			ELSE if(tipo = 'N')
				then SELECT COUNT(*) AS "Número de contas" FROM usuario;
				ELSE SELECT "Tipo de relatório inválido" AS ERRO;
			END if;
		END if;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para procedure plataforma_video.proc_relatorioVideos
DROP PROCEDURE IF EXISTS `proc_relatorioVideos`;
DELIMITER //
CREATE PROCEDURE `proc_relatorioVideos`(
	IN `tipo` CHAR(1)
)
BEGIN
	if(tipo = 'V')
		then SELECT v.codVideo AS "Código do vídeo", v.tituloVideo AS "Título",
			v.numeroCurtidas AS "Número de Curtidas", v.visualizacoes AS "Visualizações" FROM video AS v;
		ELSE if(tipo = 'G')
			then SELECT v.tituloVideo AS "Título do Vídeo", g.tipoGenero AS "Gênero",
				g.tipoPublico AS "Tipo de Público" FROM video AS v 
				INNER JOIN genero AS g WHERE v.genero_codCategoria = g.codGenero;
			ELSE SELECT "Tipo de Relatório inválido" AS ERRO;
		END if;
	END if;
END//
DELIMITER ;

-- Copiando estrutura para tabela plataforma_video.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `codusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeUsuario` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `senha` varchar(20) NOT NULL,
  PRIMARY KEY (`codusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.usuario: ~3 rows (aproximadamente)
INSERT INTO `usuario` (`codusuario`, `nomeUsuario`, `email`, `senha`) VALUES
	(1, 'João Bosco', 'joao.bosco@alunos.ifsuldeminas.edu.br', '12345'),
	(2, 'BIEL_ClubeUniforte', 'gbpgoncalves@gmail.com', '1234'),
	(3, 'TESTE', 'teste404@yahoo.com.br', '1234'),
	(4, 'Taiki Hamasaki', 'taikih2007@gmail.com', '123456789'),
	(5, 'BALLAS_Guiii', 'ballas_guii@gmail.com', 'Guilherme12345'),
	(6, 'Adamastor Pequeno', 'admpiqueno@gmail.com', '@ADMPequeno');

-- Copiando estrutura para tabela plataforma_video.video
DROP TABLE IF EXISTS `video`;
CREATE TABLE IF NOT EXISTS `video` (
  `codVideo` int(11) NOT NULL AUTO_INCREMENT,
  `tituloVideo` varchar(400) NOT NULL,
  `numeroCurtidas` int(11) DEFAULT NULL,
  `visualizacoes` int(11) DEFAULT NULL,
  `genero_codCategoria` int(11) NOT NULL,
  `canal_codCanal` int(11) NOT NULL,
  PRIMARY KEY (`codVideo`,`genero_codCategoria`,`canal_codCanal`),
  KEY `fk_video_genero1_idx` (`genero_codCategoria`),
  KEY `fk_video_canal1_idx` (`canal_codCanal`),
  CONSTRAINT `fk_video_canal1` FOREIGN KEY (`canal_codCanal`) REFERENCES `canal` (`codCanal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_video_genero1` FOREIGN KEY (`genero_codCategoria`) REFERENCES `genero` (`codGenero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela plataforma_video.video: ~3 rows (aproximadamente)
INSERT INTO `video` (`codVideo`, `tituloVideo`, `numeroCurtidas`, `visualizacoes`, `genero_codCategoria`, `canal_codCanal`) VALUES
	(1, 'Usando a estratégia da cama para matar o ender dragon - Joãozinho Gameplays', NULL, NULL, 1, 1),
	(4, 'VLOG #03', NULL, NULL, 1, 1),
	(5, 'Far Cry #03 - Taiki Hamasaki', 0, 0, 4, 6);

-- Copiando estrutura para trigger plataforma_video.tri_alteraUsuario
DROP TRIGGER IF EXISTS `tri_alteraUsuario`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_alteraUsuario` AFTER UPDATE ON `usuario` FOR EACH ROW BEGIN
	if(NEW.nomeUsuario <> OLD.nomeUsuario)
		then SET @message = CONCAT("Usuário ", OLD.nomeUsuario, " alterado para ", NEW.nomeUsuario);
		ELSE if(NEW.email <> OLD.email)
			then SET @message = CONCAT("Email do Usuário ", NEW.nomeUsuario, " alterado para ", NEW.email);
			ELSE if(NEW.senha <> OLD.senha)
				then SET @message = CONCAT("Senha do Usuário ", NEW.nomeUsuario, " alterado para ", NEW.senha);
			END if;
		END if;
	END if;
	
	INSERT INTO auditoria VALUES(NULL, @message, "usuario", NOW(), USER());
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger plataforma_video.tri_alteraVideo
DROP TRIGGER IF EXISTS `tri_alteraVideo`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_alteraVideo` AFTER UPDATE ON `video` FOR EACH ROW BEGIN
	SELECT nomeCanal INTO @canal FROM canal WHERE codCanal = NEW.canal_codCanal;
	SELECT tipoGenero INTO @genero FROM genero WHERE codGenero = NEW.genero_codCategoria;
	if(OLD.tituloVideo <> NEW.tituloVideo)
		then SET @message = CONCAT("Vídeo ", OLD.tituloVideo, " do canal ", @canal,
		" teve o título alterado para ", NEW.tituloVideo);
		ELSE if(OLD.genero_codCategoria <> NEW.genero_codCategoria)
			then SET @message = CONCAT("Vídeo ", new.tituloVideo, " do canal ", @canal,
			" teve o gênero alterado para ", @genero);
		END if;
	END if;
	
	INSERT INTO auditoria VALUES( NULL, @message, "video", NOW(), USER());
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger plataforma_video.tri_insereUsuario
DROP TRIGGER IF EXISTS `tri_insereUsuario`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_insereUsuario` BEFORE INSERT ON `usuario` FOR EACH ROW BEGIN
	SELECT u.nomeUsuario INTO @username FROM usuario AS u WHERE u.codusuario = NEW.codusuario;
	SELECT u.email INTO @usermail FROM usuario AS u WHERE u.codusuario = NEW.codusuario;
	
	SET @mensagem = CONCAT("Novo usuário registrado (", @username,") com o email ", @usermail);
	
	INSERT INTO auditoria VALUES(NULL, @mensagem, "usuario", NOW(), USER());
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger plataforma_video.tri_insereVideo
DROP TRIGGER IF EXISTS `tri_insereVideo`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_insereVideo` BEFORE INSERT ON `video` FOR EACH ROW BEGIN
	SELECT v.tituloVideo INTO @titulo FROM video AS v WHERE v.codVideo = NEW.codVideo;
	
	SET @mensagem = CONCAT("Novo vídeo criado ", @titulo);
	
	INSERT INTO auditoria VALUES(NULL, @mensagem, "video", NOW(), USER());
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
