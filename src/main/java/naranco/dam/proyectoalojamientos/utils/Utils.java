package naranco.dam.proyectoalojamientos.utils;

import java.util.Collection;
import java.util.Map;

public class Utils {

    public static <T> void printList(Collection<T> lista) {
        if(!lista.isEmpty()){
            for(T elemento: lista){
                System.out.println(elemento);
            }
        }else{
            System.out.println("Lista sin elementos");
        }
    }

    public static String cleanStringText(String cadena){
        return cadena.trim().replace("\"","").trim();
    }
    public static <K,V> void imprimirMap(Map<K,V> lista) {
        if(!lista.isEmpty()){
            for (Map.Entry<K, V> elemento:lista.entrySet()){
                System.out.println(elemento.getKey() + " --> " + elemento.getValue());
            }
        }
    }

    public static <T> void isListEmpty(Collection<T> list){
        if(list.isEmpty()){
            System.out.println("No hay datos");
        }
    }
}
