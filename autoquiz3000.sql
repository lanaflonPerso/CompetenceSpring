-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Sam 03 Novembre 2018 à 22:35
-- Version du serveur :  5.7.24-0ubuntu0.16.04.1
-- Version de PHP :  7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS autoquiz3000;
CREATE DATABASE autoquiz3000
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE autoquiz3000;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `autoquiz3000`
--

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(6);

-- --------------------------------------------------------

--
-- Structure de la table `quiz`
--

CREATE TABLE `quiz` (
  `id` bigint(20) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `scoreToAcquireSkill` int(11) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `skill_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `quiz`
--

INSERT INTO `quiz` (`id`, `endDate`, `name`, `scoreToAcquireSkill`, `startDate`, `version`, `skill_id`) VALUES
(1, '2018-11-11 00:00:00', 'QCM: Java', 60, '2018-11-01 00:00:00', 1, 1),
(2, '2018-11-11 00:00:00', 'QCM: Java avec Spring', 60, '2018-11-01 00:00:00', 0, 2),
(3, '2018-11-11 00:00:00', 'QCM: Angular', 60, '2018-11-01 00:00:00', 8, 3),
(4, '2018-11-11 00:00:00', 'QCM: Le langage Java', 60, '2018-11-01 00:00:00', 0, 4),
(5, NULL, 'Mathématique CP', 50, NULL, 10, 5),
(6, '2018-11-15 00:00:00', 'Français CP', 50, '2018-11-01 00:00:00', 7, 6),
(7, '2018-11-11 00:00:00', 'Français CP Second QCM', 50, '2018-11-02 00:00:00', 7, 6),
(9, '2018-12-10 00:00:00', 'Apprenez à programmer en Python 2', 50, '2018-10-10 00:00:00', 0, 9),
(10, '2018-11-10 00:00:00', 'Géographie', 60, '2018-11-05 00:00:00', 6, 8),
(11, '2018-11-10 00:00:00', 'Apprenez à programmer en Python 1', 60, '2018-11-01 00:00:00', 7, 9),
(12, '2018-11-11 00:00:00', 'Quiz des animaux de la ferme', 50, '2018-11-01 00:00:00', 2, 10);

-- --------------------------------------------------------

--
-- Structure de la table `quiz_question`
--

CREATE TABLE `quiz_question` (
  `id` bigint(20) NOT NULL,
  `orderNum` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `quiz_id` bigint(20) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `quiz_question`
--

INSERT INTO `quiz_question` (`id`, `orderNum`, `text`, `version`, `quiz_id`, `question_id`) VALUES
(1, 1, 'Quel package / structure est la plus récente pour les interfaces graphiques ?', 1, 1, NULL),
(2, 2, 'Le constructeur d\'une classe doit porter le meme nom que la classe', 1, 1, NULL),
(3, 3, 'J2ME, J2EE, J2SE sont adaptés respectivement pour quelles plateformes ?', 1, 1, NULL),
(4, 1, 'Dans l\'acronyme SOLID, qu\'est-ce que le principe de responsabilité unique signifie ?', 0, 2, NULL),
(5, 2, 'L\'inversion de contrôle est un des principes SOLID.', 0, 2, NULL),
(6, 3, 'Cochez les affirmations correctes parmi les  propositions  suivantes.', 0, 2, NULL),
(7, 1, 'Quelle est la commande CLI qui permet de créer un nouveau component ?', 8, 3, NULL),
(8, 2, 'Si vous avez une variable  name = \'Jonathan\'  dans votre code TypeScript, quelle syntaxe utilisez-vous pour afficher Jonathan dans le template ?', 8, 3, NULL),
(9, 3, 'Vous souhaitez qu\'un bouton de votre template déclenche une fonction dans votre TypeScript : quelle syntaxe utilisez-vous pour l\'y lier ?', 8, 3, NULL),
(10, 1, 'Pour transformer un code lisible en code compréhensible par la machine, on utilise :', 0, 4, 2),
(11, 2, 'Pour avoir un commentaire sur une ligne, on place en début de ligne le(s) caractère(s) :', 0, 4, 2),
(12, 3, '\nLequel n\'a pas sa place parmi ces choix :', 0, 4, 2),
(13, 1, 'Combien fait 10+7+4=', 10, 5, 4),
(14, 2, 'Combien fait 13+3+2', 10, 5, 4),
(15, 3, 'Combien fait 22-13=', 10, 5, 4),
(16, 1, 'Quelle est la bonne orthographe ?', 7, 6, 5),
(17, 2, 'Quelle est la bonne orthographe ?', 7, 6, 5),
(18, 3, 'Quelle est la bonne orthographe ?', 7, 6, 5),
(19, 1, 'Quelle est la bonne orthographe ?', 7, 7, NULL),
(20, 2, 'Quelle est la bonne orthographe ?', 7, 7, NULL),
(21, 3, 'Quelle est la bonne orthographe ?', 7, 7, NULL),
(25, 1, 'De quoi doit être composée une condition au minimum ?', 0, 9, 7),
(26, 2, 'Considérant les instructions ci-dessous, si variable vaut 2.8, quel va être le résultat obtenu ?\n<br />\nif variable >= 3:<br />\n    print("1")<br />\nelif variable < -1:<br />\n    print("2")<br />\nelse:<br />\n    print("3")<br />', 0, 9, 7),
(27, 3, 'Considérant le prédicat combiné ci-dessous, dans quel cas sera-t-il True (vrai) ?\npredicat_a and predicat_b', 0, 9, 7),
(28, 1, 'capitale de la France?', 6, 10, 3),
(29, 2, 'capitale de la belgique?', 6, 10, 3),
(30, 3, 'capitale de l\'afrique du sud?', 6, 10, 3),
(31, 1, 'Après ces instructions, de quel type est la variable c ?\r\n\r\na = 8\r\nb = 3\r\nc = a / b', 7, 11, 6),
(32, 2, 'Quelle est la variable de type str (chaîne de caractères) parmi les choix suivants ?', 7, 11, 6),
(33, 3, 'Quelle est la différence entre entrer une variable dans l\'interpréteur interactif et utiliser la fonction print ?', 7, 11, 6),
(34, 1, 'Comment s\'appelle le petit de la truie?', 2, 12, 8),
(35, 2, 'Comment s\'appelle le papa de l\'agneau ?', 2, 12, 8),
(36, 3, 'Pendant combien de temps la poule couve ses oeufs ?', 2, 12, 8);

-- --------------------------------------------------------

--
-- Structure de la table `quiz_response`
--

CREATE TABLE `quiz_response` (
  `id` bigint(20) NOT NULL,
  `correct` bit(1) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `question_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `quiz_response`
--

INSERT INTO `quiz_response` (`id`, `correct`, `text`, `version`, `question_id`) VALUES
(1, b'0', 'directx', 1, 1),
(2, b'1', 'javafx', 1, 1),
(3, b'0', 'swing', 1, 1),
(4, b'0', 'awt', 1, 1),
(5, b'0', 'Pas obligatoirement', 1, 2),
(6, b'0', 'C\'est déconseillé', 1, 2),
(7, b'1', 'Oui, impérativement', 1, 2),
(8, b'0', 'Non, c\'est interdit', 1, 2),
(9, b'0', 'web, smartphone, desktop', 1, 3),
(10, b'0', 'web, desktop, smartphone', 1, 3),
(11, b'1', 'smartphone, web, desktop', 1, 3),
(12, b'0', 'smartphone, desktop, web', 1, 3),
(13, b'0', 'Dans l\'application, chaque responsabilité ou fonctionnalité est assurée par une seule classe.', 0, 4),
(14, b'1', 'Une classe ne devrait avoir qu\'une seule responsabilité.', 0, 4),
(15, b'0', 'Vrai', 0, 5),
(16, b'1', 'Faux', 0, 5),
(17, b'1', 'L\'inversion de dépendances est un des 5 principes SOLID.', 0, 6),
(18, b'0', 'L\'inversion de dépendances permet d\'injecter les dépendances dans les classes qui en ont besoin.', 0, 6),
(19, b'1', 'L\'inversion de dépendances repose sur le concept d\'abstraction.', 0, 6),
(20, b'0', 'L\'utilisation d\'interfaces implique de faire de l\'inversion de dépendances.', 0, 6),
(21, b'1', 'Pour respecter le principe d\'inversion de dépendances, il est possible d\'utiliser des interfaces.', 0, 6),
(22, b'1', 'ng generate', 8, 7),
(23, b'0', 'ng new', 8, 7),
(24, b'0', 'ng build', 8, 7),
(25, b'1', 'les doubles accolades {{}}', 8, 8),
(26, b'0', 'les crochets []', 8, 8),
(27, b'0', 'les parenthèses ()', 8, 8),
(28, b'0', '(onClick)', 8, 9),
(29, b'1', '(click)', 8, 9),
(30, b'0', '[click]', 8, 9),
(31, b'1', 'Un compilateur', 0, 10),
(32, b'0', 'Un exécuteur', 0, 10),
(33, b'0', 'Un transformateur', 0, 10),
(34, b'0', 'Un traducteur', 0, 10),
(35, b'1', '//', 0, 11),
(36, b'0', '#', 0, 11),
(37, b'0', '\'', 0, 11),
(38, b'0', '%', 0, 11),
(39, b'0', 'instanceof', 0, 12),
(40, b'0', 'select', 0, 12),
(41, b'0', 'for', 0, 12),
(42, b'1', 'volatile', 0, 12),
(43, b'0', '22', 10, 13),
(44, b'1', '21', 10, 13),
(45, b'0', '17', 10, 13),
(46, b'0', '16', 10, 14),
(47, b'1', '18', 10, 14),
(48, b'0', '23', 10, 14),
(49, b'0', '7', 10, 15),
(50, b'0', '8', 10, 15),
(51, b'1', '9', 10, 15),
(52, b'1', 'Une pomme', 7, 16),
(53, b'0', 'Une pome', 7, 16),
(54, b'0', 'Des pomes', 7, 16),
(55, b'0', 'Une lionnes', 7, 17),
(56, b'0', 'Les liones', 7, 17),
(57, b'1', 'Les lionnes', 7, 17),
(58, b'0', 'Des casier', 7, 18),
(59, b'0', 'Un casiers', 7, 18),
(60, b'1', ' Des casiers', 7, 18),
(61, b'0', 'Un fauteuils', 7, 19),
(62, b'0', 'Une fauteuil', 7, 19),
(63, b'1', 'Un fauteuil', 7, 19),
(64, b'0', 'Un arbres', 7, 20),
(65, b'0', 'Un arbe', 7, 20),
(66, b'1', 'Des arbres', 7, 20),
(67, b'0', 'Des coussin', 7, 21),
(68, b'1', 'Des coussins', 7, 21),
(69, b'0', 'Un coussins', 7, 21),
(76, b'1', 'D\'un bloc if', 0, 25),
(77, b'0', 'D\'un bloc if et elif', 0, 25),
(78, b'1', 'Afficher 3.', 0, 26),
(79, b'0', 'Afficher 2.', 0, 26),
(80, b'0', 'predicat_b est vrai, peu importe predicat_a.', 0, 27),
(81, b'1', 'predicat_a et predicat_b sont tous deux vrais.', 0, 27),
(82, b'1', 'paris', 6, 28),
(83, b'0', 'londre', 6, 28),
(84, b'0', 'berlin', 6, 28),
(85, b'0', 'paris', 6, 29),
(86, b'0', 'berlin', 6, 29),
(87, b'1', 'bruxelle', 6, 29),
(88, b'1', 'le cap', 6, 30),
(89, b'1', 'pretoria', 6, 30),
(90, b'0', 'johannesburg', 6, 30),
(91, b'0', 'int (entier)', 7, 31),
(92, b'1', 'float (flottant)', 7, 31),
(93, b'0', 'str (chaîne de caractères)', 7, 31),
(94, b'0', '3', 7, 32),
(95, b'0', '3.1', 7, 32),
(96, b'1', '"3"', 7, 32),
(97, b'0', 'Aucune', 7, 33),
(98, b'0', 'Dans l\'interpréteur interactif, toutes les variables apparaissent entourées de guillemets.Dans l\'interpréteur interactif, toutes les variables apparaissent entourées de guillemets.', 7, 33),
(99, b'1', 'La fonction print est dédiée à l\'affichage, l\'interpréteur au débuggage.', 7, 33),
(100, b'0', 'Le cochonnet', 2, 34),
(101, b'1', 'Le porcelet', 2, 34),
(102, b'0', 'Le porcelin', 2, 34),
(103, b'0', 'Le bouc', 2, 35),
(104, b'1', 'Le bélier', 2, 35),
(105, b'0', 'Le boeuf', 2, 35),
(106, b'0', '11 jours', 2, 36),
(107, b'1', '21 jours', 2, 36),
(108, b'0', '31 jours', 2, 36);

-- --------------------------------------------------------

--
-- Structure de la table `quiz_Student_Class`
--

CREATE TABLE `quiz_Student_Class` (
  `quizzes_id` bigint(20) NOT NULL,
  `stClasses_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `quiz_Student_Class`
--

INSERT INTO `quiz_Student_Class` (`quizzes_id`, `stClasses_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 5),
(7, 5),
(9, 1),
(10, 5),
(11, 3),
(12, 5);

-- --------------------------------------------------------

--
-- Structure de la table `quiz_test`
--

CREATE TABLE `quiz_test` (
  `id` bigint(20) NOT NULL,
  `correctResponse` int(11) NOT NULL,
  `errorResponse` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `startDate` date DEFAULT NULL,
  `version` int(11) NOT NULL,
  `quiz_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `stClass_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `quiz_test`
--

INSERT INTO `quiz_test` (`id`, `correctResponse`, `errorResponse`, `score`, `startDate`, `version`, `quiz_id`, `user_id`, `stClass_id`) VALUES
(1, 3, 0, 100, '2018-11-02', 0, 4, 4, 1),
(2, 1, 2, 33, '2018-11-03', 0, 4, 5, 1),
(3, 1, 2, 33, '2018-11-03', 0, 10, 11, 5),
(4, 2, 1, 66, '2018-11-03', 0, 5, 11, 5),
(5, 2, 1, 66, '2018-11-03', 0, 6, 11, 5),
(6, 3, 0, 100, '2018-11-03', 0, 11, 22, 3),
(7, 1, 2, 33, '2018-11-03', 0, 9, 22, 3),
(8, 2, 1, 66, '2018-11-03', 0, 12, 9, 5),
(9, 0, 0, 0, '2018-11-03', 0, 5, 10, 5),
(10, 0, 0, 0, '2018-11-03', 0, 5, 9, 5);

-- --------------------------------------------------------

--
-- Structure de la table `quiz_todo`
--

CREATE TABLE `quiz_todo` (
  `id` bigint(20) NOT NULL,
  `idQuiz` bigint(20) NOT NULL,
  `idUser` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `quiz_todo`
--

INSERT INTO `quiz_todo` (`id`, `idQuiz`, `idUser`) VALUES
(1, 4, 3),
(2, 1, 3),
(4, 1, 4),
(6, 1, 5),
(7, 2, 6),
(8, 3, 6),
(9, 2, 7),
(10, 3, 7),
(11, 2, 8),
(12, 3, 8),
(25, 6, 9),
(26, 6, 10),
(28, 7, 9),
(29, 7, 10),
(30, 7, 11),
(31, 9, 20),
(32, 9, 21),
(34, 10, 9),
(36, 10, 10),
(37, 9, 23),
(38, 9, 24),
(39, 11, 20),
(40, 11, 21),
(42, 11, 23),
(43, 11, 24),
(44, 12, 10),
(46, 12, 11);

-- --------------------------------------------------------

--
-- Structure de la table `Skill`
--

CREATE TABLE `Skill` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Skill`
--

INSERT INTO `Skill` (`id`, `name`, `status`, `version`, `user_id`) VALUES
(1, 'Java EE', NULL, 1, NULL),
(2, 'Spring', NULL, 0, NULL),
(3, 'Angular', NULL, 8, NULL),
(4, 'Java', NULL, 2, NULL),
(5, 'Premier niveau mathématique', NULL, 10, NULL),
(6, 'Premier niveau français', NULL, 8, NULL),
(7, 'nniinn', NULL, 0, NULL),
(8, 'Premier niveau de géographie', NULL, 6, NULL),
(9, 'Python 3 les bases', NULL, 7, NULL),
(10, 'Connaitre les animaux de la ferme', NULL, 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Student_Class`
--

CREATE TABLE `Student_Class` (
  `id` bigint(20) NOT NULL,
  `endDate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `version` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Student_Class`
--

INSERT INTO `Student_Class` (`id`, `endDate`, `name`, `startDate`, `version`) VALUES
(1, '2018-11-05', 'Formation Java Débutant', '2018-06-27', 2),
(2, '2018-11-05', 'Seconde A', '2018-06-27', 7),
(3, '2018-11-05', 'Formation Python', '2018-06-27', 8),
(4, '2019-01-10', 'Formation JAVA EE', '2018-10-01', 0),
(5, '2018-12-05', 'Classe CP', '2018-09-03', 13);

-- --------------------------------------------------------

--
-- Structure de la table `student_response`
--

CREATE TABLE `student_response` (
  `id` bigint(20) NOT NULL,
  `answered` bit(1) NOT NULL,
  `idResponse` bigint(20) NOT NULL,
  `QuizTest_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `student_response`
--

INSERT INTO `student_response` (`id`, `answered`, `idResponse`, `QuizTest_id`) VALUES
(1, b'0', 32, 1),
(2, b'0', 34, 1),
(3, b'0', 33, 1),
(4, b'1', 31, 1),
(5, b'0', 38, 1),
(6, b'0', 37, 1),
(7, b'0', 36, 1),
(8, b'1', 35, 1),
(9, b'1', 42, 1),
(10, b'0', 40, 1),
(11, b'0', 39, 1),
(12, b'0', 41, 1),
(13, b'0', 34, 2),
(14, b'0', 33, 2),
(15, b'0', 32, 2),
(16, b'1', 31, 2),
(17, b'0', 35, 2),
(18, b'1', 36, 2),
(19, b'0', 38, 2),
(20, b'0', 37, 2),
(21, b'0', 42, 2),
(22, b'0', 41, 2),
(23, b'0', 39, 2),
(24, b'1', 40, 2),
(25, b'0', 84, 3),
(26, b'1', 82, 3),
(27, b'0', 83, 3),
(28, b'0', 85, 3),
(29, b'0', 87, 3),
(30, b'1', 86, 3),
(31, b'0', 89, 3),
(32, b'1', 88, 3),
(33, b'0', 90, 3),
(34, b'1', 44, 4),
(35, b'0', 43, 4),
(36, b'0', 45, 4),
(37, b'0', 48, 4),
(38, b'0', 46, 4),
(39, b'1', 47, 4),
(40, b'0', 51, 4),
(41, b'0', 49, 4),
(42, b'1', 50, 4),
(43, b'0', 53, 5),
(44, b'0', 52, 5),
(45, b'1', 54, 5),
(46, b'1', 57, 5),
(47, b'0', 55, 5),
(48, b'0', 56, 5),
(49, b'1', 60, 5),
(50, b'0', 58, 5),
(51, b'0', 59, 5),
(52, b'0', 93, 6),
(53, b'1', 92, 6),
(54, b'0', 91, 6),
(55, b'0', 95, 6),
(56, b'0', 94, 6),
(57, b'1', 96, 6),
(58, b'0', 98, 6),
(59, b'0', 97, 6),
(60, b'1', 99, 6),
(61, b'0', 77, 7),
(62, b'1', 76, 7),
(63, b'0', 78, 7),
(64, b'1', 79, 7),
(65, b'1', 80, 7),
(66, b'0', 81, 7),
(67, b'0', 100, 8),
(68, b'1', 101, 8),
(69, b'0', 102, 8),
(70, b'0', 103, 8),
(71, b'0', 105, 8),
(72, b'1', 104, 8),
(73, b'0', 107, 8),
(74, b'0', 106, 8),
(75, b'1', 108, 8);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `stClass_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `birthdate`, `email`, `firstName`, `lastName`, `password`, `token`, `type`, `version`, `stClass_id`) VALUES
(1, '1973-02-10', 'mouton@free.fr', 'marcel', 'mouton', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 2, 0, NULL),
(2, '1979-06-07', 'vianneyba@free.fr', 'vianney', 'bailleux', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 1, 0, NULL),
(3, '1978-12-19', 'dickinson@free.fr', 'bruce', 'dickinson', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 2, 1),
(4, '1980-01-04', 'halford@free.fr', 'rob', 'halford', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 2, 1),
(5, '1980-08-17', 'matos@free.fr', 'andré', 'matos', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 2, 1),
(6, '2003-03-06', 'scheepers@free.fr', 'ralf', 'scheepers', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 0, 4),
(7, '2003-06-22', 'sinner@free.fr', 'mat', 'sinner', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 0, 4),
(8, '2003-06-22', 'naumann@free.fr', 'tom', 'naumann', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 0, 4),
(9, '2012-02-22', 'aylan@free.fr', 'aylan', 'debusy', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 13, 5),
(10, '2012-03-12', 'clement@free.fr', 'clement', 'degrave', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 13, 5),
(11, '2012-12-05', 'heleonorba@free.fr', 'héléonor', 'bailleux', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 13, 5),
(12, '1996-12-12', 'dirkschneider@free.fr', 'udo', 'dirkschneider', NULL, 'T9zi5wbiNVwzkyfp7ZDMbsnB2P1jgR8CB4DLQHzSBS5AeiBdxz16vIGXiYzylpyx', 0, 6, 2),
(13, '1997-08-22', 'hoffmann@free.fr', 'wolf', 'hoffmann', NULL, '6xOLGy1jYUjRxYlGpEmH0CctYfASR1X6qogUCTn24igPANJohvqkXpIbUZ0u2G53', 0, 5, 2),
(14, '1997-02-15', 'frank@free.fr', 'herman', 'frank', NULL, 'dEWd25I6WjEgYjocmjHgnA0NxibhAKU6UKHwA1yeCFbQD1VdSLTf2LIMqB7mOavq', 0, 4, 2),
(15, '1996-11-03', 'baltes@free.fr', 'peter', 'baltes', NULL, '40RwzwSlti8O9eIseMBX9z3yC0qd9RblnPpXJKlrt56m9Zcx6lcoz13WcnYVO1h6', 0, 3, 2),
(16, '1997-01-08', 'kaufmann@free.fr', 'stefan', 'kaufmann', NULL, 'n7uAU12JhYVWOWIHwcWeIU3A1F3c1HSqhK15LG5eWtpMC5par3E3VPKo4igdx0su', 0, 2, 2),
(17, '2000-02-05', 'tornillo@free.fr', 'mark', 'tornillo', '*ECE77E9BCD5AA891B90E588829789E56593784FD', '', 0, 1, NULL),
(18, '1999-10-15', 'loureiro@free.fr', 'kiko', 'loureiro', '*ECE77E9BCD5AA891B90E588829789E56593784FD', '', 0, 1, 2),
(19, '2013-03-05', 'lola5905032013@free.fr', 'lola', 'bailleux', '*D388CF13A8F188EF0217F210DBDC76BD7BA08DE4', '', 0, 1, NULL),
(20, '2000-05-15', 'belladonna@free.fr', 'joey', 'belladonna', NULL, '6PScI8RxWnPQpGIiKoe6aiQwDpylpBaTfT85iUS5wVNq05O7CSKkxfytbaUXY4GK', 0, 6, 3),
(21, '1999-06-22', 'ian@free.fr', 'scott', 'ian', NULL, 'mKvP2o6IE9hayiNbNU75HNkP5aagSQezp9ietjRpYTlRlMBGKx580zpwvSjingiZ', 0, 5, 3),
(22, '1999-12-17', 'spitz@free.fr', 'dan', 'spitz', '*ECE77E9BCD5AA891B90E588829789E56593784FD', '', 0, 4, 3),
(23, '1998-12-11', 'bello@free.fr', 'franck', 'bello', NULL, 'PVxmMh50I4yERb7dCMDzvXYRRn6bwBY4IdMYi48W3c4UM4pPiRTOhwlCm4DXWdqA', 0, 3, 3),
(24, '1999-10-11', 'benante@free.fr', 'charlie', 'benante', NULL, 'Farr0bjozT9Bp620cXxO2YM2y991Awfu11kwx8ThFXanG2vJYQLreiolbt4xZSAG', 0, 2, 3),
(25, '1992-05-05', 'salengro59320@free.fr', 'roger', 'salengro', '*ECE77E9BCD5AA891B90E588829789E56593784FD', '', 0, 1, NULL),
(26, '1993-01-01', 'marcus59320@free.fr', 'gustave', 'marcus', '*03ED428312E6FA9465416DF76B384FF70704D0A5', '', 0, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_Skill`
--

CREATE TABLE `user_Skill` (
  `User_id` bigint(20) NOT NULL,
  `skills_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user_Skill`
--

INSERT INTO `user_Skill` (`User_id`, `skills_id`) VALUES
(4, 4),
(9, 10),
(11, 5),
(11, 6),
(22, 9);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgl1uiyo28bfgjmy6emfync07s` (`skill_id`);

--
-- Index pour la table `quiz_question`
--
ALTER TABLE `quiz_question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdtynvfjgh6e7fd8l0wk37nrpc` (`quiz_id`),
  ADD KEY `FKedt9k696d3cttvtth0hsi3w6c` (`question_id`);

--
-- Index pour la table `quiz_response`
--
ALTER TABLE `quiz_response`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm00mt5d5q67e7ietcwq5dk98b` (`question_id`);

--
-- Index pour la table `quiz_Student_Class`
--
ALTER TABLE `quiz_Student_Class`
  ADD KEY `FKpqnla34weyrvncbae4dvy8b02` (`stClasses_id`),
  ADD KEY `FKk3yfl8oj45grr44ja7ybsl8a6` (`quizzes_id`);

--
-- Index pour la table `quiz_test`
--
ALTER TABLE `quiz_test`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt3r5tygkox44kufrpbg3tc964` (`quiz_id`),
  ADD KEY `FKgajnl339rcagvtxh6d4v09enm` (`user_id`),
  ADD KEY `FKnnso4bvj9e9id6mru2bce1k4y` (`stClass_id`);

--
-- Index pour la table `quiz_todo`
--
ALTER TABLE `quiz_todo`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Skill`
--
ALTER TABLE `Skill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKspdp3l0g9a2atobplxkp1ihka` (`user_id`);

--
-- Index pour la table `Student_Class`
--
ALTER TABLE `Student_Class`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `student_response`
--
ALTER TABLE `student_response`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsodkfk5e6vf46pmk7th9spxjb` (`QuizTest_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnpgc5bj82d61sngq0ipxng4h4` (`stClass_id`);

--
-- Index pour la table `user_Skill`
--
ALTER TABLE `user_Skill`
  ADD KEY `UK_6gk67gioqj7njk456xl3pdkg4` (`skills_id`),
  ADD KEY `FKcixvxo6qybed2b1ovoovvkc00` (`User_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `quiz_question`
--
ALTER TABLE `quiz_question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT pour la table `quiz_response`
--
ALTER TABLE `quiz_response`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;
--
-- AUTO_INCREMENT pour la table `quiz_test`
--
ALTER TABLE `quiz_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `quiz_todo`
--
ALTER TABLE `quiz_todo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT pour la table `Skill`
--
ALTER TABLE `Skill`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `student_response`
--
ALTER TABLE `student_response`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `FKgl1uiyo28bfgjmy6emfync07s` FOREIGN KEY (`skill_id`) REFERENCES `Skill` (`id`);

--
-- Contraintes pour la table `quiz_question`
--
ALTER TABLE `quiz_question`
  ADD CONSTRAINT `FKdtynvfjgh6e7fd8l0wk37nrpc` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`),
  ADD CONSTRAINT `FKedt9k696d3cttvtth0hsi3w6c` FOREIGN KEY (`question_id`) REFERENCES `quiz_test` (`id`);

--
-- Contraintes pour la table `quiz_response`
--
ALTER TABLE `quiz_response`
  ADD CONSTRAINT `FKm00mt5d5q67e7ietcwq5dk98b` FOREIGN KEY (`question_id`) REFERENCES `quiz_question` (`id`);

--
-- Contraintes pour la table `quiz_Student_Class`
--
ALTER TABLE `quiz_Student_Class`
  ADD CONSTRAINT `FKk3yfl8oj45grr44ja7ybsl8a6` FOREIGN KEY (`quizzes_id`) REFERENCES `quiz` (`id`),
  ADD CONSTRAINT `FKpqnla34weyrvncbae4dvy8b02` FOREIGN KEY (`stClasses_id`) REFERENCES `Student_Class` (`id`);

--
-- Contraintes pour la table `quiz_test`
--
ALTER TABLE `quiz_test`
  ADD CONSTRAINT `FKgajnl339rcagvtxh6d4v09enm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKnnso4bvj9e9id6mru2bce1k4y` FOREIGN KEY (`stClass_id`) REFERENCES `Student_Class` (`id`),
  ADD CONSTRAINT `FKt3r5tygkox44kufrpbg3tc964` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`);

--
-- Contraintes pour la table `Skill`
--
ALTER TABLE `Skill`
  ADD CONSTRAINT `FKspdp3l0g9a2atobplxkp1ihka` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `student_response`
--
ALTER TABLE `student_response`
  ADD CONSTRAINT `FKsodkfk5e6vf46pmk7th9spxjb` FOREIGN KEY (`QuizTest_id`) REFERENCES `quiz_test` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKnpgc5bj82d61sngq0ipxng4h4` FOREIGN KEY (`stClass_id`) REFERENCES `Student_Class` (`id`);

--
-- Contraintes pour la table `user_Skill`
--
ALTER TABLE `user_Skill`
  ADD CONSTRAINT `FKcixvxo6qybed2b1ovoovvkc00` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKfddlqsd88rlvaaiitq02tly16` FOREIGN KEY (`skills_id`) REFERENCES `Skill` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
