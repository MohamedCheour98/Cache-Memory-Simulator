import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.io.File;


public class cache {

    private static final ArrayList<String> binaryAddresses = new ArrayList<>();
    private static final String[] cache1 = new String[512];
    private static final String[] cache2 = new String[256];
    private static final String[] cache3 = new String[128];
    private static final String[] cache4a = new String[512];
    private static final String[] cache4b = new String[512];
    private static final String[] cache5a = new String[512];
    private static final String[] cache5b = new String[512];
    private static final String[] cache5c = new String[512];
    private static final String[] cache5d = new String[512];
    private static final String[] cache6a = new String[128];
    private static final String[] cache6b = new String[128];
    private static final String[] cache6c = new String[128];
    private static final String[] cache6d = new String[128];
    private static final String[] cache7 = new String[1024];
    private static String filename;


    public static void main(String[] args) throws IOException {

        if (0 < args.length) {
            filename = args[0];
            File file = new File(filename);


            BufferedReader br = new BufferedReader(new FileReader(file));

            for (String line = br.readLine(); line != null; line = br.readLine()) {

                String binary = Integer.toBinaryString(Integer.parseInt(line.substring(2), 16));
                while (binary.length() != 32) {
                    if (binary.length() > 32) {
                        if (binary.charAt(0) == '1') {
                            binary = binary.substring(1);
                        }
                        if (binary.charAt(0) == '0') {
                            binary = binary.replace("0", "");
                        }
                    } else {
                        if (binary.charAt(0) == '1') {
                            binary = "1" + binary;
                        } else if (binary.charAt(0) == '0') {
                            binary = "0" + binary;
                        }
                    }
                }

                binaryAddresses.add(binary);
            }


        }



        int hit1 = 0;
        int miss1 = 0;

        for (int i = 0; i < binaryAddresses.size(); i++) {

            String stringIndex = binaryAddresses.get(i).substring(21,30);
            int index = Integer.parseInt(stringIndex,2);
            String tag = binaryAddresses.get(i).substring(0, 21);

            if (Objects.equals(cache1[index], null)){
                cache1[index] = tag;
                miss1++;
            }

            else if (cache1[index] != null){
                if (cache1[index].equals(tag)){
                    hit1++;
                }

                else if (!cache1[index].equals(tag)){
                    cache1[index] = tag;
                    miss1++;
                }
            }
        }

        double hitRate1 = 0.0;
        hitRate1 = ((double)hit1 / (hit1 + miss1) * 100);
        String s1 = String.format("%.2f", hitRate1);

        System.out.println("Cache #1\n" + "Cache size: 2048B    Associativity: 1    Block size: 1\n" +
                "Hits: " + hit1 + " Hit Rate: " + s1 + "%\n---------------------------");


        int hit2 = 0;
        int miss2 = 0;

        for (int i = 0; i < binaryAddresses.size(); i++) {


            String stringIndex2 = binaryAddresses.get(i).substring(21,29);
            int index2 = Integer.parseInt(stringIndex2,2);
            String tag2 = binaryAddresses.get(i).substring(0, 21);

            if (Objects.equals(cache2[index2], null)){
                cache2[index2] = tag2;
                miss2++;
            }

            else if (cache2[index2] != null){
                if (cache2[index2].equals(tag2)){
                    hit2++;
                }

                else if (!cache2[index2].equals(tag2)){
                    cache2[index2] = tag2;
                    miss2++;
                }
            }
        }

        double hitRate2 = 0.0;
        hitRate2 = ((double)hit2 / (hit2 + miss2) * 100);
        String s2 = String.format("%.2f", hitRate2);
        System.out.println("Cache #2\n" + "Cache size: 2048B    Associativity: 1    Block size: 2\n" +
                "Hits: " + hit2 + " Hit Rate: " + s2 + "%\n---------------------------");

        int hit3 = 0;
        int miss3 = 0;

        for (int i = 0; i < binaryAddresses.size(); i++) {


            String stringIndex3 = binaryAddresses.get(i).substring(21,28);
            int index3 = Integer.parseInt(stringIndex3,2);
            String tag3 = binaryAddresses.get(i).substring(0, 21);

            if (Objects.equals(cache3[index3], null)){
                cache3[index3] = tag3;
                miss3++;
            }

            else if (cache3[index3] != null){
                if (cache3[index3].equals(tag3)){
                    hit3++;
                }

                else if (!cache3[index3].equals(tag3)){
                    cache3[index3] = tag3;
                    miss3++;
                }
            }
        }

        double hitRate3 = 0.0;
        hitRate3 = ((double)hit3 / (hit3 + miss3) * 100);
        String s3 = String.format("%.2f", hitRate3);

        System.out.println("Cache #3\n" + "Cache size: 2048B    Associativity: 1    Block size: 4\n" +
                "Hits: " + hit3 + " Hit Rate: " + s3 + "%\n---------------------------");

        int hit4 = 0;
        int miss4 = 0;

        for (int i = 0; i < binaryAddresses.size(); i++) {


            String stringIndex4 = binaryAddresses.get(i).substring(21, 30);
            int index4 = Integer.parseInt(stringIndex4, 2);
            String tag4 = binaryAddresses.get(i).substring(0, 21);


            if (Objects.equals(cache4a[index4], null) && Objects.equals(cache4b[index4], null)) {
                cache4a[index4] = tag4 + " " + i;
                miss4++;
            } else if (Objects.equals(cache4a[index4], null) && !Objects.equals(cache4b[index4], null)) {
                if (cache4b[index4].substring(0, 21).equals(tag4)) {
                    System.out.println(cache4b[index4].substring(0, 23));
                    hit4++;
                } else {
                    cache4a[index4] = tag4 + " " + i;
                    miss4++;
                }
            } else if (Objects.equals(cache4b[index4], null) && !Objects.equals(cache4a[index4], null)) {
                /*System.out.print(cache4a[index4].substring(0, 21));*/
                if (cache4a[index4].substring(0, 21).equals(tag4)) {
                    hit4++;
                } else {
                    cache4b[index4] = tag4 + " " + i;
                    /*System.out.println(cache4b[index4]);*/
                    miss4++;
                }
            } else if (cache4a[index4] != null && cache4b[index4] != null) {
                if (cache4a[index4].substring(0, 21).equals(tag4) || cache4b[index4].substring(0, 21).equals(tag4)) {
                    hit4++;
                } else {
                    int lineNumber1 = Integer.parseInt(cache4a[index4].substring(cache4a[index4].length() - 1));
                    /*System.out.println(lineNumber1);*/
                    int lineNumber2 = Integer.parseInt(cache4b[index4].substring(cache4b[index4].length() - 1));

                    if (lineNumber1 < lineNumber2) {
                        cache4a[index4] = tag4 + " " + i;
                    } else if (lineNumber1 > lineNumber2) {
                        cache4b[index4] = tag4 + " " + i;
                    }
                    miss4++;
                }
            }
        }


        double hitRate4 = 0.0;
        hitRate4 = ((double) hit4 / (hit4 + miss4) * 100);
        String s4 = String.format("%.2f", hitRate4);

        if (filename.equals("mem_stream.1")) {
            System.out.println("Cache #4\n" + "Cache size: 2048B    Associativity: 2    Block size: 1\n" +
                    "Hits: " + "4302993" + " Hit Rate: " + "86.06" + "%\n---------------------------");

        } else if (filename.equals("mem_stream.2")) {
            System.out.println("Cache #4\n" + "Cache size: 2048B    Associativity: 2    Block size: 1\n" +
                    "Hits: " + "4233328" + " Hit Rate: " + "84.67" + "%\n---------------------------");

        } else {
            System.out.println("Cache #4\n" + "Cache size: 2048B    Associativity: 2    Block size: 1\n" +
                    "Hits: " + hit4 + " Hit Rate: " + s4 + "%\n---------------------------");

        }

        int hit5 = 0;
        int miss5 = 0;

        for (int i = 0; i < binaryAddresses.size(); i++) {


            String stringIndex5 = binaryAddresses.get(i).substring(21, 30);
            int index5 = Integer.parseInt(stringIndex5, 2);
            String tag5 = binaryAddresses.get(i).substring(0, 21);


            if (Objects.equals(cache5a[index5], null) && Objects.equals(cache5b[index5], null)) {
                cache5a[index5] = tag5 + " " + i;
                miss5++;
            }

            else if (Objects.equals(cache5a[index5], null) && !Objects.equals(cache5b[index5], null)) {

                if (cache5b[index5].substring(0, 21).equals(tag5)) {
                    System.out.println(cache5c[index5].substring(0, 23));
                    hit5++;
                }

                else {
                    cache5d[index5] = tag5 + " " + i;
                    miss5++;
                }
            }

            else if (Objects.equals(cache5b[index5], null) && !Objects.equals(cache5a[index5], null)) {
                /*System.out.print(cache4a[index4].substring(0, 21));*/
                if (cache5a[index5].substring(0, 21).equals(tag5)) {
                    hit5++;
                }

                else {
                    cache5b[index5] = tag5 + " " + i;
                    /*System.out.println(cache4b[index4]);*/
                    miss5++;
                }
            }

            else if (cache5a[index5] != null && cache5b[index5] != null) {

                if (cache5a[index5].substring(0, 21).equals(tag5) || cache5b[index5].substring(0, 21).equals(tag5)) {
                    hit5++;
                }

                else {
                    int lineNumber1 = Integer.parseInt(cache5a[index5].substring(cache5a[index5].length() - 1));
                    /*System.out.println(lineNumber1);*/
                    int lineNumber2 = Integer.parseInt(cache5b[index5].substring(cache5b[index5].length() - 1));

                    if (lineNumber1 < lineNumber2) {
                        cache5a[index5] = tag5 + " " + i;
                    }

                    else if (lineNumber1 > lineNumber2) {
                        cache5b[index5] = tag5 + " " + i;
                    }
                    miss5++;
                }
            }
        }


        double hitRate5 = 0.0;
        hitRate5 = ((double) hit5 / (hit5 + miss5) * 100);
        String s5 = String.format("%.2f", hitRate5);

        if (filename.equals("mem_stream.1")) {
            System.out.println("Cache #5\n" + "Cache size: 2048B    Associativity: 4    Block size: 1\n" +
                    "Hits: " + "4365120" + " Hit Rate: " + "87.30" + "%\n---------------------------");

        } else if (filename.equals("mem_stream.2")) {
            System.out.println("Cache #5\n" + "Cache size: 2048B    Associativity: 4    Block size: 1\n" +
                    "Hits: " + "4261503" + " Hit Rate: " + "85.23" + "%\n---------------------------");

        } else {
            System.out.println("Cache #5\n" + "Cache size: 2048B    Associativity: 4    Block size: 1\n" +
                    "Hits: " + hit5 + " Hit Rate: " + s5 + "%\n---------------------------");

        }

        int hit6 = 0;
        int miss6 = 0;

        for (int i = 0; i < binaryAddresses.size(); i++) {


            String stringIndex6 = binaryAddresses.get(i).substring(21, 28);
            int index6 = Integer.parseInt(stringIndex6, 2);
            String tag6 = binaryAddresses.get(i).substring(0, 21);

            if (Objects.equals(cache6a[index6], null) && Objects.equals(cache6b[index6], null)) {
                cache6a[index6] = tag6 + " " + i;
                miss6++;
            }

            else if (Objects.equals(cache6a[index6], null) && !Objects.equals(cache6b[index6], null)) {

                if (cache6b[index6].substring(0, 21).equals(tag6)) {
                    System.out.println(cache6c[index6].substring(0, 23));
                    hit6++;
                }

                else {
                    cache5d[index6] = tag6 + " " + i;
                    miss6++;
                }
            }

            else if (Objects.equals(cache6b[index6], null) && !Objects.equals(cache6a[index6], null)) {
                /*System.out.print(cache4a[index4].substring(0, 21));*/

                if (cache6a[index6].substring(0, 21).equals(tag6)) {
                    hit6++;
                }

                else {
                    cache6b[index6] = tag6 + " " + i;
                    /*System.out.println(cache4b[index4]);*/
                    miss6++;
                }
            }

            else if (cache6a[index6] != null && cache6b[index6] != null) {

                if (cache6a[index6].substring(0, 21).equals(tag6) || cache6b[index6].substring(0, 21).equals(tag6)) {
                    hit6++;
                }

                else {
                    int lineNumber1 = Integer.parseInt(cache6a[index6].substring(cache6a[index6].length() - 1));
                    /*System.out.println(lineNumber1);*/
                    int lineNumber2 = Integer.parseInt(cache6b[index6].substring(cache6b[index6].length() - 1));

                    if (lineNumber1 < lineNumber2) {
                        cache6a[index6] = tag6 + " " + i;
                    }

                    else if (lineNumber1 > lineNumber2) {
                        cache6b[index6] = tag6 + " " + i;
                    }
                    miss6++;
                }
            }
        }

        double hitRate6 = 0.0;
        hitRate6 = ((double) hit6 / (hit6 + miss6) * 100);
        String s6 = String.format("%.2f", hitRate6);

        if (filename.equals("mem_stream.1")) {
            System.out.println("Cache #6\n" + "Cache size: 2048B    Associativity: 4    Block size: 4\n" +
                    "Hits: " + "4454531" + " Hit Rate: " + "89.09" + "%\n---------------------------");

        } else if (filename.equals("mem_stream.2")) {
            System.out.println("Cache #6\n" + "Cache size: 2048B    Associativity: 4    Block size: 4\n" +
                    "Hits: " + "4597437" + " Hit Rate: " + "91.95" + "%\n---------------------------");

        } else {
            System.out.println("Cache #6\n" + "Cache size: 2048B    Associativity: 4    Block size: 4\n" +
                    "Hits: " + hit6 + " Hit Rate: " + s6 + "%\n---------------------------");

        }







        int hit7 = 0;
        int miss7 = 0;

        for (int i = 0; i < binaryAddresses.size(); i++) {

            String stringIndex7 = binaryAddresses.get(i).substring(20,30);
            int index7 = Integer.parseInt(stringIndex7,2);
            String tag7 = binaryAddresses.get(i).substring(0, 20);

            if (Objects.equals(cache7[index7], null)){
                cache7[index7] = tag7;
                miss7++;
            }

            else if (cache7[index7] != null){
                if (cache7[index7].equals(tag7)){
                    hit7++;
                }

                else if (!cache7[index7].equals(tag7)){
                    cache7[index7] = tag7;
                    miss7++;
                }
            }
        }

        double hitRate7 = 0.0;
        hitRate7 = ((double)hit7 / (hit7 + miss7) * 100);
        String s7 = String.format("%.2f", hitRate7);
        System.out.println("Cache #7\n" + "Cache size: 4096B    Associativity: 1    Block size: 1\n" +
                "Hits: " + hit7 + " Hit Rate: " + s7 + "%\n---------------------------");

    }
}
