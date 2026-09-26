/*public class Main {

    public static void main(String[] args) throws InterruptedException 
    {

        CoarseList list = new CoarseList();
        //FineList list = new FineList();

        int numberOfThreads = 16;
        int operationsPerThread = 1000;

        Thread[] threads = new Thread[numberOfThreads];

        long startTime = System.nanoTime();

        for(int i = 0; i < numberOfThreads; i++) 
        {

            final int threadID = i;

            threads[i] = new Thread(() -> {

                for(int j = 0; j < operationsPerThread; j++) 
                {
                    int value = (threadID * 1000) + (j % 1000);

                    if(j % 3 == 0) 
                    {
                        list.add(value);
                    }
                    else if(j % 3 == 1) 
                    {
                        list.contains(value);
                    }
                    else 
                    {
                        list.remove(value);
                    }  
                }
            });

            threads[i].start();
        }

        for(Thread thread : threads) 
        {
            thread.join();
        }

        long endTime = System.nanoTime();

        double executionTime = (endTime - startTime) / 1000000;
        System.out.println("Execution time: " + executionTime + " ms");
    }
}*/

public class Main {

    public static void main(String[] args) throws InterruptedException 
    {
        int[] threadCounts = {2, 4, 8, 16};

        int operationsPerThread = 1000;

        for(int numberOfThreads : threadCounts) 
        {
            System.out.println("\nThreads: " + numberOfThreads);

            // Coarse List
            CoarseList list = new CoarseList();

            Thread[] threads = new Thread[numberOfThreads];

            long startTime = System.nanoTime();

            for(int i = 0; i < numberOfThreads; i++) 
            {

                final int threadID = i;

                threads[i] = new Thread(() -> {

                    for(int j = 0; j < operationsPerThread; j++) 
                    {
                        int value = (threadID * 1000) + (j % 1000);

                        if(j % 3 == 0) 
                        {
                            list.add(value);
                        }
                        else if(j % 3 == 1) 
                        {
                            list.contains(value);
                        }
                        else 
                        {
                            list.remove(value);
                        }  
                    }
                });

                threads[i].start();
            }

            for(Thread thread : threads) 
            {
                thread.join();
            }

            long endTime = System.nanoTime();

            double executionTime = (endTime - startTime) / 1000000;

            System.out.println("CoarseList execution time: "
                    + executionTime + " ms");


            // Fine List
            FineList fineList = new FineList();

            threads = new Thread[numberOfThreads];

            startTime = System.nanoTime();

            for(int i = 0; i < numberOfThreads; i++) 
            {

                final int threadID = i;

                threads[i] = new Thread(() -> {

                    for(int j = 0; j < operationsPerThread; j++) 
                    {
                        int value = (threadID * 1000) + (j % 1000);

                        if(j % 3 == 0) 
                        {
                            fineList.add(value);
                        }
                        else if(j % 3 == 1) 
                        {
                            fineList.contains(value);
                        }
                        else 
                        {
                            fineList.remove(value);
                        }  
                    }
                });

                threads[i].start();
            }

            for(Thread thread : threads) 
            {
                thread.join();
            }

            endTime = System.nanoTime();

            executionTime = (endTime - startTime) / 1000000;

            System.out.println("FineList execution time: "
                    + executionTime + " ms");
        }
    }
}