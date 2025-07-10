CREATE SCHEMA IF NOT EXISTS kfk;

CREATE TABLE kfk.envio (
    id SERIAL PRIMARY KEY,
    compra_id BIGINT,
    tracking_code VARCHAR(255),
    is_delivered BOOLEAN
);

CREATE TABLE kfk.factura (
    id SERIAL PRIMARY KEY,
    compra_id BIGINT,
    tipo_factura VARCHAR(255),
    numero_cae VARCHAR(255)
);

CREATE TABLE kfk.compra (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255),
    price NUMERIC,
    envio_id BIGINT,
    factura_id BIGINT,
    CONSTRAINT fk_envio FOREIGN KEY (envio_id) REFERENCES kfk.envio(id),
    CONSTRAINT fk_factura FOREIGN KEY (factura_id) REFERENCES kfk.factura(id)
);