import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class test {

    public static void main(String[] args)
    {
        String input = "this is a string";
        String output = reverse(input);
        System.out.println(output);
        output = fullReverse(input);
        System.out.println(output);
    }

    public static String reverse(String input)
    {
        String reverse = "";
        if(input.isEmpty() || input == null)
            System.out.println("Input is empty.");
        else if(input.length() == 1)
            reverse = input;
        else
        {
            String[] originalArray = input.split("\\s+");
            for (String word : originalArray)
            {
                reverse = word + " " + reverse;
            }
        }
        return reverse;
    }

    public static String fullReverse(String input)
    {
        String reverse = "";
        char[] originalArray = input.toCharArray();
        for (char letter : originalArray)
        {
            reverse = letter + reverse;
        }
        return reverse;
    }
}
