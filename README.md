# 🔴 DOSW-2026-POKEDEX-CarlosRojas

> Pokédex individual desarrollada como parte del proyecto **DOSW · 2026 Intersemestral**.  
> Una herramienta que permite a un entrenador explorar, consultar y gestionar información de Pokémon de forma atractiva, ordenada y fácil de usar.

---

## 📋 Descripción del proyecto

Esta aplicación simula una Pokédex real inspirada en la franquicia Pokémon. Permite a los usuarios registrarse, explorar el catálogo completo de Pokémon, filtrarlos, guardarlos como favoritos, construir equipos competitivos y consultar estadísticas de uso. Los administradores pueden gestionar el catálogo y revisar propuestas de nuevos Pokémon enviadas por la comunidad.

El proyecto contempla autenticación segura, una interfaz responsiva y accesible, y una arquitectura por capas orientada a la mantenibilidad.

---

## Funcionalidades

| # | Funcionalidad |
|---|---------------|
| RF-01 | Registro con correo y contraseña |
| RF-02 | Verificación de correo electrónico |
| RF-03 | Inicio de sesión con Gmail (OAuth 2.0) |
| RF-04 | Recuperación de contraseña |
| RF-05 | Gestión del perfil de usuario |
| RF-06 | Gestión de datos base de Pokémon — CRUD (admin) |
| RF-07 | Envío de Pokémon propuesto por usuario a verificación |
| RF-08 | Revisión y aprobación de Pokémon enviados (admin) |
| RF-09 | Listado de Pokémon con paginación |
| RF-10 | Detalle completo de un Pokémon |
| RF-11 | Búsqueda de Pokémon por nombre o número |
| RF-12 | Filtros y ordenamiento del catálogo |
| RF-13 | Creación y gestión de equipos Pokémon |
| RF-14 | Análisis competitivo del equipo |
| RF-15 | Compartir equipo con enlace público |
| RF-16 | Guardar Pokémon favoritos |
| RF-17 | Métricas personales de uso |
| RF-18 | Dashboard global de métricas (admin) |
| RF-19 | Historial de actividad del usuario |
| RF-20 | Comparador de Pokémon lado a lado |

---

## Requerimientos no funcionales

| Código | Categoría | Descripción |
|--------|-----------|-------------|
| RNF-01 | Rendimiento | Listado de Pokémon carga en < 2 segundos |
| RNF-02 | Usabilidad | Búsqueda y consulta en ≤ 3 clics para usuario nuevo |
| RNF-03 | Compatibilidad | Funciona en Chrome, Firefox, Safari y Edge (versiones actuales) |
| RNF-04 | Seguridad | Contraseñas hasheadas; rutas privadas protegidas con JWT |
| RNF-05 | Accesibilidad | Cumple WCAG 2.1 nivel AA (Lighthouse score ≥ 90) |
| RNF-06 | Mantenibilidad | Arquitectura por capas; documentación en README |
| RNF-07 | Disponibilidad | Uptime ≥ 99% durante el período de evaluación |
| RNF-08 | Escalabilidad | Soporta ≥ 100 usuarios concurrentes sin degradación |
| RNF-09 | Responsividad | Adaptada a pantallas ≥ 360px (móvil y escritorio) |
| RNF-10 | Protección de datos | Sin exposición de PII en respuestas de API ni logs |

---

## 🔗 Enlaces

| Recurso | Enlace |
|---------|--------|
| 📌 Tablero Jira | [Ver tablero en Jira](#) |
| 🎨 Prototipo Figma | [Ver prototipo en Figma](https://almond-report-15011739.figma.site/) |
| 📄 Documento de requerimientos | [Ver documento DOSW](Docs/KaliMon_Requerimientos_DOSW.docx) |
| 🖼️ Manual de identidad | [Ver manual de identidad](Docs/KaliMon_Manual_Identidad.html) |

---

