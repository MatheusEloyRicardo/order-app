DO $schemaorders$
BEGIN
    CREATE SCHEMA IF NOT EXISTS orders;
END $schemaorders$;

DO $criaçãotabelas$
BEGIN
	CREATE SEQUENCE orders.sq_pedido
		START WITH 1
		INCREMENT BY 1
		MINVALUE 1
		MAXVALUE 9223372036854775807
		CACHE 1;

	CREATE TABLE orders.tb_pedido (
		id BIGINT NOT NULL DEFAULT nextval('orders.sq_pedido') CONSTRAINT pk_pedido PRIMARY KEY UNIQUE,
		total NUMERIC(10, 2) NOT NULL CHECK (total >= 0),
        status SMALLINT DEFAULT 0,
        data_criacao DATE NOT NULL DEFAULT CURRENT_DATE,
        hora_criacao TIME NOT NULL DEFAULT CURRENT_TIME
	);
	COMMENT ON COLUMN orders.tb_pedido.status IS '0: Iniciado, 1: Pendente, 2: Enviado, 3: Entregue';

	CREATE SEQUENCE orders.sq_produto
		START WITH 1
		INCREMENT BY 1
		MINVALUE 1
		MAXVALUE 9223372036854775807
		CACHE 1;

	CREATE TABLE orders.tb_produto (
		id BIGINT NOT NULL DEFAULT nextval('orders.sq_produto') CONSTRAINT pk_produto PRIMARY KEY UNIQUE,
		nome CHARACTER VARYING(200) COLLATE pg_catalog."default" NOT NULL,
		preco NUMERIC(5, 2) NOT NULL CHECK (preco >= 0)
	);

	CREATE SEQUENCE orders.sq_pedido_produto
    		START WITH 1
    		INCREMENT BY 1
    		MINVALUE 1
    		MAXVALUE 9223372036854775807
    		CACHE 1;

    CREATE TABLE orders.tb_pedido_produto (
        id BIGINT NOT NULL DEFAULT nextval('orders.sq_pedido_produto') CONSTRAINT pk_pedido_produto PRIMARY KEY,
        id_pedido BIGINT NOT NULL,
        id_produto BIGINT NOT NULL,
        quantidade INT NOT NULL CHECK (quantidade > 0),
        CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES orders.tb_pedido(id),
        CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES orders.tb_produto(id)
    );

end $criaçãotabelas$;