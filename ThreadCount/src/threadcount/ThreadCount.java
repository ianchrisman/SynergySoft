package threadcount;
//Ian Chrisman
import java.awt.*; 
import javax.swing.*;
import javax.swing.JTable;

class ThreadCount {

    public static void main(String[] args) {
        //CaveGUI caveGame = new CaveGUI();
        ThreadGUI frame = new ThreadGUI();
        System.out.println("got Here main");
        ThreadGUI.createGUI();
    }
    
}
