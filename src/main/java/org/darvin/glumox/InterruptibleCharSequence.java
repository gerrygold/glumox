/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * CharSequence that noticed thread interrupts -- as might be necessary 
 * to recover from a loose regex on unexpected challenging input. 
 * 
 * @author gojomo
 */
public class InterruptibleCharSequence implements CharSequence {
    CharSequence inner;
    // public long counter = 0; 

    public InterruptibleCharSequence(CharSequence inner) {
        super();
        this.inner = inner;
    }

    public char charAt(int index) {
        if (Thread.interrupted()) { // clears flag if set
            System.out.println("ICS INTERRUPT");
            throw new RuntimeException(new InterruptedException());
        }
        // counter++;
        return inner.charAt(index);
    }

    public int length() {
        return inner.length();
    }

    public CharSequence subSequence(int start, int end) {
        return new InterruptibleCharSequence(inner.subSequence(start, end));
    }

    @Override
    public String toString() {
        return inner.toString();
    }
    
     /**
     * Replaces the first substring of this string that matches the given <a
     * href="../util/regex/Pattern.html#sum">regular expression</a> with the
     * given replacement.
     *
     * <p> An invocation of this method of the form
     * <i>str</i><tt>.replaceFirst(</tt><i>regex</i><tt>,</tt> <i>repl</i><tt>)</tt>
     * yields exactly the same result as the expression
     *
     * <blockquote><tt>
     * {@link java.util.regex.Pattern}.{@link java.util.regex.Pattern#compile
     * compile}(</tt><i>regex</i><tt>).{@link
     * java.util.regex.Pattern#matcher(java.lang.CharSequence)
     * matcher}(</tt><i>str</i><tt>).{@link java.util.regex.Matcher#replaceFirst
     * replaceFirst}(</tt><i>repl</i><tt>)</tt></blockquote>
     *
     *<p>
     * Note that backslashes (<tt>\</tt>) and dollar signs (<tt>$</tt>) in the
     * replacement string may cause the results to be different than if it were
     * being treated as a literal replacement string; see
     * {@link java.util.regex.Matcher#replaceFirst}.
     * Use {@link java.util.regex.Matcher#quoteReplacement} to suppress the special
     * meaning of these characters, if desired.
     *
     * @param   regex
     *          the regular expression to which this string is to be matched
     * @param   replacement
     *          the string to be substituted for the first match
     *
     * @return  The resulting <tt>String</tt>
     *
     * @throws  PatternSyntaxException
     *          if the regular expression's syntax is invalid
     *
     * @see java.util.regex.Pattern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String replaceFirst(String regex, String replacement) {
        return Pattern.compile(regex).matcher(this).replaceFirst(replacement);
    }

    /**
     * Replaces each substring of this string that matches the given <a
     * href="../util/regex/Pattern.html#sum">regular expression</a> with the
     * given replacement.
     *
     * <p> An invocation of this method of the form
     * <i>str</i><tt>.replaceAll(</tt><i>regex</i><tt>,</tt> <i>repl</i><tt>)</tt>
     * yields exactly the same result as the expression
     *
     * <blockquote><tt>
     * {@link java.util.regex.Pattern}.{@link java.util.regex.Pattern#compile
     * compile}(</tt><i>regex</i><tt>).{@link
     * java.util.regex.Pattern#matcher(java.lang.CharSequence)
     * matcher}(</tt><i>str</i><tt>).{@link java.util.regex.Matcher#replaceAll
     * replaceAll}(</tt><i>repl</i><tt>)</tt></blockquote>
     *
     *<p>
     * Note that backslashes (<tt>\</tt>) and dollar signs (<tt>$</tt>) in the
     * replacement string may cause the results to be different than if it were
     * being treated as a literal replacement string; see
     * {@link java.util.regex.Matcher#replaceAll Matcher.replaceAll}.
     * Use {@link java.util.regex.Matcher#quoteReplacement} to suppress the special
     * meaning of these characters, if desired.
     *
     * @param   regex
     *          the regular expression to which this string is to be matched
     * @param   replacement
     *          the string to be substituted for each match
     *
     * @return  The resulting <tt>String</tt>
     *
     * @throws  PatternSyntaxException
     *          if the regular expression's syntax is invalid
     *
     * @see java.util.regex.Pattern
     *
     * @since 1.4
     * @spec JSR-51
     */
    public String replaceAll(String regex, String replacement) {
        
        return Pattern.compile(regex).matcher(this).replaceAll(replacement);
    }

    /**
     * Replaces each substring of this string that matches the literal target
     * sequence with the specified literal replacement sequence. The
     * replacement proceeds from the beginning of the string to the end, for
     * example, replacing "aa" with "b" in the string "aaa" will result in
     * "ba" rather than "ab".
     *
     * @param  target The sequence of char values to be replaced
     * @param  replacement The replacement sequence of char values
     * @return  The resulting string
     * @throws NullPointerException if <code>target</code> or
     *         <code>replacement</code> is <code>null</code>.
     * @since 1.5
     */
    public String replace(CharSequence target, CharSequence replacement) {
        return Pattern.compile(target.toString(), Pattern.LITERAL).matcher(
            this).replaceAll(Matcher.quoteReplacement(replacement.toString()));
    }
}