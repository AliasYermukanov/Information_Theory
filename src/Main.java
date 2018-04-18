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
    static File decoded = new File("encoded.txt");
    static File decodedHaming = new File("encodedByHaming.txt");
    static File HamingNoise = new File("HamingNoise.txt");
    static ArrayList<Decode> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        part1();
        part2(arrayList1);
        part3();
        part4();
        part5();
        part6();
    }

    private static void part6() throws IOException {
        Scanner sc1 = new Scanner(HamingNoise);
        char[] array;
        String all = "";
        while (sc1.hasNextLine()) {
            all += sc1.nextLine();
        }
        array = all.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        all = "";
        for (int i = 0; i < array.length; i++) {

            if (i % 7 == 0 && i != 0) {
                list.add(all);
                all = "";
            }
            all += array[i];
        }
        list.add(all);

        simptome(list);
    }

    private static void simptome(ArrayList<String> list) throws IOException {
        ArrayList<String> listcode = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i)+"-1");
            char[] array = list.get(i).toCharArray();
            int s1 = 0, s2 = 0, s3 = 0;
            String all = "";
            for (int j = 0; j < array.length; j++) {
                s1 = Integer.parseInt(String.valueOf(array[4])) ^ Integer.parseInt(String.valueOf(array[0])) ^ Integer.parseInt(String.valueOf(array[1])) ^ Integer.parseInt(String.valueOf(array[2]));
                s2 = Integer.parseInt(String.valueOf(array[5])) ^ Integer.parseInt(String.valueOf(array[1])) ^ Integer.parseInt(String.valueOf(array[2])) ^ Integer.parseInt(String.valueOf(array[3]));
                s3 = Integer.parseInt(String.valueOf(array[6])) ^ Integer.parseInt(String.valueOf(array[0])) ^ Integer.parseInt(String.valueOf(array[1])) ^ Integer.parseInt(String.valueOf(array[3]));
            }
            all+=String.valueOf(s1)+String.valueOf(s2)+String.valueOf(s3);
            System.out.println(all+"-s");
            String newS="";
            switch (all){
                case "100":
                    newS=changebit(list.get(i),2);
                    list.set(i,newS);
                    break;
                case "010":
                    newS=changebit(list.get(i),1);
                    list.set(i,newS);
                    break;
                case "001":
                    newS=changebit(list.get(i),0);
                    list.set(i,newS);
                    break;
                case "101":
                    newS=changebit(list.get(i),6);
                    list.set(i,newS);
                    break;
                case "111":
                    newS=changebit(list.get(i),5);
                    System.out.println("hello");
                    list.set(i,newS);
                    break;
                case "110":
                    newS=changebit(list.get(i),4);
                    list.set(i,newS);
                    break;
                case "011":
                    newS=changebit(list.get(i),3);
                    list.set(i,newS);
                    break;
                case "000":
                    break;
            }
            System.out.println(newS +"-2");
            newS = list.get(i).substring(0,4);
            System.out.println(newS+"-3");
            listcode.add(newS);
        }
        String all = "";
        for (String s : listcode) {
            all += s;
        }
        FileWriter fw = new FileWriter("newEncoded.txt");
        BufferedWriter out = new BufferedWriter(fw);
        out.write(all);
        out.close();
        decode("newEncoded.txt");
    }

    private static void part5() throws IOException {
        Scanner sc1 = new Scanner(decodedHaming);
        char[] array;
        String all = "";
        while (sc1.hasNextLine()) {
            all += sc1.nextLine();
        }
        array = all.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        all = "";
        for (int i = 0; i < array.length; i++) {

            if (i % 7 == 0 && i != 0) {
                list.add(all);
                all = "";
            }
            all += array[i];
        }
        list.add(all);
        noise(list);
    }

    private static void noise(ArrayList<String> list) throws IOException {
        int a = 0; // Начальное значение диапазона - "от"
        int b = 7; // Конечное значение диапазона - "до"
        Random random = new Random();

        for(int i=0;i<list.size();i++){
            int num = 0 + random.nextInt(7 - 0);
            String newS=changebit(list.get(i),num);

            list.set(i,newS);
        }
        String all = "";
        for (String s : list) {
            all += s;
        }
        FileWriter fw = new FileWriter("HamingNoise.txt");
        BufferedWriter out = new BufferedWriter(fw);
        out.write(all);
        out.close();

    }

    private static String changebit(String s, int random_number1) {
        int num=Integer.parseInt(s,2);

        if(isBit(num,random_number1)==1){
            num=clearBit(num,random_number1);
        }
        else {

            num=setBit(num,random_number1);

        }
        String all=Integer.toString(num,2);

        if(all.length()<7) {
            do{
                all = "0" + all;
            }
            while (all.length() != 7);

        }

        return all;
    }
    static int isBit(int word, int bit){
        return ((word >> bit) & 1);
    }

    static int setBit(int word, int bit) {
        return word | (1 << bit);
    }


    static int clearBit(int word, int bit) {
        return word & (~(1 << bit));
    }

    private static void part4() throws IOException {
        Scanner sc1 = new Scanner(decoded);
        char[] array;
        String all = "";
        while (sc1.hasNextLine()) {
            all += sc1.nextLine();
        }
        array = all.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        all = "";
        for (int i = 0; i < array.length; i++) {
            if (i % 4 == 0 && i != 0) {
                list.add(all);
                all = "";
            }
            all += array[i];

        }
        while (all.length() == 3) {
            all += "0";
        }
        list.add(all);
        Haming(list);
    }

    private static void Haming(ArrayList<String> list) throws IOException {

        ArrayList<String> listcode = new ArrayList<>();
        for (String s : list) {
            char[] array = s.toCharArray();

            int r1 = 0, r2 = 0, r3 = 0;
            String all = "";
            for (int i = 0; i < array.length; i++) {
                r1 = Integer.parseInt(String.valueOf(array[0])) ^ Integer.parseInt(String.valueOf(array[1])) ^ Integer.parseInt(String.valueOf(array[2]));
                r2 = Integer.parseInt(String.valueOf(array[1])) ^ Integer.parseInt(String.valueOf(array[2])) ^ Integer.parseInt(String.valueOf(array[3]));
                r3 = Integer.parseInt(String.valueOf(array[0])) ^ Integer.parseInt(String.valueOf(array[1])) ^ Integer.parseInt(String.valueOf(array[3]));
            }
            all = s + r1 + r2 + r3;
            listcode.add(all);


        }

        String all = "";
        for (String s : listcode) {
            all += s;
        }
        FileWriter fw = new FileWriter("encodedByHaming.txt");
        BufferedWriter out = new BufferedWriter(fw);
        out.write(all);
        out.close();

    }

    private static void part3() {
        Scanner sc = null;

        ArrayList<String> all = new ArrayList();
        try {
            sc = new Scanner(filetodecode);
            while (sc.hasNextLine()) {
                all.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = 0;
        for (String a : all) {

            list.add(new Decode(a.substring(0, 1), a.substring(2, a.length())));
        }
        //System.out.println(list.toString());
        decode("Encoded.txt");
    }

    private static void decode(String name) {
        Path path = Paths.get(name);
        Charset charset = StandardCharsets.UTF_8;

        String content;
        String result = "";
        try {

            content = new String(Files.readAllBytes(path), charset);
            String c = "";
            for (int i = 0; i < content.length(); i++) {
                c += content.charAt(i);

                for (Decode s : list) {
                    if (s.getCode().compareTo(c) == 0) {
                        String v = s.getLetter();
                        if (v.compareTo("\\n") == 0)
                            v = "\n";

                        result += v;
                        c = "";
                    }
                }

            }


            Files.write(Paths.get("de" + name), result.getBytes(charset));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void part2(ArrayList<Letters> arrayList1) throws IOException {
        code(0, arrayList1.size());
        encodefile();
        makeDictionary();
    }

    private static void makeDictionary() throws IOException {
        FileWriter fw = new FileWriter("dictionary.txt");
        BufferedWriter out = new BufferedWriter(fw);
        String all = "";
        for (Letters l : arrayList1) {
            all += l.getLetter() + "-" + l.getCode() + "\n";
        }
        out.write(all);
        out.close();
    }

    private static void encodefile() throws IOException {


        Scanner sc1 = new Scanner(file);
        char[] array;
        String all = "";
        while (sc1.hasNextLine()) {
            all += sc1.nextLine();
        }
        array = all.toCharArray();
        all = "";
        for (int i = 0; i < array.length; i++) {
            for (Letters l : arrayList1) {
                if (array[i] == l.getLetter()) {
                    all += (l.getCode());
                }
            }
        }

        FileWriter fw = new FileWriter("Encoded.txt");
        BufferedWriter out = new BufferedWriter(fw);
        out.write(all);
        out.close();

    }

    private static void code(int a, int b) {
        if (b - a <= 1 || a >= b) {
            return;
        }
        double min = 999.0;
        double sumA, sumB;
        int index = 0;
        for (int i = a + 1; i < b; i++) {
            sumA = 0;
            sumB = 0;
            for (int j = a; j < i; j++) {
                sumA += arrayList1.get(j).getProbabilty();
            }
            for (int j = i; j < b; j++) {
                sumB += arrayList1.get(j).getProbabilty();
            }
            double dif = Math.abs(sumA - sumB);
            if (dif < min) {
                min = dif;
                index = i;
            }

        }
        for (int i = a; i < index; i++) {
            arrayList1.get(i).addBit(0);
        }

        for (int i = index; i < b; i++) {
            arrayList1.get(i).addBit(1);
        }

        code(a, index);
        code(index, b);
    }

    private static void part1() throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        ArrayList arrayList = new ArrayList();
        char[] array;
        String all = "";
        while (sc.hasNextLine()) {
            all += sc.nextLine();
        }
        array = all.toCharArray();

        HashSet<Character> set = new HashSet<>();
        for (char each : array) {
            set.add(each);
        }


        for (Character r : set) {
            arrayList1.add(new Letters(r));
        }
        for (Letters l : arrayList1) {
            for (char ch : array) {
                if (l.getLetter() == ch) {
                    l.setNumber(l.getNumber() + 1);
                }
            }
        }

        for (Letters l : arrayList1) {

            l.setProbabilty((double) l.getNumber() / array.length);
        }
        Collections.sort(arrayList1);

    }
}
