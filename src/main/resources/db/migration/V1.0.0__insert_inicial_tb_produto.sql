DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM orders.tb_produto WHERE nome = 'Agua') THEN
        insert into orders.tb_produto(nome,preco) values ('Agua', 2.50);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM orders.tb_produto WHERE nome = 'Refrigerante') THEN
        insert into orders.tb_produto(nome,preco) values ('Refrigerante', 3.50);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM orders.tb_produto WHERE nome = 'Cerveja') THEN
        insert into orders.tb_produto(nome,preco) values ('Cerveja', 5.0);
    END IF;
END $$;
