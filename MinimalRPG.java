/*
 * Copyright (C) 2018 jtsuess19146
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package minimalrpg;

import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author jtsuess19146
 */
public class MinimalRPG {

    /**
     * @param args the command line arguments
     */
    //Stores all of the constructed rooms.
    static List<Room> map = new ArrayList<Room>();

    //Stores the player's various stats.
    static Player player = new Player();
    
    static List<Monster> monsterTypes = new ArrayList<Monster>();
    
    public static Random random;
    
    public static void pause()
    { 
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
    }
    
    /**
     * loadMap - This method loads a map into the game, along with its associated monster data.
     * @param fileName - The path of the file to be loaded as the map.
     * 
     */
    public static void loadMap(String fileName) {
            // FileReader reads text files in the default encoding.
            try
            {
                FileReader fileReader = 
                new FileReader(fileName + ".txt");
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    //System.out.println(line);
                    map.add(new Room(line));
                }
                FileReader monsterReader = 
                new FileReader(fileName + ".mon");
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader2 = 
                new BufferedReader(monsterReader);
                String line2;
                while((line2 = bufferedReader2.readLine()) != null) {
                    //System.out.println(line);
                    monsterTypes.add(new Monster(line2));
                }
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("The file path you entered cannot be read for some reason, or some files are missing. Make sure"
                        + " \nthat you typed it correctly.");
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file \'" 
                    + fileName + "\'");
            }
            

            
    }

    private static void playGame() {
        while (true)
        {
            player.turn();
            if (player.dead)
            {
                break;
            }
        }
        //Clear the map
        map.clear();
        //Reload the player;
        player = new Player();
    }

    private static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the minimal RPG!\n"
                + " Enter 1 to load a map, 2 to see game info, 3 to exit");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter the file path of the game you wish to load: ");
                sc.nextLine();
                String file = sc.nextLine();
                loadMap(file);
                playGame();
                break;
            case 2:
                System.out.println("This game was made by Suess and released under the GPLv3.");
                break;
            case 3:
                //System.out.println("Bye!");
                System.exit(0);

        }
    }

    public static void main(String[] args) {
        random = new Random();
        while (true) {
            mainMenu();
        }
    }

}
