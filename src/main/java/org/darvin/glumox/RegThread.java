/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.darvin.glumox;

/**
 *
 * @author ggoldman
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RegThread {

    public RegThread(String filename, int timeout) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task(filename));

        System.out.println("Threaded regex test - file=" + filename + " timeout=" + timeout + " milliseconds");
        try {
            System.out.println("Thread Started..");
            System.out.println(future.get(timeout, TimeUnit.MILLISECONDS));
            System.out.println("Thread Normal Finish!");
        } catch (TimeoutException e) {
            System.out.println("Timeout: Terminated!: Thread is done=" + future.isDone());
            List<Runnable> shutdownNow = executor.shutdownNow();
            System.out.println("Thread shutdown=" + executor.isShutdown() + " : terminated=" + executor.isTerminated());
        }

        executor.shutdownNow();
    }
}

class Task implements Callable<String> {

    private final String filename;

    public Task(String file) {
        filename = file;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder();
        Regexer r = new Regexer(filename);
        ArrayList<MatchPair> list2 = r.matchem("a(?!b)"); //match a not follwed by b
        for (MatchPair m : list2) {
            sb.append("Match2: ").append(m.matchtext).append("|").append(m.offset).append("\n");
        }
        //return sb.toString();
        return ("Matches : " + list2.size());
    }
}
