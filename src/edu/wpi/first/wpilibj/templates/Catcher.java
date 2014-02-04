/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author 25-DS
 */
public class Catcher {
    
    private static Catcher m_instance;
    
    private final OI m_oi;
    
    private final Solenoid m_leftNetExtended;
    private final Solenoid m_leftNetRetracted;
    private final Solenoid m_rightNetExtended;
    private final Solenoid m_rightNetRetracted;
    
    private final AnalogChannel m_ultrasonic;
    
    private Catcher() {
        m_oi = OI.getInstance();
        
        m_leftNetExtended = new Solenoid(Constants.LEFT_SOLENOID_OUT);
        m_leftNetRetracted = new Solenoid(Constants.LEFT_SOLENOID_IN);
        m_rightNetExtended = new Solenoid(Constants.RIGHT_SOLENOID_OUT);
        m_rightNetRetracted = new Solenoid(Constants.RIGHT_SOLENOID_IN);
        
        m_ultrasonic = new AnalogChannel(Constants.CATCHER_ULTRASONIC_ANALOG_CHANNEL);
    }
    
    public static Catcher getInstance() {
        if (m_instance == null) {
            m_instance = new Catcher();
        }
        return m_instance;
    }
    
    public void enableTeleopControls() {
        
        
        if (m_oi.getOperatorButton(6)) {
            openLeftCatcher();
        } else if (m_oi.getOperatorButton(7)) {
            closeLeftCatcher();
        } else if (m_oi.getOperatorButton(11)) {
            openRightCatcher();
        } else if (m_oi.getOperatorButton(10)) {
            closeRightCatcher();
        }
        
        if (m_oi.getOperatorTrigger()) {
            openLeftCatcher();
            openRightCatcher();
        } else if(m_oi.getOperatorButton(2)) {
            closeLeftCatcher();
            closeRightCatcher();
        }
    }
    
    public void closeLeftCatcher() {
        m_leftNetExtended.set(true);
        m_leftNetRetracted.set(false);
    }
    
    public void openLeftCatcher() {
        m_leftNetExtended.set(false);
        m_leftNetRetracted.set(true);
    }
     
    public void closeRightCatcher() {
        m_rightNetExtended.set(true);
        m_rightNetRetracted.set(false);
    }
    
    public void openRightCatcher() {
        m_rightNetExtended.set(false);
        m_rightNetRetracted.set(true);
    }
    
    public int getUltrasonicValue() {
        return m_ultrasonic.getValue();
    }
}
