-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 01 Novembre 2018 à 23:05
-- Version du serveur :  5.7.24-0ubuntu0.16.04.1
-- Version de PHP :  7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


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
(5);

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
(1, '2018-11-11 00:00:00', 'QCM Java', 60, '2018-11-01 00:00:00', 0, 1),
(2, '2018-11-11 00:00:00', 'QCM Java avec Spring', 60, '2018-11-01 00:00:00', 0, 2),
(3, '2018-11-11 00:00:00', 'QCM Angular', 60, '2018-11-01 00:00:00', 0, 3),
(4, '2018-11-11 00:00:00', 'Mathématique CP', 60, '2018-11-01 00:00:00', 0, 4),
(5, '2018-11-10 00:00:00', 'Français CP', 60, '2018-11-05 00:00:00', 0, 5);

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
(1, 1, 'Quel package / structure est la plus récente pour les interfaces graphiques ?', 0, 1, NULL),
(2, 2, 'Le constructeur d\'une classe doit porter le meme nom que la classe', 0, 1, NULL),
(3, 3, 'J2ME, J2EE, J2SE sont adaptés respectivement pour quelles plateformes ?', 0, 1, NULL),
(4, 1, 'Dans l\'acronyme SOLID, qu\'est-ce que le principe de responsabilité unique signifie ?', 0, 2, NULL),
(5, 2, 'L\'inversion de contrôle est un des principes SOLID.', 0, 2, NULL),
(6, 3, 'Cochez les affirmations correctes parmi les  propositions  suivantes.', 0, 2, NULL),
(7, 1, 'Quelle est la commande CLI qui permet de créer un nouveau component ?', 0, 3, NULL),
(8, 2, 'Si vous avez une variable  name = \'Jonathan\'  dans votre code TypeScript, quelle syntaxe utilisez-vous pour afficher Jonathan dans le template ?', 0, 3, NULL),
(9, 3, 'Vous souhaitez qu\'un bouton de votre template déclenche une fonction dans votre TypeScript : quelle syntaxe utilisez-vous pour l\'y lier ?', 0, 3, NULL),
(10, 1, 'Combien font 2+3 ?', 0, 4, 1),
(11, 2, 'Combien font 6+8 ?', 0, 4, 1),
(12, 3, 'Comment obtient t\'on 18?', 0, 4, 1),
(13, 4, 'Combien font 11-3 ?', 0, 4, 1),
(14, 5, 'Comment obtient t\'on 4?', 0, 4, 1),
(15, 1, 'Quelle est la bonne orthographe ?', 0, 5, NULL),
(16, 2, 'Quelle est la bonne orthographe ?', 0, 5, NULL),
(17, 3, 'Quelle est la bonne orthographe ?', 0, 5, NULL);

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
(1, b'0', 'directx', 0, 1),
(2, b'1', 'javafx', 0, 1),
(3, b'0', 'swing', 0, 1),
(4, b'0', 'awt', 0, 1),
(5, b'0', 'Pas obligatoirement', 0, 2),
(6, b'0', 'C\'est déconseillé', 0, 2),
(7, b'1', 'Oui, impérativement', 0, 2),
(8, b'0', 'Non, c\'est interdit', 0, 2),
(9, b'0', 'web, smartphone, desktop', 0, 3),
(10, b'0', 'web, desktop, smartphone', 0, 3),
(11, b'1', 'smartphone, web, desktop', 0, 3),
(12, b'0', 'smartphone, desktop, web', 0, 3),
(13, b'0', 'Dans l\'application, chaque responsabilité ou fonctionnalité est assurée par une seule classe.', 0, 4),
(14, b'1', 'Une classe ne devrait avoir qu\'une seule responsabilité.', 0, 4),
(15, b'0', 'Vrai', 0, 5),
(16, b'1', 'Faux', 0, 5),
(17, b'1', 'L\'inversion de dépendances est un des 5 principes SOLID.', 0, 6),
(18, b'0', 'L\'inversion de dépendances permet d\'injecter les dépendances dans les classes qui en ont besoin.', 0, 6),
(19, b'1', 'L\'inversion de dépendances repose sur le concept d\'abstraction.', 0, 6),
(20, b'0', 'L\'utilisation d\'interfaces implique de faire de l\'inversion de dépendances.', 0, 6),
(21, b'1', 'Pour respecter le principe d\'inversion de dépendances, il est possible d\'utiliser des interfaces.', 0, 6),
(22, b'1', 'ng generate', 0, 7),
(23, b'0', 'ng new', 0, 7),
(24, b'1', 'ng build', 0, 7),
(25, b'1', 'les doubles accolades {{}}', 0, 8),
(26, b'0', 'les crochets []', 0, 8),
(27, b'0', 'les parenthèses ()', 0, 8),
(28, b'0', '(onClick)', 0, 9),
(29, b'1', '(click)', 0, 9),
(30, b'0', '[click]', 0, 9),
(31, b'0', '4', 0, 10),
(32, b'1', '5', 0, 10),
(33, b'0', '6', 0, 10),
(34, b'0', '12', 0, 11),
(35, b'1', '14', 0, 11),
(36, b'0', '16', 0, 11),
(37, b'0', '6+6', 0, 12),
(38, b'0', '9+8', 0, 12),
(39, b'1', '10+8', 0, 12),
(40, b'1', '13+5', 0, 12),
(41, b'1', '8', 0, 13),
(42, b'0', '10', 0, 13),
(43, b'0', '12', 0, 13),
(44, b'1', '6-2', 0, 14),
(45, b'0', '8-5', 0, 14),
(46, b'0', '6-4', 0, 14),
(47, b'1', '0+4', 0, 14),
(48, b'1', '10-6', 0, 14),
(49, b'1', 'Une pomme', 0, 15),
(50, b'0', 'Une pome', 0, 15),
(51, b'0', 'Des pomes', 0, 15),
(52, b'0', 'Une lionnes', 0, 16),
(53, b'0', 'Les liones', 0, 16),
(54, b'1', 'Les lionnes', 0, 16),
(55, b'0', 'Des casier', 0, 17),
(56, b'0', 'Un casiers', 0, 17),
(57, b'1', 'Des casiers', 0, 17);

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
(2, 1),
(3, 1),
(4, 2),
(5, 2);

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
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `quiz_test`
--

