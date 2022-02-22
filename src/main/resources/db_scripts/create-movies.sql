USE imdb;

DROP TABLE IF EXISTS movies;

CREATE TABLE  movies (
  `movieId` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `movieType` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `movieName` text COLLATE utf8mb4_bin,
  `originalTitle` text COLLATE utf8mb4_bin,
  `isAdult` tinyint(1) DEFAULT NULL,
  `releaseYear` int(4) unsigned DEFAULT NULL,
  `endYear` int(4) unsigned DEFAULT NULL,
  `runningTime` int(4) unsigned DEFAULT NULL,
  `movieGenre` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL, `averageRating` int(4) unsigned DEFAULT NULL,`numberOfVotes` int(4) unsigned DEFAULT NULL, PRIMARY KEY (`movieId`) 
)   ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

LOAD DATA LOCAL INFILE 'titles.tsv'
INTO TABLE movies
COLUMNS TERMINATED BY '\t'
IGNORE 1 LINES;

DELETE FROM movies WHERE isAdult = 1 OR (movieType NOT IN ('movie','tvMovie')) OR releaseYear IS NULL OR movieGenre IS NULL OR runningTime is NULL;

ALTER TABLE movies
  DROP COLUMN IsAdult,
  DROP COLUMN originalTitle,
  DROP COLUMN endYear;

CREATE INDEX movie_index ON movies(movieId);
CREATE INDEX release_year_index ON movies(releaseYear);
