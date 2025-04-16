 import java.util.*;

public class RSA {

    private static boolean isPrime(int num) {
        if (num < 2)
            return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    private static int findE(int phi) {
        for (int i = 2; i < phi; i++) {
            if (gcd(i, phi) == 1)
                return i;
        }
        return 2;  
    }

    private static int findD(int e, int phi) {
        int i = 1;
        while (true) {
            int possibleD = ((phi * i) + 1) / e;
            if (((possibleD * e) % phi) == 1)
                return possibleD;
            i++;
        }
    }

    private static int modExp(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            exp >>= 1;
            base = (base * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int p, q;
        while (true) {
            System.out.print("Enter a prime number p: ");
            p = scanner.nextInt();
            if (!isPrime(p)) {
                System.out.println("p is not prime. Try again.");
                continue;
            }

            System.out.print("Enter another prime number q: ");
            q = scanner.nextInt();
            if (!isPrime(q)) {
                System.out.println("q is not prime. Try again.");
                continue;
            }

            if (p != q)
                break;
            else
                System.out.println("p and q should be different. Try again.");
        }

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        int e = findE(phi);

        int d = findD(e, phi);

        System.out.print("\nEnter a message m to encrypt: ");
        int m = scanner.nextInt();

        int c = modExp(m, e, n);

        int decryptedM = modExp(c, d, n);

        System.out.println("\nValues:");
        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("n = " + n);
        System.out.println("φ(n) = " + phi);
        System.out.println("e = " + e);
        System.out.println("d = " + d);
        System.out.println("Original Message (M) = " + m);
        System.out.println("Encrypted Message (C) = " + c);
        System.out.println("Decrypted Message (M) = " + decryptedM);

        System.out.println("code exicuted successfuly");

        scanner.close();
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
