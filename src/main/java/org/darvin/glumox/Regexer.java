/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ggoldman
 */
public class Regexer {

    InterruptibleCharSequence ics;

    public Regexer(String filename) throws IOException {
        CharSequence cs = fromFile(filename);
        ics = new InterruptibleCharSequence(cs);
    }

    private CharSequence fromFile(String filename) throws IOException {
        FileInputStream input = new FileInputStream(filename);
        FileChannel channel = input.getChannel();

        // Create a read-only CharBuffer on the file
        ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int) channel.size());
        CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
        return cbuf;
        //return bbuf.asCharBuffer();
    }

    public ArrayList<MatchPair> matchem(String regex) {
        ArrayList<MatchPair> list = new ArrayList<MatchPair>();
        
        // Create matcher on file
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ics);

        // Find all matches
        while (matcher.find()) {
            // Get the matching string
            //String match = matcher.group();
            MatchPair p = new MatchPair(matcher.group(), matcher.start());
            list.add(p);
        }
        return list;

    }
}
