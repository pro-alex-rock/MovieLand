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
                       country VARCHAR REFERENCES country(country_id),
                       genre VARCHAR REFERENCES genre(genre_id),
                       description VARCHAR (1024),
                       rating NUMERIC(3, 1),
                       price NUMERIC(6, 2),
                       picturePath VARCHAR (1024),
                       review VARCHAR REFERENCES review(review_id)
);

create table review (
                        review_id SERIAL primary key,
                        user_id INTEGER REFERENCES users(user_id),
                        movie_id INTEGER REFERENCES movie(movie_id),
                        review VARCHAR (1024) UNIQUE NOT NULL
);

INSERT INTO genre (genre) VALUES
('драма'),
('криминал'),
('фэнтези'),
('детектив'),
('мелодрама'),
('биография'),
('комедия'),
('фантастика'),
('боевик'),
('триллер'),
('приключения'),
('аниме'),
('мультфильм'),
('семейный'),
('вестерн');


INSERT INTO movie (name_russian, name_native, year, country, genre, description, rating, price, picturePath)
VALUES ('Матрица', 'Matrix', 1999, 'США', 'фантастика',
        'Мир Матрицы — это иллюзия, существующая только в бесконечном сне обреченного человечества. Холодный мир будущего, в котором люди — всего лишь батарейки в компьютерных системах.',
        10.0, 100.00, 'https://upload.wikimedia.org/wikipedia/ru/thumb/9/9d/Matrix-DVD.jpg/217px-Matrix-DVD.jpg');
INSERT INTO movie (name_russian, name_native, year, country, genre, description, rating, price, picturePath)
VALUES ('Матрица 2', 'Matrix 2', 2000, 'США', 'фантастика',
        'Борцы за свободу Нео, Тринити и Морфеус продолжают руководить восстанием людей против Армии Машин. Для уничтожения системы репрессий и эксплуатации они вынуждены прибегнуть не только к арсеналу превосходного оружия, но и к своим выдающимся навыкам.',
        10.0, 100.00,
        'https://upload.wikimedia.org/wikipedia/ru/6/62/Matrix_reloaded.jpg');
INSERT INTO movie (name_russian, name_native, year, country, genre, description, rating, price, picturePath)
VALUES ('Форрест Гамп', 'Forrest Gump', 1994, 'США', 'драма',
        'От лица главного героя Форреста Гампа, слабоумного безобидного человека с благородным и открытым сердцем, рассказывается история его необыкновенной жизни.Фантастическим образом превращается он в известного футболиста, героя войны, преуспевающего бизнесмена. Он становится миллиардером, но остается таким же бесхитростным, глупым и добрым. Форреста ждет постоянный успех во всем, а он любит девочку, с которой дружил в детстве, но взаимность приходит слишком поздно.',
        8.6, 200.60,
        'https://images-na.ssl-images-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR2,0,140,209_.jpg');
INSERT INTO movie (name_russian, name_native, year, country, genre, description, rating, price, picturePath)
VALUES ('Список Шиндлера', 'Schindler''s List', 1993, 'США', 'драма',
        'Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего фабриканта, спасшего во время Второй мировой войны почти 1200 евреев.',
        8.7, 150.50,
        'https://images-na.ssl-images-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1._SX140_CR0,0,140,209_.jpg');