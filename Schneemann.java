import GLOOP.*;
import basis.*;

public class Schneemann
{
    //objekte werden erzeugt
    private GLKugel kugel1,kugel2,kugel3,auge1,auge2;
    private GLZylinder zylinder1,zylinder2;
    private GLKegel kegel;
    private GLTextur textur,texturleder,texturkarotte,texturstein;
    
    int x,y,z;
    
    
    public Schneemann(int x,int y,int z)
    {
        //ints werden übergeben
        this.x = x;
        this.y = y;
        this.z = z;
        
        //texturen werden erzeugt
        textur = new GLTextur("schnee.jfif");
        texturleder = new GLTextur("leder.jfif");
        texturkarotte = new GLTextur("karotte.jfif");
        texturstein = new GLTextur("stein.jfif");
        //3d-objekte werden erzeugt
        kugel1 = new GLKugel(x,y,z,35);        
        kugel2 = new GLKugel(x,y+45,z,20);
        kugel3 = new GLKugel(x,y+70,z,10);
        auge1 = new GLKugel(x+4,y+73,z-9,1); //bei den augen sind sehr genaue werte nötig
        auge2 = new GLKugel(x-4,y+73,z-9,1);
        kegel = new GLKegel(x,y+70,z-10,4,20);
        zylinder1 = new GLZylinder(x,y+82,z,10,10);
        zylinder2 = new GLZylinder(x,y+77,z,20,1);
        //zylinder wird gedreht
        zylinder1.drehe(90, 0, 0);
        zylinder2.drehe(90, 0, 0);
        //texturen werden auf den objekten angewandt
        zylinder1.setzeTextur(texturleder);
        zylinder2.setzeTextur(texturleder);
        kegel.setzeTextur(texturkarotte);
        kugel1.setzeTextur(textur);
        kugel2.setzeTextur(textur);
        kugel3.setzeTextur(textur);
        auge1.setzeTextur(texturstein);
        auge2.setzeTextur(texturstein);
    }    
    
    public int getx()
    {
        return x;
    }
    
    public int gety()
    {
        return y;
    }
    
    public int getz()
    {
        return z;
    }
    
    public void delete(){
        // GLOOP aktualisiert beim Loschen nicht die Sichtbarkeit
        kugel1.setzeSichtbarkeit(false);
        kugel2.setzeSichtbarkeit(false);
        kugel3.setzeSichtbarkeit(false);
        auge1.setzeSichtbarkeit(false);
        auge2.setzeSichtbarkeit(false);
        kegel.setzeSichtbarkeit(false);
        zylinder1.setzeSichtbarkeit(false);
        zylinder2.setzeSichtbarkeit(false);
        Hilfe.pause(5); // Sonst ist das Unsichtbar machen noch nicht fertig. (Wussten nicht, dass java multithreaded ist!?)
        kugel1.loescheDich();
        kugel2.loescheDich();
        kugel3.loescheDich();
        auge1.loescheDich();
        auge2.loescheDich();
        kegel.loescheDich();
        zylinder1.loescheDich();
        zylinder2.loescheDich();
        
        //kugel1 = null;
        //kugel2 = null;
        //kugel3 = null;
        //auge1 = null;
        //auge2 = null;
        //kegel = null;
        //zylinder1 = null;
        //zylinder2 = null;
        
        //System.gc(); // Garbage Collector manuell dazu anregen seinen Job zu machen. (Hat FPS massiv verbessert)
    }
}
