CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created date NOT null default CURRENT_DATE,
    updated date not null default CURRENT_DATE,
    created_by INT not null default 0,
    updated_by INT not null default 0
);


CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    date_from date not null,
    date_to date not null,
    category_id int not null,
    created date not null default CURRENT_DATE,
    updated date not null default CURRENT_DATE,
    created_by INT not null default 0,
    updated_by INT not null default 0,

    constraint category_fk foreign key(category_id) references category(id)
);


create index task_category_id_idx on task(category_id);