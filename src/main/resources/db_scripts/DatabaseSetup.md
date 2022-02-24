## Database setup

The data used for this project is from iMDb. The dataset available is described
at https://www.imdb.com/interfaces and can be downloaded from https://datasets.imdbws.com.

We only require a subset of the data for our purposes, the following files should be downloaded:

<li>https://datasets.imdbws.com/title.basics.tsv.gz - extracted and renamed titles.tsv
<li>https://datasets.imdbws.com/name.basics.tsv.gz - extracted and renamed names.tsv
<li>https://datasets.imdbws.com/title.ratings.tsv.gz - extracted and ranamed ratings.tsv

<br>Once extracted, copy the tsv data files into the db_scripts folder, 
using a command prompt login to your MySQL instance and run the following commands in the order specified:
<br>
<li>SOURCE create-db.sql
<li>SOURCE create-movies.sql
<li>SOURCE create-names.sql
<li>SOURCE create-ratings.sql
<li>SOURCE update-movie-ratings.sql

<br>The scripts create the database, tables, load the data from the files, remove some of the data we do not require and apply indexing.  As there's a lot of data most scripts will take a few minutes to run.
<br><br>
Create an a database user account for the application with read access for the imdb database and create the following environment variables:
<li>test_movieDB - database url and name, e.g. localhost/imdb
<li>test_db_user - username for test database
<li>test_db_pwd - passwrod for test database
<br><br>
If using a separate production database, create a user and the following environment variables:
<br><br>
<li>prod_movieDB - database url and name, e.g. myprodhost/imdb
<li>prod_db_user - username for production database
<li>prod_db_pwd - passwrod for production database