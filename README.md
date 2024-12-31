# bot_finanzas
<p align="center">
  <a href="https://spring.io/projects/spring-boot" target="blank"><img src="https://icons8.com/icon/90519/spring-boot" width="120" alt="Spring Boot Logo" /></a>
</p>

# Bot de telegram para gestionar finanzas personales

Este proyecto es una aplicación desarrollada con el framework Spring Boot diseñada para gestionar finanzas personales y tener un control de los gastos realizados al mes, identificándolos por el medio de pago. Implementa una arquitectura modular que incluye integración con MongoDB como base de datos y proporciona una API robusta y bien documentada para interactuar con los datos. Además, incluye integración con Telegram para notificaciones y gestión de gastos a través de un bot.

---

## 🚀 Guía para Ejecutar en Desarrollo

### Pasos iniciales:
1. **Clonar el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   ```
2. **Instalar las dependencias:**
   ```bash
   gradle build
   ```
3. **Configurar el archivo de entorno:**
    - Clonar el archivo `.env.template` y renombrarlo a `.env`.
    - Completar las variables necesarias en el archivo `.env`.

4. **Levantar la base de datos:**
   ```bash
   docker-compose --env-file .env up -d
   ```
   > Este comando iniciará un contenedor con PostgreSQL utilizando la configuración de `docker-compose`.
   
---

## 🛠️ Documentación de Endpoints

La documentación de los endpoints está disponible en formato Swagger. Accede a ella desde:
```
http://localhost:3000/api#/
```
⚠️ TAMBIEN ⚠️ Dentro del directorio ```/data``` ubicado en la raiz principal del proyecto tenes 3 colecciones con los endpoints de la aplicación para importarlos en insomnia o postman

---

## 🧪 Ejecutar Tests

Para ejecutar los tests unitarios y de integración, usa el siguiente comando:
```bash

```

---

## 🛠️ Stack Tecnológico

- **Springboot**: Framework para Java.
- **MongoDB**: Base de datos no relacional.
- **Telegram**: App externa para registrar gastos mediante un chatbot

---

