
/**
 * Esta clase tendrá los métodos principales que se necesitan para llevar a cabo la creación del juego de la culebrita
 * 
 * @author Nicolas Bernal & Carlos Rojas
 * @version 1.0 date=05/02/2026
 */
public class HungrySnakeGame
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class HungrySnakeGame
     */
    public HungrySnakeGame()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * Reiniciar juego
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void reiniciar()
    {
        erase();
        snake.Snake();
    }
    
    public void movement(char z){
        
    }
}