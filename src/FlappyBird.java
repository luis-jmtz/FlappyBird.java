import java.awt.*; //provides classes for creating and managing GUI components 
import java.awt.event.*; // contains classes and interfaces for handling various events in 
//AWT-based applications, such as user interactions. For example, classes like ActionEvent
import java.util.ArrayList;
import java.util.random.*;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    //basically makes the class a JPanel object ^
    //the listeners actually allow the computer to detect inputs from the user and the program

    int boardWidth = 360;
    int boardHeight = 640;

    //variables for the images
    Image backgroundImg ;
    Image birdIMG;
    Image topPipeIMG;
    Image  bottomPipeIMG;


    //Game Logic variables
    Bird bird;
    Timer gameLoop;
    int velocityY = 0; // the game starts with the bird just falling downwards
    // an X velocity is not required because the pipes actually move towards the bird
    int gravity = 1; //every frame the bird will move down my one pixel


    FlappyBird(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //setBackground(Color.BLUE);

        setFocusable(true); //makes sure the the flappybird panel is the one that is being checked for the key events
        addKeyListener(this); //makes sure the keylisteners are checked

        //load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        //because I used getClass() I only needed the file name as the path for the image
        birdIMG = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeIMG = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeIMG = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //bird
        bird = new Bird(birdIMG);

        //game timer
        gameLoop = new Timer(1000/60, this); //timer determines how often the program should repeat an action
        // the first parameters is in milliseconds. Since the game should be 60 fps, we have it update 60 times in a second
        // 1000 milliseconds =  1 second

        gameLoop.start();

    }
    


    //methods that paints/loads the images into the JLabel
    public void paintComponent(Graphics g){
        super.paintComponent(g); //evokes the paint function from JPanel
        draw(g);}

    public void draw(Graphics g){
        //System.out.println("draw"); //basic debug to make sure gameloop is running
        
        g.drawImage(backgroundImg, 0,0, boardWidth, boardHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        //^ this is the reason that bird is stored as a class as it is easier to refer to the elements
        // I'm not going to change it because I'm following a tutorial, but I would personally remove the bird variables and instead store them in the bird class

        //the x and y are the starting position and the next two variables refer to the size of the image
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this will be action(s) performed 60 times per second
        move();// moves the images of their location has been updated
        repaint(); //runs the paintComponent method again
    }

    //Methods for keyboard inputs
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){velocityY = - 9;}
        // when any key is pressed, if it is the space base bar the bird jumps
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    //moves the bird
    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);// limits the max jump height of the bird
    }

    //Bird Variables
    int birdX = boardWidth/8; //places the bird on the left side of the screen
    int birdY = boardHeight/2; // at the start of the game, the bird starts in the midddle of the screen
    int birdWidth = 34; // in pixels
    int birdHeight = 24;

    
        //Class to hold bird values
        class Bird{
            int x = birdX;
            int y = birdY;
            int width = birdWidth;
            int height = birdHeight;
            Image img;
                //constructor
                Bird(Image img){this.img = img;}}


        





}
