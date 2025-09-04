/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AllFiles;

import java.security.SecureRandom;

/**
 *
 * @author pc
 */
public class GenerateAutoPassword extends Password{

    @Override
    public String generatePassword() {
        SecureRandom random = new SecureRandom();
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = lowerCase.toUpperCase();
        String digit = "123456789";
        String specialCharacter = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        String allCharacters = lowerCase + upperCase + digit + specialCharacter;
        int length = random.nextInt(5) + 8;
        StringBuilder password = new StringBuilder(length);
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(digit.charAt(random.nextInt(digit.length())));
        password.append(specialCharacter.charAt(random.nextInt(specialCharacter.length())));
        for (int i = 0; i < length - 4; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);
            int rand = random.nextInt(length);
            password.setCharAt(i, password.charAt(rand));
            password.setCharAt(rand, ch);
        }
            super.setPassword(password.toString());
            return super.getPassword();
    }
}
