/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadServer;

/**
 *
 * @author adekola
 */
public class Fibonacci {

    public static void main(String[] args){
        if (args.length > 0){
            int num = Integer.parseInt(args[0]);
            getFibo(num);
        }
        else
            getFibo(99292272);
    }
    public static int getFibo(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        int fibo1 = 1, fibo2 = 1, fibonacci = 1;
        for (int i = 3; i <= number; i++) {
            fibonacci = fibo1 + fibo2; //Fibonacci number is sum of previous two Fibonacci number
            fibo1 = fibo2;
            fibo2 = fibonacci;
            System.out.println(fibonacci);
            System.out.println(" ");
        }
        return fibonacci; //Fibonacci number
    }
}
