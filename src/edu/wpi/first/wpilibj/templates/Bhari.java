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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCameraException;

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
    
    Timer m_autonTimer;
    boolean m_targetFound;
    
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
        m_autonTimer = new Timer();
        m_targetFound = false;
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
        m_cameralight.set(Relay.Value.kOn);
        m_autonTimer.start();
        m_drivebase.brakesOff();

        while (isAutonomous() && isEnabled()) {
           // if (m_autonTimer.get() < 1.0) {
                try {
                    //m_targetFound = m_targetFound || m_vision.findHorizontalTargets();
                    m_targetFound = m_vision.findHorizontalTargets();
                } catch (AxisCameraException ex) {
                    System.out.println("An error occured.");
                    ex.printStackTrace();
                }
                System.out.println("Target found: " + m_targetFound);
        //    } 
        /*    else { // PUT ALL AUTONOMOUS ROUTINES IN HERE, BUT ONLY CALL ONE!!!!
                if (m_autonTimer.get() > 5.0) {
                    m_targetFound = true;
                }
                m_autonController.DirtySanchezJr(m_targetFound);
            } */
        }
    }    
    public void operatorControl() {
        m_autonTimer.stop();
        m_autonTimer.reset();
            
        while (isOperatorControl() && isEnabled()) {

            m_cameralight.set(Relay.Value.kOff);
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