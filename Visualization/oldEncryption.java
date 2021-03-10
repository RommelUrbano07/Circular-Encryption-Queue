/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visualization;

import java.util.Arrays;

/**
 *
 * @author Asus
 */

public class oldEncryption {

    static int count = 0;

    public static String bestCase(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, 'c');
        String output = new String(chars);
        return output;
    }

    public static String aveCase(int length) {
        char[] chars = new char[length/2];
        Arrays.fill(chars, 'c');
        char[] chars2 = new char[length/2];
        Arrays.fill(chars, 'b');
        String output = new String(chars);
        return output;
    }
    
    public static String worstCase(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, 'b');
        String output = new String(chars);
        return output;
    }

    public static void main(String[] args) {
        String input = worstCase(1);
        int offset = 3;
        Queue queue = genCircle(offset);
        String decrypt[] = encrypt(input, queue, offset).split(",");
        System.out.println(count);
        count = 0;
        input = worstCase(10);
        offset = 3;
        queue = genCircle(offset);
        decrypt = encrypt(input, queue, offset).split(",");
        System.out.println(count);
        count = 0;
//        for (int i = 100; i < 10000; i += 50) {
//            input = bestCase(i);
//            offset = 3;
//            queue = genCircle(offset);
//            decrypt = encrypt(input, queue, offset).split(",");
//            System.out.println(count);
//            count = 0;
//        }
    }

    public static Queue genCircle(int offset) {
        Queue queue = new Queue();
        int var = 'A';
        for (int i = 0; i < 26; i++) {
            int temp = var + offset;
            if (temp <= 90) {
                char letter = (char) temp;
                queue.enqueue(letter);
                var++;
            } else {
                var = 'A';
                temp = var;
                for (int j = i; j < 26; j++) {
                    char letter = (char) temp;
                    queue.enqueue(letter);
                    temp++;
                }
                break;
            }
        }
        return queue;
    }

    public static String encrypt(String input, Queue queue, int offset) {
        String output = "";
        Node temp = queue.front;
        for (int i = 0; i < input.length(); i++) {
            int encrypt = input.charAt(i);
            String value1[] = binary(encrypt, 8).split("");
            String value2[] = binary(temp.data, 8).split("");
            for (int j = 0; j < 8; j++) {
                int val1 = Integer.parseInt(value1[j]);
                int val2 = Integer.parseInt(value2[j]);
                if (val1 != val2) {
                    output += "1";
                } else {
                    output += "0";
                }
                count++;
            }
            temp = temp.next;
            if (i + 1 != input.length()) {
                output += ",";
            }
        }
        return Arrays.toString(decimal(output)).replaceAll("[\\[ \\]]", "");
    }

    public static String decrypt(String input[], Queue queue, int offset) {
        String output = "";
        Node temp = queue.front;
        for (int i = 0; i < input.length; i++) {
            int encrypt = Integer.parseInt(input[i]);
            String value1[] = binary(encrypt, 8).split("");
            String value2[] = binary(temp.data, 8).split("");
            for (int j = 0; j < 8; j++) {
                int val1 = Integer.parseInt(value1[j]);
                int val2 = Integer.parseInt(value2[j]);
                if (val1 != val2) {
                    output += "1";
                } else {
                    output += "0";
                }
            }
            temp = temp.next;
            if (i + 1 != input.length) {
                output += ",";
            }
        }
        System.out.println(output);
        return Arrays.toString(decode(output)).replaceAll("[\\[\\]]", "");
    }

    public static String[] decode(String output) {
        String arr[] = output.split(",");
        for (int i = 0; i < arr.length; i++) {
            char temp = (char) Integer.parseInt(arr[i], 2);
            arr[i] = temp + "";
        }
        return arr;
    }

    public static String[] decimal(String output) {
        String arr[] = output.split(",");
        for (int i = 0; i < arr.length; i++) {
            int temp = Integer.parseInt(arr[i], 2);
            arr[i] = temp + "";
        }
        return arr;
    }

    public static String binary(int value, int iterations) {
        if (iterations == 1) {
            return "0";
        } else {
            return binary(value / 2, --iterations) + (value % 2 + "");
        }
    }

}