INSERT INTO `quiz_test` (`id`, `correctResponse`, `errorResponse`, `score`, `startDate`, `version`, `quiz_id`, `user_id`) VALUES
(1, 4, 1, 80, '2018-11-01', 0, 4, 8);

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
(1, 1, 3),
(2, 2, 3),
(3, 3, 3),
(4, 1, 4),
(5, 2, 4),
(6, 3, 4),
(7, 1, 5),
(8, 2, 5),
(9, 3, 5),
(10, 4, 6),
(11, 4, 7),
(13, 5, 6),
(14, 5, 7),
(15, 5, 8);

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
(1, 'Java', NULL, 0, NULL),
(2, 'Spring', NULL, 0, NULL),
(3, 'Angular', NULL, 0, NULL),
(4, 'Premier niveau mathématique', NULL, 0, NULL),
(5, 'Premier niveau français', NULL, 0, NULL);

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
(1, '2018-11-05', 'Formation JAVA EE', '2018-06-27', 0),
(2, '2018-12-05', 'Classe CP', '2018-09-03', 2),
(3, '2018-12-29', 'Formation Python', '2018-07-11', 0),
(4, '2019-02-08', 'Formation Php 7', '2018-10-11', 0);

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
(1, b'1', 32, 1),
(2, b'0', 33, 1),
(3, b'0', 31, 1),
(4, b'0', 36, 1),
(5, b'0', 34, 1),
(6, b'1', 35, 1),
(7, b'0', 40, 1),
(8, b'0', 37, 1),
(9, b'1', 39, 1),
(10, b'0', 38, 1),
(11, b'0', 42, 1),
(12, b'0', 43, 1),
(13, b'1', 41, 1),
(14, b'1', 47, 1),
(15, b'1', 44, 1),
(16, b'0', 45, 1),
(17, b'0', 46, 1),
(18, b'1', 48, 1);

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
  `studentClass_id` bigint(20) DEFAULT NULL,
  `stClass_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `birthdate`, `email`, `firstName`, `lastName`, `password`, `token`, `type`, `version`, `studentClass_id`, `stClass_id`) VALUES
(1, '1973-02-10', 'mouton@free.fr', 'marcel', 'mouton', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 2, 0, NULL, NULL),
(2, '1979-06-07', 'vianneyba@free.fr', 'vianney', 'bailleux', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 1, 0, NULL, NULL),
(3, '1978-12-19', 'dickinson@free.fr', 'bruce', 'dickinson', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 0, NULL, 1),
(4, '1980-01-04', 'halford@free.fr', 'rob', 'halford', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 0, NULL, 1),
(5, '1980-08-17', 'matos@free.fr', 'andré', 'matos', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 0, NULL, 1),
(6, '2012-02-22', 'aylan@free.fr', 'aylan', 'debusy', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 2, NULL, 2),
(7, '2012-03-12', 'clement@free.fr', 'clement', 'degrave', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 2, NULL, 2),
(8, '2012-12-05', 'heleonorba@free.fr', 'héléonor', 'bailleux', '*ECE77E9BCD5AA891B90E588829789E56593784FD', NULL, 0, 2, NULL, 2),
(9, '2003-08-05', 'martin@free.fr', 'henry', 'martin', '*D388CF13A8F188EF0217F210DBDC76BD7BA08DE4', '', 0, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_Skill`
--

CREATE TABLE `user_Skill` (
  `User_id` bigint(20) NOT NULL,
  `skills_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD KEY `FKgajnl339rcagvtxh6d4v09enm` (`user_id`);

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
  ADD KEY `FK1sw4l4qeuhq3rpq92it1dhmil` (`studentClass_id`),
  ADD KEY `FKnpgc5bj82d61sngq0ipxng4h4` (`stClass_id`);

--
-- Index pour la table `user_Skill`
--
ALTER TABLE `user_Skill`
  ADD UNIQUE KEY `UK_6gk67gioqj7njk456xl3pdkg4` (`skills_id`),
  ADD KEY `FKcixvxo6qybed2b1ovoovvkc00` (`User_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `quiz_question`
--
ALTER TABLE `quiz_question`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT pour la table `quiz_response`
--
ALTER TABLE `quiz_response`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT pour la table `quiz_test`
--
ALTER TABLE `quiz_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `quiz_todo`
--
ALTER TABLE `quiz_todo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT pour la table `Skill`
--
ALTER TABLE `Skill`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `student_response`
--
ALTER TABLE `student_response`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
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
  ADD CONSTRAINT `FK1sw4l4qeuhq3rpq92it1dhmil` FOREIGN KEY (`studentClass_id`) REFERENCES `Student_Class` (`id`),
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
