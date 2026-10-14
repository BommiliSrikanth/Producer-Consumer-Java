// Producer-Consumer Problem using wait() and notify()
// Shared buffer: LinkedList with fixed capacity

import java.util.LinkedList;

class PC {
    // Shared buffer
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;   // Fixed capacity as per example

    // Producer method
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                // Wait while buffer is full
                while (list.size() == capacity) {
                    wait();
                }

                // Produce an item
                System.out.println("Producer produced-" + value);
                list.add(value);
                value++;

                // Notify the consumer
                notify();

                // Sleep for 1 second
                Thread.sleep(1000);
            }
        }
    }

    // Consumer method
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // Wait while buffer is empty
                while (list.size() == 0) {
                    wait();
                }

                // Consume an item
                int val = list.removeFirst();
                System.out.println("Consumer consumed-" + val);

                // Notify the producer
                notify();

                // Sleep for 1 second
                Thread.sleep(1000);
            }
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        final PC pc = new PC();

        // Producer thread
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer thread
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        producerThread.start();
        consumerThread.start();

        // Join both threads (they run indefinitely)
        producerThread.join();
        consumerThread.join();
    }
}
