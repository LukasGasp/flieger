import GLOOP.*;
public class Landschaft
{
    //objekte werden erzeugt
    private GLLicht licht1,licht2;
    private GLBoden boden;
    private GLHimmel himmel;
    private Schneemann schneemann1,schneemann2;
    private GLTextur textur;  
    private GLTextur himmeltextur;
    
    public Landschaft()
    {
        //Lichter werden erzeugt
        licht1 = new GLLicht();
        licht1.drehe(20, 20, 20);        
        licht2 = new GLLicht();        
        licht2.setzeDrehung(10, 180, 0);
        licht2.setzePosition(100, 100, 100); //habe ein zweites licht für smoothere beleuchtung gemacht
        //objekte werden konstruiert
        textur = new GLTextur("schneeboden.jfif");  //textur für den boden   
        
        himmeltextur = new GLTextur("sky.jpg");
        boden = new GLBoden(textur); //textur wird sofort angewandt
        himmel = new GLHimmel(himmeltextur);
        //der eigentliche schneemann wird erzeugt
        schneemann1 = new Schneemann(1,10,1);
        schneemann2 = new Schneemann(100,10,1);
    }
    
    
}
