import java.awt.Color;
import java.awt.image.BufferedImage;
 
/**
 *
 * @author Luis Marcos
 */
public class Histograma {

    /**
     * Devuelve el histograma de la imagen.
     * @param imagen BufferedImagen de la cual se quiere obtener el histograma
     * @return Devuelve una variable int[5][256], donde el primer campo[0] corresponde
     * al canal Rojo, [1]=verde [2]=azul [3]=alfa [4]=escala grises
     */
    public int[][] histograma(BufferedImage imagen){
        Color colorAuxiliar;
        /*Creamos la variable que contendrá el histograma
        El primer campo [0], almacenará el histograma Rojo
        [1]=verde [2]=azul [3]=alfa */
        //int histogramaReturn[][] =ew int[5][256];
        int[][] histogramaReturn = new int[4][256];
        //Recorremos la imagen
        for( int i = 0; i < imagen.getWidth(); i++ ){
            for( int j = 0; j < imagen.getHeight(); j++ ){
                //Obtenemos color del píxel actual
                colorAuxiliar=new Color(imagen.getRGB(i, j));
                //Sumamos una unidad en la fila roja [0], 
                //en la columna del color rojo obtenido
                histogramaReturn[0][colorAuxiliar.getRed()]+=1;
                histogramaReturn[1][colorAuxiliar.getGreen()]+=1;
                histogramaReturn[2][colorAuxiliar.getBlue()]+=1;
                histogramaReturn[3][colorAuxiliar.getAlpha()]+=1;
            }
        }
        //Retorna uma matriz com os valores das cores no esquema RGB e o canal alpha.
        //0 - vermelho, 1 - verde, 2 - azul, 3 - alpha
        return histogramaReturn;
    }
}