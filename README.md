<img src="docs/banner.png" alt="Produtos API — Spring Boot" width="100%">

# Produtos API

API REST para catálogo de produtos e categorias, com paginação e mapeamento entre entidades e DTOs.

`Java` · `Spring Boot` · `Spring Data JPA` · `MySQL`

---

## O que esta API faz

Gerencia um catálogo onde produtos pertencem a categorias. Permite cadastrar, listar, buscar por identificador ou por nome e remover produtos, além de cadastrar categorias. As consultas que retornam listas usam paginação do Spring Data, e as entidades nunca são expostas diretamente — há uma camada de DTOs e um `Mapper` fazendo a conversão nas duas direções.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/api/produtos` | Lista os produtos, paginado |
| `POST` | `/api/add-produto` | Cadastra um produto |
| `GET` | `/api/procurar-produto-id?id=` | Busca um produto por identificador |
| `GET` | `/api/procurar-produto-nome?nome=` | Busca produtos por nome, paginado |
| `DELETE` | `/api/deletar-produto?id=` | Remove um produto |
| `POST` | `/api/add-categorias` | Cadastra uma categoria |

## Como rodar

**Pré-requisitos:** Java 17 ou superior e MySQL 8.

```bash
# 1. Clone
git clone https://github.com/BernardoMonnerat/ProdutoApi.git
cd ProdutoApi

# 2. Crie o banco
mysql -u root -p -e "CREATE DATABASE ebac;"

# 3. Informe as credenciais
export DB_URL="jdbc:mysql://localhost:3306/ebac?useSSL=false&serverTimezone=UTC"
export DB_USERNAME=root
export DB_PASSWORD=sua_senha

# 4. Suba a aplicação
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

> O `ddl-auto` está em `create-drop`: o esquema é recriado a cada inicialização e os dados são descartados ao desligar. É conveniente para desenvolvimento; para manter dados entre execuções, troque para `update`.

## Exemplo de uso

```bash
# Cadastrar uma categoria
curl -X POST http://localhost:8080/api/add-categorias \
  -H "Content-Type: application/json" \
  -d '{"nome":"Periféricos"}'

# Cadastrar um produto
curl -X POST http://localhost:8080/api/add-produto \
  -H "Content-Type: application/json" \
  -d '{"nome":"Teclado mecânico","preco":350.00}'

# Buscar por nome
curl "http://localhost:8080/api/procurar-produto-nome?nome=Teclado"
```

## Estrutura

```
src/main/java/br/com/ebac/productsApi/
├── Configuration/   Db — montagem do DataSource
├── Controller/      endpoints REST
├── Service/         GerenciaService — regras de negócio
├── Repository/      ProdutoRepository, CategoriaRepository
├── Model/           entidades Produto e Categoria
├── Dto/             contratos de entrada e saída
└── Utils/           Mapper — conversão entre entidade e DTO
```

## Roadmap

- [ ] Padronizar as respostas: alguns endpoints devolvem texto em vez de JSON
- [ ] Renomear `gerenciaController` seguindo a convenção de classes em Java
- [ ] Validação com Bean Validation nos DTOs de entrada
- [ ] Tratamento global de exceções
- [ ] Documentação com Swagger

## Licença

MIT — veja [LICENSE](LICENSE).
