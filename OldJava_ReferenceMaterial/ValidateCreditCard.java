/*
 * Robert Roland
 * Validating Credit Cards
 */

import java.util.Scanner;

public class ValidateCreditCard
{
    public static void main(String args[])
    {
        Scanner input = new Scanner (System.in);
        long creditNum;
        int digits;
        int firstNum;

        System.out.print("Type the credit card number: ");
        creditNum = input.nextLong();

        digits = countDigits(creditNum); //Count and return digits
        firstNum = firstDigit(creditNum); //Return first number on card (first two if 37)
        validateCard(digits, firstNum, creditNum); //Begin validation
    }
    public static int countDigits(long toCount)
    {
        //https://stackoverflow.com/questions/1306727/way-to-get-number-of-digits-in-an-int
        //THIS IS NOT THE BEST WAY, READ THE README.txt
        int numDigits = String.valueOf(toCount).length(); //Turn the number into a string and count digits
        if (numDigits < 13 || numDigits > 16) //There must be between 13 - 16 numbers on the card
        {
            return 0;
        }
        else
        {
            return numDigits;
        }
    }
    public static int firstDigit(long toParse)
    {
        //https://stackoverflow.com/questions/2967898/retrieving-the-first-digit-of-a-number
        int digit = Integer.parseInt(Long.toString(toParse).substring(0, 1)); //Turn number into string and get first position
        if (digit == 3) //If the first digit is a 3, we must test that the second is a 7 (for American Express)
        {
            int testDigit2 = Integer.parseInt(Long.toString(toParse).substring(0, 2)); //Get first two positions
            if (testDigit2 == 37) //If it is 37 it is American Express
            {
                return testDigit2; //and we need to return "37" not just "3"
            }
            else //If it is any other number, it means the card is invalid
            {
                return 0;
            }
        }
        else //If the first number is any other test if it is 4, 5, or 6
        {
            if (digit == 4 || digit == 5 || digit == 6)
            {
                return digit;
            }
            else
            {
                return 0;
            }
        }
    }
    public static void validateCard(int digits, int firstNum, long creditNum)
    {
        if (digits == 0 || firstNum == 0) //The other functions will return 0 for these variables if something is not correct
                                          //so we can check here if either = 0 don't continue.
        {
            System.out.println("Invalid card!");
        }
        else
        {
            int evenSum = doubleEvens(digits, creditNum); //Function to double each even position number
            int oddSum = addOdds(digits, creditNum); //Function to add all the odd position numbers
            int totalSum = evenSum + oddSum; //Add up the sums of the two above funtions
            if (totalSum % 10 == 0) //The total sum must be divisable by 10 evenly to be valid
            {
                System.out.print( creditNum + " is a valid ");
                if (firstNum == 4)
                {
                    System.out.println("Visa.");
                }
                else if (firstNum == 5)
                {
                    System.out.println("Master Card.");
                }
                else if (firstNum == 6)
                {
                    System.out.println("Discover Card.");
                }
                else
                {
                    System.out.println("American Express.");
                }
            }
            else
            {
                System.out.print("Invalid card!");
            }
        }
    }
    public static int doubleEvens(int digits, long creditNum)
    {
        int sum; //Sum of the current number being doubled
        int total = 0; //Total of all the numbers that were doubled
        for (int i = 0; i < digits; i+=2) //Start at position 0, move two positions each time
        {
            sum = Integer.parseInt(Long.toString(creditNum).substring(i, i+1)); //get position i
            sum = sum * 2; //multiply the number *2
            if (sum > 9) //If the sum happens to be a double digit number
            {
                int one = Integer.parseInt(Long.toString(sum).substring(0, 1)); //Get the first digit
                int two = Integer.parseInt(Long.toString(sum).substring(1, 2)); //Get the second digit
                sum = one + two; //Add the two digits together
            }
            total += sum; //Add the sum of this operation to the total
        }
        return total; //Return the total once we're done with all possible positions
    }
    public static int addOdds(int digits, long creditNum)
    {
        int total = 0; //Total of all the numbers added
        for(int i = 1; i < digits; i+=2) //Start at position 1 and move two positions each time
        {
            total += Integer.parseInt(Long.toString(creditNum).substring(i, i+1)); //add the odd numbers to total
        }
        return total; //return the total once we're done with all possible positions
    }
}
