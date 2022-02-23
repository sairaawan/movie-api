USE imdb;

UPDATE Movie
INNER JOIN movie_ratings ON Movie.movieId = movie_ratings.movieId
SET Movie.averageRating = movie_ratings.averageRating, Movie.numberOfVotes = movie_ratings.numberOfVotes;

DELETE FROM Movie where averageRating is null;

