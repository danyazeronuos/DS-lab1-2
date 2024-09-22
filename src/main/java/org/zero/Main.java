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

    }
}