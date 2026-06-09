# Praxis

A full-stack behavioral psychology experiment platform built to showcase production-ready full-stack development across a Spring Boot backend and a React frontend.

Participants complete experiments drawn from peer-reviewed behavioral science literature, submit their responses, and receive immediate feedback alongside aggregated response distributions from all previous participants — making the dataset richer with every session.

---

![Praxis Demo](docs/screenshots/demo.gif)

## 🧪 Experiments

| Experiment | Type | Source |
|---|---|---|
| **Cognitive Reflection Test** | Free-text numeric input | Frederick, S. (2005). *Journal of Economic Perspectives* |
| **Asian Disease Problem** | Single choice | Tversky & Kahneman (1981). *Science* |
| **Ultimatum Game** | Slider | Güth et al. (1982). *Journal of Economic Behavior* |

### Architectural Note | Answer Distribution
`answerDistribution` exposes pre-aggregated response data across **all user sessions**. Every time a participant completes a question, their answer is recorded and immediately reflected in the distribution chart shown to subsequent users. As the dataset grows, each question reveals genuine behavioral patterns rather than isolated individual responses.

---

## 🛠️ Architecture & Design

```mermaid
graph TD
    classDef default fill:#ffffff,stroke:#000000,stroke-width:1px,color:#000000;
    classDef primary fill:#f5f500,stroke:#000000,stroke-width:2px,color:#000000;

    Frontend[React Frontend]:::primary --> API[REST API]

    subgraph Backend
        API --> Controller[Controllers]
        Controller --> Service[Service Layer]
        Service --> Repository[JPA Repositories]
        Repository --> DB[(PostgreSQL)]
    end

    subgraph Frontend
        Pages[Pages] --> Hooks[Custom Hooks]
        Hooks --> Client[Axios API Client]
        Pages --> Components[Components]
    end
```

### Backend
The backend follows a strict layered architecture with full separation of concerns:

- **Entities → DTOs** — domain model never exposed directly; dedicated request/response DTOs for every endpoint
- **Service layer** — all business logic isolated from controllers; DTO mapping via Java streams
- **Global exception handling** — `@ControllerAdvice` with `ResourceNotFoundException` for consistent error responses
- **Seed guard** — `CommandLineRunner` with per-experiment `existsByName()` check prevents duplicate seeding on restart

### Frontend
- **Custom hooks** — `useExperiment` and `useResult` encapsulate all API calls and state management
- **Conditional rendering** — `QuestionType` enum drives input component selection (`FreeTextInput`, `SingleChoiceInput`, `SliderInput`)
- **CSS Modules** — scoped styles per component, zero global leakage

---

## 🗄️ Data Model

```mermaid
erDiagram
    Experiment ||--o{ Question : "contains"
    Experiment ||--o{ UserSession : "hosts"
    Question ||--o{ QuestionOption : "has"
    Question ||--o{ UserAnswer : "receives"
    UserSession ||--o{ UserAnswer : "records"

    Experiment {
        long id PK
        string name
        string description
        boolean hasScore
    }

    Question {
        long id PK
        long experiment_id FK
        string text
        string questionType
        string correctAnswer
        string intuitiveAnswer
        string explanation
        int sliderMin
        int sliderMax
        int sliderStep
    }

    QuestionOption {
        long id PK
        long question_id FK
        string text
    }

    UserSession {
        long id PK
        long experiment_id FK
        datetime createdAt
    }

    UserAnswer {
        long id PK
        long session_id FK
        long question_id FK
        string answerGiven
        boolean isCorrect
    }
```

---

## 📸 Screenshots

### Home — Experiment Selection
![Home](docs/screenshots/home.png)

### Experiment — Cognitive Reflection Test
![CRT](docs/screenshots/crt.png)

### Experiment — Asian Disease Problem
![ADP](docs/screenshots/adp.png)

### Experiment — Ultimatum Game
![Ultimatum](docs/screenshots/ultimatum.png)

### Results Page
![Results](docs/screenshots/results.png)

---

## 🔧 Getting Started

### Prerequisites
- **Java 26**
- **Maven**
- **Node.js 18+**
- **Docker**

### Backend

1. Clone the repository:
   ```bash
   git clone https://github.com/SydBrain/praxis.git
   cd praxis
   ```

2. Start PostgreSQL via Docker:
   ```bash
   docker-compose up -d
   ```

3. Run the backend:
   ```bash
   ./mvnw spring-boot:run
   ```

   On first startup, the `CommandLineRunner` seeds all three experiments automatically.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend runs on `http://localhost:5173` and proxies API calls to `http://localhost:8080`.

---

## 🧰 Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 4.x, Java 26, JPA/Hibernate, Lombok |
| Database | PostgreSQL 16 (Docker) |
| Frontend | React, TypeScript, Vite, Tailwind CSS v4 |
| UI | Framer Motion, Recharts, CSS Modules |
| Fonts | Bebas Neue, IBM Plex Mono |

