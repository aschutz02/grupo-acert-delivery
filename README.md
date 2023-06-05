# grupo-acert-delivery
Teste técnico para o Grupo Acert

Para rodar a aplicação local, basta se conectar ao banco de dados sobreescrevendo as seguintes informações no arquivo application.properties:

	spring.datasource.url=jdbc:firebirdsql://localhost:3050//home/arthur/db/delivery.fdb
	spring.datasource.driver-class-name=org.firebirdsql.jdbc.FBDriver
	spring.jpa.database-platform=org.hibernate.dialect.FirebirdDialect
	spring.datasource.username=SYSDBA

spring.datasource.password=masterkey

E então rodar o sistema através de uma IDE, OU:

Baixar a imagem Docker do seguinte repositório no Docker Hub: https://hub.docker.com/repository/docker/arthurschutz/grupo-acert-delivery/general

docker pull arthurschutz/grupo-acert-delivery:1.0.0
___________________________________________________________________________________________________________
APIs do projeto

Cadastro:

POST /cadastro - Cadastrar um cliente

----------------------------------
Cliente:

	GET /cliente - Listar todos os clientes
	GET /cliente/{email) - Encontrar cliente pelo email
	PUT /cliente/{email) - Atualizar um cliente
	DEL /cliente/{email) - Deletar cliente pelo email

----------------------------------
Entrega:

	POST /entrega - Cadastrar uma entrega
	GET /entrega - Listar todas as entregas
	GET /entrega/{id) - Encontrar entrega pelo id
	PUT /entrega/{id) - Atualizar uma entrega
	DEL /entrega/{id) - Deletar entrega pelo id

----------------------------------
Login:

	POST /login/auth - Logar na aplicação

----------------------------------
Pedido:

	POST /pedido - Cadastrar um pedido
	GET /pedido - Listar todos os pedidos
	GET /pedido/{id) - Encontrar pedido pelo id
	PUT /pedido/{id) - Atualizar um pedido
	DEL /pedido/{id) - Deletar pedido pelo id

----------------------------------
Produto:

	POST /produto - Cadastrar um produto
	GET /produto - Listar todos os produtos
	GET /produto/{id) - Encontrar produto pelo id
	PUT /produto/{id) - Atualizar um produto
	DEL /produto/{id) - Deletar produto pelo id
___________________________________________________________________________________________________________
OpenAPI 3.0 - Swagger

Link para o swagger: http://localhost:8080/swagger-ui/#/
___________________________________________________________________________________________________________

O script usado para criar as tabelas no banco de dados Firebird foi:

	CREATE TABLE entregas (
			id BIGINT NOT NULL,
			cidade VARCHAR(255) NOT NULL,
			estado VARCHAR(255) NOT NULL,
			bairro VARCHAR(255) NOT NULL,
			cep VARCHAR(255) NOT NULL,
			numero INTEGER NOT NULL,
			complemento VARCHAR(255),
			pedido_id BIGINT UNIQUE NOT NULL,
			PRIMARY KEY (id)
	);

	CREATE TABLE produtos (
			id BIGINT NOT NULL,
			nome VARCHAR(255) NOT NULL,
			preco DECIMAL(10, 2) NOT NULL,
			pedido_id BIGINT NOT NULL,
			PRIMARY KEY (id)
	);

	CREATE TABLE clientes (
			id BIGINT NOT NULL,
			email VARCHAR(255) NOT NULL,
			senha VARCHAR(255) NOT NULL,
			pedido_id BIGINT NOT NULL,
			PRIMARY KEY (id)
	);

	CREATE TABLE pedidos (
			id BIGINT NOT NULL,
			valor_total DECIMAL(10, 2) NOT NULL,
			produto_id BIGINT NOT NULL,
			PRIMARY KEY (id)
	);

	ALTER TABLE clientes
	ADD CONSTRAINT pedido_id_foreign_key_clientes
	FOREIGN KEY (pedido_id) REFERENCES pedidos (id);

	ALTER TABLE pedidos
	ADD CONSTRAINT produto_id_foreign_key_pedidos
	FOREIGN KEY (produto_id) REFERENCES produtos (id);

	ALTER TABLE entregas
	ADD CONSTRAINT pedido_id_foreign_key_entregas
	FOREIGN KEY (pedido_id) REFERENCES pedidos (id);
___________________________________________________________________________________________________________

Observações:

- Na aba das "Issues" e "Milestones" é possível ver como quebrei o desafio em partes menores e me organizei a partir disso.
- Por simplicidade, uma vez que as Services e Controller tem praticamente os mesmos métodos, criei 2 classes de teste: ProdutoServiceTest (unitário) e CadastroControllerTestIT (integração)
- Os nomes das atributos, métodos e classes estão em português para que ficasse bem claro a nomenclatura dos mesmos.
- Alguns trechos de código estão comentados nas entidades pois a configuração de relacionamento entre as entidades não ficou bem estabelecida, de todas eu formas eu preferi deixar para mostrar o que considero o "ideal" nessa etapa de modelagem do banco de dados.
