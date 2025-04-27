-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 27 avr. 2025 à 19:31
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

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
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id_article` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `description` text,
  `prix` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT '0',
  `seuil_remise` int(11) DEFAULT NULL,
  `categorie` varchar(255) NOT NULL,
  `marque` varchar(255) NOT NULL,
  `image_principale` varchar(255) NOT NULL DEFAULT 'image/default_product.png' COMMENT 'Chemin vers l''''image principale',
  `disponibilite` tinyint(1) NOT NULL DEFAULT '1',
  `date_ajout` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_article`)
) ENGINE=InnoDB AUTO_INCREMENT=1768251591 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id_article`, `nom`, `description`, `prix`, `stock`, `seuil_remise`, `categorie`, `marque`, `image_principale`, `disponibilite`, `date_ajout`) VALUES
(379355638, 'Tele', 'Une télévision comme une autre', '123.00', 145, 4, 'Electromenager', 'Samsung', 'image/default_product.png', 1, '2025-04-27 21:28:54'),
(832745446, 'y', 'Produit y', '14.00', 57, 8, 'Nourriture', 'Samsung', 'image/default_product.png', 1, '2025-04-23 16:26:24'),
(949478470, 'ae', 'Un produit test', '12.00', 115, 5, 'Electromenager', 'Bosch', 'image/default_product.png', 1, '2025-04-17 17:28:56'),
(949478471, 'Café en grains Bio', 'Café Arabica 100% bio, origine Brésil, paquet de 1kg', '12.90', 47, 10, 'Nourriture', 'Ethica', 'image/nourriture/cafe_bio.png', 1, '2025-04-17 20:00:39'),
(949478472, 'Chocolat noir 70%', 'Tablette de chocolat noir équitable, 200g', '3.50', 100, 20, 'Nourriture', 'Alter Eco', 'image/nourriture/chocolat_noir.png', 1, '2025-04-17 20:00:39'),
(949478473, 'Miel de lavande', 'Miel artisanal de Provence, pot en verre de 500g', '8.20', 29, 5, 'Nourriture', 'Miel & Nous', 'image/nourriture/miel_lavande.png', 1, '2025-04-17 20:00:39'),
(949478474, 'Pâtes complètes', 'Pâtes artisanales à la farine complète, sachet de 500g', '2.10', 80, 15, 'Nourriture', 'Terra Bio', 'image/nourriture/pates_completes.png', 1, '2025-04-17 20:00:39'),
(949478475, 'Huile d\'olive extra vierge', 'Huile d\'olive AOP de Nyons, bouteille de 75cl', '9.90', 39, 5, 'Nourriture', 'Oliviers & Co', 'image/nourriture/huile_olive.png', 1, '2025-04-17 20:00:39'),
(949478476, 'Confiture de framboise', 'Confiture maison sans additifs, pot de 370g', '4.50', 60, 10, 'Nourriture', 'Maison Douce', 'image/nourriture/confiture_framboise.png', 1, '2025-04-17 20:00:39'),
(949478477, 'Thé vert Matcha', 'Poudre de thé Matcha premium, boîte de 30g', '15.90', 34, 5, 'Nourriture', 'JaponTea', 'image/nourriture/the_matcha.png', 1, '2025-04-17 20:00:39'),
(949478478, 'Biscuits aux chocolats', 'Sachet de 10 biscuits aux chocolat bio sans gluten', '5.20', 70, 15, 'Nourriture', 'BioDelice', 'image/nourriture/biscuits_amandes.png', 1, '2025-04-17 20:00:39'),
(949478479, 'T-shirt coton bio', 'T-shirt unisex en coton biologique, couleur bleu', '19.90', 75, 10, 'Vêtements', 'EcoWear', 'image/vetements/tshirt_coton.png', 1, '2025-04-17 20:00:39'),
(949478480, 'Jean slim délavé', 'Jean slim pour homme, coupe moderne, taille ajustable', '49.90', 60, 5, 'Vêtements', 'DenimCo', 'image/vetements/jean_slim.png', 1, '2025-04-17 20:00:39'),
(949478481, 'Robe d\'été fleurie', 'Robe légère en lin, motifs floraux, taille S à XL', '35.50', 45, 10, 'Vêtements', 'SunnyDays', 'image/vetements/robe_ete.png', 1, '2025-04-17 20:00:39'),
(949478482, 'Veste en laine', 'Veste chaude en laine mérinos, pour homme et femme', '89.90', 25, 5, 'Vêtements', 'WoolNature', 'image/vetements/veste_laine.png', 1, '2025-04-17 20:00:39'),
(949478483, 'Chaussures de running', 'Chaussures de sport légères pour running, pointure 36-46', '69.90', 55, 10, 'Vêtements', 'RunFast', 'image/vetements/chaussures_running.png', 1, '2025-04-17 20:00:39'),
(949478484, 'Chemise en lin', 'Chemise élégante pour homme, plusieurs coloris', '45.00', 40, 10, 'Vêtements', 'CottonLine', 'image/vetements/chemise_lin.png', 1, '2025-04-17 20:00:39'),
(949478485, 'Legging yoga', 'Legging stretch confortable pour femme, noir', '29.90', 65, 15, 'Vêtements', 'YogaFit', 'image/vetements/legging_yoga.png', 1, '2025-04-17 20:00:39'),
(949478486, 'Manteau d\'hiver', 'Manteau imperméable avec capuche, taille XS à XXL', '119.90', 30, 5, 'Vêtements', 'WinterPro', 'image/vetements/manteau_hiver.png', 1, '2025-04-17 20:00:39'),
(949478487, 'Le Petit Prince', 'Édition collector du classique de Saint-Exupéry, illustrée', '14.90', 90, 20, 'Livres', 'Folio', 'image/livres/petit_prince.png', 1, '2025-04-17 20:00:39'),
(949478488, '1984 - George Orwell', 'Roman dystopique, édition avec notes explicatives', '10.50', 65, 15, 'Livres', 'Penguin', 'image/livres/1984_livre.png', 1, '2025-04-17 20:00:39'),
(949478489, 'L\'art de la guerre', 'Traduction commentée du traité de Sun Tzu', '12.00', 40, 10, 'Livres', 'Flammarion', 'image/livres/art_guerre.png', 1, '2025-04-17 20:00:39'),
(949478490, 'Cuisine végétarienne', 'Livre de recettes végétariennes pour tous les jours', '22.90', 35, 5, 'Livres', 'Marabout', 'image/livres/cuisine_vegetarienne.png', 1, '2025-04-17 20:00:39'),
(949478491, 'Guide de voyage Japon', 'Guide complet avec cartes et conseils pratiques', '18.50', 50, 10, 'Livres', 'Lonely Planet', 'image/livres/guide_japon.png', 1, '2025-04-17 20:00:39'),
(949478492, 'Le Seigneur des Anneaux', 'Trilogie complète en édition spéciale', '39.90', 25, 5, 'Livres', 'Pocket', 'image/livres/seigneur_anneaux.png', 1, '2025-04-17 20:00:39'),
(949478493, 'Astrophysics for People in a Hurry', 'Introduction à l\'astrophysique par Neil deGrasse Tyson', '16.90', 45, 10, 'Livres', 'W.W. Norton', 'image/livres/astrophysics.png', 1, '2025-04-17 20:00:39'),
(949478494, 'Le pouvoir du moment présent', 'Guide de développement personnel', '9.90', 60, 15, 'Livres', 'J\'ai Lu', 'image/livres/pouvoir_moment.png', 1, '2025-04-17 20:00:39'),
(949478495, 'Reproduction - La Joconde (Mona Lisa)', 'Réproduction sur toile du chef-d\'œuvre de Léonard de Vinci, encadrée, 50x70cm', '89.90', 12, 3, 'Décoration', 'ArtPremium', 'image/decoration/joconde.png', 1, '2025-04-17 20:00:39'),
(949478496, 'Reproduction - La Nuit étoilée', 'Toile de Van Gogh avec cadre en bois massif, 60x80cm', '79.90', 15, 5, 'Décoration', 'VanGoghRepro', 'image/decoration/nuit_etoilee.png', 1, '2025-04-17 20:00:39'),
(949478497, 'Reproduction - La Liberté guidant le peuple', 'Tableau emblématique de Delacroix, version toile, 60x90cm', '69.90', 10, 3, 'Décoration', 'FrenchMaster', 'image/decoration/liberte_peuple.png', 1, '2025-04-17 20:00:39'),
(949478498, 'Reproduction - Guernica', 'Œuvre anti-guerre de Picasso en reproduction haute qualité, 70x150cm', '129.90', 6, 2, 'Décoration', 'ModernArtRepro', 'image/decoration/guernica.png', 1, '2025-04-17 20:00:39'),
(949478499, 'Portrait de Napoléon Bonaparte', 'Reproduction du portrait officiel par Jacques-Louis David', '49.90', 20, 5, 'Décoration', 'HistoryCanvas', 'image/decoration/napoleon.png', 1, '2025-04-17 20:00:39'),
(949478500, 'Portrait de Jeanne d\'Arc', 'Reproduction d\'après les représentations médiévales', '45.90', 15, 5, 'Décoration', 'MedievalArt', 'image/decoration/jeanne_arc.png', 1, '2025-04-17 20:00:39'),
(949478501, 'Portrait de Marie-Antoinette', 'D\'après le tableau de Vigée Le Brun, cadre doré', '59.90', 12, 5, 'Décoration', 'VersaillesArt', 'image/decoration/marie_antoinette.png', 1, '2025-04-17 20:00:39'),
(949478502, 'Sphère armillaire ancienne', 'Modèle décoratif en laiton, diamètre 30cm', '89.90', 15, 5, 'Décoration', 'AntiqueWorld', 'image/decoration/sphere_armillaire.png', 1, '2025-04-17 20:00:39'),
(1768251590, 'lave-vaiselle', 'Un lave-vaiselle comme un autre', '1235.00', 75, 6, 'Electromenager', 'Bosch', 'image/default_product.png', 1, '2025-04-19 14:41:04');

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

DROP TABLE IF EXISTS `avis`;
CREATE TABLE IF NOT EXISTS `avis` (
  `id_avis` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `id_article` int(11) DEFAULT NULL,
  `commentaire` text,
  `note` int(11) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_avis`),
  KEY `utilisateur_avis` (`id_utilisateur`),
  KEY `article_avis` (`id_article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`id_avis`, `id_utilisateur`, `id_article`, `commentaire`, `note`, `date`) VALUES
(859565961, 1022904176, 832745446, '', 1, '2025-04-27');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `date_commande` datetime DEFAULT CURRENT_TIMESTAMP,
  `statut` enum('en attente','payée','expédiée','livrée','annulée') DEFAULT 'en attente',
  `adresse_livraison` text NOT NULL,
  `montant_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=850155970 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_commande`, `id_client`, `date_commande`, `statut`, `adresse_livraison`, `montant_total`) VALUES
(97874786, 121615499, '2025-04-27 16:30:51', 'payée', 'ty', '12.00'),
(818365035, 1022904176, '2025-04-27 17:42:17', 'payée', 'taro', '14.00');

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

DROP TABLE IF EXISTS `lignecommande`;
CREATE TABLE IF NOT EXISTS `lignecommande` (
  `id_ligne_commande` int(11) NOT NULL AUTO_INCREMENT,
  `id_commande` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  `id_promotion` int(11) DEFAULT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` decimal(10,2) NOT NULL,
  `prix_apres_remise` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_ligne_commande`),
  KEY `id_commande` (`id_commande`),
  KEY `id_article` (`id_article`),
  KEY `id_promotion` (`id_promotion`)
) ENGINE=InnoDB AUTO_INCREMENT=841392512 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lignecommande`
--

INSERT INTO `lignecommande` (`id_ligne_commande`, `id_commande`, `id_article`, `id_promotion`, `quantite`, `prix_unitaire`, `prix_apres_remise`) VALUES
(224221002, 97874786, 949478471, NULL, 1, '12.00', '12.00'),
(532661992, 818365035, 832745446, NULL, 1, '14.00', '14.00');

-- --------------------------------------------------------

--
-- Structure de la table `lignepanier`
--

DROP TABLE IF EXISTS `lignepanier`;
CREATE TABLE IF NOT EXISTS `lignepanier` (
  `id_ligne_panier` int(11) NOT NULL AUTO_INCREMENT,
  `id_panier` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  `quantite` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_ligne_panier`),
  KEY `id_panier` (`id_panier`),
  KEY `id_article` (`id_article`)
) ENGINE=InnoDB AUTO_INCREMENT=988467564 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lignepanier`
--

INSERT INTO `lignepanier` (`id_ligne_panier`, `id_panier`, `id_article`, `quantite`) VALUES
(416839297, 853999664, 949478473, 1),
(492374271, 853999664, 949478471, 1);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id_paiement` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateur` int(11) NOT NULL,
  `date_commande` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `montant` decimal(10,2) NOT NULL,
  `moyen` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  PRIMARY KEY (`id_paiement`),
  KEY `paiement_utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=966490343 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id_paiement`, `id_utilisateur`, `date_commande`, `montant`, `moyen`, `statut`) VALUES
(113131525, 1022904176, '2025-04-27 17:42:24', '14.00', 'Visa', 'payée'),
(308505197, 121615499, '2025-04-27 16:15:07', '12.90', 'Visa', 'payée'),
(346120554, 1022904176, '2025-04-26 14:46:43', '76570.00', 'Visa', 'payée'),
(646544179, 121615499, '2025-04-27 16:16:45', '15.90', 'PayPal', 'payée'),
(769153278, 121615499, '2025-04-27 16:30:57', '12.90', 'Visa', 'payée');

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id_panier` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `date_creation` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_modification` datetime DEFAULT NULL,
  PRIMARY KEY (`id_panier`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=853999665 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id_panier`, `id_client`, `date_creation`, `date_modification`) VALUES
(853999664, 121615499, '2025-04-27 17:43:58', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

DROP TABLE IF EXISTS `photo`;
CREATE TABLE IF NOT EXISTS `photo` (
  `id_photo` int(11) NOT NULL AUTO_INCREMENT,
  `id_article` int(11) NOT NULL,
  `chemin` varchar(255) NOT NULL COMMENT 'Chemin relatif vers l''image dans le dossier /image',
  `alt_text` varchar(255) DEFAULT NULL,
  `ordre_affichage` int(11) DEFAULT '1',
  `date_ajout` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_photo`),
  KEY `id_article` (`id_article`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id_promotion` int(11) NOT NULL AUTO_INCREMENT,
  `code_promotion` varchar(50) DEFAULT NULL,
  `pourcentage_remise` decimal(5,2) NOT NULL,
  `seuil_volume` int(11) DEFAULT NULL,
  `date_debut` datetime NOT NULL,
  `date_fin` datetime NOT NULL,
  `id_article` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_promotion`),
  UNIQUE KEY `code_promotion` (`code_promotion`),
  KEY `id_article` (`id_article`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(191) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `type_utilisateur` enum('client','admin') NOT NULL,
  `fidelite` varchar(255) DEFAULT NULL,
  `date_creation` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_derniere_connexion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2139289819 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `email`, `mot_de_passe`, `type_utilisateur`, `fidelite`, `date_creation`, `date_derniere_connexion`) VALUES
(121615499, 'Andriamanga', 'Andy', 'andyandria@gmail.com', 'Andy*2004', 'client', 'Bronze', '2025-04-27 16:03:16', NULL),
(405552660, 'Lanquetin', 'Octave', 'olanquetin@mail.com', 'olanquetin', 'admin', 'Bronze', '2025-04-27 21:26:39', NULL),
(979134441, 'Tanguy', 'Alara', 'atanguy@mail.com', 'atanguy', 'admin', 'Bronze', '2025-04-27 21:28:07', NULL),
(1022904176, 'Andria', 'Andy', 'andri@gmail.com', '123456', 'admin', 'Bronze', '2025-04-17 11:47:16', NULL),
(1335405254, 'ae', 'ae', 'ae@mail.com', 'ae', 'client', 'Bronze', '2025-04-17 17:25:53', NULL),
(2139289818, 'Andry', 'Andre', 'andry@mail.fr', 'sardine', 'client', 'Bronze', '2025-04-17 14:56:27', NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `article_avis` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`),
  ADD CONSTRAINT `utilisateur_avis` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `utilisateur` (`id_utilisateur`);

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
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `paiement_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `panier_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`id_article`) REFERENCES `article` (`id_article`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
