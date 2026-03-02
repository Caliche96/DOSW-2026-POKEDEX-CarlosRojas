import shapes.Rectangle;
import java.util.ArrayList;
/**
 * Hará la serpiente como una lista de cuadrados
 * 
 * @author Nicolas Bernal & Carlos Rojas
 * @version 1.0 made= 5/02/2026
 */


public class Snake
{
    public static final int cell =20;
    private static final int RECT_INIT_X=70;
    private static final int RECT_INIT_Y=15;
    private int dx;
    private int dy;
    private int longitud;
    /**
     * Constructor for objects of class Snake
     */
    public Snake()
    {
        int dx=20;
        int dy=20;
        int longitud=5;
    }

    public void movement(char z){
        if (z=='w'){
            dy+=1;
        }else if (z=='a'){
            dx-=1;
        }else if (z=='s'){
            dy-=1;
        }else if (z=='d'){
            dx+=1;
        }else{
            return;
        }
    }
    
    public void reiniciar(){
        Recantgle.erase();
        Snake.Snake();
    }
    
    public void eatFruit(){
        if (head==posFruit){
            fruit.erase();
            longitud+=5;
        }
    }
    
    public void gameState(){
        System.out.println("La longitud de la serpiente es " + longitud );
    }
    
    
    
    
}