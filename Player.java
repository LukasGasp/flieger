import GLOOP.*;
public class Player
{   
    // Objekte
    GLKamera kamera0;
    
    private double x;
    private double y;
    private double z;
    private int winkhorglob, winkvertglob,masse;
    private double ywinkel,vvert,vside,vhor,vertwinkelbewegung,horwinkelbewegung,drag,lift,alpha,beta,gamma,zeit,v;
    private long letztezeit,diesezeit;
    
    double power;
    public Player()
    {
        // Variablen werden initialisiert
        
        
        x = 100;
        y = 1000;
        z = 0;
        winkvertglob = 0;
        winkhorglob = 0;
        vhor = 200;
        vvert = 0;
        vside = 0;
        masse = 1000;
        vertwinkelbewegung = 0;
        horwinkelbewegung = 0;
        letztezeit = System.currentTimeMillis();
        
        // Objekte erzeugen        
        // Kamera:
        kamera0 = new GLKamera(1000,1000);
        
        kamera0.setzePosition(x, y, z);
        kamera0.zeigeAchsen(true);   
        kamera0.setzeBlickpunkt(0, 100, 0);
        
        kamera0.setzeStereomodus(true); // Stereo!
    }
    
    public void bewegezu(double tempx, double tempy, double tempz){
        kamera0.setzePosition(tempx, tempy, tempz);
        x = tempx;
        y = tempy;
        z = tempz;
    }
    
    public void bewegeum(double tempx, double tempy, double tempz){
        kamera0.setzePosition(x+tempx, y+tempy, z+tempz);
        x = x+tempx;
        y = y+tempy;
        z = z+tempz;
        
    }
    
    public void gehe(double amount){
        
        kamera0.vor(1);
        x=kamera0.gibX();
        y=kamera0.gibY();
        z=kamera0.gibZ();
        
    }
    
    public void neuehorgesch(){
        vhor = Math.sqrt(Math.pow(vhor,2)+Math.pow(vside,2));
        horwinkelbewegung = horwinkelbewegung + Math.toDegrees(Math.atan(vside/vhor));
        beta = winkhorglob - horwinkelbewegung;
        vside = 0;
    }
    
    //FLugzeugphysics Start
    
    public double vertbeschl(){
        
        return (            power*Math.sin(Math.toRadians(alpha)) 
                            - drag(vhor)
                            + lift(vhor)*Math.cos(Math.toRadians(alpha))*Math.cos(Math.toRadians(gamma))
                            - masse * 9.81)
                            /masse;
                            
    }
    
    public double horbeschl(){
        return (    power * Math.cos(Math.toRadians(alpha)) * Math.cos(Math.toRadians(beta))
                    - drag(vhor)
                    - lift(vhor) * (Math.sin(Math.toRadians(alpha)) * Math.cos(Math.toRadians(gamma)) * Math.cos(Math.toRadians(beta)) + Math.sin(Math.toRadians(gamma)) * Math.sin(Math.toRadians(beta))  ))
                    /masse;
    }
    
    public double sidebeschl(){
        return (    power * Math.cos(Math.toRadians(alpha)) * Math.sin(Math.toRadians(beta))
                    - drag(vside)
                    - lift(vside) * (Math.sin(Math.toRadians(alpha)) * Math.cos(Math.toRadians(gamma)) * Math.sin(Math.toRadians(beta)) * Math.cos(Math.toRadians(beta)) + Math.sin(Math.toRadians(gamma)) * Math.cos(Math.toRadians(beta))  
                    ))
                    /masse;
    }
    
    public double drag(double temp){
        //v = Math.sqrt(Math.pow(vhor,2)+Math.pow(vvert,2));
        return dragcoefficient() * 10 * 1.225 * Math.pow(temp,2) / 2;
        
    }
    
    public double lift(double temp){
        v = Math.sqrt(Math.pow(vhor,2)+Math.pow(vvert,2));
        return Math.pow(temp,2)*liftcoefficient();
        
    }
    
