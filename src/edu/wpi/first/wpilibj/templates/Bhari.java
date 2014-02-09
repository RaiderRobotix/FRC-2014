/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Bhari extends SimpleRobot {
    
    OI m_OI;
    Drivebase m_drivebase;
    Catcher m_catcher;
    Pickup m_pickup;
    
    DigitalInput m_pressureSwitch;
    Relay m_compressor;
    
    public Bhari() {
        m_OI = OI.getInstance();
        m_drivebase = Drivebase.getInstance();
        m_catcher = Catcher.getInstance();
        m_pickup = Pickup.getInstance();
        m_pressureSwitch = new DigitalInput(Constants.PRESSURE_SWITCH_PWM);
        m_compressor = new Relay(Constants.COMPRESSOR_RELAY, Relay.Direction.kForward); 
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        while (isAutonomous() && isEnabled()) {
            m_drivebase.brakesOff();
        }
    }    
    public void operatorControl() {
        
        while (isOperatorControl() && isEnabled()) {
            m_drivebase.enableTeleopControls();
            m_catcher.enableTeleopControls();
            m_pickup.enableTeleopControls();
            if (!m_pressureSwitch.get()) {
                m_compressor.set(Relay.Value.kOn);
            } else {
                m_compressor.set(Relay.Value.kOff);
            }           
        }
    }    
    /**
     * This function is called once each time the robot enters test mode.
     */
    
    public void test() {
    
    }
}