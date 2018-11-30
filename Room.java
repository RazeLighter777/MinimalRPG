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
public class Room {

    //Stores a map of directions and the labels that they lead to.
    HashMap<String, String> pathways = new HashMap<String, String>();

    HashMap<String, String> tags = new HashMap<String, String>();

    //The description that appears when you enter the room
    public String description = "This room has no description.";

    //A unique label describing the room, we can't use pointers because 
    //we have to load from a file.
    public String uniqueLabel = "No label specified.";

    public Room(String roomline) {
        String[] parts = roomline.split("\\;");

        uniqueLabel = parts[0];

        description = parts[1];
        description.replace("\\\\n", "\n");

        //Split the pathways part of the string
        String[] pathwaysraw = parts[2].split("\\,");

        //Split the room tags part of the string.
        String[] tagsraw = parts[3].split("\\,");

        int index = 0;
        while (true) {
            pathways.put(pathwaysraw[index], pathwaysraw[index + 1]);
            if ((index + 1) >= (pathwaysraw.length - 1)) {
                break;
            }
            index+=2;
        }
        index = 0;
        while (true) {
            tags.put(tagsraw[index], tagsraw[index + 1]);
            if ((index + 1) >= (tagsraw.length - 1)) {
                break;
            }
            index+=2;
        }

    }

}
