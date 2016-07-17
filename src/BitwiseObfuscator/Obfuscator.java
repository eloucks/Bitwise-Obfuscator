package BitwiseObfuscator;

import java.util.ArrayList;

public class Obfuscator {

    public static final int blockSize = 4; //size of comparison blocks of binary
    public static final int lookDist = 64; //number of blocks ahead of first instance that duplicates are looked for

    public static String obfuscate(String input){
        byte[] bytes = input.getBytes();
        StringBuilder sbin = new StringBuilder();
        for (int i = 0; i<bytes.length; i++) {
            sbin.append(toBinary(bytes[i]));
        }
        String bin = sbin.toString(); //produce string of binary from original string
        String combin = iterate(bin, 2);
        byte[] bytescom = new byte[combin.length()/8];
        for (int i = 0; i<combin.length()/8; i++){
            bytescom[i] = toByte(combin.substring(i*8, i*8+8));
        }
        return new String(bytescom);
    }

    private static String iterate(String bin, int numItr){
        String combin = "";
        while (numItr>0) {
            StringBuilder scom = new StringBuilder();
            int numBlocks = bin.length() / blockSize;  //determines number of blocks in data
            ArrayList<String> blockList = new ArrayList<>();
            for (int i = 0; i < numBlocks * blockSize; i += blockSize) { //creates an arraylist of binary strings of size blockSize
                StringBuilder temp = new StringBuilder();
                for (int x = 0; x < blockSize; x++) {
                    temp.append(bin.charAt(i + x));
                }
                blockList.add(temp.toString());
            }
            int blockIndex = 0;
            while (blockIndex < blockList.size()) { //essentially, while has next, checks for duplicates
                scom.append(blockList.get(blockIndex));
                StringBuilder temp = new StringBuilder();
                for (int i = 1; i <= lookDist && blockIndex + i < blockList.size(); i++) { //if the element is the same, write 0
                    if (blockList.get(blockIndex).equals(blockList.get(blockIndex + i))) {
                        temp.append(1);
                    } else {
                        temp.append(0);
                    } //something wrong?
                }
                while (temp.length() < lookDist) { //ensures result is the same length even if no more blocks
                    temp.append(0);
                }
                String scan = temp.toString();
                scom.append(scan);
                for (int i = scan.length() - 1; i > 0; i--) { //remove repeated elements
                    if (scan.charAt(i) == '1') {
                        blockList.remove(i + blockIndex);
                    }
                }
                blockIndex++;
            }
            combin = scom.toString();
            bin = combin;
            numItr--;
        }
        return combin;
    }

    public static String toBinary(byte b){
        StringBuilder sb = new StringBuilder();
        if (b<0){
            sb.append(1);
            b *= -1;
        } else { sb.append(0);}
        if (b>=64) { sb.append(1); b-=64;} else {sb.append(0);}
        if (b>=32) { sb.append(1); b-=32;} else {sb.append(0);}
        if (b>=16) { sb.append(1); b-=16;} else {sb.append(0);}
        if (b>=8) { sb.append(1); b-=8;} else {sb.append(0);}
        if (b>=4) { sb.append(1); b-=4;} else {sb.append(0);}
        if (b>=2) { sb.append(1); b-=2;} else {sb.append(0);}
        if (b>=1) { sb.append(1); b-=1;} else {sb.append(0);}
        return sb.toString();
    }

    public static byte toByte(String s){
        byte b = 0;
        if (s.charAt(1) == '1'){b+=64;}
        if (s.charAt(2) == '1'){b+=32;}
        if (s.charAt(3) == '1'){b+=16;}
        if (s.charAt(4) == '1'){b+=8;}
        if (s.charAt(5) == '1'){b+=4;}
        if (s.charAt(6) == '1'){b+=2;}
        if (s.charAt(7) == '1'){b+=1;}
        if (s.charAt(0) == '1'){b*=-1;}
        return b;
    }
}
