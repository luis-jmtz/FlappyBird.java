import java.awt.*; //provides classes for creating and managing GUI components 
import java.awt.event.*; // contains classes and interfaces for handling various events in 
//AWT-based applications, such as user interactions. For example, classes like ActionEvent
import java.util.ArrayList;
import java.util.Random;
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
    Timer placePipesTimer;

    int velocityX = -4; //this is the rate that the pipes move every frame
    int velocityY = 0; // the game starts with the bird just falling downwards
    // an X velocity is not required because the pipes actually move towards the bird
    int gravity = 1; //every frame the bird will move down my one pixel
    
    ArrayList<Pipe> pipes;
    Random random = new Random();


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

        pipes = new ArrayList<Pipe>();

        //place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){placePipes();;}});

        placePipesTimer.start();
        
        
        //game timer
        gameLoop = new Timer(1000/60, this); //timer determines how often the program should repeat an action
        // the first parameters is in milliseconds. Since the game should be 60 fps, we have it update 60 times in a second
        // 1000 milliseconds =  1 second

        gameLoop.start();

    }
    


    public void placePipes(){
        int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random()* pipeHeight/2);
        
        Pipe topPipe = new Pipe(topPipeIMG);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);}

    //methods that paints/loads the images into the JLabel
    public void paintComponent(Graphics g){
        super.paintComponent(g); //evokes the paint function from JPanel
        draw(g);}

    public void draw(Graphics g){
        //System.out.println("draw"); //basic debug to make sure gameloop is running
        
        g.drawImage(backgroundImg, 0,0, boardWidth, boardHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        //^ this is the reason that bird is stored as a class as it is easier to refer to the elements

        //pipes
        for(int i = 0; i < pipes.size(); i++ ){
            Pipe pipe =  pipes.get(i);
            g.drawImage(pipe.img, pipe.x,pipe.y,pipe.width,pipe.height, null);
        }
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
        bird.y = Math.min(bird.y, 616);// limits the max jump height of the bird

        //pipes
        for(int i = 0; i < pipes.size(); i++ ){
            Pipe pipe =  pipes.get(i);
            pipe.x += velocityX; //every frame the pipe moves 4 pixels to the left
        }
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

    //pipe class
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;  //scaled by 1/6
    int pipeHeight = 512;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img) {
            this.img = img;
        }
    }
        

        





}
