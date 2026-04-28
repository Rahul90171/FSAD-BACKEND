# AchieveTrack Walkthrough: Enterprise-Grade Full-Stack Implementation

I have successfully developed the "AchieveTrack" platform end-to-end. The codebase is now production-ready, featuring a robust Spring Boot backend and an ultra-premium Next.js frontend with cinematic 3D effects.

## 1. Backend: Spring Boot & MongoDB
Located in `backend/`

> [!NOTE]
> The backend acts as a highly secure, high-performance RESTful API.

- **Security & Authentication**: Implemented fully stateless JWT-based authentication. Role-Based Access Control (RBAC) securely separates `ROLE_ADMIN` and `ROLE_STUDENT` endpoints.
- **Models**:
  - `User`: Manages student and admin accounts.
  - `Achievement`: Tracks extracurricular activities, category, status (`PENDING`, `APPROVED`, `REJECTED`), and proof URLs.
- **Controllers**:
  - `AuthController`: Handles `/api/auth/register` and `/api/auth/login`.
  - `StudentController`: Handles `/api/student/achievements` for submission and tracking.
  - `AdminController`: Handles `/api/admin/achievements` for system-wide review and status updates.
- **Configuration**: MongoDB connection points to `mongodb://localhost:27017/achievetrack`. CORS is configured for seamless Next.js frontend integration.

## 2. Frontend: Next.js + Framer Motion + Three.js
Located in `frontend/`

> [!TIP]
> The UI employs cutting-edge Next.js App Router patterns with Tailwind CSS v4, Zustand, and React Three Fiber.

- **Cinematic Landing Page**:
  - `Hero`: Features a fully animated, interactive 3D abstract sphere (`ThreeScene.tsx`) leveraging `@react-three/drei` and `Three.js` over a dark futuristic background.
  - `Navbar`: A smooth, sticky, glassmorphic navigation bar that reacts to scrolling.
  - `Features`: A dynamic grid with custom animated neon-bordered cards highlighting platform capabilities.
  - `Footer`: A polished, enterprise-style footer with comprehensive platform links.
- **Authentication Flow**:
  - Custom `Login` and `Register` pages wrapped in smooth `framer-motion` page transitions, featuring animated background glows and comprehensive input fields.
  - State management handled persistently via `Zustand`.
- **Role-Based Dashboards**:
  - `Student Dashboard`: A data-rich interface showcasing submission history, current statuses, and summary counters for `Total`, `Approved`, and `Pending` achievements.
  - `Admin Dashboard`: An administrative command center tracking all student submissions globally, providing tools to review and approve/reject claims. Both share a reusable `Sidebar` component.

## 3. Global Styles & Theming
- Implemented a custom `globals.css` with a strictly enforced Dark Mode, utilizing `--background: #000000`, primary Neon Cyan (`#00f0ff`), and secondary Neon Purple (`#8b5cf6`).
- Created reusable classes like `.glass-panel` and `.glow-effect` for rapid deployment of premium aesthetics.

## How to Run

1. **Start MongoDB**: Ensure your local MongoDB instance is running on port `27017`.
2. **Start Backend**: Navigate to `backend/` and run `./mvnw spring-boot:run`. The backend will start on port `8080`.
3. **Start Frontend**: Navigate to `frontend/` and run `npm run dev`. The frontend will be available at `http://localhost:3000`.
