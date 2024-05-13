CREATE TABLE companies
(
    id    SERIAL PRIMARY KEY UNIQUE,
    name  VARCHAR(255) not null,
    cnpj  VARCHAR(255) null,
    phone VARCHAR(255) null
);

CREATE TABLE users
(
    id          SERIAL PRIMARY KEY UNIQUE,
    name        VARCHAR(255) NOT NULL,
    email       TEXT         NOT NULL UNIQUE,
    password    TEXT         NOT NULL,
    last_access TIMESTAMP    NULL,
    created_at  TIMESTAMP    NULL,
    updated_at  TIMESTAMP    NULL,
    company_id  INTEGER      NOT NULL,
    role        TEXT         NOT NULL,
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE products
(
    id               SERIAL PRIMARY KEY UNIQUE,
    name             VARCHAR(255) NULL,
    description      VARCHAR(255) NULL,
    quantity         FLOAT(32)    NULL,
    price            FLOAT(32)    NULL,
    company_id       INTEGER      NOT NULL,
    author_id        INTEGER      NOT NULL,
    update_author_id INTEGER      NOT NULL,
    CONSTRAINT fk_update_author_id FOREIGN KEY (update_author_id) REFERENCES users (id),
    CONSTRAINT fk_user_id FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);