package notebook_job;

import java.util.Scanner;

public class sqrt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = (int) Math.sqrt(n);
        System.out.println(ans);
    }
}
