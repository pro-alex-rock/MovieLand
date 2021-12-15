create table genre (
                       id SERIAL,
                       genre VARCHAR (64) primary key NOT NULL
);

ALTER TABLE movie DROP COLUMN genre CASCADE;
ALTER TABLE movie ADD genre VARCHAR REFERENCES genre(genre);

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