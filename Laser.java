import GLOOP.*;
import basis.*;


public class Laser
{

    GLZylinder Laser;
    double x,y,z;
    double xw,yw,zw;
    boolean sichtbar;

    public Laser()
    {
        Laser = new GLZylinder(0,0,0,3,500);
        Laser.setzeFarbe(100, 0, 0);
        sichtbar = true;
    }


    public void rotiere(double winkel1, double winkel2, double winkel3)
    {
        Laser.setzeDrehung(winkel1, winkel2, winkel3);
        xw=winkel1;
        yw=winkel2;
        zw=winkel3;
    }

    public void setzeposition(double xi,double yi,double zi)
    {
        Laser.setzePosition(xi, yi, zi);
        x=xi;
        y=yi;
        z=zi;
    }

    public void verschwinde()
    {
        if(sichtbar)Laser.setzeSichtbarkeit(false);
        else Laser.setzeSichtbarkeit(true);

    }

}