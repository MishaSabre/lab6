public class QueueX {
    private final int maxSize;
    public String[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public QueueX(int i){
        maxSize = i;
        queArray = new String[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void add(String i){
        if (i != null) {
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queArray[++rear] = i;
            nItems++;
        }

    }

    public String remove(){
        String temp = queArray[front++];
        if(front == maxSize) {
            front = 0;
        }
        nItems--;

        return temp;
    }

    public String poll(){

        if ( !isEmpty() ) {

            String str = queArray[front];
            remove();


            return str;


        }else {
            return null;
        }
    }

    public String peek(){
        return queArray[front];
    }

    public boolean isEmpty(){
        return (nItems == 0);
    }

    public boolean isFull(){
        return (nItems == maxSize);
    }

    public int getSize(){
        return nItems;
    }
}
