/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;

/**
 *
 * @author 25-DS
 */
public class Vision {
    
    private static Vision m_instance;
    
    private Vision() {
        
    }
    
    public static Vision getInstance() {
        if (m_instance == null) {
            m_instance = new Vision();
        }
        return m_instance;
    }
    
}
