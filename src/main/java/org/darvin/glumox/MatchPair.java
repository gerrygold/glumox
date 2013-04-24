/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

/**
 *
 * @author ggoldman
 */
public class MatchPair {
     public int offset;
     public String matchtext;
    
    MatchPair(String text, int loc) {
        matchtext = text;
        offset = loc;
    }
    
}
