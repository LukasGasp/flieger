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
            //spieler.gehe(10);
            
            int random = 0 + rand.nextInt(400);
            enemylist.toFirst();
            enemylist.getContent().delete();    // Objekte löschen
            Hilfe.pause(5);
            enemylist.setContent(null);         // Garbage Collector löscht eher, wenn der Wert null ist
            enemy = new Schneemann(
            -10000 + spieler.getx() + rand.nextInt(20000),
            -10000 + spieler.gety() + rand.nextInt(20000),
            -10000 + spieler.getz() + rand.nextInt(20000)
            ); //Neuer Schneemann wird erzeugt
            enemylist.toLast();
            enemylist.setContent(enemy);
            
            //Kamera wird bewegt
            spieler.bewegdich();
            enemylist.toFirst();
            while (enemylist.hasAccess()) {
                
                if(spieler.getx() <= enemylist.getContent().getx()+50
                && spieler.getx() >= enemylist.getContent().getx()-50
                && spieler.gety() <= enemylist.getContent().gety()+50
                && spieler.gety() >= enemylist.getContent().gety()-50 
                && spieler.getz() <= enemylist.getContent().getz()+50
                && spieler.getz() >= enemylist.getContent().getz()-50){
                   System.out.println("Gratulation"); 
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
                System.out.println(spieler.getvvert() + " " + spieler.getvside() + " " + spieler.power);
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
                        if(spieler.power < 30000){
                            spieler.power=spieler.power+5000;
                            break;
                        }
                    case 'l':
                        if(spieler.power >= 5000){
                            spieler.power=spieler.power-5000;
                            
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
