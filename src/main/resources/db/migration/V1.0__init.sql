--create user relationship--

create table users(
    ID SERIAL not null,
    username varchar(50) not null,
    password varchar(100) not null,
    enabled Boolean not null,
    primary key(ID),
    CONSTRAINT "usernameUnique" UNIQUE ("username")
);

insert into users (username, password, enabled) values
    ('rinat', '{noop}123', true),
    ('ruslan', '{noop}321', true),
    ('manager', '{noop}123', true),
    ('user', '{noop}123', true);

create table authorities(
    ID SERIAL not null,
    username varchar(50) not null,
    authority varchar(50) not null,
    primary key(ID),

    constraint authorities_idx unique(username, authority),

    constraint authorities_ibfk_1
        foreign key(username)
            references users(username)
            on update cascade
);

insert into authorities (username, authority) values
    ('rinat', 'ROLE_ADMIN'),
    ('ruslan', 'ROLE_ADMIN'),
    ('manager', 'ROLE_MANAGER'),
    ('user', 'ROLE_USER');

-----

CREATE TABLE status
(
    id serial NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO status (name) VALUES ('Прочитано'), ('Не прочитано'), ('Читаю'), ('хочу приобрести');

CREATE TABLE book
(
    id serial NOT NULL,
    name text NOT NULL,
    capacity serial NOT NULL,
    image text NOT NULL,
    description text NOT NULL,
    genres text[] NOT NULL,
    year_published text NOT NULL,
    published text NOT NULL,
    author text[] NOT NULL,
    ISBN text NOT NULL UNIQUE,

    PRIMARY KEY (id)
);

CREATE TABLE class
(
    id serial NOT NULL,
    name text NOT NULL,
    user_id serial NOT NULL,
    PRIMARY KEY (id),

    constraint class_ibfk_1
        foreign key(user_id)
            references users(ID)
            on update cascade
);

CREATE TABLE place
(
    id serial NOT NULL,
    name text NOT NULL,
    user_id serial NOT NULL,
    PRIMARY KEY (id),

    constraint place_ibfk_1
        foreign key(user_Id)
            references users(ID)
            on update cascade
);

CREATE TABLE additional_Information
(
    id serial NOT NULL,
    notes text[] NOT NULL,
    place_Info text,
    last_Page serial NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE library
(
    id serial NOT NULL,
    user_Id serial NOT NULL,
    class_Id serial,
    status_Id serial,
    book_Id serial NOT NULL,
    place_id serial,
    hidden text NOT NULL,
    additional_Information_Id serial,
    PRIMARY KEY (id),

    constraint library_ibfk_1
        foreign key(user_Id)
            references users(ID)
            on update cascade,


    constraint library_ibfk_2
        foreign key(book_Id)
            references book(id)
            on update cascade,

    constraint library_ibfk_5
        foreign key(class_Id)
            references class(id)
            on update cascade,

    constraint library_ibfk_3
        foreign key(additional_Information_Id)
            references additional_Information(id)
            on update cascade,


    constraint library_ibfk_4
        foreign key(place_id)
            references place(id)
            on update cascade,

    constraint library_ibfk_6
        foreign key(status_Id)
            references status(id)
            on update cascade
);


CREATE TABLE comments
(
    id serial NOT NULL,
    book_Id serial NOT NULL,
    user_id serial NOT NULL,
    text text NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "commentsBookfk" FOREIGN KEY (book_Id)
        REFERENCES book (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,

    constraint comments_ibfk_1
        foreign key(user_Id)
            references users(ID)
            on update cascade
);

CREATE TABLE requests
(
    id serial NOT NULL,
    status text NOT NULL,
    user_id serial NOT NULL,
    date date NOT NULL,
    name text NOT NULL,
    capacity serial NOT NULL,
    image text NOT NULL,
    description text NOT NULL,
    genres text[] NOT NULL,
    ISBN text UNIQUE NOT NULL,
    year_published text NOT NULL,
    published text NOT NULL,
    author text[] NOT NULL,

    primary key(id),

    constraint request_ibfk_1
        foreign key(user_Id)
            references users(ID)
            on update cascade
)