/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

import junit.framework.TestCase;

/**
 *
 * @author ggoldman
 */
public class InterruptibleCharSequenceTest extends TestCase {
    
    public InterruptibleCharSequenceTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of charAt method, of class InterruptibleCharSequence.
     */
    public void testCharAt() {
        System.out.println("charAt");
        int index = 0;
        InterruptibleCharSequence instance = new InterruptibleCharSequence(" this");
        char expResult = ' ';
        char result = instance.charAt(index);
        assertEquals(expResult, result);
    }



    /**
     * Test of replaceFirst method, of class InterruptibleCharSequence.
     */
    public void testReplaceFirst() {
        System.out.println("replaceFirst");
        String regex = " ";
        String replacement = "X";
        InterruptibleCharSequence instance = new InterruptibleCharSequence(" this ");;
        String expResult = "Xthis ";
        String result = instance.replaceFirst(regex, replacement);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of replaceAll method, of class InterruptibleCharSequence.
     */
    public void testReplaceAll() {
        System.out.println("replaceAll");
        String regex = " ";
        String replacement = "X";
        InterruptibleCharSequence instance =  new InterruptibleCharSequence(" this ");;;
        String expResult = "XthisX";
        String result = instance.replaceAll(regex, replacement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of replace method, of class InterruptibleCharSequence.
     */
    public void testReplace() {
        System.out.println("replace");
        CharSequence target = " ";
        CharSequence replacement = "X";
        InterruptibleCharSequence instance = new InterruptibleCharSequence(" this ");
        String expResult = "XthisX";
        String result = instance.replace(target, replacement);
        assertEquals(expResult, result);
        
    }
}
