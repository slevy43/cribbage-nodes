public class PowerSet <T> {

    // Private variable
    Set<T>[] set;

    // Constructor
    public PowerSet(T[] elements) {
        // Storing lengths of element list and its powerSet size
        int elementsLength = elements.length;                     // (1) get length of the elements array
        int powerSetSize = (int) (Math.pow(2, elementsLength)); // (2) get number of power sets

        // List of binary string representations for every possible combination
        String[] binaryList = new String[powerSetSize];         // create the array for binary reps

        // Loop that makes binary strings for powerSet items
        for (int i=0; i < (powerSetSize); ++i) {                // loop for the amount of PowerSets
            int binaryLength = elements.length;
            String currentBinary = String.format("%" + binaryLength + "s", Integer.toBinaryString(i)).replace(' ', '0');
            binaryList[i] = currentBinary;                        // store binary representation in the list
        } // end loop

        // Create [set] for powerSets to be added to
        set = new Set[0];                              // new set - length: amount of power sets

        // Making a new powerSet for each binary string
        for (int i = 0; i < binaryList.length; ++i) {               // for the amount of binary strings there are
            Set<T> currentPowerSet = new Set<T>();             // create a Set object for this current string

            // Looking through each string for corresponding bits
            for (int j = 0; j < elementsLength; ++j) {           // for each character in this string
                if (binaryList[i].charAt(j) == '1') {            // if current character is a 1...
                    currentPowerSet.add(elements[j]);} }  // add element to Set that corresponds with this bit

            // Helper function "appends" new Set to PowerSet
            addToSet(currentPowerSet);
            }
    } // constructor


    // Methods
    public int getLength() {
        return set.length;  // Length of list
    } // getSet

    public Set<T> getSet(int i){
        return set[i];  // Card at given index
    } // getSet

    // Helper Methods
    private void addToSet(Set<T> setToAppend) {
        // Creates new PowerSet array but 1 longer
        Set<T>[] newSetArray = (Set<T>[]) new Set[set.length + 1];

        // Copying over original set to new, longer set
        for (int i = 0; i < set.length; i++) {         // for every item in the set
            newSetArray[i] = set[i]; }                 // copy elements of original array to new

        // Add given set to that new empty spot in the new set
        newSetArray[newSetArray.length - 1] = setToAppend;
        set = newSetArray;   // "set" now refers to this new array we have made
    }

    @Override
    public String toString() {
        String printedSet = "";                   // Start with empty string
        System.out.print("\n\n[!] Printing..."); // Still needs to put through System.out.print()
        for (int i = 0; i < set.length; i++) {                       // for every item in the set
            printedSet += "\n   [" + (i+1) + "] {" + set[i] + "}"; } // Formatted, current Set<T> in the PowerSet
        return printedSet;
    }
} // PowerSet end
