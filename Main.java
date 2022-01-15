import GLOOP.*;
import basis.*;
import java.util.Random;

public class Main
{   
    // Objekte
    Landschaft landschaft;
    Player spieler;
    Schneemann enemy;
    Sys sys;
    // io

    GLTastatur t; // Tastatur
    GLMaus m;

    // Variablen

    boolean running;
    int seite;

    // Schneemannliste

    List<Schneemann> enemylist;

    public Main()
    {   
        spieler = new Player();
        landschaft = new Landschaft();
        
        sys = new Sys();
        t = new GLTastatur();
        
        Random rand = new Random(); 
        
        enemylist = new List<Schneemann>();
        for (int i = 0; i < 50; i++) {
            enemy = new Schneemann(
            -50000 + spieler.getx() + rand.nextInt(100000),
            -50000 + spieler.gety() + rand.nextInt(100000),
            -50000 + spieler.getz() + rand.nextInt(100000)
            );
            enemylist.append(enemy);
        }
        this.fuehreaus();
    }

    private void fuehreaus(){
        running = true;
        Random rand = new Random();
        System.out.println("Im Spiel + / - drücken, um durch Infos zu stöbern.");
        while(running){
            Hilfe.pause(5);
            
            //in zufälligen abständen werden schneemänner zufällig entfernt
            if(rand.nextInt(1000) == 0){
                enemylist.toFirst();
                enemy = new Schneemann(
                -5000 + spieler.getx() + rand.nextInt(10000),
                -5000 + spieler.gety() + rand.nextInt(10000),
                -5000 + spieler.getz() + rand.nextInt(10000)
                ); //Neuer Schneemann wird erzeugt
                for (int i = 0; i < 0 + rand.nextInt(49); i++){
                    enemylist.next();
                }
                enemylist.getContent().delete();
                enemylist.setContent(enemy);
            }
            
            //Kamera wird bewegt
            spieler.bewegdich();
            enemylist.toFirst();
            //Es wird geguckt ob die Schneemänner in einem Radius von 500 vom flugzeug sind
            while (enemylist.hasAccess()) {
                System.out.println(enemylist.getContent().getx());
                if(spieler.getx() <= enemylist.getContent().getx()+500
                && spieler.getx() >= enemylist.getContent().getx()-500
                && spieler.gety() <= enemylist.getContent().gety()+500
                && spieler.gety() >= enemylist.getContent().gety()-500 
                && spieler.getz() <= enemylist.getContent().getz()+500
                && spieler.getz() >= enemylist.getContent().getz()-500){
                   Sys.erstelleAusgabe("Gratulation"); 
                }
                enemylist.next();
            }
            
            //Einzelne Konsolenseiten werden angezeigt(zum debugging)
            switch(seite)
            {
                case 4:
                System.out.println();
                System.out.println();
                System.out.println("Koordinaten:");
                System.out.println(spieler.getx());
                System.out.println(spieler.gety());
                System.out.println(spieler.getz());
                break;
            }
            switch(seite)
            {
                case 1:
                System.out.println();
                System.out.println();
                System.out.println("Geschwindigkeiten:");
                System.out.println("Vertikal / Seitlich / Power:");
                System.out.println(spieler.getvvert() + " " + spieler.getvside() + " " + spieler.getpower());
                System.out.println("HORIZONTAL:");
                System.out.println(spieler.getvhor());
                break;
            }
            switch(seite)
            {
                case 2:
                System.out.println();
                System.out.println();
                System.out.println("Beschleunigungen:");
                System.out.println();
                System.out.println("Vertikal / Horizonatal / Seitlich:");
                System.out.println(spieler.vertbeschl() + " " + spieler.horbeschl()+ " " + spieler.sidebeschl());
                System.out.println();
                break;
            }
            switch(seite)
            {
                case 3:
                System.out.println();
                System.out.println();
                System.out.println("Winkel:");
                System.out.println("Alpha / Beta / Gamma:");
                System.out.println(spieler.getalpha() + " " + spieler.getbeta() + " " + spieler.getgamma());
                System.out.println("Vertikal / Horizonatal:");
                System.out.println(spieler.getvertwinkelbewegung() + " " + spieler.gethorwinkelbewegung());
                break;
            }
            switch(seite)
            {
                case 0:
                System.out.println();
                System.out.println();
                
                break;
            }
            
            if(t.wurdeGedrueckt()){       //Steuerung
                switch(t.gibZeichen()){
                    case 'w':
                    spieler.pitch(1);
                    break;
                    
                    case 's':
                        spieler.pitch(-1);
                        break;
                    case 'a':
                        spieler.yaw(-1);
                        break;
                    case 'd':
                        spieler.yaw(1);
                        break;
                    case 'o':
                        if(spieler.getpower() < 30000){
                            spieler.setpower(spieler.power+5000);
                            break;
                        }
                    case 'l':
                        if(spieler.getpower() >= 5000){
                            spieler.setpower(spieler.power-5000);
                            
                        }
                        break;
                    case '+':
                        seite++;
                        if(seite>4)seite=0;
                        break;
                    case '-':
                        seite--;
                        if(seite<0)seite=4;
                        break;
                    case '@': //programm wird per "@" geschlossen
                        running=false;
                        break;
                    default:
                    Sys.erstelleAusgabe("Kein gültiges Zeichen");
                    break;
                }
            }

        }
        sys.beenden();
    }
    }
