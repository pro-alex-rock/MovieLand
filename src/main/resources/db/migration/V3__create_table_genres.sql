create table IF NOT EXISTS genre (
                       genre_id SERIAL primary key NOT NULL,
                       genre VARCHAR (64)
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