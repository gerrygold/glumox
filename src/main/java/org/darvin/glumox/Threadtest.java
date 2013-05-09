/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// Added line for git
/**
 *
 * @author ggoldman
 */
public class Threadtest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String testfile1="";
        String testfile2="";
        try {
            String filePath = (Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getResource("")).getFile(); 
            System.out.println(filePath);
             testfile1 = filePath + "/smalltext.txt";
             testfile2 = filePath + "/largetext.txt";
            
            File f = new File(testfile1);
            if (f.exists()) {
                 System.out.println(testfile1 + " exists");
            } else {
                System.out.println(testfile1 + " does not exist");
            } 
                
             f = new File(testfile2);
            if (f.exists()) {
                 System.out.println(testfile2 + " exists");
            } else {
                System.out.println(testfile2 + " does not exist");
            }
              Regexer r = new Regexer(testfile1);
            ArrayList<MatchPair> list1 = r.matchem("ab."); //match ab


            for (MatchPair m : list1) {
                System.out.println("Match1: " + m.matchtext + "|" + m.offset);
            }

            ArrayList<MatchPair> list2 = r.matchem("a(?!b)"); //match a not follwed by b


            for (MatchPair m : list2) {
                System.out.println("Match2: " + m.matchtext + "|" + m.offset);
            }
        } catch (IOException ex) {
            Logger.getLogger(Threadtest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Threadtest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            RegThread rt = new RegThread(testfile1, 1);
            RegThread rt2 = new RegThread(testfile2, 1);
            rt2 = new RegThread(testfile2, 10);
            rt2 = new RegThread(testfile2, 100);
            rt2 = new RegThread(testfile2, 1000);

        } catch (Exception ex) {
            Logger.getLogger(Threadtest.class.getName()).log(Level.SEVERE, null, ex);
        }





    }
}
