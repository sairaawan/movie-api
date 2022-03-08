USE imdb;

DROP TABLE IF EXISTS Movie;

CREATE TABLE  Movie (
  `movieId` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `movieType` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `movieName` varchar(255) COLLATE utf8mb4_general_ci,
  `originalTitle` text COLLATE utf8mb4_general_ci,
  `isAdult` tinyint(1) DEFAULT NULL,
  `releaseYear` int(4) unsigned DEFAULT NULL,
  `endYear` int(4) unsigned DEFAULT NULL,
  `runningTime` int(4) unsigned DEFAULT NULL,
  `movieGenre` varchar(80) COLLATE utf8mb4_general_ci DEFAULT NULL, `averageRating` float unsigned DEFAULT NULL,`numberOfVotes` int(4) unsigned DEFAULT NULL, PRIMARY KEY (`movieId`)
)   ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOAD DATA LOCAL INFILE 'titles.tsv'
INTO TABLE Movie
COLUMNS TERMINATED BY '\t'
IGNORE 1 LINES;

DELETE FROM Movie WHERE isAdult = 1 OR (movieType NOT IN ('movie','tvMovie')) OR releaseYear IS NULL OR movieGenre IS NULL OR runningTime is NULL;

ALTER TABLE Movie
  DROP COLUMN IsAdult,
  DROP COLUMN originalTitle,
  DROP COLUMN endYear;

CREATE INDEX movie_index ON Movie(movieId);
CREATE INDEX release_year_index ON Movie(releaseYear);
