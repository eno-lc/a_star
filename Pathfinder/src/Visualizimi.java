import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/*
* Fillimisht kemi krijuar klasen Visualizimi e cila ka per detyre te beje visualizimin e algoritmit ne fjale,
* klasa jone trashegon nga klasa JPanel qe lejon krijimin e nje container apo hapesire ne te cilen do te vendosen te gjitha
* elementet tona grafike, kjo klase vjen nga frameworku i Java Swing dhe pastaj implementojme interface si MouseListener,
* MouseMotionListener, KeyListener, interface te cilat do te ndihmojne ne detektimin e levizjeve tona ne program, kur personi kontrollues
* ndermerr ndonje aksion fiziki permes mausit apo tastieres, te cilat vijne nga frameworkut AWT apo Abstract Window Toolkit
* per te bere paraqitjet grafike dhe per evente te ndryshme.
*
* Pastaj krijojme nje instance statike te klases Visualizimi, ku tregojm se si instance i perket klases dhe jo objektit, njejte edhe per
* variablen eshteNeVeprim e cila eshte e llojit boolean, e cila tregon se kur personi kontrollues i programit po ndermerr ndonje aksion fizik.
*
* Pastaj krijojme dy variabla te tipit integer, te cilat tregojne per gjatesin dhe gjeresine e nje qelize ne rrjete,
* krijojme edhe dy boolean variable ku tregon gjendjen se nese eshte caktuar nyja startuese dhe nyja fundore.
*
* Krijojme nje 2D array e cila do ta krijoje rrjeten apo grid-in tone e tipit Node, e cila do te kete 50 rreshta dhe 50 kolona, qe ne total do te kete
* 2500 qeliza.
*
* Krijojme dy(2) nyje me emrin fillimi dhe fundi, qe reprezentojne nyjen startuese dhe nyjen fundore apo perfundimtare.
*
* Krijojme nje objekt te klases Algoritmi.
*
* Krijojme nje metode statike per klasen tone me emrin getInstanca e cila do te ktheje instancen e klases visualizimi nese eshte e gatshme,
* nese eshte null atehere krijon nje instance te re dhe e kthen ate.
*
* Pastaj kemi konstruktorin e klases Visualizimi ne te cilin inicializohen apo vendosen disa funksionalitete,
* fillojme duke vendosur ngjyren e prapavijes, permes setFocusable tregojm nese nje komponent mund te fitoj fokusin nese
* i kerkohet nje gje e tille dhe requestFocusInWindow tregon se komponenti ne fjale duhet te jete i shfaqshem, i afte per tu fokusuar,
* i dukshem dhe gjithe antaret e nderlidhur me te duhet te jene te dukshem.
*
* MouseListener - njofton apo tregon sa here ndryshohet gjendja e mausit.
* MouseMotionListener - merr te gjitha ngjarjet qe nderlidhen me mausin, njoftohet sa here qe levizim apo terheqim mausin.
* KeyListener - njofton sa here ndryshon gjendja e nje tasti ne tastiere.
*
* Per te krijuar nyjet krijojme nje for loop e cila do te iteroje neper 2D array e llojit Node apo nyje e cila
* do te sherbej per te krijuar rrjeten apo grid-in tone. Ketu verejme nested for loops ne te cilat kemi O(n^2),
* ne for loop-in e pare behet iterimi per rreshta ndersa ne for loop-in e dyte behet iterimi per shtylla apo kolona,
* pastaj krijohet nje nyje e re ne pozicionin e rreshtit dhe kolones ne formen matricore. Shembul [0,0].
* Fillimisht ato jane vetem qeliza te zbrazta ndersa pas iterimit ato transformohen ne nyje apo nodes, te qasshme
* per algoritmin tone.
*
* Pastaj per t'iu qasur te gjitha nyjee fqinje, krijohet nje for loop tjeter njejte si me pare, e njejtit format,
* ku kemi O(n^2) per arsye se kemi nested for loop. Ne kete for loop behet gjetja e nyjeve fqinje per nje nyje ne kordinata
* te caktuara x,y brenda 2D array-it, ku mirren te gjitha nyjet fqinje ekzistuese ne rrjete.
*
* Dallojme metoden paintComponent qe vjen nga framework-u AWT apo Abstract Window Toolkit nga Java, per paraqitjen grafike
* ne panel apo dritare. Metoda ne parameter pranon nje Graphics komponent qe trashegohet nga klasa JComponent per
* te vizatuar grafet te cilat deshirojme.
* Pastaj kemi krijimin e nje objekti Graphics2D qe vjen nga klasa Graphics2D qe trashegon nga klasa Graphics, qe ndihmon
* ne paraqitjen me te sofistikuara grafike te gjeometrise, te ciles i behet typecast parametri "g" nga Graphics.
*
* Behet krijimi dy(2) for loops te cilat jane nested me kompleksitet kohor O(n^2) ne te cilat do te behet ngjyrosja e komponenteve.
* Per secilin komponent do te behet ngjyrosja e nyjes apo node-it qe i caktohet, aty vendosen disa kushte per te
* percaktuar nese nyja ne fjale eshte: Rruga qe konsiderohet, nyje fqinje, nyja startuese, nyja fundore, nyje pengese apo
* eshte rruga me e shkurter e gjetur.
*
* Permes objektit "g2d" ne ngjyrosim nyjet te cilat plotsojne kushtin e caktuar.
* setColor - mundeson vendosjen e ngjyres per nje node apo nyje te caktuar, cila do qofte ajo.
* fillRoundRect - ku do te behet vendosja e nyjes se ngjyrosur dhe ne cfare forme do te vendoset, ku kemi:
* x - kordinata X e drejtekendeshit qe do te plotesohet,
* y - kordinata Y e drejtekendeshit qe do te plotesohet,
* width - gjeresia e drejtkendeshit qe do te mbushet,
* height - gjatesia e drejtkendeshit qe do te mbushet,
* arcWidth - diametri horizontal i harkut ne kater qoshet,
* arcHeight - diametri vertikal i harkut ne kater qoshet.
*
* Pastaj kur dalim nga te dy(2) for loops, kemi dy(2) for loops tjera me te cilat do te behet vizatimi
* i rrjetes tone. Vendosim ngjyren qe deshirojme te krijojme vijat e rrjetes, for loop-i i pare ngjyrose
* te gjitha rreshtat, ndersa for loop-i i dyte ngjyrose te gjitha kolonat.
*
* Me vone, shohim metoden restarto, ne te cilen momentin kur behet trigger, ndrrohet gjendja e eshteNeVeprim
* pasi qe personi kontrollues i programit i mundesohet te ndermarre veprime. Ne momentin e ristartimit,
* behet inicializimi i nyjeve te reja njejte si me pare, ne rreshtat dhe kolonat e caktuara ne rrjet.
* Dhe njejte behet edhe gjetja e nyjeve fqinje per nje nyje te caktuar, ku mirren te gjitha nyjet fqinje
* ekzistuese ne rrjet.
*
* Ndryshohet vlera e nyjeve si fillimi, fundi, kaCaktuarFillimin, kaCaktuarFundin dhe krijohet instacimi i algoritmit
* ku duhet te rifilloj pas restartimit.
*
* Pastaj kemi metodat te cilat vijne nga implementimi i interface-ave si MouseListener, MouseMotionListener dhe KeyListener.
* Metoda mouseClicked pranon ne parameter nje event te mouse, parameter i cili do te perdoret per gjetjen
* e kordinatave per rreshtat dhe kolonat.
*
* Fillimisht behet nje check nese po klikohet ndonje buton, eshteNeVeprim eshte true dhe rreshti ndodhet
* brenda kufinjeve te rrjetes apo grid-it dhe rreshti eshte me i vogel se gjatesia totale e 2D array me emrin nyjet dhe
* kolona eshte brenda kufinjeve te rrjetes apo grid-it dhe kolona eshte me e vogel se gjatesia e kolones se pare ne rrjet.
*
* Pastaj kemi nje nested if, i cili duhet te kontrolloj nese nuk ka cekur fillimin e nyjet, futet ne funksion ku
* fillimi caktohet ne baze te rreshtit dhe kolones per te cilat kemi gjetur x-in dhe y-in ne rrjete,
* fillimit i vendosim gjendjen eshteFillimi pasi qe aty vendoset nyja startuese, ose perndryshe nese nuk do te hynte ne kushtin if,
* atehere do te benim nje check nese nuk eshte caktuar fundi DHE nyja ne pozicionin e caktuar ne array nuk eshte fillimi,
* dhe nyja e fillimit nuk eshte afer me nyjen ne pozicionin e caktuar ne array. Nese kushti plotesohet,
* nyja fundore do te jete nyja qe eshte e vendosur ne pozicionin aktual ne array, gjendja e nyjes fundore do te vendsoet ne eshteFundi true,
* dhe gjendja e kaCaktuarFundin do te vendoset ne true.
*
* Ne fund do therrasim metoden repaint qe vjen nga framework-u AWT, nga Graphics.
*
* Tani kemi metoden e dyte e cila eshte e implementuar nga interface-at, e cila do te detektoj nese mausi terhiqet i shtypur ne dritare,
* Njejte gjejme kordinatat per rreshtat dhe kolonat permes parametrit te derguar MouseEvent, behet nje check
* nese nuk eshte caktuar fillimi atehere njejte caktohet fillimi si me pare, nese nuk hyn ne if atehere
* caktohet fundi apo nyja fundore dhe i nderrohen gjendjet.
*
* Pastaj kemi nje check ku shikojme nese eshte caktuar fundi dhe fillimi, nyja aktuale ne array nuk eshte fillimi apo fundi,
* nyja nuk eshte ne veprim e siper dhe personi kontrollues i programit po ndermerr nje veprim apo aksion,
* atehere nyja ne poziten e caktuar ne array merr gjendjen se eshtePengese dhe thirret metoda repaint per te ngjyrosur pengesat.
*
*
* Dhe metoda e trete(3) dhe e fundit e cila perdoret ku mirret nga interface-at e implementuara eshte keyPressed, e cila
* detekton nese nje tast ne tastiere eshte shtypur. Permes kesaj metode neve na mundesohet te nisim dhe restartojme programin.
*
* Ne check-un e pare, shikohet nese po klikohet tasti SPACE dhe nese eshte gati per veprim, nese po atehere
* thirret algoritmi bashk me metoden e tij A_Star ku i dergohen dy(2) parametra, te cilat jane nyja startuese e fillimit dhe
* nyja fundore apo perfundimtare e fundit.
*
* Ne check-un e dyte, shikohet nese po klikohet tasti R, nese po atehere thirret metoda restarto dhe restartohet programi,
* ne fund thirret metoda repaint per ta ngjyrosur grafikun cili do kusht qe plotsohet.
*
* */

