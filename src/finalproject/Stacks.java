package finalproject;

public class Stacks<E> {

    private Node<E> top = null;
 
    private int size = 0;

    public Stacks() {
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E input) {
        if (isEmpty()) {
            Node<E> node = new Node<E>(input,top);
            top=node;
      
            
        } else {
            top = new Node<E>(input, top);

        }
        size++;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            return top.getElement();
        }

    }

    public E pop() {
        E element = top.getElement();
        if (isEmpty()) {
            return null;
        } else {
            top=top.getNext();
            size--;
           
        }
        return element;
    }

    private static class Node<E> {

        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public Node() {
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

    }
}
