USE imdb;

DROP TABLE IF EXISTS names;
CREATE TABLE names (
  `nameId` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `primaryName` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `birthYear` int(4) unsigned DEFAULT NULL,
  `deathYear` int(4) unsigned DEFAULT NULL,
  `primaryProfession` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `knownForMovies` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL
, PRIMARY KEY (`nameId`)) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

LOAD DATA LOCAL INFILE 'names.tsv'
INTO TABLE names
COLUMNS TERMINATED BY '\t'
IGNORE 1 LINES;

DELETE FROM names where knownForMovies is null OR birthYear is null;

CREATE INDEX names_index ON names(nameId);
CREATE INDEX primary_name_index ON names(primaryName(80));
CREATE INDEX profession ON names(primaryProfession(80));