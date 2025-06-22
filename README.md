# 🌟 NewsApp – Spring Boot JWT News API

## Overview
A RESTful Spring Boot application to register, login, manage preferences, and fetch news via JWT-based authentication using H2 in-memory database.

## Features
- ✅ User Registration (`/api/auth/register`)
- ✅ Login with JWT issuance (`/api/auth/login`)
- ✅ JWT-secured endpoints for:
  - 🔹 GET `/api/preferences` – View preferences
  - 🔹 PUT `/api/preferences` – Update preferences
  - 🔹 GET `/api/news/fetch?category=...` – Fetch news based on preference or optional category
- ✅ Input validation via `@Valid`, exception handling via `@ControllerAdvice`
- ✅ H2 in-memory DB (with console enabled under `/h2-console`)
- ✅ External news integration using NewsAPI.org

## Running the Project
1. Clone the repository and switch to your branch:
   ```bash
   git checkout feedback

