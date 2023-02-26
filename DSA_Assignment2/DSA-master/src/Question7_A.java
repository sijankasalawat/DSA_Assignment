//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//class MatrixMultiplication {
//    private final int[][] matrix1;
//    private final int[][] matrix2;
//    private final int[][] result;
//
//    public MatrixMultiplication(int[][] matrix1, int[][] matrix2) {
//        this.matrix1 = matrix1;
//        this.matrix2 = matrix2;
//        this.result = new int[matrix1.length][matrix2[0].length];
//    }
//
//    public void multiply() {
//        int numThreads = matrix1.length * matrix2[0].length;
//        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
//
//        for (int i = 0; i < matrix1.length; i++) {
//            for (int j = 0; j < matrix2[0].length; j++) {
//                MatrixMultiplicationTask task = new MatrixMultiplicationTask(i, j);
//                executor.execute(task);
//            }
//        }
//
//        executor.shutdown();
//
//        try {
//            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//        } catch (InterruptedException e) {
//            // Thread interrupted, stop multiplication
//        }
//    }
//
//      class MatrixMultiplicationTask implements Runnable {
//        private final int row;
//        private final int col;
//
//        public  MatrixMultiplicationTask(int row, int col) {
//            this.row = row;
//            this.col = col;
//        }
//
//        @Override
//        public void run() {
//            for (int k = 0; k < matrix2.length; k++) {
//                result[row][col] += matrix1[row][k] * matrix2[k][col];
//            }
//        }
//    }
//
//}
//



import java.util.Random;
import java.util.concurrent.*;

public class Question7_A {
    private static final int NUM_THREADS = 4; // number of threads to use

    public static void main(String[] args) {
        int n = 3; // size of matrices
        Random random = new Random();

        // create matrices A and B with random values
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = random.nextInt();
                B[i][j] = random.nextInt();
            }
        }

        // create a thread pool with NUM_THREADS threads
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        // create an array of Future objects to hold the results of each thread
        Future<Double>[][] futures = new Future[n][n];

        // multiply the matrices using threads
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int row = i;
                final int col = j;
                futures[i][j] = pool.submit(() -> {
                    double sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += A[row][k] * B[k][col];
                    }
                    return sum;
                });
            }
        }

        // wait for all threads to finish and get the results
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    C[i][j] = futures[i][j].get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // shutdown the thread pool
        pool.shutdown();

        // print the result
        System.out.println("Result:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}