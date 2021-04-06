/**
 * Author:[Shrestha Ghosh]-[s3805530]
 */
public class A2 {
    /*
     * YOU MAY MODIFY THIS DATA FOR TESTING PURPOSES YOU MUST MAKE SURE YOUR FINAL
     * SUBMISSION CONTAINS THIS DATASET UNMODIFIED.
     */
    private String[] menu = { "INDIAN|Biryani|16.99", "VIETNAMESE||17.00", "CHINESE|Chicken with Black Bean Sauce|16.5",
            "MIDDLE_EASTERN|Falafel|17.99", "Indian|Butter Chicken|17.00", "THAI|Pad Thai|23.00",
            "|Orange & Poppyseed Cake|15.99", "GREEK|Moussaka|18.50", "JAPANESE|Sushi|", "ITALIAN|Lasagne|18.50",
            "INDIAN|Dhal Tadka|9.50", "INDIAN|Brinjal|11.50" };

    /*
     * YOU MAY MODIFY CODE IN THIS METHOD FOR TESTING PURPOSES YOU MUST MAKE SURE
     * YOUR FINAL SUBMISSION CONTAINS THIS METHOD IN ITS UNMODIFIED STATE.
     */
    public void run() {
        String preliminary = "\n\nPLEASE NOTE: This code represents initial test data\n"
                + "You need to thoroughly test your solution.\n"
                + "During assessment we will change the values in these\n"
                + "tests and change the underlying dataset.\n" + "When you submit your final version make sure that\n"
                + "the code in this run method has not been changed.\n\n"
                + "You should also focus on best practice code by writing \n"
                + "cohesive methods and following the principles taught in this course\n\n";

        System.out.println(preliminary);
        /**
         * TESTING Meal Summary
         */
        System.out.println("TESTING - MEAL SUMMARY (VALID)");
        System.out.println("(16.99 - 18.50)");
        String result = getMealSummaryInRange(16.99, 18.50);
        System.out.println(result);

        System.out.println("TESTING - MEAL SUMMARY (NOT FOUND)");
        System.out.println("(30.00 - 50.00)");
        result = getMealSummaryInRange(30.00, 50.00);
        System.out.println(result);
        System.out.println("------------");

        /**
         * TESTING Get Change
         */
        System.out.println("TESTING - GET CHANGE (VALID)");
        System.out.println("(Biryani,Falafel, Moussaka, Lasagne)");
        String[] mealsOrdered = { "Biryani", "Falafel", "Moussaka", "Lasagne" };
        result = getChange(mealsOrdered, 123.00);
        System.out.println(result);

        System.out.println("TESTING - GET CHANGE (INSUFFICIENT FUNDS)");
        System.out.println("(Biryani,Falafel, Moussaka, Lasagne)");
        result = getChange(mealsOrdered, 53.99);
        System.out.println(result);

        System.out.println("\nTESTING - GET CHANGE (EMPTY)");
        mealsOrdered[0] = "";
        mealsOrdered[1] = "";
        mealsOrdered[2] = "";
        mealsOrdered[3] = "";
        result = getChange(mealsOrdered, 123.00);
        System.out.println(result);

        System.out.println("\nTESTING - GET CHANGE (NULL)");
        mealsOrdered = null;
        result = getChange(mealsOrdered, 123.00);
        System.out.println(result);
        System.out.println("------------");

        /**
         * TESTING MEALS BY CUISINE
         */
        System.out.println("\nTESTING - MEALS BY CUISINE");
        System.out.println("(INDIAN)");
        String[] mealsByCuisine = getMealsByCuisine("INDIAN");
        for (int i = 0; i < mealsByCuisine.length; i++) {
            System.out.println(mealsByCuisine[i]);
        }

        System.out.println("\nTESTING - MEALS BY CUISINE");
        System.out.println("(RUSSIAN)");
        mealsByCuisine = getMealsByCuisine("RUSSIAN");
        for (int i = 0; i < mealsByCuisine.length; i++) {
            System.out.println(mealsByCuisine[i]);
        }

        System.out.println("\nTESTING - MEALS BY CUISINE");
        System.out.println("(Empty)");
        mealsByCuisine = getMealsByCuisine("");
        for (int i = 0; i < mealsByCuisine.length; i++) {
            System.out.println(mealsByCuisine[i]);
        }

        System.out.println("\nTESTING - MEALS BY CUISINE");
        System.out.println("(NULL)");
        mealsByCuisine = getMealsByCuisine(null);
        for (int i = 0; i < mealsByCuisine.length; i++) {
            System.out.println(mealsByCuisine[i]);
        }

        System.out.println("------------");

        System.out.println("\nTESTING - MEALS HISTOGRAM");
        result = mealsAvailableHistogram();
        System.out.println(result);
        System.out.println("------------");

    }

