import java.util.ArrayList;

/**
 * Clase del tablero 
 */
public class Board{
    Fleet fleet;
    public Board(){
        this.fleet= fleet;
    }
}


public class Fleet{
    private Board board;
    private ArrayList<Sailor> sailors;
    private ArrayList<Machine> machines;

    public Fleet(){
        board= this.board;
        sailors=this.sailors;
        machines=this.machines;
    }
    /**
     * Método que retornará la lista de maquinas que pueden ser destruidas
     * @param int longitude     Posicion en x
     * @param int latitude      Posicion en y
     * @return ArrayList machineToBeDestroyed       Lista de maquinas que pueden ser destruidas
     */
    public ArrayList<Machine> willBeDestroyed(int longitude, int latitude){
        ArrayList<Machine> machineToBeDestroyed = new ArrayList<Machine>();     //creacion de lista de maquinas que se pueden destruir
        for (Machine maquina: machines){            //Ciclo para verificar en todas las máquinas
            if maquina.willBeDestroyed(longitude,latitude){     //Se verifica con el método de maquinas si se puede destruir
                machineToBeDestroyed.add(maquina);          //Si es un True se añade a la lista de máquinas que se pueden destruir
            }
        }
        return machineToBeDestroyed;            //Retornamos la lista de maquinas que se pueden destruir
    }

    /**
     * Método que me dara la lista de las máquinas debiles de la flota
     */
    public ArrayList<Machine> weakMachine1(){
        ArrayList<Machine> weakMachines= new ArrayList<Machine>();
        for (Machine maquina : machines){
            if (maquina.isWeak()){
                weakMachines.add(maquina);
            }
        }
        return weakMachines;
    }

    public void attack(int lon, int lat){
        for (Machine maquina : machines){
            if (!maquina.isWeak()){         //Verifica que la maquina no sea débil
                maquina.moveTo(lon,lat);    // mueve la maquina a la posicion dada
            
        }

    }

    /**
     * Realizar este metodo pero con clase Abstracta
     * @return  ArrayList<Machine> weakMachines       Lista de maquinas debiles
     */
    public ArrayList<Machine> weakMachine(){
        ArrayList<Machine> weakMachines= new ArrayList<Machine>();
        for (Machine maquina : machines){
            if (maquina.isWeak()){
                weakMachines.add(maquina);
            }
        }
        return weakMachines;
    }
}

public class Machine{
    private Position location;  // Atributo de posicion de la máquina
    public boolean willBeDestroyed(int lon, int lat){           //Método para ver si se puede destruir
        return location.equals(lon, lat);                //Verificacion de igualdad de la lon y lat si es igual a su posicion
    }

    public boolean isWeak(){
        return false;
    }

    public void moveTo(int lon, int lat){
        location.setLongitude(lon);
        location.setLatitude(lat);
    }
}


public class Position{
    private int longitude;
    private int latitude;

    public boolean equals(int lon, int lat){        //Sobrecargamos el método equals
        return longitude == lon && latitude == lat;     //Hacemos la comparación de longitud y latitud
    }
    public void setLongitude(lon){
        longitude=lon;
    }
    public void setLatitude(lat){
        latitude=lat;
    }
}

public class Ship extends Machine{
    private ArrayList<Sailor> sailors;
    @Override               //Sobreescribimos el metodo isWeak
    public boolean isWeak(){
        if (sailors.size()<5){      //Revisamos el numero de marinos si es menor a 5
            return True;
        }
        return sailors.size()<5;        //Metodo corto
    }
}

public class Plane extends Machine{
    private boolean inAir;
    private Sailor pilot;
    @Override
    public boolean willBeDestroyed(int lon, int lat){
        if(inAir==false){               //Verificamos que el estado enAire sea falso
            return super.willBeDestroyed(lon, lat); //Realizamos el método de la clase padre 
        }
        return !inAir && super.willBeDestroyed(lon, lat);       //Manera corta de hacer el proceso de arriba
    }
    @Override
    public boolean isWeak(){
        return pilot == null;               //Verificamos que el piloto no esté
    }
}

public class AircraftCarrier extends Ship{
    private ArrayList<Plane> airplanes;
    private boolean flag;
    @Override
    public boolean isWeak(){
        //if (super.isWeak()&&for (Plane planes; airplanes){planes.isWeak()})
        for(Plane planes: airplanes){
            if(planes.isWeak()){
                flag=true;
            }
        }
        
        return flag && super.isWeak();
    }
}

public class Sailor{
    private String name;
    private int rank;

    public Sailor(){
        this.name=name;
        this.rank=rank;
    }
}
