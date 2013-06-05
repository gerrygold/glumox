/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author ggoldman
 */
public class InterruptTest extends TestCase {

    String largeText = "";
    static final int LARGECHK = 100000;
    static final String CHK = "abcdefghijklmnopqrstuvwyz";

    public InterruptTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        StringBuilder sb = new StringBuilder();
        for (int ff = 0; ff < LARGECHK; ff++) {
            sb.append(CHK);
        }
        largeText = sb.toString();
        System.out.println("Large text length=" + largeText.length());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of replaceAll method, of class InterruptibleCharSequence.
     */
    public void testReplaceAll() {
        System.out.println("replaceAll");
        String regex = "abc";
        String replacement = "";
        InterruptibleCharSequence instance = new InterruptibleCharSequence(largeText);
        String result = instance.replaceAll(regex, replacement);
        int expectedResultSize = (CHK.length() - 3) * LARGECHK;
        assertEquals(expectedResultSize, result.length());
        // TODO review the generated test code and remove the default call to fail.   
    }

    public void testReplaceInt() {
        System.out.println("testReplaceInt - Thread completes before interrupt");
        final String regex = "abc";
        final String replacement = "";
        final InterruptibleCharSequence instance = new InterruptibleCharSequence(largeText);
        Thread testThread;
        testThread = new Thread() {
            @Override
            public void run() {
                try {
                    String res = instance.replaceAll(regex, replacement);
                } catch (Exception ex) {
                    System.out.println("Caught exception:" + ex.getMessage());
                    System.out.println("instance.isInterrupted: " + instance.isInterrupted());
                }
            }
        };

        try {
            testThread.start();
            Thread.sleep(1000);
            testThread.interrupt();
        } catch (InterruptedException ex) {
            Logger.getLogger(InterruptTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(false, testThread.isAlive());
        assertEquals(false, instance.isInterrupted());

    }

    public void testReplaceInt1() {
        System.out.println("testReplaceInt1 - thread is interrupted");
        final String regex = "abc";
        final String replacement = "";
        final InterruptibleCharSequence instance = new InterruptibleCharSequence(largeText);
        Thread testThread;
        testThread = new Thread() {
            @Override
            public void run() {
                try {
                    String res = instance.replaceAll(regex, replacement);
                } catch (Exception ex) {
                    System.out.println("Caught exception:" + ex.getMessage());
                    System.out.println("instance.isInterrupted: " + instance.isInterrupted());
                }
            }
        };

        //interrupt after 1 MS. Let thread execute another 1MS before continuing. This should give 
        //enough time for the ICS to be interrupted
        try {
            testThread.start();
            Thread.sleep(1);
            testThread.interrupt();
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(InterruptTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //thread should be dead and ics should have interrupt flag set
        assertEquals(false, testThread.isAlive());
        assertEquals(true, instance.isInterrupted());

    }

    public void testReplaceInt2() {
        System.out.println("testReplaceInt2 - string replace thread is not interrupted");
        final String regex = "abc";
        final String replacement = "";
        //final InterruptibleCharSequence instance = new InterruptibleCharSequence(largeText);
        Thread testThread;
        testThread = new Thread() {
            @Override
            public void run() {
                try {
                    String res = largeText.replaceAll(regex, replacement);
                } catch (Exception ex) {
                    System.out.println("Caught exception:" + ex.getMessage());
                    //System.out.println("instance.isInterrupted: " + instance.isInterrupted());
                }
            }
        };

        try {
            testThread.start();
            Thread.sleep(1);
            testThread.interrupt();
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(InterruptTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //string replace (regex) is not intteruptable. Thread will remain alive 
        assertEquals(true, testThread.isAlive());

    }
}
