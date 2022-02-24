USE imdb;

DROP TABLE IF EXISTS People;
CREATE TABLE People (
  `PeopleId` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `primaryName` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `birthYear` int(4) unsigned DEFAULT NULL,
  `deathYear` int(4) unsigned DEFAULT NULL,
  `primaryProfession` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  `knownForMovies` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL
, PRIMARY KEY (`PeopleId`)) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

LOAD DATA LOCAL INFILE 'names.tsv'
INTO TABLE People
COLUMNS TERMINATED BY '\t'
IGNORE 1 LINES;

DELETE FROM People where knownForMovies is null OR birthYear is null;

CREATE INDEX people_index ON People(PeopleId);
CREATE INDEX primary_name_index ON People(primaryName(80));
CREATE INDEX profession ON People(primaryProfession(80));