create table users (
                       user_id SERIAL primary key,
                       name VARCHAR (250) UNIQUE NOT NULL,
                       email VARCHAR (250) UNIQUE NOT NULL,
                       alias VARCHAR (250) UNIQUE NOT NULL
);

create table IF NOT EXISTS genre (
                       genre_id SERIAL primary key NOT NULL,
                       genre VARCHAR (64)
);

create table country (
                       country_id SERIAL primary key,
                       country VARCHAR (250)
);


create table movie (
                       movie_id SERIAL primary key,
                       name_russian VARCHAR (350) UNIQUE NOT NULL,
                       name_native VARCHAR (350) UNIQUE NOT NULL,
                       year INTEGER,
                       country VARCHAR REFERENCES country(country),
                       genre VARCHAR REFERENCES genre(genre),
                       description VARCHAR (1024),
                       rating NUMERIC(3, 1),
                       price NUMERIC(6, 2),
                       picturePath VARCHAR (1024)
);

create table review (
                       review_id SERIAL primary key,
                       user_id INTEGER REFERENCES users(user_id),
                       movie_id INTEGER REFERENCES movie(movie_id),
                       review VARCHAR (1024) UNIQUE NOT NULL
);