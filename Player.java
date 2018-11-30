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

import java.util.*;

/**
 *
 * @author jtsuess19146
 */
public class Player {

    public String name;

    public double health;

    public int armor;
    
    public double agility;

    public int attack;

    public int gold = 0;

    public boolean dead = false;

    public int mana;

    public String currentRoom = "start";

    public List<String> Items = new ArrayList<>();

    public boolean monsterEncounter(Monster enemy) {
        //Loop until broken.
        while (true) {
            if (false) {
                break;
            }
        }

        return true;
    }

    public void turn() {

        while (true) {

            Room temproom = null;
            //Find the room on the map
            for (Room map : MinimalRPG.map) {
                if (currentRoom.equals(map.uniqueLabel)) {
                    temproom = map;
                    System.out.println("==================");
                    System.out.println(temproom.description);
                    System.out.println("==================");
                }
            }

            //Tag application. Should probably be moved to another function in the future.
            //Check if the room will kill you, and do so if it does.
            if (temproom.tags.containsKey("killroom")) {
                if (temproom.tags.get("killroom").equals("true")) {
                    System.out.println("You are now dead.");
                    dead = true;
                    return;
                }
            }

            //Check for gold.
            if (temproom.tags.containsKey("gold")) {
                if (Integer.parseInt(temproom.tags.get("gold")) > 0) {
                    System.out.println("You find " + temproom.tags.get("gold") + " gold");
                    gold += Integer.parseInt(temproom.tags.get("gold"));
                    temproom.tags.put("gold", "0");
                }

            }

            //Print all of the possible routes:
            System.out.println("You may go: ");
            for (String key : temproom.pathways.keySet()) {
                //iterate over keys
                System.out.println(key);
            }

            //Prompt for the player's input 
            System.out.println("Enter an action OR a direction: ");

            //Read the players input
            //Parse for commands
            //TODO
            String input;

            Scanner sc = new Scanner(System.in);

            input = sc.nextLine();

            if (input.equalsIgnoreCase("/balance") || input.equalsIgnoreCase("/gold")) {
                System.out.println("You check your gold bag and find " + Integer.toString(gold) + " gold.");
                return;
            }
            //Parse for directions

            for (String key : temproom.pathways.keySet()) {
                //iterate over keys
                if (key.equals(input)) {
                    //Check if the room is open.
                    if (!temproom.tags.get("closed").equals("true")) {

                        System.out.println("Move successful.");
                        currentRoom = temproom.pathways.get(input);

                        return;
                    } else {
                        System.out.println("That pathway is closed.");
                    }

                }
            }
            System.out.println("Invalid command or direction. Check your spelling.");

            MinimalRPG.pause();
        }

        //Check for room tags and apply them
    }

}
