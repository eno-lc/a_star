import java.util.PriorityQueue;
import javax.swing.Timer;
/*
* Ne klasen Algoritmi, ne kemi bere implementimin e algoritmit A-Star apo A*.
* Ne implementimin tone ne kemi perdorur nje PriorityQueue per te bere perzgjedhjen e perseritur te nyjeve apo nodave
* me kosto minimale per tu zgjeruar apo per te kaluar neper rruge deri ne gjetjen e rruges me te shkurt. Ne kete PriorityQueue
* ne do te vendosim nyjet te cilat jane ende te pa vizituara dhe jane ne dispozicion per tu shqyrtuar.
*
* Kemi krijuar metoden e algoritmit me emrin A_Star e cila si parametra pranon dy(2) nyje apo nodes, te cilat do te paraqesin
* nyjen startuese apo filluese dhe nyjen perfundimtare apo fundore.
* Fillimisht vendosim vleren e G-cost ne zero(0) pasi qe nyja startuese ka distance larg vetes me vlere 0.
* Vendosim vleren e H-cost duke perdorur metoden ktheDistancen mbi nyjen startuese dhe si parameter i dergohet nyja fundore,
* ku duhet te kalkulohet rruga nga fillimi tek fundi.
* Vendosim vleren e F-cost te barabarte me shumen e G-cost dhe H-cost.
* Shtojme ne grupin e hapur apo priorityqueue nyjen startuese sepse eshte tashme e vizituar sepse eshte vetvetja.
*
* Pastaj kemi kohematesin i cili vjen nga klasa Timer nga framework-u Java Swing, i cili gjeneron nje ose me shume veprime
* apo aksione gjate nje intervali te caktuar kohor, varesisht nga vonesa qe i caktohet. Aksionet te cilat gjenerohen brenda ketij
* kohematsi mund te jene te ndryshme, ne rastin tone eshte ekzekutimi i algoritmit, ku pranon si parameter nje integer me emrin delay
* i cili tregon kohen ne milisekonda ne te cilen do te ndermirret aksioni, 1000ms=1s, nese e vendosim vonesen ne 1000ms atehere cdo
* nje(1) sekond do te kemi nderrmarje te nje aksioni, ndersa parametri i dyte qe i dergohet eshte vet aksioni i cili do te ndermirret.
*
* Pasi fillon ndermarrja e aksionit te derguar ne parameter, fillimisht behet nje check nese grupiHapur apo priorityQueue eshte e zbrazet,
* nese po atehere vendosim nyjen aktuale ne grupin e hapur permes metodes poll, metode e cila perdore per te marre dhe hequr elementin e pare
* ne priorityQueue ose elementin ne krye te rradhes apo queue.
*
* Pastaj behet nje check i dyte ku shikojme nese nyja aktuale eshte nyja fundore apo perfundimtare, nese po atehere ne nderprejme levizjet ne
* visualizim, thirret metoda e kthimit prapa apo backtrack e cila pasi behet restart algoritmi fillon nga fillimi, dhe ndalojme kohematesin
* pasi qe visualizimi ka ndaluar.
*
* Se treti, kemi nje check tjeter, ku shikojme se nese nyja aktuale nuk eshte vizituar, nese po, i vendosim nyjes aktuale gjendjen si e vizituar,
* krijojme nje for loop e cila do te iteroj varesisht nga numri i fqinjeve te nyjes aktuale, ne raste bazike do te jete 9 here por nese nyja
* eshte e vendosur ne qoshe te rrjetes atehere ai numer i iterimeve mund te ndryshoj. Krijohet nje nyjeSkajore te ciles i vendoset vlera
* e numrit se ku gjindet ne rrjete permes for loop.
*
* Pastaj bejme nje check tjeter, ku shikojme nese nyja skajore nuk eshte vizituar DHE nyja skajore nuk eshte pengese, nese po
* atehere krijohen ne variabel e tipit double qe do te tregoj cmimin e ri te nyjes, dhe behet nje check tjeter ku shikojme se nese
* nyja aktuale ka ne diagonale nyjen skajore permes metodes eshteDiagonale. Nese po atehere cmimi i ri i cili i vendoset G-costit
* eshte 1.4 per arsye se distanca diagonale nga nyja aktuale tek nyja fqinje eshte rrenja e 2 apo 1.4, ndersa nese kushti nuk plotesohet
* dhe nyja aktuale ka si nyje fqinje jo diagonale nyjen skajore atehere vlera qe i vendoset G-cost eshte 1, per arsye se eshte ne vendosje kordinative
* ne rrjete ne kordinatat x dhe y.
*
* Pastaj marrim vleren e H-costit nga nyja skajore apo fqinje dhe ja vendosim vleren e distances prej nyjes skajore deri te nyja fundore apo perfundimtare.
*
* Krijohet nje variable e re e tipit double per te gjeneruar vleren totale te F-cost, e cila eshte e barabarte me cmimin e ri
* i cili reprezenton vleren e G-costit dhe me vleren e H-costit te vleres skajore qe reprezenton distancen nga nyja skajore deri tek nyja fundore.
*
* Pastaj kemi nje check tjeter, i cili kontrollon nese totali i ri apo vlera e F-costit eshte me e vogel se vlera e F-costit aktual per nyjen
* skajore apo fqinje, nese po atehere vleres se G-costit per nyjen skajore i vendoset cmimi i ri, vleres se F-costit per nyjen skajore
* i vendoset shuma e cila vie nga vlera e G-costit per nyjen skajore dhe vlera e H-costit per nyjen skajore.
*
* Nyja skajore si prind me vlerat e F-cost, G-cost dhe H-cost behet nyje aktuale per gjetje te rruges.
*
* Shtojme nyjen aktuale ne grupin e hapur apo priority queue, dhe i vendoset gjendja qe tregon qe eshte antare i grupit te hapur.
*
* Pastaj thirret klasa e Visualizimit te ciles i kerkohet instanca dhe i dergohet metoda repaint qe vjen nga klasa paintComponents
* qe vjen nga framework-u Java Swing.
*
* Dhe ne fund kohematsi starton.
*
* Ne fund kemi metoden kthimiPrapa apo ne algoritem sic njohim backtracking, e cila si parameter pranon nje nyje apo node fundi,
* e cila shikon se kur nyja aktuale eshte e barabarte me nyjen fundore apo ndodhet ne pozicionin e saj, atehere fillon iterimi per
* sa kohe nyja aktuale nuk eshte ne fillim, ku nyjes aktuale i vendoset gjendja ne rruge ne true, ku tregon qe algoritmi eshte ne proces
* e siper, dhe nyja aktuale behet nyje kryesore udhetuese apo prind.
* */

