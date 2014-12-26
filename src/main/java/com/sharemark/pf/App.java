package com.sharemark.pf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws InterruptedException {

        final ExecutorService exec = Executors.newFixedThreadPool(4);

        final ReentrantLock lock = new ReentrantLock();

        final Condition con = lock.newCondition();

        final int time = 5;

        final Runnable add = new Runnable() {

          public void run() {

            System.out.println("Pre " + lock);
            lock.lock();
            System.out.println("QueueLength :" + lock.getQueueLength());

            try {

              con.await(time, TimeUnit.SECONDS);
//              Thread.sleep(5000);

            } catch (InterruptedException e) {

              e.printStackTrace();

            } finally {

              System.out.println("Post " + lock.toString());

              lock.unlock();

            }

          }

        };

        for(int index = 0; index < 4; index++)

          exec.submit(add);

        exec.shutdown();

      }
	
	
}
