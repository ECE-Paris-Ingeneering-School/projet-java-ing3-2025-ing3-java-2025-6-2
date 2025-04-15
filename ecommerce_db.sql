-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 15 avr. 2025 à 08:13
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ecommerce_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE IF NOT EXISTS `administrateur` (
  `id_admin` int NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `niveau_acces` int DEFAULT '1',
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`id_admin`, `nom`, `prenom`, `niveau_acces`) VALUES
(1, 'Doe', 'John', 2);

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id_article` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `description` text,
  `prix` decimal(10,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `seuil_remise` int DEFAULT NULL,
  `id_categorie` int DEFAULT NULL,
  `id_marque` int DEFAULT NULL,
  `date_ajout` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_article`),
  KEY `id_categorie` (`id_categorie`),
  KEY `id_marque` (`id_marque`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id_article`, `nom`, `description`, `prix`, `stock`, `seuil_remise`, `id_categorie`, `id_marque`, `date_ajout`) VALUES
(1, 'Smartphone X', 'Smartphone haut de gamme avec écran OLED', 799.99, 49, 5, 1, 1, '2025-04-15 10:12:52'),
(2, 'T-Shirt Casual', 'T-shirt en coton bio, plusieurs coloris', 29.99, 98, 10, 2, 2, '2025-04-15 10:12:52');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id_categorie` int NOT NULL AUTO_INCREMENT,
  `nom_categorie` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`id_categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `nom_categorie`, `description`) VALUES
(1, 'Électronique', 'Appareils électroniques et gadgets'),
(2, 'Vêtements', 'Vêtements pour hommes, femmes et enfants');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `adresse` text,
  `telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `nom`, `prenom`, `adresse`, `telephone`) VALUES
(2, 'Dupont', 'Jean', '123 Rue de Test, 75000 Paris', '0612345678');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande` int NOT NULL AUTO_INCREMENT,
  `id_client` int NOT NULL,
  `date_commande` datetime DEFAULT CURRENT_TIMESTAMP,
  `statut` enum('en attente','payée','expédiée','livrée','annulée') DEFAULT 'en attente',
  `adresse_livraison` text NOT NULL,
  `montant_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_commande`, `id_client`, `date_commande`, `statut`, `adresse_livraison`, `montant_total`) VALUES
(1, 2, '2025-03-31 10:12:52', 'livrée', '123 Rue de Test, 75000 Paris', 859.96);

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

DROP TABLE IF EXISTS `lignecommande`;
CREATE TABLE IF NOT EXISTS `lignecommande` (
  `id_ligne_commande` int NOT NULL AUTO_INCREMENT,
  `id_commande` int NOT NULL,
  `id_article` int NOT NULL,
  `id_promotion` int DEFAULT NULL,
  `quantite` int NOT NULL,
  `prix_unitaire` decimal(10,2) NOT NULL,
  `prix_apres_remise` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_ligne_commande`),
  KEY `id_commande` (`id_commande`),
  KEY `id_article` (`id_article`),
  KEY `id_promotion` (`id_promotion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lignecommande`
--

INSERT INTO `lignecommande` (`id_ligne_commande`, `id_commande`, `id_article`, `id_promotion`, `quantite`, `prix_unitaire`, `prix_apres_remise`) VALUES
(1, 1, 1, NULL, 1, 799.99, 799.99),
(2, 1, 2, NULL, 2, 29.99, 26.99);

-- --------------------------------------------------------

--
-- Structure de la table `lignepanier`
--

DROP TABLE IF EXISTS `lignepanier`;
CREATE TABLE IF NOT EXISTS `lignepanier` (
  `id_ligne_panier` int NOT NULL AUTO_INCREMENT,
  `id_panier` int NOT NULL,
  `id_article` int NOT NULL,
  `quantite` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_ligne_panier`),
  KEY `id_panier` (`id_panier`),
  KEY `id_article` (`id_article`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lignepanier`
--

INSERT INTO `lignepanier` (`id_ligne_panier`, `id_panier`, `id_article`, `quantite`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

DROP TABLE IF EXISTS `marque`;
CREATE TABLE IF NOT EXISTS `marque` (
  `id_marque` int NOT NULL AUTO_INCREMENT,
  `nom_marque` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`id_marque`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `marque`
--

INSERT INTO `marque` (`id_marque`, `nom_marque`, `description`) VALUES
(1, 'TechBrand', 'Marque spécialisée en high-tech'),
(2, 'FashionLine', 'Marque de vêtements tendance');

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id_panier` int NOT NULL AUTO_INCREMENT,
  `id_client` int DEFAULT NULL,
  `date_creation` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_modification` datetime DEFAULT NULL,
  PRIMARY KEY (`id_panier`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id_panier`, `id_client`, `date_creation`, `date_modification`) VALUES
(1, 2, '2025-04-15 10:12:52', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id_promotion` int NOT NULL AUTO_INCREMENT,
  `code_promotion` varchar(50) DEFAULT NULL,
  `pourcentage_remise` decimal(5,2) NOT NULL,
  `seuil_volume` int DEFAULT NULL,
  `date_debut` datetime NOT NULL,
  `date_fin` datetime NOT NULL,
  `id_article` int DEFAULT NULL,
  PRIMARY KEY (`id_promotion`),
  UNIQUE KEY `code_promotion` (`code_promotion`),
  KEY `id_article` (`id_article`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE IF NOT EXISTS `session` (
  `id_session` varchar(255) NOT NULL,
  `id_utilisateur` int NOT NULL,
  `date_debut` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_expiration` datetime NOT NULL,
  `adresse_ip` varchar(45) DEFAULT NULL,
  `user_agent` text,
  PRIMARY KEY (`id_session`),
  KEY `id_utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `email` varchar(191) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `type_utilisateur` enum('client','admin') NOT NULL,
  `date_creation` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_derniere_connexion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `email`, `mot_de_passe`, `type_utilisateur`, `date_creation`, `date_derniere_connexion`) VALUES
(1, 'admin@test.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'admin', '2025-04-15 10:12:52', NULL),
(2, 'client@test.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'client', '2025-04-15 10:12:52', NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `administrateur_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`),
  ADD CONSTRAINT `article_ibfk_2` FOREIGN KEY (`id_marque`) REFERENCES `marque` (`id_marque`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Contraintes pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD CONSTRAINT `lignecommande_ibfk_1` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id_commande`),
  ADD CONSTRAINT `lignecommande_ibfk_2` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`),
  ADD CONSTRAINT `lignecommande_ibfk_3` FOREIGN KEY (`id_promotion`) REFERENCES `promotion` (`id_promotion`);

--
-- Contraintes pour la table `lignepanier`
--
ALTER TABLE `lignepanier`
  ADD CONSTRAINT `lignepanier_ibfk_1` FOREIGN KEY (`id_panier`) REFERENCES `panier` (`id_panier`) ON DELETE CASCADE,
  ADD CONSTRAINT `lignepanier_ibfk_2` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `panier_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`);

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
