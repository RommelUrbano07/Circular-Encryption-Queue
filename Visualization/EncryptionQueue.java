/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visualization;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Asus
 */
public class EncryptionQueue {

    static int count = 0;
    static int CharOffset = 0;
    static int QueueOffset = 3;
    static Queue queue = genQueue(QueueOffset);

    public static void main(String[] args) {
        queue.display();
//        String bestCase = bestCase(10, CharOffset);
//        String arr[] = encrypt(bestCase, CharOffset, QueueOffset).split(",");
////        System.out.println(count);
//        count = 0;
//        String decrypt = decrypt(arr, queue, QueueOffset);
//        System.out.println(count);
//        count = 0;

//        String aveCase = aveCase(10, CharOffset);
//        String arr[] = encrypt(aveCase, CharOffset, QueueOffset).split(",");
////        System.out.println(count);
//        count = 0;
//        String decrypt = decrypt(arr, queue, QueueOffset);
//        System.out.println(count);
//        count = 0;
//        String worstCase = aveCase(10, CharOffset);
//        String arr[] = encrypt(worstCase, CharOffset, QueueOffset).split(",");
////        System.out.println(count);
//        count = 0;
//        String decrypt = decrypt(arr, queue, QueueOffset);
//        System.out.println(count);
//        count = 0;
        for (int i = 100; i <= 10000; i += 50) {
//            bestCase = bestCase(i, CharOffset);
//            arr = encrypt(bestCase, CharOffset, QueueOffset).split(",");
////            System.out.println(count);
//            count = 0;
//            decrypt = decrypt(arr, queue, QueueOffset);
//            System.out.println(count);
//            count = 0;

//            aveCase = aveCase(i, CharOffset);
//            arr = encrypt(aveCase, CharOffset, QueueOffset).split(",");
////            System.out.println(count);
//            count = 0;
//            decrypt = decrypt(arr, queue, QueueOffset);
//            System.out.println(count);
//            count = 0;
//            worstCase = worstCase(i, CharOffset);
//            arr = encrypt(worstCase, CharOffset, QueueOffset).split(",");
////            System.out.println(count);
//            count = 0;
//            decrypt = decrypt(arr, queue, QueueOffset);
//            System.out.println(count);
//            count = 0;
        }

//        String decrypt = decrypt(arr, queue, QueueOffset);
//        System.out.println(decrypt);
//        decrypt = decrypt(arr2, queue, QueueOffset);
//        System.out.println(decrypt);
//        decrypt = decrypt(arr1, queue, QueueOffset);
//        System.out.println(decrypt);
//          String SampleInput="Hello World";
//          System.out.println("Sample Input: "+SampleInput);
//          String arr[]=encrypt(SampleInput,CharOffset,QueueOffset).split(",");
//          String temp=Arrays.toString(arr).replaceAll("[\\[\\]]", "");
//          temp=temp.replaceAll(" ", "");
//          System.out.println("Encrypted Values: "+(temp));
//          
//          System.out.println("Decoding Encrypted Values");
//          String decode=decrypt(temp.split(","), queue, QueueOffset).replaceAll(",", "");
//          String tempArr[]=decode.split("W");
//          System.out.println("Decoded Values: "+tempArr[0]+" W"+tempArr[1] );
    }

    /**
     * gets the length of the case and generates the first character of the
     * queue
     *
     * @param length
     * @param offset
     * @return
     */
    public static String bestCase(int length, int offset) {
        StringBuilder input = new StringBuilder();
        int var = queue.front.data + offset;
        //this part, front of the queue + offset results in the first character in the offsetQueue
        for (int i = 0; i < length; i++) {
            input.append((char) var);
        }
        return input.toString();
    }

    /**
     * gets the length of the case and generates the last character of the queue
     *
     * @param length
     * @param offset
     * @return
     */
    public static String worstCase(int length, int offset) {
        StringBuilder input = new StringBuilder();
        int var = queue.rear.data + offset;
        //this part, rear of the queue + offset results in the last character in the offsetQueue
        for (int i = 0; i < length; i++) {
//            input.append((char)var);
            if ((var + offset) > 126) {
                var = ' ';
                int hold = var + offset;
                input.append((char) hold);
//                var++;
            } else {
                int hold = var;
                input.append((char) hold);
//                var++;
            }
        }
        return input.toString();
    }
    /**
     * simply a combination of half of best case and half of worst case
     * @param length
     * @param offset
     * @return 
     */
    public static String aveCase(int length, int offset) {
        StringBuilder input = new StringBuilder();
        input.append(bestCase(length / 2, offset));
        input.append(worstCase(length / 2, offset));
        return input.toString();
    }
    /**
     * generation of QUEUE data structure, not CIRCULAR QUEUE
     * @param offset
     * @return 
     */
    public static Queue genQueue(int offset) {
        Queue queue = new Queue();
        int var = ' ';
        for (int i = 0; i < 127; i++) {
            int temp = var + offset;
            if (temp <= 126) {
                char letter = (char) temp;
                queue.enqueue(letter);
                var++;
            } else {
                var = ' ';
                temp = var;
                for (int j = 0; j < offset; j++) {
                    char letter = (char) temp;
                    queue.enqueue(letter);
                    temp++;
                }
                break;
            }
        }
        return queue;
    }
    /**
     * encryption of Text:
     * the process is, split the characters into an array
     * iterate through the queue until the corresponding character is found
     * add to the encryption output the count frequency + offset
     * @param input
     * @param CharOffset
     * @param QueueOffset
     * @return 
     */
    public static String encrypt(String input, int QueueOffset) {
        ArrayList<String> encryptedList = new ArrayList<>();
        int encryptData = 0;
        for (int i = 0; i < input.length(); i++) {
            Queue queue1 = genQueue(QueueOffset);
            Node temp = queue1.front;
            for (int j = 0; j < queue.size; j++) {
                if (input.charAt(i) == temp.data) {
                    encryptedList.add(encryptData + "");
                    encryptData = 0;
                    break;
                } else {
                    encryptData++;
                    count++;
                    temp = temp.next;
                }
            }
        }
        return Arrays.toString(encryptedList.toArray()).replaceAll("[\\[ \\]]", "");
    }
    /**
     * process of decryption:
     * parse the character input into an integer
     * continue dequeuing until the integer is exhausted
     * store the data of the front of the queue at the decrypted list.
     * @param input
     * @param queue
     * @param offset
     * @return 
     */
    public static String decrypt(String input[], Queue queue, int offset) {
        ArrayList<String> var = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            Queue temp = genQueue(offset);
            for (int j = 0; j < Integer.parseInt(input[i]); j++) {
                temp.dequeque();
                count++;
            }
            var.add(temp.front.data + "");
        }
        return Arrays.toString(var.toArray()).replaceAll("[\\[ \\]]", "");
    }
}
