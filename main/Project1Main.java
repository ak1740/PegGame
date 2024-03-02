package PegGame.main;

import java.util.Scanner;


public class Project1Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please Enter Your File Name: "); //user MUST input file for the board format
        String input = scanner.nextLine();
        char[][] exboard = TextReader.readArrayFromFile(input); //board is being created using TextReader
        CommandLine pegGame=new CommandLine(exboard);
        CommandLine.playPegGame(pegGame, scanner);
}
}