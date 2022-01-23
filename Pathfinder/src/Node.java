import java.util.ArrayList;

/*
* Ne klasen Node apo Nyje, ne fillimisht implementojme interface-in Comparable ku si parameter te formes gjenerike
* ia dergojme klasen Node apo Nyje. Permes interface-it ne mund t'i qasemi metodave te interface-it Comperable.
*
* Fillimisht kemi deklaruar disa variabla te tipi boolean me te cilat do te tregojme gjendjen per nyjet e caktuara,
*
* Pastaj kemi deklaruar dhe inicializuar vlerat per G-cost, H-cost dhe F-cost. Vlera te cilat duhet te bazohen ne madhesine e
* rrjetes se krijuar nga nje 2D Array e cila eshte e perbere nga 50 rreshta dhe 50 kolona qe ne total do te kete 2500 qeli, qe duhet
* te jete vlera maksimale nese eshte nevoja per te ecur neper te gjitha qelite ne rrjete.
*
* Kemi deklaruar variablat e tipit integer per rreshtat dhe kolonat qe duhet te jene ne rrjeten tone.
*
* Kemi deklaruar nje variabel te tipit Node qe paraqet nyjen prind.
*
* Kemi deklaruar nje arraylist ku ne parametrin e formes gjenerike kemi derguar klasen Node apo Nyjen, e cila do te
* pranoj nyjet apo vijat nderprerese te qelizes ne rrejte.
*
* Kemi krijuar konstruktorin i cili do te pranoj rreshtat dhe kolonat per krijimin e rrjetes dhe qelizave te saj.
*
* Kemi krijuar metoden ktheDistancen e cila si parameter pranon nje nyje nga klasa Node apo Nyje, ku do te perdoret
* per llogaritjen e H-costit ku do te llogaritet vlera e distances nga nyja startuese deri te nyja fundore, te ciles do ti llogaritet
* rrenja katrore dhe do te ktheje nje vlere double.
*
* Kemi krijuar metoden eshteDiagonale e kthen nje vlere boolean true apo false, qe detekton nese ekziston nje rruge me e shpejte
* diagonale nga fillimi tek fundi. Pranon nje parameter node apo nyje te klases Node apo Nyje.
* Ky format i levizjes apo heuristikes perdoret ne raste te levizjes kur kemi 8 drejtime ku kostoja e levizjes diagonale ndryshon
* nga kostoja jo-diagonale. Paraqitet nevoja per perdorimin e nje vlere konstante per koston e distances diagonale se sa te perdoret
* operacioni i rrenjes katrore qe mund te na kushtoj ne kohe dhe hapesire. Ne metode kemi 4 kushte ku secila nga to reprezenton njeren
* nga qelizat diagonale nga pozicioni i nyjes aktuale. Tic Tac Toe Example -1 ne rresht dhe -1 ne kolon, kalon ne diagonale.
*
* Kemi krijuar metoden merrSkajet e cila supozohet te kthej te gjitha nyjet qe jane fqinje dhe jane brenda rrjetes me vlere pozitive ku
* jane krijuar 8 kushte per arsye se maximumi i nyjeve fqinje qe mund te kete nje nyje eshte 8, keto 8 te cilat e rrethojne po ate nyje.
*
* Kemi krijuar metoden eshteAferMe, e cila si parameter pranon nje node/nyje nga klasa Node apo nyje. Metoda shikon per nyjet fqinje
* qe mund ti kete afer vetes dhe tregon nese eshte me te vertete fqinje, nese po kthen true, nese jo kthen false.
*
* Ne fund kemi metoden compareTo e cila vjen nga Interface Comparable, permes kesaj metode ne shikojme per nyjet me F-cost me te vogel,
* metoda kthen nje vlere integer, pra ne rastet kur kemi nje F-cost me te madh se sa base F-cost apo F-cost aktual metoda kthen -1,
* ndersa kur kemi F-cost me te vogel se sa base F-cost apo F-cost aktual metoda kthen 1 ku duhet te ndjekim ate nyje me ate F-cost.
*/


public class Node implements Comparable<Node> {

    boolean eshteVizituar, eshteFillimi, eshteFundi, eshtePengese, neRruge, neGrupinEHapur;
    double G = 2501, H = 2501, F = G + H;
    int rreshti, kolona;

    Node Prindi;
    ArrayList<Node> skajet = new ArrayList<>();

    public Node(int rreshti, int kolona) {
        this.rreshti = rreshti;
        this.kolona = kolona;
    }

    public double ktheDistancen(Node node) {
        return Math.sqrt((node.rreshti - this.rreshti)*(node.rreshti - this.rreshti) + (node.kolona - this.kolona)*(node.kolona - this.kolona));
    }

    public boolean eshteDiagonale(Node node) {
        if(node.rreshti == this.rreshti - 1 && node.kolona == this.kolona -1)
            return true;
        if(node.rreshti == this.rreshti - 1 && node.kolona == this.kolona +1)
            return true;
        if(node.rreshti == this.rreshti + 1 && node.kolona == this.kolona -1)
            return true;
        if(node.rreshti == this.rreshti + 1 && node.kolona == this.kolona +1)
            return true;
        return false;
    }

    public void merrSkajet(Node[][] nodes) {
        if(rreshti - 1 >= 0 && kolona - 1 >= 0)
            skajet.add(nodes[rreshti-1][kolona-1]);
        if(rreshti - 1 >= 0)
            skajet.add(nodes[rreshti-1][kolona]);
        if(rreshti - 1 >= 0 && kolona + 1 < nodes[0].length)
            skajet.add(nodes[rreshti-1][kolona+1]);
        if(kolona - 1 >= 0)
            skajet.add(nodes[rreshti][kolona-1]);
        if(kolona + 1 < nodes[0].length)
            skajet.add(nodes[rreshti][kolona+1]);
        if(rreshti + 1 < nodes.length && kolona - 1 >= 0)
            skajet.add(nodes[rreshti+1][kolona-1]);
        if(rreshti + 1 < nodes.length)
            skajet.add(nodes[rreshti+1][kolona]);
        if(rreshti + 1 < nodes.length && kolona + 1 < nodes[0].length)
            skajet.add(nodes[rreshti+1][kolona+1]);
    }

    public boolean eshteAferMe(Node node){
        if(rreshti == node.rreshti-1 && kolona == node.kolona-1 ||
                rreshti == node.rreshti-1 && kolona == node.kolona   ||
                rreshti == node.rreshti-1 && kolona == node.kolona+1 ||
                rreshti == node.rreshti   && kolona == node.kolona-1 ||
                rreshti == node.rreshti   && kolona == node.kolona+1 ||
                rreshti == node.rreshti+1 && kolona == node.kolona-1 ||
                rreshti == node.rreshti+1 && kolona == node.kolona   ||
                rreshti == node.rreshti+1 && kolona == node.kolona+1)
            return true;
        return false;
    }

    @Override
    public int compareTo(Node node) {
        if (node.F > this.F)
            return -1;
        if (node.F < this.F)
            return 1;
        return 0;
    }

}
