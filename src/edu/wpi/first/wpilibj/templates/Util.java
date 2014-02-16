/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author 25-DS
 */
public class Util {
    
    public static double inchesToCounts(double inches) {
        return inches / Constants.INCHES_PER_COUNT;
    }
    
    public static double countsToInches(double counts) {
        return counts * Constants.INCHES_PER_COUNT;
    }
}
