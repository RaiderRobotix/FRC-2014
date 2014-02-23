/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author 25-DS
 */
public class AutonController {
    
    private static AutonController m_instance;
    private int m_step;
    
    private final Drivebase m_drivebase;
    private final Pickup m_pickup;
    
    private boolean m_driveComplete;
    
    private final Timer m_timer;
    private double m_ms;
    
    private AutonController() {
        m_drivebase = Drivebase.getInstance();
        m_driveComplete = false;
        
        m_pickup = Pickup.getInstance();
        
        m_timer = new Timer();
        m_ms = 0.1;
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
    
    public void DoNothing() {
        m_drivebase.setSpeed(0.0);
    }
    
    public void PrintTime() {
        if (m_step == 0) {
            m_timer.start();
            m_step++;
        } 
        else if (m_step == 1) {
            double time = m_timer.get();
            if (time > m_ms) {
                System.out.println("Timer: " + time);
                m_ms += 0.1;
            }
        }
    }
    
    public void DirtySanchezJr(boolean timeToScore) {
        //System.out.println("Auton step: " + m_step);
        if (m_step == 0) {
            m_driveComplete = m_drivebase.driveStraight(-185.0, 1.0, -90);
            if (m_driveComplete) {
                m_driveComplete = false;
                m_timer.start();
                m_timer.reset();
                m_step++;
            }
        } 
        else if (m_step == 1) {
            m_pickup.openPickup();;
            if (m_timer.get() > 0.2) {
                m_step++;
            }
        } else if (m_step == 2) {
            if (timeToScore) {
                m_pickup.runIntakeWheelsOut();
                m_pickup.openKicker();
                m_step++;
            }
        }
        else {
            m_drivebase.setSpeed(0.0);
        }
    }
    
    public void DriveStraight() {
        System.out.println("Distance: " + m_drivebase.getLeftEncoderDistance());
        if (m_step == 0) {
            m_driveComplete = m_drivebase.driveStraight(-185.0, 1.0, -90);
            if (m_driveComplete) {
                m_driveComplete = false;
                m_step++;
            }
        } 
        else {
            m_drivebase.setSpeed(0.0);
        }
    }
    
    public void DriveStraightDeadReckon(boolean timeToScore) {
        double distance = m_drivebase.getLeftEncoderDistance();
        System.out.println("Distance: " + distance);
        if (m_step == 0) {
            m_drivebase.resetEncoders();
            m_step++;
        } 
        else if (m_step == 1) {
            if (distance > -5) {
                m_drivebase.setSpeed(-0.30, -0.25);
            } else {
                m_step++;
            }
        }
        else if (m_step == 2) {
            if (distance > -100) {
                m_drivebase.setSpeed(-1.0, -0.97);
            } else {
                m_step++;
            }
        }
        else if (m_step == 3) {
            if (distance > -140) {
                m_drivebase.setSpeed(-0.5, -0.47);
            } else {
                m_step++;
            }
        }
        else if (m_step == 4) {
            if (distance > -160) {
                 m_drivebase.setSpeed(-0.30, -0.25);
            } else {
                m_drivebase.setSpeed(0.0);
                m_timer.start();
                m_timer.reset();
                m_step++;
            }
        }
        else if (m_step == 5) {
            m_drivebase.turnCameraLightOff();
            m_pickup.openPickup();
            if (m_timer.get() > 0.5) {
                m_timer.reset();
                m_step++;
            }
        }
        else if (m_step == 6) {
            if (timeToScore) {
                m_pickup.runIntakeWheelsOut();
                m_pickup.openKicker();
                m_step++;
            }
        }
        else {
            m_drivebase.setSpeed(0.0);
        }
    }
}  