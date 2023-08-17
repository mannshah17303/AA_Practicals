package Lab2;

import java.util.Random;
import java.util.Scanner;

public class PrimalityTest {
    static Random rand = new Random();
    public static long gcd(long a, long b) {
        long r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static long power(long a, long x, long n) {
        long res = 1;
        while (x > 0) {
            if (x % 2 == 1) {
                res = (res * a) % n;
            }
            x = x / 2;
            a = (a * a) % n;
        }
        return res;
    }

    public static boolean isPrime(long number) {
        if (number <= 1 || number == 4) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        int k = 100;
        while (k > 0) {
            long a = 2 + rand.nextInt((int) (number - 3));
            if (gcd(a, number) != 1) {
                return false;
            }
            if (power(a, number - 1, number) != 1) {
                return false;
            }
            k--;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        long number = scanner.nextLong();

        if (isPrime(number)) {
            System.out.println(number + " is prime");
        } else {
            System.out.println(number + " is not prime");
        }
        scanner.close();
    }
    
}
