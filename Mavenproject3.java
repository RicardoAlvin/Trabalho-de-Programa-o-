/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject3;

/**
 *
 * @author Ricardo
 */
import javax.swing.JOptionPane;
public class Mavenproject3 {
    public static void main(String[] args) {
        String firstNumber=
            JOptionPane.showInputDialog("Enter first integer");
        String secondNumber=
            JOptionPane.showInputDialog("Enter second integer");
        int number1 = Integer.parseInt(firstNumber);
        int number2 = Integer.parseInt(secondNumber);
        int sum = number1 + number2;
        JOptionPane.showConfirmDialog(null, "The sum is" + sum, "Sum of two Integers", JOptionPane. PLAIN_MESSAGE );
    }
       
}
    
