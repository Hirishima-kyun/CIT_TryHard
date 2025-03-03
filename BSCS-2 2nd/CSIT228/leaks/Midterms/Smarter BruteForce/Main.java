import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static String goal = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input String to bruteforce: ");
        goal = sc.nextLine();
        sc.close();

        final String vowels = "aeiou";
        int length = goal.length();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int idxLength = length;
            while (idxLength-- > 0) {
                threads.add(new Thread(new Worker(idxLength, vowels.charAt(i), Thread.currentThread())));
            }
        }

        for (Thread i : threads) {
            i.start();
        }

        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                for (Thread i : threads) {
                    i.interrupt();
                }
                return;
            }
        }
    }

    private static class Worker implements Runnable {
        static int ID = 1;
        int idx, id, length;
        char c;
        Thread main;
        StringBuilder sb;
        public Worker(int idx, char c, Thread main) {
            this.idx = idx;
            this.c = c;
            id = ID++;
            this.main = main;
            sb = new StringBuilder();

            this.length = goal.length();
            int idxLength = length;

            while (idxLength-- > 0) {
                if (idxLength == idx) {
                    sb.insert(0, c);
                } else {
                    sb.insert(0, '_');
                }
            }

            System.out.println("Thread " + (id < 10 ? "0" + id : id) + ": " + sb);

            sb = new StringBuilder();
            idxLength = length;
            while (idxLength-- > 0) {
                if (idxLength != idx) {
                    sb.insert(0, 'a');
                } else {
                    sb.insert(0, c);
                }
            }
        }

        @Override
        public void run() {
            final String letters = "abcdefghijklmnopqrstuvwxyz";
            while (!Objects.equals(Main.goal, sb.toString())) {
                System.out.println("Thread " + (id < 10 ? "0" + id : id) + ": " + sb);
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    return;
                }

                int idxLength = length;
                StringBuilder sb = new StringBuilder();

                boolean refactor = true;

                while (idxLength-- > 0) {
                    if (idxLength != idx) {
                        char c = this.sb.charAt(idxLength);

                        if (refactor)
                            if (c == 'z') {
                                sb.insert(0, 'a');
                            } else {
                                sb.insert(0, letters.charAt(letters.indexOf(this.sb.charAt(idxLength)) + 1));
                                refactor = false;
                            }
                        else {
                            sb.insert(0, this.sb.charAt(idxLength));
                        }
                    } else {
                        sb.insert(0, c);
                    }
                }

                this.sb = sb;
            }

            System.out.println("Thread " + (id < 10 ? "0" + id : id) + ": found " + sb);
            main.interrupt();
        }
    }
}
