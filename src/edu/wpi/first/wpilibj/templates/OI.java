
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    private static OI m_instance;
    
    private Joystick m_leftStick;
    private Joystick m_rightStick;
    
    private Joystick m_operatorStick;
        
    private OI()
    {
      m_leftStick = new Joystick(Constants.LEFT_JOYSTICK_PORT);
      m_rightStick = new Joystick(Constants.RIGHT_JOYSTICK_PORT);
      m_operatorStick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT);
    }
    
    public static OI getInstance() {
        if (m_instance == null) {
            m_instance = new OI();
        }
        return m_instance;
    }
    
    public double getLeftY() {
        return m_leftStick.getY();
    }
    
    public double getRightY() {
        return m_rightStick.getY();
    }
    
    public boolean getLeftTrigger(){
        return m_leftStick.getTrigger();
    }
    public boolean getRightTrigger(){
        return m_rightStick.getTrigger();
    }
    
    public boolean getLeftButton(int button){
        return m_leftStick.getRawButton(button);
    }
    
    public boolean getRightButton(int button){
        return m_rightStick.getRawButton(button);
    }
    
    public boolean getOperatorButton(int button) {
        return m_operatorStick.getRawButton(button);
    }
    
    public boolean getOperatorTrigger() {
        return m_operatorStick.getTrigger();
    }
}