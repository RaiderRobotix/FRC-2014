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
    
    Vision m_vision;
    AutonController m_autonController;
    
    OI m_OI;
    Drivebase m_drivebase;
    Catcher m_catcher;
    Pickup m_pickup;
    
    DigitalInput m_pressureSwitch;
    Relay m_compressor;
    
    Relay m_cameralight;
    
    public Bhari() {
        m_vision = Vision.getInstance();
        m_autonController = AutonController.getInstance();
        m_OI = OI.getInstance();
        m_drivebase = Drivebase.getInstance();
        m_catcher = Catcher.getInstance();
        m_pickup = Pickup.getInstance();
        m_pressureSwitch = new DigitalInput(Constants.PRESSURE_SWITCH_PWM);
        m_compressor = new Relay(Constants.COMPRESSOR_RELAY, Relay.Direction.kForward);
        m_cameralight = new Relay(Constants.CAMERA_LIGHT, Relay.Direction.kForward);
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
        m_cameralight.set(Relay.Value.kOn);
        m_autonController.reset();
        
        while (isAutonomous() && isEnabled()) {
            m_drivebase.brakesOff();
            m_vision.robotInit();
            m_vision.autonomous();
            
            //m_autonController.TestAuton();
            
            System.out.println("Left Encoder: " + m_drivebase.getLeftEncoderDistance());
            System.out.println("Left PID: " + m_drivebase.getLeftEncoderPIDValue());
            System.out.println("Right Encoder: " + m_drivebase.getRightEncoderDistance());
            System.out.println("Right PID: " + m_drivebase.getRightEncoderPIDValue());
            
            //System.out.println(m_catcher.getUltrasonicValue());
        }
    }    
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
            m_cameralight.set(Relay.Value.kOn);
            m_OI.enableTeleopControls();
            
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