/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionusuarios;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Database.crearTablaSiNoExiste(); // Asegurar que la tabla existe
        testConnection();
        mostrarMenu();
    }

    private static void testConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = Database.getConnection()) {
                System.out.println("¡Conexión exitosa a MySQL!");
                System.out.println("Base de datos: " + conn.getCatalog());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado");
            System.err.println("Asegúrate de tener mysql-connector-java en lib/");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Mostrar todos los usuarios");
            System.out.println("2. Agregar nuevo usuario");
            System.out.println("3. Actualizar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Buscar usuario por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1 -> mostrarUsuarios();
                case 2 -> agregarUsuario();
                case 3 -> actualizarUsuario();
                case 4 -> eliminarUsuario();
                case 5 -> buscarUsuarioPorId();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void mostrarUsuarios() {
        System.out.println("\n--- LISTADO DE USUARIOS ---");
        List<Usuario> usuarios = Database.obtenerUsuarios();
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    private static void agregarUsuario() {
        System.out.println("\n--- AGREGAR USUARIO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        
        Usuario nuevo = new Usuario(nombre, email, edad);
        if (Database.insertarUsuario(nuevo)) {
            System.out.println("Usuario agregado con ID: " + nuevo.getId());
        } else {
            System.out.println("Error al agregar usuario");
        }
    }

    private static void actualizarUsuario() {
        System.out.println("\n--- ACTUALIZAR USUARIO ---");
        System.out.print("ID del usuario a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Usuario existente = Database.obtenerUsuarioPorId(id);
        if (existente == null) {
            System.out.println("Usuario no encontrado");
            return;
        }
        
        System.out.println("Datos actuales: " + existente);
        System.out.println("\nIngrese nuevos datos (deje en blanco para mantener):");
        
        System.out.print("Nuevo nombre [" + existente.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) existente.setNombre(nombre);
        
        System.out.print("Nuevo email [" + existente.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) existente.setEmail(email);
        
        System.out.print("Nueva edad [" + existente.getEdad() + "]: ");
        String edadStr = scanner.nextLine();
        if (!edadStr.isEmpty()) existente.setEdad(Integer.parseInt(edadStr));
        
        if (Database.actualizarUsuario(existente)) {
            System.out.println("Usuario actualizado");
        } else {
            System.out.println("Error al actualizar");
        }
    }

    private static void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");
        System.out.print("ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("¿Está seguro? (s/n): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("s")) {
            if (Database.eliminarUsuario(id)) {
                System.out.println("Usuario eliminado");
            } else {
                System.out.println("Error al eliminar");
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }

    private static void buscarUsuarioPorId() {
        System.out.println("\n--- BUSCAR USUARIO POR ID ---");
        System.out.print("ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Usuario usuario = Database.obtenerUsuarioPorId(id);
        if (usuario != null) {
            System.out.println("Usuario encontrado:");
            System.out.println(usuario);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
}