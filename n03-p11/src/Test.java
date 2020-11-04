public class Test {
    public void start()
    {
        UniInfo test = new UniInfo(111111,"University");
        Realisation<UniInfo> set = new Realisation<>(10);
        for (int i = 0;  i<15; i++ ){
            UniInfo temp = new UniInfo((int) (Math.random()*1000000),""+i);
            set.add(temp);

        }
        set.add(test);
        System.out.println(set.toString());
        System.out.println(set.contains(test));
        set.remove(test);
        System.out.println(set.toString());
        System.out.println(set.contains(test));
        set.clear();
        System.out.println(set.toString());
    }
}
