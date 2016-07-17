package BitwiseObfuscator;

import static BitwiseObfuscator.Obfuscator.obfuscate;

public class Exe {

    public static long time = System.currentTimeMillis();

    public static void main(String[] args) {
        try {
            /**
            Scanner scanner = new Scanner(new File("test.txt"));
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()){
                sb.append(scanner.nextLine());
            }
            String test = sb.toString(); **/
            String test2 = "The grey goose wanders aimlessly through the underbrush, knowing nothing and everything";

            String res = obfuscate(test2);
            System.out.println(res);

            System.out.println("length in: " + test2.length() + ", length out: " + res.length());
            time = System.currentTimeMillis() - time;
            System.out.println("Execution time: " + time + " ms");
        } catch (Exception e) {}
    }
}