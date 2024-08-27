import javax.swing.*;


public class App {

    public static void main(String[] args) throws Exception {
    
        int boardWidth = 360;
        int boardHeight = 640; //integers are in pixels

        JFrame frame = new JFrame("Flappy Bird - Code Practice"); 
        //frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null); //places the window at the center of the screen
        frame.setResizable(false);//keeps user from resizing screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Frame Created Successfully");
        
        //adding the FlappyBird class
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();


        
        frame.setVisible(true);
    }















}
