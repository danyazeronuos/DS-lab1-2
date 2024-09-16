package org.zero;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
        System.out.print("Enter a >> ");
        var a = sc.nextDouble();
        System.out.print("Enter b >> ");
        var b = sc.nextDouble();
        System.out.print("Enter n >> ");
        var n = sc.nextInt();
        System.out.print("Enter threads num >> ");
        var threads = sc.nextInt();
        System.out.println("Select strategy: ");
        System.out.println("[0] new Thread()");
        System.out.println("[1] CompletableFuture()");
        System.out.print("Enter strategy ID >> ");
        var strategy = sc.nextInt();
        sc.close();

        System.out.println();

        var manager = switch (strategy) {
            case 1 -> new FutureThreadManager(threads);
            default -> new StandardThreadManager(threads);
        };

        var calculator = new IntegralCalculator(manager);
        var function = new MyFunction();
        try {
            var start = Instant.now();
            var result = calculator.calculate(a,  b, n, function);
            var end = Instant.now();
            System.out.println("Result: " + result);
            var duration = Duration.between(start, end);
            System.out.println("Duration: " + duration.toMillis());
        } catch (Exception e) {
            System.out.println("Threads error");
        }
    }
}