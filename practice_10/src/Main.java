import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] arr = new int[length];
        for (int i =0; i< length; i++){
            arr[i] = sc.nextInt();
        }

        BinaryTree tree = createBinaryTree(arr);
        tree.printTree();
    }

    public static BinaryTree createBinaryTree(int[] array) {
        BinaryTree bt = new BinaryTree();

        for (int i : array) {
            bt.add(i);
        }
        return bt;
    }
}
