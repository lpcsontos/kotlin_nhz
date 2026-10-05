Chat app backend (Ktor)
The backend of a chat application, written in Kotlin with Ktor as a university assignment. The user and authentication layer is finished; the chat itself (rooms and messages over WebSockets) is the next step.
> **Status:** work in progress. Registration, login, JWT authentication, profiles and rate limiting work; chat rooms are not implemented yet.

Features
Registration and login with JWT. A successful register or login returns a signed token (HMAC-SHA256, valid for 24 hours), which protected routes check through Ktor's `auth-jwt` plugin.
Password hashing with BCrypt. Passwords are pre-hashed with SHA-256 first, so long passwords are not silently cut off by BCrypt's 72-byte input limit.
Profiles behind unguessable links. Every user gets a random 50-character profile slug (generated with `SecureRandom`), so profiles can be opened by link but cannot be found by guessing usernames. Slugs are validated before they reach the database.
Rate limiting per IP address: 10 requests per minute on the auth endpoints, 20 per minute on profile lookups, and a global limit of 100 per minute.
Layered structure: routes → repository → service → database, with separate DTOs for requests and responses.
Tech stack
Kotlin · Ktor 3 (Netty) · Exposed ORM · MySQL · kotlinx.serialization · JWT (auth0 java-jwt) · jBCrypt · Gradle (Kotlin DSL)
API
Method	Endpoint	Auth	Description
POST	`/auth/register`	–	Create a user (`username`, `password`, `displayname`, `description`), returns a token
POST	`/auth/login`	–	Returns a token
GET	`/profile/{slug}`	Bearer token	Returns the display name and description of a user
Project structure
```
src/main/kotlin/dev/lpcsontos/k_nhz/
├── config/       plugins: database, JWT security, rate limits, serialization, WebSockets, logging
├── routes/       auth and protected routes
├── repository/   registration and login logic, token issuing
├── service/      database access and JWT generation
├── security/     password hashing, profile slug generation
├── db/           Exposed table definitions
└── dto/, model/  request and response types
```
Running locally
Requirements: JDK 17 or newer and a running MySQL server with an empty database.
Create a `.env` file in the project root based on `.env.example`:
```
   HOST=localhost
   PORT=8080
   DB_HOST=localhost
   DB_PORT=3306
   DB_NAME=chat
   DB_USER=...
   DB_PASS=...
   JWT_SECRET=<long random string>
   HASH_ROUNDS=10
   ```
Start the server:
```
   ./gradlew run
   ```
The `users` table is created automatically on first start. The server listens on http://localhost:8080.
Planned
Chat rooms and real-time messages over WebSockets (the WebSocket plugin and a first room table are already in place)
Editing the profile and changing the password
