package datos;

public class Utils {
    
    public int S2I(String sCadena){
        int suma = 0;
        char[] aCaracteres = sCadena.toCharArray();
        for(int i=0; i < aCaracteres.length; i++){
            suma = suma + aCaracteres[i];
        }
        
        return suma;
    }
    
}
