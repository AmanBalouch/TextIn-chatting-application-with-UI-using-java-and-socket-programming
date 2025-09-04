package AllFiles;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pc
 */
public abstract class Password{
    private String password;
    
    public Password(){
    }
    public Password(String p){
        password=p;
    }
    
    public boolean passwordStrength() {
        int passLength = password.length();
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;
        for (int i = 0; i < passLength; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch))
                hasUpperCase = true;
            else if (Character.isLowerCase(ch))
                hasLowerCase = true;
            else if (Character.isDigit(ch))
                hasDigit = true;
            else
                hasSpecialCharacter = true;
        }
        return passLength >= 8 && hasUpperCase && hasLowerCase && hasDigit && hasSpecialCharacter;
    }
    
    public abstract String generatePassword();
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String p){
        password=p;
    }
}
