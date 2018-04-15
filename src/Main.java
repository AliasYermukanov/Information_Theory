import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {
        part1();
    }

    private static void part1() throws FileNotFoundException {
        File file = new File("file.txt");
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

        ArrayList<Letters> arrayList1 = new ArrayList<>();
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
        System.out.println(arrayList1.toString());
    }



}
