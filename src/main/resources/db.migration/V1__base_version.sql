create table users (
                       user_id SERIAL primary key,
                       name VARCHAR (250) UNIQUE NOT NULL,
                       email VARCHAR (250) UNIQUE NOT NULL,
                       alias VARCHAR (250) UNIQUE NOT NULL
);

create table genre (
                       genre_id SERIAL primary key,
                       title_genre VARCHAR (100) UNIQUE NOT NULL
);

create table movie (
                       movie_id SERIAL primary key,
                       title_movie VARCHAR (350) UNIQUE NOT NULL,
                       year INTEGER,
                       country VARCHAR (250),
                       genre_id INTEGER REFERENCES genre(genre_id),
                       description VARCHAR (1024),
                       rating NUMERIC(3, 1),
                       price NUMERIC(6, 2)
);

create table review (
                        review_id SERIAL primary key,
                        user_id INTEGER REFERENCES users(user_id),
                        movie_id INTEGER REFERENCES movie(movie_id),
                        review VARCHAR (1024) UNIQUE NOT NULL
);

create table poster (
                        poster_id SERIAL primary key,
                        movie_id INTEGER REFERENCES movie(movie_id),
                        poster_link VARCHAR (1024)
);