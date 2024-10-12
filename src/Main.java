import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("************************************");
        System.out.println("""
                Sea bienvenid@ al Conversor de Monedas \n
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Dólar =>> Peso colombiano
                5) Peso colombiano =>> Dólar
                7) Salir
                Elija una opción válida.
                """);
        System.out.println("************************************");

        Scanner keyboard = new Scanner(System.in);
        ConsultaDeApi consultaDeApi = new ConsultaDeApi();
        String opcion;

        while (true) {
            try {
                System.out.print("Ingrese su opción: ");
                opcion = keyboard.nextLine();

                if (opcion.equals("7")) {
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
                    break;
                }

                System.out.print("Ingrese la cantidad que desea convertir: ");
                double cantidad = Double.parseDouble(keyboard.nextLine());

                double resultado = consultaDeApi.realizarConversion(opcion, cantidad);
                System.out.println("El resultado de la conversión es: " + resultado);

            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            } catch (RuntimeException e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }
}
