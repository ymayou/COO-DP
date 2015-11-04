/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Flo
 */
public class Emmeteur implements Runnable{
    private PrintWriter out;
    private Scanner sc = null;
    private String message = null;

    public Emmeteur(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        while(true){
            out.println(message);
            out.flush();
        }
    }
    
    
}
