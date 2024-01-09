import java.util.Random;

public class ProducerConsumer{
    private double[] buffer = new double[1000];
    private int bufferSize = 0;
    private int MAXSIZE = 1000;
    private int head = 0;
    private int tail = 0;
    private int bufferValueCounter;

    public synchronized void producer() throws InterruptedException{
        int produceCount = 0;
        double producedSum = 0;

        do{

        Random random = new Random();
        Double bufferElement = random.nextDouble();
        
        while (bufferSize == MAXSIZE){
            wait();
        }
        
        buffer[tail] = bufferElement;
        tail = (tail + 1) % MAXSIZE;
        bufferSize++;
        produceCount++;
        bufferValueCounter++;
        producedSum = producedSum + bufferElement;

        if(produceCount % 100000 == 0){
            System.out.println("Producer: generated " + produceCount + " items, Cumulative value of generated items = " + producedSum);
        }

        notify();

        }while(produceCount < 1000000);

        System.out.println("Producer: finished producing " + produceCount + " items");
    }

    public synchronized void consumer() throws InterruptedException{
        
        int consumedCount = 0;
        double consumedSum = 0;

        do{
        
        while (bufferSize == 0){
            wait();
        }

        double item = buffer[head];
        head = (head + 1)% MAXSIZE;
        bufferSize--;
        consumedCount++;
        bufferValueCounter++;
        consumedSum = consumedSum + item;

        if(consumedCount % 100000 == 0){
            System.out.println("Consumer: consumed " + consumedCount + " itmes, Cumulative value of consumed items = " + consumedSum);
        }

        notify();
        }while(consumedCount < 1000000);

        System.out.println("Consumer: finished consuming " + consumedCount + " items");
    }   

    public static void main(String[] args){
        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            try {
                producerConsumer.producer();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                producerConsumer.consumer();
            } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join(); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("EXITING");
    }
}