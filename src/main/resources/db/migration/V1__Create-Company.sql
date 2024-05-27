CREATE TABLE companies
(
    id         SERIAL PRIMARY KEY UNIQUE,
    name       TEXT NOT NULL,
    created_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    cnpj       VARCHAR(255) DEFAULT NULL,
    phone      VARCHAR(255) DEFAULT NULL
);

CREATE TABLE users
(
    id          SERIAL PRIMARY KEY UNIQUE,
    name        VARCHAR(255) NOT NULL,
    email       TEXT         NOT NULL UNIQUE,
    password    TEXT         NOT NULL,
    last_access TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    company_id  INTEGER      NOT NULL,
    role        TEXT         NOT NULL,
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE products
(
    id               SERIAL PRIMARY KEY UNIQUE,
    name             TEXT    NOT NULL,
    description      VARCHAR(255) DEFAULT NULL,
    quantity         FLOAT(32)    DEFAULT 0,
    price            FLOAT(32)    DEFAULT 0,
    created_at       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    company_id       INTEGER NOT NULL,
    author_id        INTEGER NOT NULL,
    update_author_id INTEGER NOT NULL,
    CONSTRAINT fk_update_author_id FOREIGN KEY (update_author_id) REFERENCES users (id),
    CONSTRAINT fk_user_id FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE customized_fields
(
    id               SERIAL PRIMARY KEY UNIQUE,
    active           BOOLEAN   DEFAULT TRUE,
    name             TEXT    NOT NULL,
    label            TEXT    NOT NULL,
    obligatory       BOOLEAN   DEFAULT FALSE,
    type             INTEGER NOT NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    company_id       INTEGER NOT NULL,
    author_id        INTEGER NOT NULL,
    update_author_id INTEGER NOT NULL,
    CONSTRAINT fk_update_author_id FOREIGN KEY (update_author_id) REFERENCES users (id),
    CONSTRAINT fk_user_id FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE product_customized_field
(
    product_id          INTEGER,
    customized_field_id INTEGER,
    value               TEXT,
    PRIMARY KEY (product_id, customized_field_id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (customized_field_id) REFERENCES customized_fields (id)
);