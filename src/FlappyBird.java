import java.awt.*; //provides classes for creating and managing GUI components 
import java.awt.event.*; // contains classes and interfaces for handling various events in 
//AWT-based applications, such as user interactions. For example, classes like ActionEvent
import java.util.ArrayList;
import java.util.random.*;
import javax.swing.*;

public class FlappyBird extends JPanel{
    //basically makes the class a JPanel object ^

    int boardWidth = 360;
    int boardHeight = 640;

    //variables for the images
    Image backgroundImg ;
    Image birdIMG;
    Image topPipeIMG;
    Image  bottomPipeIMG;


    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //setBackground(Color.BLUE);

        //load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        //because I used getClass() I only needed the file name as the path for the image
        birdIMG = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeIMG = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeIMG = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        

    }
    


    //methods that paints/loads the images into the JLabel
    public void paintComponent(Graphics g){
        super.paintComponent(g); //evokes the paint function from JPanel
        draw(g);}

    public void draw(Graphics g){
        g.drawImage(backgroundImg, 0,0, boardWidth, boardHeight, null);
    }






}
