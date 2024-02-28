public class Set <T> {

    // Private variable for list start
    LinearNode<T> setStart;             // represents the start of the set

    // Constructors
    public Set() {
        setStart = null;                // set begins empty (no nodes)
    }

    // Methods
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<T>(element);
        if (setStart == null) {        // If the list is empty
            setStart = newNode;           // setStart variable points to new node
        } else {                       // If the list has an element
            newNode.setNext(setStart);    // newNode now points to front of list
            setStart = newNode;           // newNode is now the starting reference
        }
    } // add

    public int getLength() {
        int count = 0;                  // counter for list length
        LinearNode<T> current = setStart;  // setting up Current node
        try {
        while (current != null) {       // for every existent node
            count++;                       // increment counter
            current = current.getNext();}  // move on to the next node
        return count;                      // return sum at the end of the loop
        } catch (Exception e) {    // if something goes wrong
            System.err.println(e.getMessage());
            return 0;              // print message + return 0
        }
    } // getLength

    public T getElement(int i) {
        LinearNode<T> current = setStart;    // setting current node
        for (int listIndex = 0; listIndex < i; listIndex++) { // looping to item of [i] given
            current = current.getNext(); }   // going to next node for each run
        return current.getElement();         // returning the node when the [i] amount is reached
    } // getElement

    public boolean contains(T element) {
        LinearNode<T> current = setStart;          // setting current node
        while (current != null) {                  // while a node exists...
            if (current.getElement() == element) {    // if this node matches the given element...
                return true; }                        // ...this set contains that element
            current = current.getNext(); }            // if not, go to the next node
        return false;                        // false if full list is checked without a match
        } // contain

    @Override
    public String toString() {
        String printedSet = "";            // starting with empty string to be added to
        LinearNode<T> current = setStart;  // setting current node as first node
        while (current != null) {          // while a node exists...
            printedSet += current.getElement() + " ";   // add the current element and a space to the string
            current = current.getNext();}               // go to the next node
        return printedSet;                 // return the complete string at the end
    } // toString

} // end of class
