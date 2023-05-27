package juegoPalitosA;
import java.util.Scanner;

public class juegoDelospalitos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido al juego de los palitos!");
        System.out.println("Seleccione quién inicia primero:");
        System.out.println("1. Jugador");
        System.out.println("2. Computadora");

        int opcion = scanner.nextInt();
        boolean jugadorPrimero = opcion == 1;

        int palitosRestantes = 10;

        while (palitosRestantes > 0) {
            if (jugadorPrimero) {
                System.out.print("Palitos restantes: " + palitosRestantes);
                System.out.print("\nSeleccione el número de palitos a remover (1, 2 o 3): ");
                int palitosJugador = scanner.nextInt();
                palitosRestantes -= palitosJugador;
                System.out.println("El jugador ha removido " + palitosJugador + " palitos.");
            } else {
                System.out.println("Turno de la computadora...");
                int palitosComputadora = calcularMovimientoComputadora(palitosRestantes);
                palitosRestantes -= palitosComputadora;
                System.out.println("La computadora ha removido " + palitosComputadora + " palitos.");
            }

            jugadorPrimero = !jugadorPrimero;
        }

        if (jugadorPrimero) {
            System.out.println("¡Has ganado!");
        } else {
            System.out.println("La computadora ha ganado.");
        }
    }

    private static int calcularMovimientoComputadora(int palitosRestantes) {
        // Implementación del algoritmo minimax con poda alfa-beta
        int mejorMovimiento = 0;
        int mejorValor = Integer.MIN_VALUE;
        int alfa = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (int i = 1; i <= 3; i++) {
            int valor = minimax(palitosRestantes - i, false, alfa, beta);
            if (valor > mejorValor) {
                mejorValor = valor;
                mejorMovimiento = i;
            }
            alfa = Math.max(alfa, mejorValor);
        }

        return mejorMovimiento;
    }

    private static int minimax(int palitosRestantes, boolean esTurnoJugador, int alfa, int beta) {
        if (palitosRestantes <= 0) {
            return (esTurnoJugador) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        if (esTurnoJugador) {
            int mejorValor = Integer.MIN_VALUE;
            for (int i = 1; i <= 3; i++) {
                int valor = minimax(palitosRestantes - i, false, alfa, beta);
                mejorValor = Math.max(mejorValor, valor);
                alfa = Math.max(alfa, mejorValor);
                if (alfa >= beta) {
                    break;
                }
            }
            return mejorValor;
        } else {
            int mejorValor = Integer.MAX_VALUE;
            for (int i = 1; i <= 3; i++) {
                int valor = minimax(palitosRestantes - i, true, alfa, beta);
                mejorValor = Math.min(mejorValor, valor);
                beta = Math.min(beta, mejorValor);
                if (alfa >= beta) {
                    break;
                }
            }
            return mejorValor;
        }
    }
}
