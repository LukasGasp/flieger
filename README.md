<h1>Dies ist das Informatik Projekt von Michael und Lukas</h1>

- PROJEKTBEZEICHNUNG:             Flieger  
- PROJEKTZWECK:                   Informatik Arbeit  
- VERSION:                        V.0.5.2  
- WIE IST DAS PROJEKT ZU STARTEN: BlueJ  
- AUTOREN:                        Michael, Lukas  
- BENUTZERHINWEISE:               Sie müssen fliegen können  

<h2>Spielidee:</h2>

Man steuert ein exakt 2000 kg schweres Flugzeug, welches eine Kraft von 30000 Newton aufbringen kann. Gemäß folgender Veröffentlichungen der NASA haben wir Formeln extrahiert, verarbeitet und implementiert.

[Informatik.pdf](https://github.com/LukasGasp/flieger/files/7875854/Informatik.pdf)

Man steuert dieses Flugzeug durch eine traumhafte Schneelandschaft. Um diese spannender zu machen (und den Arbeitsauftrag zu erfüllen) werden Schneemänner zufällig um den Spieler erzeugt. Das erste Element der Liste wird gelöscht und durch Zufall neu um den Spieler platziert. Der neue Scheemann wird ans Ende der Liste gehangen. Im laufe des Spiels kommen immer mehr Schneemänner dazu. (Bis zu 50). So gibt es Möglichkeit Speedruns zu machen.

<h2>Benutzerhinweise</h2>

Bitte 3D Brille tragen.

<h2>Steuerung:</h2>

Wir gehen davon aus, dass dem Spieler die Gesetzte der Aerodynamik bekannt sind. Hier eine kurze Erinnerung an Kräfte, welche auf ein Flugzeug wirken:

![forces](https://user-images.githubusercontent.com/72341833/149591342-965aa6f5-e0e1-4934-bc57-c9db41a18560.jpg)

Turbinen beschleunigen: o  
Turbinen entschleunigen: l  
Steuerung: w, a, s, d  
Infos: + / - 
Programm schließen: z

<h2>Konsole</h2>

**WIR EMPFEHLEN UNBEDINGT DIE KONSOLE OFFEN ZU HALTEN!**  
Wenn man im Hauptfenster + oder - drückt ändern sich die anezeigten Werte. Es gibt: 

- Koordinaten
- Geschwindigkeiten
- Beschleunigungen
- Winkel

Diese Werte müssen ALLE vom Spieler im Auge behalten werden. Anderfalls kommt es zu einem kritischen Absturz.

<h2>Code-Einblick</h2>
Eine der Funktionen. Natürlich werden hier Gravitation, Auftrieb, Masse, etc. beachtet. Alles ist relativ,

```java
  public double sidebeschl(){
      return (    power * Math.cos(Math.toRadians(alpha)) * Math.sin(Math.toRadians(beta))
                  - drag(vside)
                  - lift(vside) * (Math.sin(Math.toRadians(alpha)) * Math.cos(Math.toRadians(gamma)) * Math.sin(Math.toRadians(beta)) * Math.cos(Math.toRadians(beta)) + Math.sin(Math.toRadians(gamma)) * Math.cos(Math.toRadians(beta))  
                  ))
                  /masse;
  }
```

<h2>Probleme</h2>
Eine große Herausforderung war die GLOOP Bibliothek. Diese bietet KEINE möglichkeit die Kamera um ihre eigene Achse zu drehen. (Die rotieren Funktion funktioniert einfach nicht)
Selbst das Kamera ausrichten bereitete Aufgrund dazu fehlender Funktionen (im Gegensatz zu Objekten) noch größere Probleme.
Wir würden uns freuen für das nächste Projekt eine andere Bibliothek nutzen zu dürfen. 
Trotzdem vielen Dank, dass wir von Basis zu Gloop wechseln durften.
