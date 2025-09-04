package AllFiles;

import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
public class GeneratePasswordManually extends Password{
    
    public GeneratePasswordManually(String p){
        super(p);
    }
   
    @Override
    public String generatePassword(){
        while(!super.passwordStrength()){
            JOptionPane.showMessageDialog(null, "Signup Fail.Password is so weak.");
            String p=JOptionPane.showInputDialog(null,"Enter Your Password.String password=JOptionPane.showInputDialog(null,\"Enter Your Password.The minimum length of password should be 8 and it must contain a lowercase Character,An upercase character,a special character and a Number.\");");
            super.setPassword(p);
        }
        return super.getPassword();
    }
}
