public class Turn {
    private int size;
    private int currentSize;
    private int objects[];
    Turn(int size)
    {
        currentSize = 0;
        this.size = size;
        objects = new int[size];
    }

    //Готово
    public void addLast(int object)
    {
        currentSize++;
        if(currentSize>size)
        {
            int[] ob = new int[2*size];
            for(int i=0;i<size;i++)
                ob[i] = objects[i];

            ob[size] = object;

            size = 2*size;
            objects = ob;
        }else
        {
            objects[currentSize-1] = object;
        }
    }

    //Готово
    public void delete(int number)
    {

        if(number>currentSize || number <= 0)
            return;

        int[] ob = new int[size-1];

        for(int i=0;i<number-1;i++)
            ob[i] = objects[i];

        for(int i=number;i<size;i++)
            ob[i-1] = objects[i];

        currentSize--;
        size--;
        objects = ob;

    }

    //Готово
    public int getElement(int number)
    {
        if(number>currentSize || number <= 0)
            return Integer.parseInt(null);
        return objects[number-1];
    }

    //Готово
    public int getCurrentSize()
    {
        return currentSize;
    }

    //Готово
    public void showAll()
    {
        for(int i=0;i<currentSize;i++)
            System.out.println(objects[i]);
    }

    //Готово
    public boolean isFull()
    {
        if(currentSize == size && size!=0)
            return true;
        else
            return false;
    }

    public boolean isEmpty()
    {
        if(currentSize == 0)
            return true;
        else
            return false;
    }

    public void spaw(int numberA,int numberB)
    {
        if(numberA>currentSize || numberA <= 0 || numberB>currentSize || numberB <= 0)
            return;
        else {
            int a = objects[numberA-1];
            objects[numberA-1] = objects[numberB-1];
            objects[numberB-1] = a;
        }

    }

    public void addFirst(int object)
    {
        currentSize++;
        int[] ob = new int[2*size];
        for(int i=0;i<size;i++)
            ob[i+1] = objects[i];

        ob[0] = object;

        size = 2*size;
        objects = ob;
    }
}
