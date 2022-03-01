USE imdb;

DROP TABLE IF EXISTS movie_ratings;
CREATE TABLE  movie_ratings (
  `movieId` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `averageRating` float DEFAULT NULL,
  `numberOfVotes` int(11) DEFAULT NULL
, PRIMARY KEY (`movieId`) ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOAD DATA LOCAL INFILE  'ratings.tsv'
INTO TABLE movie_ratings
COLUMNS TERMINATED BY '\t'
IGNORE 1 LINES;

CREATE INDEX ratingsIndex ON movie_ratings(movieId);