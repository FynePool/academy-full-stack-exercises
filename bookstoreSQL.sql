select * from book;

create table category (
	category_id bigint,
    name varchar(45) not null,
    primary key (category_id)
);

create table author (
	author_id bigint unique,
    first_name varchar(45) not null,
    last_name varchar(45) not null,
    primary key (author_id)
);

create table publisher (
	publisher_id bigint unique,
    name varchar(255) not null,
    primary key (publisher_id)
);

create table book (
	book_id bigint unique,
    title varchar(255) NOT NULL,
    author_id bigint,
    price decimal(5,2),
    publisher_id bigint,
    category_id bigint,
    year smallint,
    primary key (book_id),
    foreign key (author_id) references author(author_id)
    on update cascade
    on delete no action,
    foreign key (publisher_id) references publisher(publisher_id)
    on update cascade
    on delete no action,
    foreign key (category_id) references category(category_id)
    on update cascade
    on delete cascade
);
    