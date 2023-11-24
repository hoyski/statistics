package com.hoyski.statistics;

import java.util.List;

/**
 * Permutator generations all permutations of a List of type T using a non-recursive
 * implementation of Heap's algorithm.
 * <p>
 * This implementation is based on the pseudo-code in the Wikipedia
 * article {@link https://en.wikipedia.org/wiki/Heap%27s_algorithm}
 *
 * @author Dave Hoy
 */
public class Permutator<T> {
    // The List for which to create permutations
    private List<T> list;

    // State variables
    private int[] c;
    private int i;
    private boolean firstPermutation;

    /**
     * Constructs a Permutator for the given List. The permutations are generated in place in {@code list} by
     * repeatedly exchanging elements until all the permutations have been generated.
     *
     * @param list List to permutate
     * @throws IllegalArgumentException when {@code list} is null or empty
     */
    public Permutator(List<T> list) {
        // Ensure list can be permutated (if that's a word)
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list must be non-empty");
        }

        this.list = list;

        c = new int[list.size()];

        // Initialize the state variables
        reset();
    }

    /**
     * Creates the next permutation in place within {@code list}
     *
     * @return {@code this.list} if another permutation was created. Otherwise {@code null}
     */
    public List<T> nextPermutation() {
        // For the first permutation just return the original list
        if (firstPermutation) {
            firstPermutation = false;
            return list;
        }

        // Perform the next swap and then return
        while (i < list.size()) {
            if (c[i] < i) {
                if (i % 2 == 0) {
                    swap(list, 0, i);
                } else {
                    swap(list, c[i], i);
                }

                c[i] += 1;

                i = 0;

                // A swap was performed so return
                return list;
            } else {
                c[i] = 0;
                i += 1;
            }
        }

        // Made it thru the while list. All permutations complete
        return null;
    }

    /**
     * Resets all the internal state variables so that the generation of permutations can be restarted.
     * <p>
     * <br/><b>Note:</b> This does not reset the List to its original order.
     */
    public void reset() {
        i = 0;

        for (int x = 0; x < c.length; ++x) {
            c[x] = 0;
        }

        firstPermutation = true;
    }

    /**
     * Swaps the two elements in {@code list} at indexes {@code idx1} and {@code idx2}
     */
    private static <T> void swap(List<T> list, int idx1, int idx2) {
        T item1 = list.get(idx1);
        T item2 = list.get(idx2);

        list.set(idx1, item2);
        list.set(idx2, item1);
    }

}
