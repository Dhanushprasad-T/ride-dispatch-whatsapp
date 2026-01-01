# 🚕 Ride Dispatch System – Backend (Java + Spring Boot)

A real-world backend system that simulates Uber/Rapido style ride dispatching.  
This project focuses on **backend logic realism** — efficiently matching riders with the nearest available driver using **graphs, BFS, Dijkstra & Priority Queue – distance-optimized dispatch engine**, and storing persistent data via MySQL.

---

## 🎯 Objective

Design and implement a scalable backend system capable of:
- Storing drivers, ride requests & current availability
- Matching users to the nearest driver efficiently
- Handling full ride lifecycle → *Requested → Driver Assigned → Ongoing → Completed*

---

## 🧠 Core Backend Features

| Feature | Description |
|--------|-------------|
| 🚕 Driver Matching | Nearest driver selection using BFS + Dijkstra + Priority Queue |
| 👤 User / Driver | CRUD, authentication optional (scope-expandable) |
| 🛣️ Route Algorithm | Multi-city routing simulation |
| 📊 Persistence | MySQL database for entities |
| 🌐 REST API | Spring Boot REST Controllers |
| 🧮 Simulation Mode | View output directly in browser / Postman |

---

## 🛠️ Tech Stack

| Component | Technology |
|----------|------------|
| Language | Java |
| Backend | Spring Boot |
| Algorithm | BFS, Dijkstra, PriorityQueue |
| Database | MySQL |
| Tools | Maven, Postman |
| Repo | `github.com/Dhanushprasad-T/ride-dispatch-whatsapp` |

---

## 🏗️ System Architecture

```mermaid
flowchart TD
User -->|Ride Request| REST[Spring Boot REST API]
REST --> Service[Dispatch Service]
Service --> Algo[Dijkstra + BFS Engine]
Service --> DB[(MySQL Database)]
Algo --> DriverPool[Driver List in Memory]
DriverPool --> REST
