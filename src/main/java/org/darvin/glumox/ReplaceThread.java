/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

/**
 *
 * @author ggoldman
 */
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class ReplaceThread {

    public ReplaceThread(String name, String text,String regex, String repl, int timeout) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new RTask(text,regex,repl));

        System.out.println("Threaded replace test - file=" + name + " timeout=" + timeout + " milliseconds");
        try {
            System.out.println("Thread Started..");
            System.out.println(future.get(timeout, TimeUnit.MILLISECONDS));
            System.out.println("Thread Normal Finish!");
        } catch (TimeoutException e) {
            System.out.println("Thread Timeout");
            List<Runnable> shutdownNow = executor.shutdownNow();
            System.out.println("Thread shutdown=" + executor.isShutdown() + " : terminated=" + executor.isTerminated());
            
        }

        executor.shutdownNow();
    }
}
class RTask implements Callable<String> {
    private final String text;
    private final String regex;
    private final String repl;
    
    public RTask (String text, String regex, String repl ) {
        this.text = text; 
        this.regex = regex;
        this.repl = repl;
    }
    @Override
    public String call() throws Exception {
        InterruptibleCharSequence ics = new InterruptibleCharSequence(text);
        
        String s2 = ics.replaceAll(regex, repl);
        
        return ("s2 : " + s2.length());
    }
}