    public double dragcoefficient()
    {
        return Math.pow(angleofattack(),2) * (20/22500) + 0.05;
    }
    
    public double liftcoefficient(){
        if(angleofattack()<=18) return angleofattack()*0.074+0.37;
        
        else return - angleofattack()*0.074+8.362;
    }
    
    public double angleofattack()
    {
        
        return alpha - vertwinkelbewegung;
    }
    
    //Flugzeugphysics Ende
    
    public void bewegdich(){
        //kamera0.setzePosition(x + tempx, y + tempy, z + tempz);
        double tempx;
        double tempy;
        double tempz;
        
        
        
        diesezeit = System.currentTimeMillis();
        zeit = diesezeit - letztezeit;
        
        v = Math.sqrt(Math.pow(vhor,2)+Math.pow(vvert,2));
        
        vvert = vvert + vertbeschl()*(zeit/1000);
        vhor = vhor + horbeschl()*(zeit/1000);
        vside = vside + sidebeschl()*(zeit/1000);
        
        
        
        neuehorgesch();
        
        vertwinkelbewegung = Math.toDegrees(Math.atan(vvert/vhor));
        
        
        bewegezu(x + Math.cos(Math.toRadians(horwinkelbewegung))*vhor*(zeit/1000),
        y + vvert*(zeit/1000),
        z  + Math.sin(Math.toRadians(horwinkelbewegung))*vhor*(zeit/1000)
        );
        
        kamera0.setzeBlickpunkt(x + Math.cos(Math.toRadians(winkhorglob)) * Math.cos(Math.toRadians(winkvertglob)) *100,
                               Math.sin(Math.toRadians(winkvertglob)) * 100+ y, 
                               z  + Math.sin(Math.toRadians(winkhorglob)) * Math.cos(Math.toRadians(winkvertglob)) *100);
        x=kamera0.gibX();
        y=kamera0.gibY();
        z=kamera0.gibZ();
        letztezeit = System.currentTimeMillis();
        
    }
    
    public void yaw(double winkel){
        
        winkhorglob = winkhorglob + (int)winkel;
        ywinkel = Math.sin(Math.toRadians(winkvertglob)) * 100 + y;
        kamera0.setzeBlickpunkt(x+ Math.cos(Math.toRadians(winkhorglob))* Math.cos(Math.toRadians(winkvertglob)) *  100      ,ywinkel, z + Math.sin(Math.toRadians(winkhorglob))* Math.cos(Math.toRadians(winkvertglob))*100);
        x=kamera0.gibX();
        y=kamera0.gibY();
        z=kamera0.gibZ();
    }
    public void pitch(double winkel){
        
        winkvertglob = winkvertglob + (int)winkel;
        alpha = alpha + winkel;
        ywinkel = Math.sin(Math.toRadians(winkvertglob)) * 100 + y;
        kamera0.setzeBlickpunkt(x + Math.cos(Math.toRadians(winkhorglob)) * Math.cos(Math.toRadians(winkvertglob)) *100,
                               Math.sin(Math.toRadians(winkvertglob)) * 100 + y, 
                               z  + Math.sin(Math.toRadians(winkhorglob)) * Math.cos(Math.toRadians(winkvertglob)) *100);
        x=kamera0.gibX();
        y=kamera0.gibY();
        z=kamera0.gibZ();
    }
    
    // Getter
    // Um Probleme mit Variablentypen vorzubeugen
    
    public int getx(){
        return (int) x;
    }
    
    public int gety(){
        return (int) y;
    }
    
    public int getz(){
        return (int) z;
    }
    
    public double getvvert(){
        return  vvert;
    }
    
    public double getvhor(){
        return  vhor;
    }
    
    public double getalpha(){
        return  alpha;
    }
    
     public double getbeta(){
        return  beta;
    }
    
     public double getgamma(){
        return  gamma;
    }
    
     public double getvertwinkelbewegung(){
        return  vertwinkelbewegung;
    }
    
     public double gethorwinkelbewegung(){
        return  horwinkelbewegung;
    }
    
     public double getvside(){
        return  vside;
    }
}
