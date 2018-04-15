import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static ArrayList<Letters> arrayList1 = new ArrayList<>();
    static File file = new File("file.txt");
    static File filetodecode = new File("dictionary.txt");
    static ArrayList<Decode> list= new ArrayList<>();

    public static void main(String[] args)throws IOException {
        part1();
        part2(arrayList1);
        part3();
    }

    private static void part3() {
        Scanner sc = null;

        ArrayList<String> all= new ArrayList();
        try {
            sc = new Scanner(filetodecode);
            while (sc.hasNextLine()){
                all.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n=0;
        for(String a: all){

            list.add(new Decode(a.substring(0,1),a.substring(2,a.length())));
        }
        //System.out.println(list.toString());
        decode();
    }

    private static void decode() {
        Path path = Paths.get("encoded.txt");
        Charset charset = StandardCharsets.UTF_8;

        String content;
        String result = "";
        try {

            content = new String(Files.readAllBytes(path), charset);
            String c = "";
            for(int i = 0 ; i < content.length() ; i++){
                c += content.charAt(i);

                for (Decode s : list) {
                    if(s.getCode().compareTo(c) == 0){
                        String v = s.getLetter();
                        if(v.compareTo("\\n") == 0)
                            v = "\n";

                        result += v;
                        c = "";
                    }
                }

            }


            Files.write(Paths.get("decodedText.txt"), result.getBytes(charset));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void part2(ArrayList<Letters> arrayList1) throws IOException {
        code(0,arrayList1.size());
        encodefile();
        makeDictionary();
    }

    private static void makeDictionary() throws IOException {
        FileWriter fw = new FileWriter("dictionary.txt");
        BufferedWriter out = new BufferedWriter(fw);
        String all="";
        for (Letters l:arrayList1) {
            all += l.getLetter()+"-"+l.getCode()+"\n";
        }
        out.write(all);
        out.close();
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
        while(sc.hasNextLine()){
            all += sc.nextLine();
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
