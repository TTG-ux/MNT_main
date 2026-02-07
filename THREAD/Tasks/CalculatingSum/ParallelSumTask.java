package THREAD.Tasks.CalculatingSum;

import java.util.Random;
import java.util.concurrent.*;

public class ParallelSumTask {

    public static void main(String[] args) throws Exception {
        int[] array = new int[10_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        // Однопоточное суммирование
        long start = System.currentTimeMillis();
        long singleThreadSum  = singleThreadSum(array);
        long singleThreadTime  = System.currentTimeMillis() - start;
        System.out.println("Однопоточно: сумма=" + singleThreadSum  + ", время=" + singleThreadTime  + " мс");

        // ExecutorService
        start = System.currentTimeMillis();
        long executorSum = executorSum(array);
        long executorTime = System.currentTimeMillis() - start;
        System.out.println("Executor: сумма = " + executorSum + ", время = " + executorTime + " мс");

        // Fork/Join
        start = System.currentTimeMillis();
        long forkJoinSum = forkJoinSum(array);
        long forkJoinTime = System.currentTimeMillis() - start;
        System.out.println("Fork/Join: сумма = " + forkJoinSum + ", время = " + forkJoinTime + " мс");
    }

    private static long singleThreadSum(int[] array) {
        long sum = 0;
        for (int value : array) sum += value;
        return sum;
    }

    private static long executorSum(int[] array) throws Exception {
        int threads = Runtime.getRuntime().availableProcessors();
        int chunkSize = array.length / threads;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        Future<Long>[] futures = new Future[threads];

        for (int i = 0; i < threads; i++) {
            final int start = i * chunkSize;
            final int end = (i == threads - 1) ? array.length : start + chunkSize;

            futures[i] = executor.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) sum += array[j];
                return sum;
            });
        }

        long total = 0;
        for (Future<Long> future : futures) total += future.get();
        executor.shutdown();
        return total;
    }

    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 100_000;
        private final int[] array;
        private final int start;
        private final int end;

        SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) sum += array[i];
                return sum;
            }

            int mid = start + (end - start) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);

            left.fork();
            long rightSum = right.compute();
            long leftSum = left.join();
            return leftSum + rightSum;
        }
    }

    private static long forkJoinSum(int[] array) {
        return new ForkJoinPool().invoke(new SumTask(array, 0, array.length));
    }
}