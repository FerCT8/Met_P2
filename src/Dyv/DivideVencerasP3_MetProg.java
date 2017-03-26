package Dyv;

import utilidades.*;

public class DivideVencerasP3_MetProg {

    public static void main(String[] args) {

        System.out.println("Practica 3- Metodologia de la programación- Divide y venceras");

        apartado1();

        apartado2();

    }

    public static void apartado1() {
        char repetir;

        do {
            int[] vPisos = {10, 100, 1000, 10000, 100000, 1000000};
            int[] vLads = {1, 2, 10, 100, 1000};
            int auxVar;
            long tInicio, tFinal;

            char medida = leer.caracter("¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");

            for (int i = 0; i < vPisos.length; i++) {

                auxVar = calcularAleatorios(i, vPisos);

                for (int j = 0; j < vLads.length; j++) {
                    System.out.println("Para K ladrillos y N pisos.\n\n" + vLads[j] + vPisos[i]);

                    try {
                        tInicio = obtenerTiempo(medida);
                        recursivo(vLads[j], 0, vPisos[i], new Ladrillo(auxVar));
                        tFinal = obtenerTiempo(medida);
                        long tTotal = tFinal - tInicio;
                        System.out.println("El algoritmo ha tardado" + tTotal);
                    } catch (StackOverflowError s) {
                        System.out.println("ERROR: La pila se ha desbordado -> " + s.getMessage());
                    }
                    System.out.println("\n\n");
                }
            }
            repetir = Character.toUpperCase(leer.caracter("¿Quieres repetir la prueba con otra unidad de tiempo? (S=si/N=no)"));
        } while (repetir == 'S');
    }

    public static void apartado2() {

        char c;
        do {

            int npisos = leer.entero("Indique el número de pisos:");

            while (npisos < 1) {
                System.out.println("\nERROR. Debe haber al menos un piso.");
                npisos = leer.entero("Vuelva a introducir el dato:");
            }

            System.out.printf("El numero óptimo de ladrillos para %d pisos es %d. ",
                    npisos, logaritmo(npisos));

            c = leer.caracter("¿Quiere repetir esta prueba? Si/No");

        } while (c == 'S' || c == 's');

    }

    public static void recursivo(int nLadrillos, int cotaInferior, int cotaSuperior, Ladrillo lad) {

        if (nLadrillos == 1) {

            if (roto(lad, cotaInferior)) {
                System.out.println("El ultimo ladrillo consiguio alcanzar su maxima resistencia en el piso: " + cotaInferior);
            } else {
                cotaInferior = cotaInferior + 1;
                recursivo(nLadrillos, cotaInferior, cotaSuperior, lad);
            }

        } else {

            int piso = calculoPiso(cotaInferior, cotaSuperior);

            if (roto(lad, piso)) {
                nLadrillos = nLadrillos - 1;
                if (piso == cotaInferior || piso == cotaSuperior) {
                    System.out.println("El ladrillo consiguio alcanzar su maxima resistencia en el piso " + piso);
                } else {
                    recursivo(nLadrillos, cotaInferior, piso, lad);
                }
            } else {
                recursivo(nLadrillos, piso, cotaSuperior, lad);

            }
        }
    }

    public static boolean roto(Ladrillo l, int piso) {
        return l.getAlturaMax() <= piso;
    }

    public static int calculoPiso(int ci, int cs) {
        int altura = ci + ((cs - ci) / 2);
        if (altura == ci) {
            return cs;
        } else {
            return altura;
        }
    }

    public static int logaritmo(int n) {
        return (int) Math.ceil((Math.log(n) / Math.log(2)));
    }

    public static int calcularAleatorios(int i, int vPisos[]) {
        return (int) Math.floor((Math.random() * (vPisos[i] + 1)));
    }

    public static long obtenerTiempo(char medida) {
        medida = Character.toUpperCase(medida);
        long time = 0;
        switch (medida) {
            case 'M':
                time = System.currentTimeMillis();
                break;
            case 'N':
                time = System.nanoTime();
                break;
        }
        return time;
    }
}
