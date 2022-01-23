import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.util.Objects;

/*
* Klasa Dritare eshte klasa e cila na mundeson krijimin e dritares e cila na shfaq programin dhe vizualizimin e algoritmit,
* klasa Dritare trashegon funksionalitetin nga klasa JFrame qe vjen nga nje kornize apo framework e Java Swing, si nje toolkit
* e cila mundeson perdorimin e disa API apo Application Programming Interfaces qe ne kete rast mundeson vizualizimin e algoritmit tone.
* Ne konstruktorin e klases Dritare, ne shtojme funksionalitetet e nevojshme qe duhen inicializuar ne startup te programit,
* vendosim në dritare një instancë të klases se vizualizimit e cila do të vizualizohet, vendosim një titull për dritaren,
* vendosim funksionin e mbylljes së dritarës në momentin që personi kontrollues klikon në X,
* vendoset lartësia dhe gjerësia e dritares, vendosim një foto si ikonë të dritares, vendosim funksionalitetin që dritarja të mos lejoj ndryshimin e përmasave,
* vendosim paraqitjen e dritarës,pastaj duhet të vendosim kërkesën “pack”,
* e cila në një lloj forme paketon çdo gjë dhe pastaj vendosim lokacionin se ku do të pozicionohet dritarja në momentin e hapjes.
* */
public class Dritare extends JFrame {

    public Dritare(){
        add(Visualizimi.getInstanca());
        setTitle("Gjetja e rruges me te shkurte - A*");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(750,750));
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("astar.jpg"))).getImage());
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);


    }

    /*
    * Ne metoden apo thread-in main ekzekutohet programi ku thirret nje instance e klases Dritare.
    */

    public static void main(String[] args) {
        new Dritare();
    }
}














