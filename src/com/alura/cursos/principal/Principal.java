package com.alura.cursos.principal;

import com.alura.cursos.modelos.ConsultaMoneda;
import com.alura.cursos.modelos.Moneda;

import java.util.Scanner;

public class Principal {

    private static final String menu = """
            ***********************************************                
            Sea Bienvenido/a Al Conversor de Moneda =]
                                
            1) Dolar ==> Peso Argentino
            2) Peso Argentino ==> Dolar
            3) Dolar ==> Real Brasileño
            4) Real Brasileño ==> Dolar
            5) Dolar ==> Peso Colombiano
            6) Peso Colombiano ==> Dolar
            7) Salir
            Elija una opcion valida:
            ***********************************************
            """;
    private static final String MSJ_INGRESE_CANTIDAD = "Ingrese el valor o cantidad que desea convertir";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        while (true) {
            System.out.println(menu);
            int opcion = leerOpcionValida(sc);

            if (opcion == 7) {
                System.out.println("Saliendo del conversor. ¡Gracias!");
                break;
            }
            System.out.println(MSJ_INGRESE_CANTIDAD);
            double cantidad = leerCantidadValida(sc);
            ejecutarConversion(opcion, consulta, cantidad);
        }
    }

    private static int leerOpcionValida(Scanner sc) {
        int opcion = -1;
        while (true) {
            try {
                opcion = Integer.valueOf(sc.nextLine());
                if (opcion >= 1 && opcion <= 7) {
                    break;
                } else {
                    System.out.println("Por favor, elija una opción válida (1-7).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
        return opcion;
    }

    private static double leerCantidadValida(Scanner sc) {
        double cantidad = 1;
        while (true) {
            try {
                cantidad = Integer.valueOf(sc.nextLine());
                if (cantidad > 0) {
                    break;
                } else {
                    System.out.println("La cantidad debe ser mayor que cero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un valor numérico.");
            }
        }
        return cantidad;
    }

    private static void ejecutarConversion(int opcion, ConsultaMoneda consulta, double cantidad) {
        String base = "", codigo = "";
        switch (opcion) {
            case 1 -> {
                base = "USD";
                codigo = "ARS";
            }
            case 2 -> {
                base = "ARS";
                codigo = "USD";
            }
            case 3 -> {
                base = "USD";
                codigo = "BRL";
            }
            case 4 -> {
                base = "BRL";
                codigo = "USD";
            }
            case 5 -> {
                base = "USD";
                codigo = "COP";
            }
            case 6 -> {
                base = "COP";
                codigo = "USD";
            }
        }

        Moneda moneda = consulta.buscaMoneda(base, codigo, cantidad);
        System.out.println("El valor "+cantidad+" ["+moneda.base_code()+"] corresponde al valor final de ==> "+
                moneda.conversion_result()+" ["+moneda.target_code()+"]");

    }
}