    /**
     * ALGORITHM
     * 
     * BEGIN:
     * 
     * Checks if any of the strings in the arrays of cuisine,meal and price is null.
     * Compares the given range with the price
     * Displays the meals within the range
     * Calculates minimum and maximum price from the price array
     * Compares the range with minimum and maximum
     * Display invalid range if not fallen with the range.
     * 
     * END
     * 
     * Description:
     * startPrice and upperPrice are the price range given in the run()
     */
    private String getMealSummaryInRange(double startPrice, double upperPrice) {
        String[] Copyingcuisine = cuisine();
        String[] Copyingmeal = meal();
        String[] Copyingprice = price();
        String result = "";

        for (int i = 0; i < menu.length; i++) {
            if (Copyingprice[i] != null && Copyingmeal[i] != null && Copyingcuisine[i] != null
                    && Double.parseDouble(Copyingprice[i]) >= startPrice
                    && Double.parseDouble(Copyingprice[i]) <= upperPrice) {
                result = result + String.format("%-30s %s %n", Copyingmeal[i], Copyingprice[i]);

            }
        }

        double min = Double.parseDouble(Copyingprice[0]);
        double max = Double.parseDouble(Copyingprice[0]);

        for (int i = 0; i < menu.length; i++) {
            if (Copyingprice[i] != null)

            {
                double value = Double.parseDouble(Copyingprice[i]);
                if (value < min) {
                    min = value;
                } else if (value > max) {
                    max = value;
                }
            }
        }

        if ((startPrice < min && upperPrice < min) || (startPrice > max && upperPrice > max)) {
            result = "No meals found within range.";
        }


        return result;
    }

    /**
     * ALGORITHM
     * 
     * BEGIN:
     * Checks if the given array is NULL
     * Check if the given array is empty
     * Checks the prices of the given meals
     * Adds the prices for the total cost of the meal
     * Displays the total cost and change(paid-total cost)
     * Checks if the paid amount is zero and displas error message
     * Checks if paid amount is less than total cost
     * Displays error and advice to remove item
     * END
     */
    private String getChange(String[] meals, double paid) {

        String result = "";
        String[] Copyingmeal = meal();
        String[] Copyingprice = price();
        double value = 0.0;
        double change;
        boolean t = true;
        int count = 0;
        int count2 = 0;
        count = checkMealsArray(meals);

        if (count < 1) {
            result = result + "The method was passed a null value for meals";
        }

        else if (count > 0) {
            for (int i = 0; i < meals.length; i++) {
                if (meals[i].isEmpty() == t) {
                    count2 = count2 + 1;
                }
            }

            if (count2 > 0) {
                result = result + " Meal data supplied was invalid ";
            }

            if (count2 < 1) {
                result = result + String.format("%-40s %s %n", "You tendered : ", paid);

                for (int i = 0; i < meals.length; i++) {
                    for (int j = 0; j < Copyingmeal.length; j++) {

                        if (Copyingmeal[j] != null && Copyingmeal[j].equalsIgnoreCase(meals[i])) {
                            value = value + Double.parseDouble(Copyingprice[j]);

                        }
                    }

                }

                result = result + String.format("%-40s %.2f %n", "The total cost of the meal was : ", value);

                if (paid > value) {
                    change = paid - value;
                    result = result + String.format("%-40s %.2f %n", "Your change : ", change);
                }

                else if (value > paid) {
                    result = result + "\nYou provided insufficient funds. ";
                    change = value - paid;
                    change=Math.round(change*100.0)/100.0;
                    
                    for (int i = 0; i < Copyingmeal.length; i++) {
                       
                        for (int j = 0; j < meals.length; j++) {
                            if (Copyingmeal[i] != null && Copyingmeal[j] != null
                                    && meals[j].equals(Copyingmeal[i])
                                    && Double.parseDouble(Copyingprice[i]) == change) {
                                result = result + "Please remove " + Copyingmeal[i] + " from the list.";

                            }
                        }

                    }
                }

                if (paid == 0.00) {
                    result = result + String.format("%-40s %.2f %n", "Error!Please pay sufficient amount of : ", value);
                }
            }
        }
        return result;
    }

    /**
     * ALGORITHM
     * 
     * BEGIN:
     * Checks if any of the strings in the arrays of cuisine and meal is null.
     * Checks the given cuisine string with the array of cuisine
     * If (similar)
     * Count=Count+1
     * Count becomes the length of the new array result
     * Again checks the given cuisine string with the array of cuisine
     * Copies the similar ones in the new array result
     * Checks if given string(i.e cuisine) is NULL or EMPTY
     * END
     */
    private String[] getMealsByCuisine(String cuisine) {

        String[] Copyingmeal = meal();
        String[] Copyingcuisine = cuisine();
        int count = 0;
        int j = 0;
        boolean t = true;
        int compareResult = 0;

        for (int i = 0; i < menu.length; i++) {
            if (Copyingcuisine[i] != null && Copyingmeal[i] != null && Copyingcuisine[i].equals(cuisine)) {
                count = count + 1;

            } else {
                count = 1;
            }
        }

        String[] result = new String[count];

        for (int i = 0; i < menu.length; i++) {
            if (Copyingcuisine[i] != null && Copyingmeal[i] != null && Copyingcuisine[i].equals(cuisine)) {
if(j<count)
               { result[j] = Copyingmeal[i];
                compareResult = compareResult + 1;
                 j++;}

            }

        }
        if (cuisine == null) {
            result[0] = "A null value was provided for cuisine.";

        }

        else if (cuisine.isEmpty() == t) {
            result[0] = "Meal not found";

        } else if (compareResult < 1) {
            result[0] = "Meal not found";
        }

        return result;

    }

