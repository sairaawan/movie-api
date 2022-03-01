USE imdb;

DROP TABLE IF EXISTS Person;
CREATE TABLE Person (
  `PersonId` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `primaryName` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `birthYear` int(4) unsigned DEFAULT NULL,
  `deathYear` int(4) unsigned DEFAULT NULL,
  `primaryProfession` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `knownForMovies` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL
, PRIMARY KEY (`PersonId`)) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


LOAD DATA LOCAL INFILE 'names.tsv'
INTO TABLE Person
COLUMNS TERMINATED BY '\t'
IGNORE 1 LINES;

DELETE FROM Person where knownForMovies is null OR birthYear is null;

CREATE INDEX person_index ON Person(PersonId);
CREATE INDEX primary_name_index ON Person(primaryName(80));
CREATE INDEX profession ON Person(primaryProfession(80));