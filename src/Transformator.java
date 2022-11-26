import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

public class Transformator{

    public QueueX queue;
    public StackX stack;
    String[] mass;

    public String change(String input){



        this.mass = input.split(" ");
        queue = new QueueX(mass.length);
        stack = new StackX(mass.length);

        return "Постфиксная форма - " + infic() + "\n Ответ: " + calc();
    }

    public String infic() {

        String outer = "";

        for (String str : mass) {
            System.out.println(str);
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
            for (String str : stack.stackArray) {
                if (str != null) {
                    if (!str.equals("(")) {
                        queue.add(str);
                    }
                }
            }
        }

        for (String str : queue.queArray){
            if (str != null) {
                outer += str;
            }
        }

        return outer;

    }

    private void pop(){

        for (int i = stack.stackArray.length - 1; i >=0; i--){
            if (stack.stackArray[i] != null) {
                if (stack.peek().equals("(")) {
                    stack.pop();

                    break;
                }
                queue.add(stack.pop());
            }

        }

    }

    private Integer calc(){
        String str;
        int a = 0;
        int b = 0;

        Stack<Integer> summass = new Stack<>();

        while (!queue.isEmpty()) {

            str = queue.poll();
            System.out.println(str);

            boolean isMatch = Pattern.compile("\\d").matcher(str).find();

            if (isMatch) {
                summass.add(Integer.parseInt(str));
            } else if (str.equals("+")) {
                a = summass.pop();
                b = summass.pop();
                summass.add(a + b);
            } else if (str.equals("-")) {
                b = summass.pop();
                a = summass.pop();
                summass.add(a - b);
            } else if (str.equals("*")) {
                a = summass.pop();
                b = summass.pop();
                summass.add(a * b);
            } else if (str.equals("/")) {
                b = summass.pop();
                a = summass.pop();
                summass.add(a / b);
            } else if (str.equals("^")) {
                b = summass.pop();
                a = summass.pop();
                summass.add((int) (Math.pow(a, b)));
            }

        }

        return summass.pop();


    }

}
