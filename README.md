# 📚 Aplicación de Gestión de Literatura 📚

Una herramienta robusta para explorar y gestionar tu mundo literario.

---

## 📖 Descripción del Proyecto

Esta es una aplicación de consola desarrollada con **Spring Boot**, diseñada para interactuar con la vasta **API de Gutendex** y gestionar una colección personal de libros y autores. Permite a los usuarios buscar y registrar nuevas obras, así como realizar consultas sobre autores vivos en un año específico y listar libros por idioma almacenados en una base de datos **PostgreSQL**.

Diseñada con la modularidad y la escalabilidad en mente, esta aplicación sirve como una base sólida para proyectos de gestión de datos literarios.

---

## ✨ Funcionalidades Destacadas

La aplicación ofrece un menú interactivo con las siguientes capacidades:

* **🔍 Búsqueda y Registro de Libros:** Permite buscar libros por título a través de la API de Gutendex y guardar sus metadatos (título, autor(es), idioma, número de descargas) en la base de datos local. Si un autor no existe, se registra automáticamente.
* **🌱 Listado de Autores Vivos:** Consulta y presenta una lista de autores que estuvieron vivos en un año específico, proporcionado por el usuario.
* **🗣️ Listado de Libros por Idioma:** Filtra y muestra todos los libros registrados en la base de datos según el código de idioma ingresado por el usuario (ej. `es` para español, `en` para inglés).

---

## 🛠️ Tecnologías y Herramientas

Este proyecto ha sido construido utilizando las siguientes tecnologías y herramientas:

* **Java 17**: Lenguaje de programación principal, aprovechando sus últimas características.
* **Spring Boot 3.x**: El framework líder para el desarrollo rápido de aplicaciones Java.
* **Spring Data JPA**: Abstracción para simplificar la interacción con la base de datos.
* **Hibernate**: Implementación de JPA para mapeo objeto-relacional (ORM).
* **PostgreSQL**: Base de datos relacional utilizada para persistir los datos.
* **Maven**: Herramienta de automatización de construcción y gestión de dependencias.
* **API de Gutendex**: Fuente externa de datos bibliográficos.
* **Jackson**: Librería fundamental para el procesamiento y mapeo de datos JSON desde la API externa a objetos Java.

---

## 🚀 Cómo Ejecutar el Proyecto

Para poner en marcha esta aplicación en tu entorno local, sigue los siguientes pasos:

### 📋 Prerrequisitos
Asegúrate de tener instalado lo siguiente:
* **Java Development Kit (JDK) 17** o una versión superior.
* **Apache Maven 3.x** o una versión superior.
* Un servidor de base de datos **PostgreSQL** en ejecución.

### ⚙️ Configuración de la Base de Datos
1.  **Crea una base de datos** en PostgreSQL (por ejemplo, `literatura_db`).
2.  Abre el archivo `src/main/resources/application.properties` en tu proyecto.
3.  **Actualiza las propiedades de conexión** con tus credenciales de PostgreSQL:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literatura_db
    spring.datasource.username=tu_usuario_postgres
    spring.datasource.password=tu_contraseña_postgres
    spring.jpa.hibernate.ddl-auto=update # Puedes usar 'create' la primera vez para crear las tablas
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```

### 🏃‍♂️ Compilación y Ejecución
1.  **Clona este repositorio** en tu máquina local usando Git:
    ```bash
    git clone [https://github.com/](https://github.com/)[tu_usuario_github]/spring-boot-challenge-literatura.git
    ```
2.  **Navega al directorio raíz** del proyecto en tu terminal:
    ```bash
    cd spring-boot-challenge-literatura
    ```
3.  **Compila y ejecuta la aplicación** utilizando Maven:
    ```bash
    mvn spring-boot:run
    ```
    Alternativamente, puedes ejecutar la clase `LiteraturaApplication.java` directamente desde tu IDE (IntelliJ IDEA).

Una vez iniciada la aplicación, se presentará un menú en la consola para interactuar con las diferentes funcionalidades.

---

## 🧠 Desafíos y Aprendizajes Clave

El desarrollo de este proyecto fue una experiencia enriquecedora, abordando y superando varios desafíos técnicos:

* **Consultas JPA Personalizadas (`@Query`):** Implementación de consultas JPQL avanzadas para manejar lógicas complejas, como la de identificar autores vivos en un rango de años, utilizando correctamente los parámetros con `@Param`.
* **Manejo de Relaciones Complejas (`@ManyToMany`):** Configuración y gestión de la relación muchos-a-muchos entre libros y autores, asegurando la persistencia y recuperación correcta de datos interconectados.
* **Validación de Entrada de Usuario:** Implementación de mecanismos de validación robustos para las entradas de usuario, garantizando que la aplicación procese solo datos válidos y mejore la experiencia del usuario.
* **Integración con API REST:** Consumo efectivo de la API de Gutendex, incluyendo el parseo de respuestas JSON y la transformación de datos externos a los modelos internos de la aplicación.
* **Solución de Problemas con Git:** Manejo de historiales de Git divergentes en el primer `push` a un repositorio existente, utilizando comandos como `git pull --allow-unrelated-histories` o `git push --force-with-lease` de forma segura.

---

## 👨‍💻 Autor

* Javier Marchena Gallo*
* **GitHub**: https://github.com/marchena290
* **LinkedIn**: www.linkedin.com/in/javier-marchena-gallo-748b64343

---
