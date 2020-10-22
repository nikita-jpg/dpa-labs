class Host {
    int value;
    Host left;
    Host right;

    Host(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}

public class BinaryTree {

    Host root;


    private Host addRecursive(Host current, int value) {
        if (current == null) {
            return new Host(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public void add(int value) {                                // вставка элемента начиная с корня древа
        root = addRecursive(root, value);
    }

    //ПОИСК ЭЛЕМЕНТА

    private boolean isContainNodeRecursive(Host current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? isContainNodeRecursive(current.left, value)
                : isContainNodeRecursive(current.right, value);
    }

    public boolean isContainNode(int value) {
        return isContainNodeRecursive(root, value);
    }

    //УДАЛЕНИЕ ЭЛЕМЕНТА

    private Host deleteRecursive(Host current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;

        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Host root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private void printBinaryTree(Host host, int level) {
        if (host != null) {
            printBinaryTree(host.right, level + 1);
            for (int i = 0; i < level; i++)
                System.out.print("   ");//чем ниже уровень, тем отступ больше
            System.out.println(host.value);
            printBinaryTree(host.left, level + 1);
        }
    }
    public void printTree(){
        printBinaryTree(root,1);
    }

}

