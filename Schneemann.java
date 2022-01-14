import GLOOP.*;
public class Schneemann
{
    //objekte werden erzeugt
    private GLKugel kugel1,kugel2,kugel3,auge1,auge2;
    private GLZylinder zylinder1,zylinder2;
    private GLKegel kegel;
    private GLTextur textur,texturleder,texturkarotte,texturstein;
    
    
    public Schneemann(int x,int y,int z)
    {
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
    
    public void delete(){
        kugel1.loescheDich();
        kugel2.loescheDich();
        kugel3.loescheDich();
        auge1.loescheDich();
        auge2.loescheDich();
        kegel.loescheDich();
        zylinder1.loescheDich();
        zylinder2.loescheDich();
    }
}
