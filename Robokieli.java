import java.util.Arrays;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Robokieli {
    public static void main(String[] args) {
        boolean verbose = false;
        int pc = 0;
        int ic = 0;
        int currentNum = 0;

        String[] comm = loadComm(args[0]);
        int[] nmb = loadNmb(args[1]);
        int[] mempatches = loadNmb(args[2]);
        int[] mem = new int[100000];
        for (int i = 0; i < mempatches.length; i++) {
            mem[i] = mempatches[i];
        }

        if (args.length == 4 && args[3].contains("-v")) {
            verbose = true;
        }

        String outputQueue = "";

        // main program loop
        loop: while (true) {


            String[] command = new String[2];
            command[0] = comm[pc];

            if (comm[pc].contains("(")) {
                command = comm[pc].split("\\(");
                command[1] = command[1].substring(0, command[1].length() - 1);
            }

            switch (command[0]) {
                case "INPUT":
                    try {         
                        if (verbose) {
                            System.out.println("Taking in " + nmb[ic]);
                        }

                        currentNum = nmb[ic];
                        ic++;
                        break;
                    } catch (ArrayIndexOutOfBoundsException e){
                        break loop;
                    }


                case "OUTPUT":
                    if (verbose) {
                        System.out.println("Outputting " + currentNum);
                    }

                    outputQueue += currentNum + ", ";
                    break;

                case "ADD":
                    if (verbose) {
                        System.out.println("Adding " + currentNum + " to " + command[1]);
                    }

                    currentNum += mem[Integer.parseInt(command[1])];
                    break;

                case "SUB":
                    if (verbose) {
                        System.out.println("Substracting " + currentNum + " from " + command[1]);
                    }

                    currentNum -= mem[Integer.parseInt(command[1])];
                    break;

                case "COPYTO":
                    if (verbose) {
                        System.out.println("Copying " + currentNum + " to memory slot " + command[1]);
                    }

                    mem[Integer.parseInt(command[1])] = currentNum;
                    break;

                case "COPYFROM":
                    if (verbose) {
                        System.out.println("Copying " + currentNum + " from memory slot " + command[1]);
                    }

                    currentNum = mem[Integer.parseInt(command[1])];
                    break;

                case "JUMP":
                    if (verbose) {
                        System.out.println("Jumping to sub " + findSub(comm, command[1]));
                    }

                    pc = findSub(comm, command[1]);
                    break;

                case "JUMPIFZERO":
                    if (verbose) {
                        System.out.println("Jumping to sub " + findSub(comm, command[1]));
                    }

                    if (currentNum == 0) {
                        pc = findSub(comm, command[1]);
                    }
                    break;

                case "JUMPIFNEG":
                    if (verbose) {
                        System.out.println("Jumping to subroutine " + findSub(comm, command[1]));
                    }

                    if (currentNum < 0) {
                        pc = findSub(comm, command[1]);
                    }
                    break;

                default:
                    break;

                }
            pc++;
            
            if (verbose) {
                System.out.println("Current num is " + currentNum);
            }

            if (pc >= comm.length) {
                break loop;
            }


        }
        System.out.println("Output is: " + outputQueue.substring(0, outputQueue.length() - 2));





    }

    public static int findSub(String[] comms, String sub) {
        for (int i = 0; i < comms.length; i++) {
            if (comms[i].equals(sub + ":")) {
                return i;
            }
        }
        return 0;
    }

    public static String[] loadComm(String fName) {
        try {

            Scanner input = new Scanner(new File(fName));
            String out = "";
            while (input.hasNextLine()) {
                out += input.nextLine() + ".";
            }

            String[] outArr = out.split("[.]");
            return outArr;

        } catch (FileNotFoundException e) {
            System.exit(1);
            return null;
        }
    }

    public static int[] loadNmb(String fname) {
        try {
            String out = "";
            Scanner input = new Scanner(new File(fname));
            while (input.hasNextLine()) {
                out = out + input.nextLine() + ".";
            }
            int[] realOut = new int[out.split("[.]").length];

            int i = 0;
            for (String s : out.split("[.]")) {
                realOut[i] = Integer.parseInt(s);
                i++;
            }

            return realOut;

        } catch (FileNotFoundException e) {
            System.exit(1);
            return null;
        }
    }

}