public class Algoritmi  {

    PriorityQueue<Node> grupiHapur = new PriorityQueue<>();
    Timer koheMatesi;


    public void A_Star(Node fillimi, Node fundi){
        fillimi.G = 0;
        fillimi.H = fillimi.ktheDistancen(fundi);
        fillimi.F = fillimi.G + fillimi.H;
        grupiHapur.add(fillimi);

        koheMatesi = new Timer(1, e -> {

            if(!grupiHapur.isEmpty()){
                Node nyjaAktuale = grupiHapur.poll();

                if(nyjaAktuale.equals(fundi)){
                    Visualizimi.eshteNeVeprim = false;
                    kthimiPrapa(nyjaAktuale);
                    koheMatesi.stop();
                }

                if(!nyjaAktuale.eshteVizituar){
                    nyjaAktuale.eshteVizituar = true;
                    for(int i = 0; i< nyjaAktuale.skajet.size(); i++){
                        Node nyjaSkajore = nyjaAktuale.skajet.get(i);
                            if(!nyjaSkajore.eshteVizituar && !nyjaSkajore.eshtePengese){
                                double cmimiRi;
                                    if(nyjaAktuale.eshteDiagonale(nyjaSkajore)){
                                        cmimiRi = nyjaAktuale.G + 1.4;
                                    }else{
                                        cmimiRi = nyjaAktuale.G + 1;
                                    }
                                        nyjaSkajore.H = nyjaSkajore.ktheDistancen(fundi);

                                        double totaliRi = cmimiRi + nyjaSkajore.H;
                                        if(totaliRi < nyjaSkajore.F){
                                            nyjaSkajore.G = cmimiRi;
                                            nyjaSkajore.F = nyjaSkajore.G + nyjaSkajore.H;
                                            nyjaSkajore.Prindi = nyjaAktuale;
                                            grupiHapur.add(nyjaSkajore);
                                            nyjaSkajore.neGrupinEHapur = true;
                                        }
                            }
                    }
                    Visualizimi.getInstanca().repaint();
                }
            }
        });
        koheMatesi.start();
    }


    private void kthimiPrapa(Node fundi){
        Node nyjaAktuale = fundi.Prindi;
        while(!nyjaAktuale.eshteFillimi){
            nyjaAktuale.neRruge = true;
            nyjaAktuale = nyjaAktuale.Prindi;
        }
    }

}