public class Visualizimi extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    static Visualizimi instanca;
    static boolean eshteNeVeprim = true;

    int gjatesia = 15;
    int gjeresia = 15;
    boolean kaCaktuarFillimin, kaCaktuarFundin;

    Node[][] nyjet = new Node[50][50];
    Node fillimi, fundi;
    Algoritmi algoritmi = new Algoritmi();

    public static Visualizimi getInstanca() {
        if (instanca == null)
            instanca = new Visualizimi();
        return instanca;
    }

    private Visualizimi() {

        this.setBackground(new Color(192, 190, 190));
        this.setFocusable(true);
        this.requestFocusInWindow();

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);


        for (int rreshti = 0; rreshti < nyjet.length; rreshti++) {
            for (int kolona = 0; kolona < nyjet[rreshti].length; kolona++) {
                nyjet[rreshti][kolona] = new Node(rreshti, kolona);
            }
        }
       
        for (int rreshti = 0; rreshti < nyjet.length; rreshti++) {
            for (int kolona = 0; kolona < nyjet[rreshti].length; kolona++) {
                nyjet[rreshti][kolona].merrSkajet(nyjet);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int rreshti = 0; rreshti < nyjet.length; rreshti++) {
            for (int kolona = 0; kolona < nyjet[rreshti].length; kolona++) {
                if (nyjet[rreshti][kolona].eshteVizituar && !nyjet[rreshti][kolona].neRruge) {
                    g2d.setColor(new Color(41, 155, 195));
                    g2d.fillRoundRect(kolona * gjeresia, rreshti * gjatesia, gjeresia, gjatesia, 0, 0);
                }
                if (nyjet[rreshti][kolona].neGrupinEHapur && !nyjet[rreshti][kolona].eshteVizituar) {
                    g2d.setColor(new Color(105,193,0));
                    g2d.fillRoundRect(kolona * gjeresia + (gjeresia * 1/5), rreshti * gjatesia + (gjatesia * 1/5),
                            (int)(gjeresia * 3/5), (int)(gjatesia * 3/5),2,2);
                }
                if (nyjet[rreshti][kolona].eshteFillimi) {
                    g2d.setColor(new Color(0, 255, 0));
                    g2d.fillRoundRect(kolona * gjeresia, rreshti * gjatesia, gjeresia, gjatesia,25,25);
                }
                if (nyjet[rreshti][kolona].eshteFundi) {
                    g2d.setColor(new Color(255, 0, 0));
                    g2d.fillRoundRect(kolona * gjeresia, rreshti * gjatesia, gjeresia, gjatesia, 25, 25);
                }
                if (nyjet[rreshti][kolona].eshtePengese) {
                    g2d.setColor(new Color(0, 0, 0));
                    g2d.fillRect(kolona * gjeresia, rreshti * gjatesia, gjeresia, gjatesia);
                }
                if (nyjet[rreshti][kolona].neRruge) {
                    g2d.setColor(new Color(80, 226, 80, 255));
                    g2d.fillRoundRect(kolona * gjeresia, rreshti * gjatesia, gjeresia, gjatesia, 35, 35);
                }
            }
        }

    }


    public void restarto() {

        eshteNeVeprim = true;

        for (int rreshti = 0; rreshti < nyjet.length; rreshti++) {
            for (int kolona = 0; kolona < nyjet[rreshti].length; kolona++) {
                nyjet[rreshti][kolona] = new Node(rreshti, kolona);
            }
        }

        for (int rreshti = 0; rreshti < nyjet.length; rreshti++) {
            for (int kolona = 0; kolona < nyjet[rreshti].length; kolona++) {
                nyjet[rreshti][kolona].merrSkajet(nyjet);
            }
        }
        fillimi = null;
        fundi = null;
        kaCaktuarFillimin = false;
        kaCaktuarFundin = false;
        algoritmi = new Algoritmi();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int rreshti = e.getY() / gjatesia;
        int kolona = e.getX() / gjeresia;

        if (e.getButton() == MouseEvent.BUTTON1 && eshteNeVeprim &&
                rreshti >= 0 && rreshti < nyjet.length &&
                kolona >= 0 && kolona < nyjet[0].length) {

            if (!kaCaktuarFillimin) {
                fillimi = nyjet[rreshti][kolona];
                fillimi.eshteFillimi = true;
                kaCaktuarFillimin = true;
            } else if (!kaCaktuarFundin && !nyjet[rreshti][kolona].eshteFillimi && !fillimi.eshteAferMe(nyjet[rreshti][kolona])) {
                fundi = nyjet[rreshti][kolona];
                fundi.eshteFundi = true;
                kaCaktuarFundin = true;
            }
        }
        repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int rreshti = e.getY() / gjatesia;
        int kolona = e.getX() / gjeresia;


        if (!kaCaktuarFillimin) {
            fillimi = nyjet[rreshti][kolona];
            fillimi.eshteFillimi = true;
            kaCaktuarFillimin = true;
        } else if (!kaCaktuarFundin && !nyjet[rreshti][kolona].eshteFillimi && !fillimi.eshteAferMe(nyjet[rreshti][kolona])) {
            fundi = nyjet[rreshti][kolona];
            fundi.eshteFundi = true;
            kaCaktuarFundin = true;
        }



        if (kaCaktuarFillimin && kaCaktuarFundin &&
                !nyjet[rreshti][kolona].eshteFillimi && !nyjet[rreshti][kolona].eshteFundi &&
                !nyjet[rreshti][kolona].eshtePengese && eshteNeVeprim)
            nyjet[rreshti][kolona].eshtePengese = true;

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && eshteNeVeprim) {
            algoritmi.A_Star(fillimi, fundi);
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            restarto();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}


}
