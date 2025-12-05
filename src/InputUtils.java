import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String nextLine(){
        return scanner.nextLine();
    }

    public static String nextOption(String instruction, String[] validOptions, String error){
        // Keep looping until the user provides a valid response
        while(true){
            System.out.println(instruction);

            String option = scanner.nextLine();

            for(String validOption : validOptions){
                if(validOption.equals(option)){
                    return option;
                }
            }

            if(error != null){
                System.out.println(error);
            }
        }
    }

    public static int nextOption(String instruction, int[] validOptions, String error){
        String[] validMovesStr = new String[validOptions.length];

        for(int i = 0; i < validOptions.length; i++){
            validMovesStr[i] = String.valueOf(validOptions[i]);
        }

        return Integer.parseInt(nextOption(instruction, validMovesStr, error));
    }
}
