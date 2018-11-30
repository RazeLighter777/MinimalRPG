2/*
 * Copyright (C) 2018 JTSuess19146
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

import java.util.Random;
/**
 *
 * @author JTSuess19146
 */

//Raw and dirty data structure for a monster, with a constructor allowing it to be 
//created from a string.
public class Monster {
    
    private final String monsterName;
    
    private final String monsterType;
    
    private double health;
    
    private final double damageMin, damageMax;
    
    private final double speedMin,speedMax;
    
    public double getReactionTime()
    {
        return speedMin + (speedMax - speedMin) * MinimalRPG.random.nextDouble();
    }
    
    public double getDamage()
    {
        return damageMin + (damageMax - damageMin) * MinimalRPG.random.nextDouble();
    }
    
    public String getName()
    {
        return monsterName;
    }
    public void damage(double amount)
    {
        health-=amount;
    }
    public double getHealth()
    {
        return health;
    }
    
    public Monster(String readline)
    {
        String[] parts = readline.split("\\;");
        
        monsterType = parts[0];
        
        monsterName = parts[1];

	health = Double.parseDouble(parts[3]);
        
        damageMin = Double.parseDouble(parts[4]);
        damageMax = Double.parseDouble(parts[5]);
        
        speedMin = Double.parseDouble(parts[6]);
        speedMax = Double.parseDouble(parts[7]);
    }
    
    
}
