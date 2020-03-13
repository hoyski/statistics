package com.hoyski.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Combinator produces all combinations (aka chooses) of a specific size from a
 * given List of type T.
 * 
 * <br/>
 * For example, given a choose size of 2 and the List {"Apple", "Banana",
 * "Pear"}, Combinator's {@link #nextCombination()} will return the Lists
 * <ul>
 * <li>{"Apple", "Banana"}</li>
 * <li>{"Apple", "Pear"}</li>
 * <li>{"Banana", "Pear"}</li>
 * <li>null</li>
 * <ul>
 * <br/>
 *
 * 
 * @author Dave Hoy
 */
public class Combinator<T>
{
  // The list from which to extract combinations
  private List<T> list;

  // The current combination
  private List<T> combination;

  private int[] counters;

  private boolean done;

  public Combinator(List<T> list, int choose)
  {
    if (list == null || list.size() == 0)
    {
      throw new IllegalArgumentException("list must be non-empty");
    }

    if (choose > list.size() || choose < 1)
    {
      throw new IllegalArgumentException("choose must be between 1 and the list size");
    }

    this.list = list;
    this.combination = new ArrayList<T>(choose);

    counters = new int[choose];
    for (int i = 0; i < choose; ++i)
    {
      counters[i] = i;
      combination.add(list.get(i));
    }

    done = false;
  }

  /**
   * Returns the next combination or {@code null} if all have been generated
   */
  public List<T> nextCombination()
  {
    if (done)
    {
      return null;
    }

    populateCombinationFromCounters();

    if (!advanceCounters())
    {
      done = true;
    }

    return combination;
  }

  /**
   * Examine the values in {@code counters} incrementing the rightmost one that
   * can be incremented without over running the end of {@code list} or
   * overlapping the value to its right, if any.
   * <p/>
   * Whenever a counter is incremented, all of the values to its right are "pulled
   * back" to the positions adjacent to the counter that was incremented. For
   * example, when calculating a choose size of 3 on a 5 element List, the values
   * in {@code counters} will update as follows after each call.
   * 
   * <pre>
   * 0 1 2 <-- Initial state and first combination returned
   * 0 1 3
   * 0 1 4
   * 0 2 3 <-- 2nd value was incremented so 3rd value was pulled back
   * 0 2 4
   * 0 3 4 <-- 2nd value was incremented but 3rd value was already adjacent
   * 1 2 3 <-- 1st value was incremented so 2nd and 3rd values were pulled back
   * 1 2 4
   * 1 3 4
   * 2 3 4 <-- In this state, no value can be incremented without overlapping 
   *           or over running so generation is complete
   * </pre>
   */
  private boolean advanceCounters()
  {
    boolean incrementedACounter = false;

    for (int r = counters.length - 1; r >= 0 && !incrementedACounter; --r)
    {
      if (canIncrement(r))
      {
        counters[r] += 1;
        incrementedACounter = true;

        // Set the counters right of r to one more than the value to their left
        for (int i = r + 1; i < counters.length; i++)
        {
          counters[i] = counters[i - 1] + 1;
        }
      }
    }

    return incrementedACounter;
  }

  /**
   * Returns {@code true} if the value at {@code idx} in {@code counters} can be
   * incremented without either over running the size of {@code list} or equaling
   * the counter to its right, if any
   */
  private boolean canIncrement(int idx)
  {
    return (counters[idx] <= list.size() - 2 // Can increment without over running 'list'
        && (idx == counters.length - 1 // 'idx' is the rightmost counter
            || counters[idx] < counters[idx + 1] - 1) // Incrementing 'idx'th counter won't
                                                      // make it equal 'idx'th + 1 value
    );
  }

  /**
   * Sets the elements of {@code combination} to the elements in {@code list} at
   * the positions in {@code counters}.
   */
  private void populateCombinationFromCounters()
  {
    for (int i = 0; i < counters.length; ++i)
    {
      T item = list.get(counters[i]);

      combination.set(i, item);
    }
  }
}
