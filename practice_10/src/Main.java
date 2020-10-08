import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] arr = new int[length];
        for (int i =0; i< length; i++){
            arr[i] = in.nextInt();
        }

        BinaryTree tree = createBinaryTree(arr);
        tree.printTree();
        System.out.println("");

        AVLTree avlTree = createAVLTree(arr);
        avlTree.printAVLTree();

    }


    public static BinaryTree createBinaryTree(int[] array) {
        BinaryTree bt = new BinaryTree();

        for (int i : array) {
            bt.add(i);
        }

        return bt;
    }

    public static AVLTree createAVLTree(int[] array) {
        AVLTree bt = new AVLTree();

        for (int i : array) {
            bt.insert(i);
        }

        return bt;
    }



}
