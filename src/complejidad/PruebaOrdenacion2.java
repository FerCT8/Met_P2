/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejidad;

import static complejidad.PruebaOrdenacion.obtenerTiempo;
import utilidades.leer;

/**
 * @author Javier
 * @author Fernando
 */
public class PruebaOrdenacion2 {
    
    public static void apartado3(){
        
                char repetir;
        do {
            int N=leer.entero("Introduce un valor N por teclado");
            char medida = leer.caracter("¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");
            long tb = obtenerTiempo(medida);
            iterativo(N);
            long tbi = obtenerTiempo(medida);
            long tbrecursivo=tbi-tb;
            
            System.out.println("  N   |	     Iterativo	   |  Recursivo   |");
            long ts = obtenerTiempo(medida);
            recursivo(N);
            long tsi = obtenerTiempo(medida);
            long tsrecursivo=tsi-ts;

            System.out.printf("   %d |        %d       |       %d        |\n",N, tbrecursivo,tsrecursivo);
            
            repetir = Character.toUpperCase(leer.caracter("¿Quieres repetir la prueba? (S=si/N=no)"));
        }while (repetir == 'S');
    }  
      public static void apartado4(){
           
        System.out.println("\n\nAhora realizaremos pruebas con distintos tamaños del vector");
        
        int[] valoresN = {100, 500, 1000, 5000, 8000, 9000, 10000, 11000, 20000, 50000};

        char medida = leer.caracter("¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");
        System.out.println("  N   |	     Iterativo	   |  Recursivo     |");

        for(int i=0;i<valoresN.length;i++){
            
            int valor=valoresN[i];
            
            long tv = obtenerTiempo(medida);
            iterativo(valor);
            long tvi= obtenerTiempo(medida);
            long tviterativo=tvi-tv;
            
            long tz= obtenerTiempo(medida);
            recursivo(valor);
            long tzi = obtenerTiempo(medida);
            long tzrecursivo=tzi-tz;
            
            System.out.printf("   %7d |        %10d     |       %10d      |\n",valor,tviterativo,tzrecursivo);

        }
         System.out.printf("\n\n");
      }
      
       public static int iterativo(int n) {
        int act = 0, ant1, ant2;
        ant1 = 0;
        ant2 = 1;
        if (n == 0) {
            act = 0;         
        }else if(n == 1){
            act = 1;
        } else {
            for (int i = 0; i < n; i++) {
                act = ant1 + ant2;
                ant2 = ant1;
                ant1 = act;
            }
        }
        return act;
    }

    public static int recursivo(int n) {
        int m;
        if (n == 0 || n == 1) {
            return n;
        } else {
            return recursivo(n - 2) + recursivo(n - 1);
        }
    }

}
