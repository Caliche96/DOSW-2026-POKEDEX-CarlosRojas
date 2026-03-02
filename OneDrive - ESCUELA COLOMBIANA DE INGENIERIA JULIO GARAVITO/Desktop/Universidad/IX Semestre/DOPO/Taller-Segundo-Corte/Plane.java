public class Plane extends Machine{
    private boolean inAir;

    @Override
    public boolean willBeDestroyed(int, lon, int lat) {
        if(inAir==false){
            return super.willBeDestroyed(lon, lat);
        }
    }

    @Override
    public boolean isWeak(){
        return pilot ==null;
    }
}