import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        int[] arr = new int[10];
        Scanner in = new Scanner(System.in);
        System.out.print("Введите 10 положительных чисел массива для сортировки:");
        for(int i=0;i<10;i++)
            arr[i] = in.nextInt();

        Variant_1 variant1 = new Variant_1();
        Turn[] turns = variant1.sort(arr);

        for(int i=0;i<turns.length;i++)
            turns[i].showAll();
    }
    
}
