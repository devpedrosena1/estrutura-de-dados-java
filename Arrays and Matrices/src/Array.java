public class Array {
    public static void main(String[] args) {

        // Structure Array

        // int[] quantity; -> quantity = new int[10];
        int[] quantity = new int[10];

        // response with empty array
        System.out.println(quantity[0]);
        System.out.println(quantity[1]); // -> the answer is 0 because is a default value

        // default value for double, char, int and boolean
        // double = 0.0
        // char = null
        // int = 0
        // boolean = false

        // we can initialize the array with position to position
        String[] products = new String[3];
        products[0] = "Coffee";
        products[1] = "Juice";
        products[2] = "Cake";

        // or initialize with know values
        String[] productsTwo = {
                "Coffee",
                "Juice",
                "Cake"
        };

        System.out.println(productsTwo.length);

        // array with length 5 [0, 1, 2, 3, 4] - starts in 0
        System.out.println(productsTwo[0]);
        System.out.println(productsTwo[1]);

        // the last element is length -1
        System.out.println(productsTwo[productsTwo.length - 1]); // the last element is "Cake"

        // using a for loop to iterate through the array
        for (int i = 0; i < productsTwo.length; i++) {
            System.out.println(productsTwo[i]);
        }

    }
}
