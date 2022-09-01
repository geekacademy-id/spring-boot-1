import java.util.LinkedList;

public class KalkulatorSederhana {

    static boolean Operator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static String calculator(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int num = 0;
        int sign = 1;
        String failure = "";
        Character activeOperator = '+';
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if (c == '-' && (i == 0 || Operator(s.charAt(i-1)))) {
                sign = -1;
            }

            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }


            if (i == s.length()-1 || Operator(c)) {
                if (activeOperator == '+') {
                    stack.push(num * sign);
                } else if (activeOperator == '-') {
                    stack.push(-1 * num * sign);
                } else if (activeOperator == '*') {
                    stack.push(stack.pop() * num * sign);
                } else if (activeOperator == '/') {
                    if (num * sign == 0){
                        failure = "Tidak Bisa Dilakukan";
                    } else {
                        stack.push(stack.pop() / num * sign);
                    }
                }

                activeOperator = c;
                num = 0;
                sign = 1;
            }
        }


        int result = 0;

        while(!stack.isEmpty()) {
            result += stack.pop();
        }

        if (failure.isEmpty()){
            return result + "";
        } else {
            return failure;
        }


    }

    public static void main(String[] args) {
        System.out.println("Ouput : " +calculator("2+2"));
        System.out.println("Ouput : " +calculator("3-1"));
        System.out.println("Ouput : " +calculator("8*2"));
        System.out.println("Ouput : " +calculator("2/0"));

    }
}
