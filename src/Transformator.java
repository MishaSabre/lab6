import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

public class Transformator{

    StringBuilder sb = new StringBuilder();
    String buf = "";
    Queue<String> queue = new LinkedList<>();
    Stack<String> stack = new Stack<String>();
    StringBuffer bf = new StringBuffer();
    String[] mass;

    public String change(String input){

        this.mass = input.split(" ");

        return "Постфиксная форма - " + infic() + "\n Ответ: " + calc();
    }

    public String infic() {

        String outer = "";

        for (String str : mass) {
            boolean isMatch = Pattern.compile("\\d").matcher(str).find();

            if (str.equals("+") || str.equals("-")) {

                if (stack.isEmpty() || stack.peek().equals("(")) {
                    stack.push(str);
                } else if (Pattern.compile("[/*^]").matcher(stack.peek()).find()) {
                    pop();
                    stack.push(str);
                } else {
                    queue.add(stack.pop());
                    stack.push(str);

                }

            } else if (str.equals("*") || str.equals("/")) {

                if ( !stack.isEmpty() ) {
                    if ((stack.peek().equals("*")) || stack.peek().equals("/")) pop();
                }
                stack.push(str);

            } else if (isMatch) {

                queue.add(str);

            } else if (str.equals("(")) {

                stack.push(str);

            } else if (str.equals(")")) {

                if (stack.contains("(")) {
                    pop();
                }

            }else if(str.equals("^")){
                stack.push(str);
            }else {
                System.out.println("Недопустимый символ");
            }
        }

        if (!stack.isEmpty()) {
            for (String str : stack) {
                if (!str.equals("(")) {
                    queue.add(str);
                }
            }
        }

        for (String str : queue){
            outer += str;
        }

        return outer;

    }

    private void pop(){
        for (int i = stack.lastIndexOf(stack.lastElement()); i >=0; i--){
            if (stack.peek().equals("(")){
                stack.pop();

                break;
            }
            queue.add(stack.pop());


        }

    }

    private Integer calc(){
        String str;
        int a = 0;
        int b = 0;

        Stack<Integer> summass = new Stack<>();

        while (!queue.isEmpty()){

            str = queue.poll();
            boolean isMatch = Pattern.compile("\\d").matcher(str).find();

            if (isMatch){
                summass.add(Integer.parseInt(str));
            }else if(str.equals("+")){
                a = summass.pop();
                b = summass.pop();
                summass.add(a + b);
            }else if(str.equals("-")){
                b = summass.pop();
                a = summass.pop();
                summass.add(a - b);
            }else if(str.equals("*")){
                a = summass.pop();
                b = summass.pop();
                summass.add(a * b);
            }
            else if(str.equals("/")){
                b = summass.pop();
                a = summass.pop();
                summass.add(a / b);
            }
            else if(str.equals("^")){
                b = summass.pop();
                a = summass.pop();
                summass.add( (int) (Math.pow(a, b)));
            }
        }

        return summass.pop();


    }

}
