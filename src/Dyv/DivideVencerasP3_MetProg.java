package Dyv;

import utilidades.*;

public class DivideVencerasP3_MetProg {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        System.out.println("\n\n ~ ~ ~ | PRÁCTICA 3 - DIVIDE Y VENCERÁS | ~ ~ ~ \n");
        
        System.out.println("APARTADO 1");        
        // Apartado 1, para N pisos y K ladrillos.
        apartado1();
        
        System.out.println("APARTADO 2");
        // Apartado 2, para N pisos introducidos, calculas el K óptimo.
        apartado2();
         
    }

    public static void apartado1() {

        int[] vecPisos = {10, 100, 1000, 10000, 100000, 1000000};
        int[] vecLads = {1, 2, 10, 100, 1000};
        int aux;
        double tIni, tFin;
        
        for (int p = 0; p < vecPisos.length; p++) {
            aux = r(Math.random() * (vecPisos[p] + 1));
            for (int l = 0; l < vecLads.length; l++) {
                System.out.printf("Para %d ladrillos y %d pisos.\n\n", vecLads[l], vecPisos[p]);
                try{
                    tIni = tiempo();
                    algoritmo(vecLads[l], 0, vecPisos[p], new Ladrillo(aux));
                    tFin = tiempo();
                    System.out.printf("\nHa tardado %.2f nanosegundos.", tFin-tIni);
                }catch(StackOverflowError soe){
                    System.out.println("ERROR: La pila se ha desbordado -> " + soe.toString());
                }
                System.out.println("\n\n");
            }
        }
    }

    public static void apartado2() {

        char x;

        do {

            int npisos = leer.entero("Indique el número de pisos:");

            while (npisos < 1) {
                System.out.println("\nERROR. Debe haber al menos un piso.");
                npisos = leer.entero("Vuelva a introducir el dato:");
            }

            System.out.printf("El numero óptimo de ladrillos para %d pisos es %d. ",
                    npisos, log(npisos));
            
            x = leer.caracter("¿Quiere repetir esta prueba? Si/No");

        } while (x == 'S' || x == 's');

    }

    public static void algoritmo(int nLads, int li, int ls, Ladrillo l){

        if (nLads == 1) {

            if (seRompe(l, li)) {

                System.out.println("La resistencia del ladrillo es: " + li);

            } else {
                
                li = li + 1;
                algoritmo(nLads, li, ls, l);                

            }

        } else {
            
            int piso = calcularPiso(li, ls);
            
            if (seRompe(l, piso)) {

                nLads--;

                if(piso == li || piso == ls){
                    
                    System.out.println("La resistencia del ladrillo es: " + piso);
                
                }else{
                
                    algoritmo(nLads, li, piso, l);
                    
                }
                
            } else {

                algoritmo(nLads, piso, ls, l);

            }
        }       
    }

    public static boolean seRompe(Ladrillo lad, int piso) {
        return lad.getAlturaMax() <= piso;
    }

    public static int r(double n) {
        return (int) Math.floor(n);
    }

    public static int calcularPiso(int li, int ls) {
        int h = li + ((ls - li) / 2);
        if (h == li) return ls;
        else return h;
    }
    
    public static double tiempo(){
        return System.nanoTime();
    }
    
    public static int log(int n){
        return (int) Math.ceil((Math.log(n)/Math.log(2)));
    }

}
