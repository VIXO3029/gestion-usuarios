📝 Sistema de Gestión de Usuarios con MySQL y Java
Java
MySQL
GitHub

Aplicación de consola en Java para gestionar usuarios en una base de datos MySQL, implementando operaciones CRUD completas.

🚀 Características Principales
CRUD Completo: Create, Read, Update, Delete de usuarios

Interfaz de Consola: Menú interactivo fácil de usar

Conexión a MySQL: Usando JDBC y el patrón DAO

Validaciones: Comprobación de datos de entrada

Manejo de Errores: Control de excepciones SQL

📋 Requisitos Previos
Componente	Versión	Descarga
Java JDK	17+	Oracle
MySQL	8.0+	MySQL o XAMPP
MySQL Connector/J	8.0+	Descargar
🛠 Configuración Inicial
Clonar el repositorio:

bash
Copy
git clone https://github.com/VIXO3029/gestion-usuarios.git
cd gestion-usuarios
Configurar la base de datos:

sql
Copy
CREATE DATABASE usuarios;
USE usuarios;
Importar en NetBeans:

Abrir NetBeans > File > Open Project

Seleccionar la carpeta del proyecto

🖥️ Uso del Sistema
Ejecuta la clase principal:

bash
Copy
src/gestionusuarios/Main.java
Menú principal:

Copy
=== MENÚ PRINCIPAL ===
1. Mostrar todos los usuarios
2. Agregar nuevo usuario
3. Actualizar usuario
4. Eliminar usuario
5. Buscar usuario por ID
0. Salir
🗃️ Estructura del Proyecto
Copy
gestion-usuarios/
├── src/
│   └── gestionusuarios/
│       ├── Usuario.java       # Modelo de datos
│       ├── Database.java      # Conexión y operaciones CRUD
│       └── Main.java          # Interfaz de usuario
├── lib/
│   └── mysql-connector-java-8.0.28.jar
├── nbproject/                 # Configuración de NetBeans
└── README.md                  # Este archivo
📝 Licencia
Este proyecto está bajo la licencia MIT.

⌨️ Desarrollado por VIXO3029 con ❤️

🌟 Características Avanzadas
Patrón DAO: Separación clara entre lógica de negocio y acceso a datos

Try-with-resources: Manejo automático de recursos

Prepared Statements: Protección contra inyección SQL

Manejo de transacciones: Operaciones atómicas

🐛 Reporte de Problemas
Si encuentras algún error, por favor abre un issue en GitHub.

🤝 Contribuciones
Las contribuciones son bienvenidas. Haz fork del proyecto y envía un pull request.

💡 Tip: Para desarrollo local, configura tu archivo Database.java con tus credenciales de MySQL:

java
Copy
private static final String URL = "jdbc:mysql://localhost:3306/usuarios";
private static final String USER = "tu_usuario";
private static final String PASS = "tu_contraseña";
