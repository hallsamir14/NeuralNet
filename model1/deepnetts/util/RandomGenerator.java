/**  
 *  DeepNetts is pure Java Deep Learning Library with support for Backpropagation 
 *  based learning and image recognition.
 * 
 *  Copyright (C) 2017  Zoran Sevarac <sevarac@gmail.com>
 *
 *  This file is part of DeepNetts.
 *
 *  DeepNetts is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.package deepnetts.core;
 */

package deepnetts.util;

import java.util.Random;

/**
 * Random number generator singleton.
 * 
 * @author Zoran Sevarac
 */
public class RandomGenerator {
    
    private static RandomGenerator instance;
    
    private Random randomGen =  new Random(123);
        
    /**
     * Prevent instantiation of this class
     */
    private RandomGenerator() { }
    
    public final static RandomGenerator getDefault() {
        if (instance == null) {
            instance = new RandomGenerator();
        }
        
        return instance;
    }
    
    public void initSeed(long seed)  {
        randomGen = new Random(seed);
    }
    
    public float nextFloat() {
        return randomGen.nextFloat();
    }
    
    public float nextGaussian() {
        return (float)randomGen.nextGaussian();
    }    
    
    public int nextInt() {
        return randomGen.nextInt();
    }
    
    public Random getRandom() {
        return  randomGen;
    }
    

}
