import GLOOP.*;
import basis.*;

public class Main
{   
    // Objekte
    Landschaft landschaft;
    Player spieler;
    Schneemann enemy;
    // io
        
    GLTastatur t; // Tastatur
    GLMaus m;
    
    // Variablen
    
    boolean running;
    
    // Schneemannliste
    
    List<Schneemann> enemylist;
    
    public Main()
    {   
        spieler = new Player();
        landschaft = new Landschaft();
        
        t = new GLTastatur();
        
        enemylist = new List<Schneemann>();
        for (int i = 0; i < 50; i++) {
          System.out.println("Scheemann " + i + "erstellt.");
          enemy = new Schneemann(20000, 200, 0 + i);
          enemylist.append(enemy);
        }
        this.fuehreaus();
    }
    
    private void fuehreaus(){
        running = true;
        //spieler.hoehe(30);
        spieler.vvert = 0;
        while(running){
            Hilfe.pause(5);
            //spieler.gehe(10);
            spieler.bewegdich();
            System.out.println();
            System.out.println("Koordinaten:");
            System.out.println(spieler.x);
            System.out.println(spieler.y);
            System.out.println(spieler.z);
            System.out.println();
            System.out.println("Geschwindigkeiten:");
            System.out.println("Vertikal / Seitlich / Power:");
            System.out.println(spieler.vvert + " " + spieler.vside + " " + spieler.power);
            System.out.println("Beschleunigungen:");
            System.out.println();
            System.out.println("Vertikal / Horizonatal / Seitlich:");
            System.out.println(spieler.vertbeschl() + " " + spieler.horbeschl()+ " " + spieler.sidebeschl());
            System.out.println();
            System.out.println("Winkel:");
            System.out.println("Alpha / Beta / Gamma:");
            System.out.println(spieler.alpha + " " + spieler.beta + " " + spieler.gamma);
            System.out.println("Vertikal / Horizonatal:");
            System.out.println(spieler.vertwinkelbewegung + " " + spieler.horwinkelbewegung);
            if(t.wurdeGedrueckt()){ 
                switch(t.gibZeichen()){
                case 'w':
                    spieler.pitch(1);
                    break;
                //case 's':
                //    spieler.gehe(-10);
                //    break;
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
                    spieler.power=spieler.power+5000;
                    break;
                case 'l':
                    spieler.power=spieler.power-5000;
                    break;
                default:
                    System.out.println("Kein gültiges Zeichen");
                    break;
                }
            }
            
            }
        }
    }
