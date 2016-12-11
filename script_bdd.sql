-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Mer 07 Décembre 2016 à 20:04
-- Version du serveur :  5.5.38
-- Version de PHP :  5.6.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `mediatheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `Categorie`
--

CREATE TABLE `Categorie` (
`idcategorie` int(11) NOT NULL,
  `libeleCat` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Categorie`
--

INSERT INTO `Categorie` (`idcategorie`, `libeleCat`, `description`) VALUES
(1, 'CD audio', ''),
(2, 'Livres', ''),
(3, 'DVD videos', '');

-- --------------------------------------------------------

--
-- Structure de la table `Emprunt`
--

CREATE TABLE `Emprunt` (
`idEmprunt` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `rendu` tinyint(1) DEFAULT NULL,
  `panier` tinyint(1) DEFAULT NULL,
  `Media_idMedia` int(11) NOT NULL,
  `Membre_idMembre` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Emprunt`
--

INSERT INTO `Emprunt` (`idEmprunt`, `date`, `rendu`, `panier`, `Media_idMedia`, `Membre_idMembre`) VALUES
(26, '2016-12-07 19:45:01', 0, 0, 42, 8),
(27, '2016-12-07 19:45:44', 0, 0, 50, 8);

-- --------------------------------------------------------

--
-- Structure de la table `Media`
--

CREATE TABLE `Media` (
`idMedia` int(11) NOT NULL,
  `titre` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `reference` varchar(100) DEFAULT NULL,
  `auteur` varchar(50) DEFAULT NULL,
  `annee` year(4) DEFAULT NULL,
  `Theme_idTheme` int(11) NOT NULL,
  `Categorie_idcategorie` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Media`
--

INSERT INTO `Media` (`idMedia`, `titre`, `reference`, `auteur`, `annee`, `Theme_idTheme`, `Categorie_idcategorie`) VALUES
(34, 'La physique quantique et ses applications', 'A7999', 'Jean-Louis Basdevant', NULL, 4, 2),
(35, 'Sites web - Les bonnes pratiques', 'A2273', 'Laurent Denis', NULL, 5, 2),
(36, 'Programmer en Java', 'A1257', 'Claude Delannoy', NULL, 5, 2),
(40, 'Initiation à la géométrie de Riemann', 'A2331', 'François Rouvière', NULL, 1, 2),
(41, 'Sécurité informatique et Malwares', 'A7664', 'Paul Rascagnères', NULL, 5, 2),
(42, 'Réseaux et transmissions', 'A8773', 'Dominique Présent', NULL, 17, 2),
(43, 'Forrest Gump', 'A3442', 'Tom Hanks', NULL, 2, 3),
(44, 'Inception', 'A9982', 'Leonardo DiCaprio', NULL, 28, 3),
(45, 'Gladiator', 'A1124', 'Russell Crowe', NULL, 27, 3),
(46, 'Le Seigneur des anneaux', 'A7236', 'Peter Jackson', NULL, 27, 3),
(47, 'Intouchables', 'A7226', 'Omar Sy', NULL, 32, 3),
(48, 'Rainmaker', 'A8772', 'Yanni', NULL, 26, 1),
(49, 'The storm', 'A8722', 'Yanni', NULL, 26, 1),
(50, 'Not Afraid', 'A8722', 'Eminem', NULL, 25, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Membre`
--

CREATE TABLE `Membre` (
`idMembre` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `motPasse` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fonction` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Membre`
--

INSERT INTO `Membre` (`idMembre`, `nom`, `prenom`, `motPasse`, `email`, `fonction`) VALUES
(1, 'TOTO', 'admin', 'toto', 'toto@gmail.com', 'Admin'),
(2, 'TAHRI', 'Othmane', 'othmane', 'othmane.tahri@gmail.com', 'Membre'),
(3, 'membre', 'membre', 'membre', 'membre@gmail.com', 'Membre'),
(8, 'KISSAMI', 'Douaa', 'douaa', 'douaa.kissami@gmail.com', 'Membre');

-- --------------------------------------------------------

--
-- Structure de la table `Theme`
--

CREATE TABLE `Theme` (
`idTheme` int(11) NOT NULL,
  `libeleTheme` varchar(45) DEFAULT NULL,
  `Categorie_idcategorie` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Theme`
--

INSERT INTO `Theme` (`idTheme`, `libeleTheme`, `Categorie_idcategorie`) VALUES
(1, 'Maths ', 2),
(2, 'Drama', 3),
(3, 'POP', 1),
(4, 'Physique', 2),
(5, 'Informatique', 2),
(17, 'Réseaux', 2),
(19, 'Biologie', 2),
(24, 'Rock', 1),
(25, 'Hip-Hop', 1),
(26, 'Classique', 1),
(27, 'Aventure', 3),
(28, 'Science fiction', 3),
(31, 'Action', 3),
(32, 'Comédie', 3);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Categorie`
--
ALTER TABLE `Categorie`
 ADD PRIMARY KEY (`idcategorie`);

--
-- Index pour la table `Emprunt`
--
ALTER TABLE `Emprunt`
 ADD PRIMARY KEY (`idEmprunt`), ADD KEY `fk_Emprunt_Media1_idx` (`Media_idMedia`), ADD KEY `fk_Emprunt_Membre1_idx` (`Membre_idMembre`);

--
-- Index pour la table `Media`
--
ALTER TABLE `Media`
 ADD PRIMARY KEY (`idMedia`), ADD KEY `fk_Media_Theme1_idx` (`Theme_idTheme`), ADD KEY `fk_Media_Categorie1_idx` (`Categorie_idcategorie`);

--
-- Index pour la table `Membre`
--
ALTER TABLE `Membre`
 ADD PRIMARY KEY (`idMembre`);

--
-- Index pour la table `Theme`
--
ALTER TABLE `Theme`
 ADD PRIMARY KEY (`idTheme`), ADD KEY `fk_Theme_Categorie_idx` (`Categorie_idcategorie`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Categorie`
--
ALTER TABLE `Categorie`
MODIFY `idcategorie` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `Emprunt`
--
ALTER TABLE `Emprunt`
MODIFY `idEmprunt` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT pour la table `Media`
--
ALTER TABLE `Media`
MODIFY `idMedia` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT pour la table `Membre`
--
ALTER TABLE `Membre`
MODIFY `idMembre` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `Theme`
--
ALTER TABLE `Theme`
MODIFY `idTheme` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Emprunt`
--
ALTER TABLE `Emprunt`
ADD CONSTRAINT `fk_Emprunt_Media1` FOREIGN KEY (`Media_idMedia`) REFERENCES `Media` (`idMedia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Emprunt_Membre1` FOREIGN KEY (`Membre_idMembre`) REFERENCES `Membre` (`idMembre`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Media`
--
ALTER TABLE `Media`
ADD CONSTRAINT `fk_Media_Categorie1` FOREIGN KEY (`Categorie_idcategorie`) REFERENCES `Categorie` (`idcategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Media_Theme1` FOREIGN KEY (`Theme_idTheme`) REFERENCES `Theme` (`idTheme`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Theme`
--
ALTER TABLE `Theme`
ADD CONSTRAINT `fk_Theme_Categorie` FOREIGN KEY (`Categorie_idcategorie`) REFERENCES `Categorie` (`idcategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION;
