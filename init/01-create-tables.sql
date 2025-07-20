CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created TIMESTAMP NOT null default LOCALTIMESTAMP,
    updated TIMESTAMP not null default LOCALTIMESTAMP,
    created_by INT not null default 0,
    updated_by INT not null default 0
);
CREATE UNIQUE INDEX category_name_idx on category(name);

CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    date_from date not null,
    date_to date not null,
    category_id int not null,
    created TIMESTAMP not null default LOCALTIMESTAMP,
    updated TIMESTAMP not null default LOCALTIMESTAMP,
    created_by INT not null default 0,
    updated_by INT not null default 0,

    constraint category_fk foreign key(category_id) references category(id) ON DELETE CASCADE ON UPDATE CASCADE
);


create index task_category_id_idx on task(category_id);

CREATE TABLE IF NOT EXISTS app_user (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    created TIMESTAMP not null default LOCALTIMESTAMP,
    updated TIMESTAMP not null default LOCALTIMESTAMP,
    created_by INT not null default 0,
    updated_by INT not null default 0
);

CREATE TABLE IF NOT EXISTS app_role (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    created TIMESTAMP not null default LOCALTIMESTAMP,
    updated TIMESTAMP not null default LOCALTIMESTAMP,
    created_by INT not null default 0,
    updated_by INT not null default 0
);

CREATE TABLE IF NOT EXISTS app_user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    PRIMARY KEY (user_id, role_id),
    constraint user_fk foreign key(user_id) references app_user(user_id),
    constraint role_fk foreign key(role_id) references app_role(role_id)
);