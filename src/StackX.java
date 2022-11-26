
public class StackX {
    private int maxSize;
    public String[] stackArray;
    private int top;

    public StackX(int i){
        maxSize = i;
        stackArray = new String[maxSize];
        top = -1;
    }

    public void push(String i){
        if(!this.isFull()) {
            stackArray[++top] = i;
        }else System.out.println("Can't insert, stack is full");
    }

    public String pop(){

        String str = stackArray[top--];
        stackArray[top + 1] = null;

        return str;
    }

    public String peek(){
        return stackArray[top];
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public boolean isFull(){
        return (top == maxSize-1);
    }

    public boolean contains(String input){
        boolean ans = false;
        for (String string : stackArray){
            if (input.equals(string)) {
                ans = true;
                break;
            }
        }
        return ans;
    }
}