    /**
     *ALGORITHM
     * 
     * BEGIN:
     * Checks if any of the strings in the arrays of cuisine and meal is null.
     * IF(null) 
     *   invalid=invalid+1
     * ELSE
     * Fixes one array with variable i and compares the fixed one with the next values
     * Counts the number of valid items
     * Displays the cuisines and the number of valid item
     * END
     */
    private String mealsAvailableHistogram() {
        String stringResult = "";
        String[] Copyingmeal = meal();
        String[] Copyingcuisine = cuisine();
        String[] Copyingprice = price();
        
        int invalid = 0;
        

        for (int i = 0; i < menu.length; i++) {
            int count = 0;
            if (Copyingcuisine[i] == null || Copyingmeal[i] == null || Copyingprice[i] == null) {
                invalid = invalid + 1;
            }

            for (int j = 0; j < menu.length; j++) {

                if (Copyingcuisine[i] != null && Copyingcuisine[j] != null
                        && Copyingcuisine[i].equals(Copyingcuisine[j])) {
                            
                    if (Copyingmeal[i] != null && Copyingprice[i] != null)
                    {
                        count = count + 1;
                    } else if (Copyingmeal[i] == null || Copyingprice[i] == null) {
                        count=0;
                    }
                }

            }
            
            if (Copyingcuisine[i] != null) {
                
                stringResult = stringResult + String.format("%-20s %s %n", Copyingcuisine[i], count);
            }

        }

        stringResult = stringResult + String.format("%-20s %s %n", "Invalid", invalid);
        return stringResult;
    }


    /**
     * This method copies the cuisines from the menu array to the new cuisine array.
     * Returns the cuisine array as  copycuisine
     */

    
    private String[] cuisine() 
    {
        String[] copycuisine = new String[menu.length];
        int i = 0;
        int check = 0;
        boolean f = false;

        for (i = 0; i < menu.length; i++) {

            int firstsymbol = menu[i].indexOf("|");
            if (firstsymbol >= 0) {
                check = checkingStringUppercase(menu[i].substring(0, firstsymbol));
                if (menu[i].substring(0, firstsymbol).isBlank() == f && check > 1) {
                    copycuisine[i] = menu[i].substring(0, firstsymbol);

                } else {
                    copycuisine[i] = null;
                }

            }
        }

        return copycuisine;

    }

     /**
     * This method copies the meals from the menu array to the new meal array.
     * Returns the meal array as copymeal
     */

    private String[] meal() 
    {
        String[] copymeal = new String[menu.length];
        int i = 0;
        boolean f = false;
        for (i = 0; i < menu.length; i++) {
            int firstsymbol = menu[i].indexOf("|");
            if (firstsymbol >= 0) {
                int secondsymbol = menu[i].indexOf("|", firstsymbol + 1);

                if (menu[i].substring(firstsymbol + 1, secondsymbol).isEmpty() == f) {
                    copymeal[i] = menu[i].substring(firstsymbol + 1, secondsymbol);
                } else {
                    copymeal[i] = null;
                }
            }

        }

        return copymeal;

    }

    /**
     * This method checks if the string is uppercase
     */

    
    private int checkingStringUppercase(String str)  
     {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 65 && c <= 90) {
                count = count + 1;
            }
        }

        return count;
    }

/**
     * This method copies the price from the menu array to the new price array.
     * Returns the price array as copyprice
     */

    private String[] price() 
    {
        String[] copyprice = new String[menu.length];
        int i = 0;
        boolean f = false;
        for (i = 0; i < menu.length; i++) {
            int firstsymbol = menu[i].indexOf("|");
            if (firstsymbol >= 0) {
                int secondsymbol = menu[i].indexOf("|", firstsymbol + 1);
                if (menu[i].substring(secondsymbol + 1).isEmpty() == f) {
                    copyprice[i] = (menu[i].substring(secondsymbol + 1));
                } else {
                    copyprice[i] = null;
                }
            }

        }

        return copyprice;

    }

/**
     * This method checks if the given meals array is null or not
     */

    private int checkMealsArray(String[] meals)  
    {
        int count = 0;

        if (meals == null) {
            count = 0;
        } else {
            count = count + 1;
        }

        return count;

    }

}