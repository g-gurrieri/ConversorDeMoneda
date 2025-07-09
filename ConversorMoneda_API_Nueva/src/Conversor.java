import java.util.Map;
import java.util.Scanner;

public class Conversor {

    public static void exibirMenu() {
        System.out.println("""
                ****************************************************
                Sea bienvenido/a al Conversor de Moneda =]

                Seleccione una opción para convertir:

                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Salir

                Elija una opción válida:
                ****************************************************
                """);
    }

    public static void ejecutar(Scanner scanner, ConsultaMoneda consulta) {
        int opcion;
        do {
            exibirMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> convertir(scanner, consulta, "USD", "ARS");
                case 2 -> convertir(scanner, consulta, "ARS", "USD");
                case 3 -> convertir(scanner, consulta, "USD", "BRL");
                case 4 -> convertir(scanner, consulta, "BRL", "USD");
                case 5 -> convertir(scanner, consulta, "USD", "COP");
                case 6 -> convertir(scanner, consulta, "COP", "USD");
                case 7 -> System.out.println("¡Gracias por usar el conversor!");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 7);
    }

    private static void convertir(Scanner scanner, ConsultaMoneda consulta, String origen, String destino) {
        System.out.printf("Ingrese el monto en %s: ", origen);
        double monto = scanner.nextDouble();
        scanner.nextLine();

        Moneda moneda = consulta.obtenerMonedas(origen);

        if (moneda == null || moneda.getConversion_rates() == null || !moneda.getConversion_rates().containsKey(destino)) {
            System.out.println("Tasa no disponible para la conversión de " + origen + " a " + destino + ".");
            return;
        }

        double tasa = moneda.getConversion_rates().get(destino);
        double resultado = monto * tasa;

        System.out.printf("Resultado: %.2f %s => %.2f %s\n", monto, origen, resultado, destino);
    }
}