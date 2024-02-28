public class Counter {

    // Private variables
    PowerSet<Card> cardps;
    Card starter;

    // Constructor
    public Counter(Card[] hand, Card starter) {
        // Initialize the starter
        this.starter = starter;

        // Assigning PowerSet from given [hand] of Cards
        cardps = new PowerSet<Card>(hand); }

    // Method
    public int countPoints() {
        int pointsScored = 0; // Sum total for point-keeping

        // Iterate through each subset of cards in the power set.
        for (int i=0; i < cardps.getLength(); ++i) {

            // Check if it qualifies for scoring categories:
            if (isFifteen(cardps.getSet(i))) {    // Fifteen (2)
                pointsScored += 2; }
            if (isPair(cardps.getSet(i))) {       // Pair (2)
                pointsScored += 2; }
            if (isHisKnobs(cardps.getSet(i))) {    // His Knob (1)
                pointsScored += 1; }
            pointsScored += isFlush(cardps.getSet(i));  // Flush
        } // loop through sets

        // Check all of PowerSet for runs
        pointsScored += pointsFromRun();  // Runs

        // Return score from hand
        return pointsScored;
    }// countPoints()

    // Helper Methods
    private boolean isFifteen(Set<Card> currentSet) {
        int handValue = 0;
        // Loop through current set
        for (int i = 0; i < currentSet.getLength(); i++) {
            handValue += currentSet.getElement(i).getFifteenRank(); } // increment the current Card's value to sum

        // Checking if this Set qualifies
        if (handValue == 15) {
//            System.out.print("\n[!] 15 scored");
            return true;
        } else {return false;}
    } // isFifteen()

    private boolean isPair(Set<Card> currentSet) {
        // Making sure it's only dealing with pairs of 2
        if (currentSet.getLength() != 2) {
            return false;}   // Not evaluating just 2 cards

        // Storing value of both cards
        String firstValue = currentSet.getElement(0).getLabel();
        String secondValue = currentSet.getElement(1).getLabel();

        // Checking for a match
        if (firstValue.equals(secondValue)) {
            return true; }   // Pair matches
        return false;        // Pair does not match
    } // isPair()

    private boolean isHisKnobs(Set<Card> currentSet) {
        // Making sure it's only dealing with full hands
        if (currentSet.getLength() != 5) {
            return false;}   // Not evaluating full hand

        // Get suit of starter
        String starterSuit = starter.getSuit();

        // Hand without starter
        Set<Card> handWithoutStarter = new Set<Card>();
        for (int i = 0; i < currentSet.getLength(); i++) {
            if (currentSet.getElement(i) != starter) {
                handWithoutStarter.add(currentSet.getElement(i)); }
        } // no starter

        // Looking through hand for a Jack
        for (int i = 0; i < handWithoutStarter.getLength(); i++) {
            // If the current card is a Jack
            if (handWithoutStarter.getElement(i).getLabel().equals("J")) {
                // If the current card matches the starter suit
                if (currentSet.getElement(i).getSuit().equals(starterSuit)) {
                    return true; } } }
        return false; // no Knob was found
    } // isPair()

    private int isFlush(Set<Card> currentSet) {
        // Making sure it's only dealing with sets of 5
        if (currentSet.getLength() != 5) {
            return 0;}   // Not evaluating just 5 cards

        // Counters for each suit
        int spadeCounter = 0;
        int heartCounter = 0;
        int diamondCounter = 0;
        int clubCounter = 0;

        // Looking through each card
        for (int i = 0; i < currentSet.getLength(); i++) {
            String curItemSuit = currentSet.getElement(i).getSuit();
            if (curItemSuit.equals("S")) {          // If current Card's suit is Spade
                spadeCounter += 1;
            } else if (curItemSuit.equals("H")) {   // If current Card's suit is Heart
                heartCounter += 1;
            } else if (curItemSuit.equals("D")) {   // If current Card's suit is Diamond
                diamondCounter += 1;
            } else if (curItemSuit.equals("C")) {   // If current Card's suit is Spade
                clubCounter += 1; } } // for

        // Determine how many points to award
        if (spadeCounter == 4 || heartCounter == 4 || diamondCounter == 4 || clubCounter == 4) {
            return 4; }     // Flush in the hand
        else if (spadeCounter == 5 || heartCounter == 5 || diamondCounter == 5 || clubCounter == 5) {
            return 5; }     // Full flush
        else {return 0;}      // No flush

    } // isFlush()

    private int pointsFromRun() {
        int longestRun = 0;
        int sumOfRuns = 0;
        // Storing the length of the longest run
        for (int i=0; i < cardps.getLength(); ++i) {

            // Storing info for readability
            int runLength = cardps.getSet(i).getLength();
            if(isRun(cardps.getSet(i))) {

                // Set longest length
                if (runLength > longestRun) {
                    longestRun = runLength; } } }

        // Check in sets of the same size
        for (int i=0; i < cardps.getLength(); ++i) {

            // Storing info for readability
            int runLength = cardps.getSet(i).getLength();
            Set<Card> currentSet = cardps.getSet(i);

            // If equally sized set is a run, count it
            if (runLength == longestRun) {
                if (isRun(currentSet)) {
                    sumOfRuns += longestRun; } } }
        return sumOfRuns;
    } // pointsFromRun



    private boolean isRun (Set<Card> set) {
        // Given method: finds if a given set is a run
        int n = set.getLength();
        if (n <= 2) return false; // Run must be at least 3 in length.
        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.

        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank()-1] += 1; }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) maxStreak = streak;
            } else {
                streak = 0; } }
        if (maxStreak == n) { // Check if this is the maximum streak.
            return true;
        } else {
            return false; }
    } // isRun()

} // counter end
