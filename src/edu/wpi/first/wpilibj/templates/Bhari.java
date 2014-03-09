/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Bhari extends SimpleRobot {
    
    AutonController m_autonController;
    
    OI m_OI;
    Drivebase m_drivebase;
    Catcher m_catcher;
    Pickup m_pickup;
    
    DigitalInput m_pressureSwitch;
    Relay m_compressor;
    
    Timer m_autonTimer;
    boolean m_targetFound;
    
    DriverStation m_ds;
    
    DigitalInput m_bannerSensor;
    
    public Bhari() {
        m_autonController = AutonController.getInstance();
        m_OI = OI.getInstance();
        m_drivebase = Drivebase.getInstance();
        m_catcher = Catcher.getInstance();
        m_pickup = Pickup.getInstance();
        m_pressureSwitch = new DigitalInput(Constants.PRESSURE_SWITCH_PWM);
        m_compressor = new Relay(Constants.COMPRESSOR_RELAY, Relay.Direction.kForward);
        m_autonTimer = new Timer();
        m_targetFound = false;
        m_ds = DriverStation.getInstance();
        m_bannerSensor = new DigitalInput(Constants.BANNER_SENSOR_DIGITAL_IN);
    }
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        m_autonTimer.start();
        m_drivebase.brakesOff();

        while (isAutonomous() && isEnabled()) {
           
            System.out.println("Target found: " + m_targetFound);

            if (m_autonTimer.get() > 6.0) {
                m_targetFound = true;
            }
            
            if (m_autonTimer.get() > 0.5) {
                if (m_ds.getDigitalIn(1)) {
                    m_autonController.DoNothing();
                }
                else {
                    m_autonController.DriveStraightDeadReckon(m_targetFound);
                }
            } else {
                m_targetFound = m_targetFound || m_bannerSensor.get();
            }
        }
    }
    
    public void operatorControl() {
        m_autonTimer.stop();
        m_autonTimer.reset();
            
        while (isOperatorControl() && isEnabled()) {

            m_OI.enableTeleopControls();
            
            if (!m_pressureSwitch.get()) {
                m_compressor.set(Relay.Value.kOn);
            } else {
                m_compressor.set(Relay.Value.kOff);
            }
            
            //System.out.println("Catcher Ultrasonic: " + m_catcher.getUltrasonicValue());
            //System.out.println("Gyro: " + m_drivebase.getGyroAngle());
        }
    }    
    /**
     * This function is called once each time the robot enters test mode.
     */
    
    public void test() {
    
    }
}