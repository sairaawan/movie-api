USE imdb;

UPDATE movies
INNER JOIN movie_ratings ON movies.movieId = movie_ratings.movieId
SET movies.averageRating = movie_ratings.averageRating, movies.numberOfVotes = movie_ratings.numberOfVotes;

DELETE FROM movies where averageRating is null;

