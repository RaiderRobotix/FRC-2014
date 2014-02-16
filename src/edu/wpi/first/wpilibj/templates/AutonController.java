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
public class AutonController {
    
    private static AutonController m_instance;
    private int m_step;
    
    private Drivebase m_drivebase;
    
    private boolean m_driveComplete;
    
    private AutonController() {
        m_drivebase = Drivebase.getInstance();
        
        m_driveComplete = false;
    }
    
    public static AutonController getInstance() {
        if (m_instance == null) {
            m_instance = new AutonController();
        }
        return m_instance;
    }
    
    public void reset() {
        m_step = 0;
        m_drivebase.resetEncoders();
    }
    
    public void TestAuton() {
        System.out.println("Auton step: " + m_step);
        if (m_step == 0) {
            m_driveComplete = m_drivebase.driveForward(-185.0, 1.0);
            if (m_driveComplete) {
                m_step++;
            }
        } else {
            m_drivebase.setSpeed(0.0);
        }
    }
} 