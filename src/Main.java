import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Letters> arrayList1 = new ArrayList<>();
    static File file = new File("file.txt");

    public static void main(String[] args)throws IOException {
        part1();
        part2(arrayList1);
        System.out.printf(arrayList1.toString());
    }

    private static void part2(ArrayList<Letters> arrayList1) throws IOException {
        code(0,arrayList1.size());
        encodefile();
    }

    private static void encodefile() throws IOException {


        Scanner sc1 = new Scanner(file);
        char[] array;
        String all="";
        while(sc1.hasNext()){
            all += sc1.next();
        }
        array=all.toCharArray();
        all="";
        for (int i=0;i<array.length;i++) {
            for (Letters l:arrayList1){
                if(array[i] == l.getLetter()){
                    all+=(l.getCode());
                }
            }
        }

        FileWriter fw = new FileWriter("encoded.txt");
        BufferedWriter out = new BufferedWriter(fw);
        out.write(all);
        out.close();

    }

    private static void code(int a, int b) {
        if(b - a <= 1 || a >= b){
            return;
        }
        double min = 999.0;
        double sumA, sumB;
        int index = 0;
        for(int i = a+1; i < b; i++){
            sumA = 0;
            sumB = 0;
            for(int j = a; j < i; j++){
                sumA += arrayList1.get(j).getProbabilty();
            }
            for(int j = i; j < b; j++){
                sumB += arrayList1.get(j).getProbabilty();
            }
            double dif = Math.abs(sumA - sumB);
            if(dif < min){
                min = dif;
                index = i;
            }
            //System.out.println("\t"+i +") " +sumA + " " + sumB + " dif = "+dif);
        }
        for(int i = a ; i < index; i++){
            arrayList1.get(i).addBit(0);
        }

        for(int i = index ; i < b; i++){
            arrayList1.get(i).addBit(1);
        }

        code(a,index);
        code(index,b);
    }

    private static void part1() throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        ArrayList arrayList = new ArrayList();
        char[] array;
        String all="";
        while(sc.hasNext()){
            all += sc.next();
        }
        array=all.toCharArray();

        HashSet<Character> set = new HashSet<>();
        for(char each : array){
            set.add(each);
        }


        for(Character r:set){
            arrayList1.add(new Letters(r));
        }
        for(Letters l : arrayList1){
            for (char ch:array){
                if(l.getLetter() == ch){
                    l.setNumber(l.getNumber()+1);
                }
            }
        }

        for(Letters l : arrayList1){

            l.setProbabilty((double) l.getNumber()/array.length);
        }
        Collections.sort(arrayList1);

    }



}
