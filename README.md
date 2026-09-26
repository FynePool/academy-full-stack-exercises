# Full Stack Academy 22

Exercises and projects built during the Full Stack Academy 22 course: Java SE,
JDBC/JPA, Servlets/JSP, Spring Boot (MVC + REST), Angular, plain HTML/CSS/JS and
SQL against MySQL.

Each top-level folder is an independent project — there is no parent build.

## Requirements

| Tool | Version used |
| --- | --- |
| JDK | 17 |
| Maven | 3.x (Spring projects ship the `mvnw` wrapper) |
| Node.js + Angular CLI | Angular 14.2 (`npm i -g @angular/cli@14`) |
| MySQL | 8.x, with the `bookstore` and `sakila` schemas |
| Servlet container | Tomcat 9 / any Servlet 4.0 container (for `thelibrary-web`) |

## Projects

### `sitostatico` — static site (HTML/CSS/JS)
Plain front-end exercises: lists and tables, forms, a Bootstrap page (loaded from
CDN) and a set of standalone JavaScript scripts under `javascript/` (variables,
loops, arrays, objects, functions, form handling, factorial).
Open `sitostatico/index.html` in a browser.
`javascript/fattoriale.test.js` is a Jest test for `fattoriale.js`; Jest is not
wired up in this repo yet (`jest.config.js` is empty and there is no Jest
dependency), so install it locally to run it.

### `firstmavenpj` — Java SE exercises
Plain Maven project (`com.riccardorocco:firstmavenpj:1.0`) collecting the core
Java exercises:
- `AcademyExercises`, `TwoVariables`, `Shape` — language, collections, streams,
  date/time and recursion exercises, with JUnit 4 tests under `src/test`.
- `Disegno/` — inheritance exercise (`Figura` and its `Cerchio`, `Quadrato`,
  `Rettangolo`, `Triangolo` subclasses).
- `IO/` — file reading/writing, copying and filtering streams.
- `SakilaDB/` — JDBC, DBCP2 connection pooling and JPA/Hibernate access to the
  MySQL Sakila sample database (see `src/main/resources/META-INF/persistence.xml`).

```bash
cd firstmavenpj
mvn test
mvn exec:java -Dexec.mainClass=com.riccardorocco.AcademyExercises   # or run a class from the IDE
```

### `bookstore` — domain model, DAO layer, JDBC and JPA
Maven project `com.bookstore:bookstore:1.0` under `bookstore/bookstore`.
- Domain: `Book`, `Author`, `Publisher`, `BookCategory`, `BookStore`
  (JPA-annotated entities mapped to the `bookstore` schema).
- `Main` — console menu over an in-memory/CSV bookstore (`resources/books.csv`).
- `PersistentBookStore` — same model through the DAO layer.
- `dao/` — `DaoFactory` abstraction with two interchangeable implementations,
  `dao/jdbc` (plain JDBC + commons-dbutils) and `dao/jpa` (Hibernate); which one
  is returned is decided in `DaoFactoryCreator`.

```bash
cd bookstore/bookstore
mvn install          # required: bookstore-api depends on this artifact
```

### `thelibrary-web` — Servlet/JSP webapp
WAR project with a single `HelloServlet` mapped to `/hello` plus `index.jsp`.

```bash
cd thelibrary-web/thelibrary-web
mvn package          # target/thelibrary-web.war -> deploy to Tomcat
```

### `springapp` — Spring Boot MVC + Thymeleaf
Spring Boot 2.7.5 app, server-side rendered with Thymeleaf.
- `IndexController` — `/`, `/bookstore`, `/form`, `/liste`, `/books`.
- `GuessTheNumberController` — `/guessthenumber`, a session-scoped guessing game
  with bean validation and `/guessthenumber/reset`.
- `RemainingTriesController` — REST sample at `/api/v1/tentativi`.

```bash
cd springapp
./mvnw spring-boot:run       # http://localhost:8080
```

### `bookstore-api` — Spring Boot REST API
Spring Boot 2.7.5 + Spring Data JPA, exposing the `Author` entity reused from the
`bookstore` project. CORS is open to `http://localhost:4200` for the Angular client.

| Method | Path | Description |
| --- | --- | --- |
| GET | `/author` | list all authors |
| GET | `/author/{id}` | single author (404 via `AuthorNotFoundException`) |
| POST | `/author` | create an author |
| PUT | `/author/{id}` | update, or create with that id |
| DELETE | `/author/{id}` | delete, 404 if missing |

```bash
cd bookstore/bookstore && mvn install   # once, to publish the dependency
cd bookstore-api && ./mvnw spring-boot:run   # http://localhost:8080
```

### `firstangularpj/hello-cli` — first Angular app
Angular 14 CLI starter with a simple `ProductComponent`.

```bash
cd firstangularpj/hello-cli
npm install && npm start     # http://localhost:4200
```

### `bookstore-ng` — Angular client for the Bookstore API
Angular 14 + Bootstrap 5. Routed views (`/home`, `/authors`, `/authors/:id`) with
`AuthorService` calling the REST API on `http://localhost:8080`.
Start `bookstore-api` first.

```bash
cd bookstore-ng
npm install && npm start     # http://localhost:4200
npm test                     # Karma/Jasmine
```

## SQL scripts

| File | Purpose |
| --- | --- |
| `bookstoreSQL.sql` | `bookstore` schema: `book`, `author`, `publisher`, `category` |
| `insert_bookstore_data.sql` | mysqldump-style seed data for `bookstore` |
| `sql_exercises.sql` | query exercises against the MySQL **Sakila** sample database |
| `insert_fantozzi.sql` | inserts exercise (actors/films) into **Sakila** |

```bash
mysql -u root -p -e "CREATE DATABASE bookstore;"
mysql -u root -p bookstore < bookstoreSQL.sql
mysql -u root -p bookstore < insert_bookstore_data.sql
```

## Database configuration

The MySQL host, user and password are hardcoded in three places; change them to
match your local setup before running anything that touches the database:

- `bookstore/bookstore/src/main/java/com/bookstore/dao/DaoFactoryCreator.java`
- `firstmavenpj/src/main/resources/META-INF/persistence.xml`
- `bookstore-api/src/main/resources/application.properties`

> These files contain a committed development password. It is a local course
> database, but do not reuse that password anywhere real.

## Known rough edges

- `bookstore-ng` `AuthorService.editAuthor()` calls `https://localhost:8080/...`
  while every other call uses `http` — the PUT fails until that is fixed.
- The root `package.json` only pulls in Bootstrap; the static site actually loads
  Bootstrap from a CDN, so `npm install` at the root is not needed.
- API base URLs in the Angular client are hardcoded rather than read from
  `src/environments/`.
