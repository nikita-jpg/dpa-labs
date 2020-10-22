import java.util.Scanner;

public class Main {

    private static int length;
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите длину массива:");
        length = in.nextInt();
        int[] arr = new int[length];

        System.out.print("Введите числа для сортировки:");
        for(int i=0;i<length;i++)
            arr[i] = in.nextInt();

        Variant_1 variant1 = new Variant_1();
        Turn[] turns = variant1.sort(arr);

        for(int i=0;i<turns.length;i++)
            turns[i].showAll();
    }
    
}
