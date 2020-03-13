package com.hoyski.permutation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hoyski.statistics.Combinator;

public class CombinatorTest extends StatisticsTest
{
  @Test(expected = IllegalArgumentException.class)
  public void test_exception_thrown_for_empty_list()
  {
    new Combinator<String>(new ArrayList<String>(), 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void test_exception_thrown_for_choose_too_big()
  {
    new Combinator<String>(Arrays.asList("A"), 2);
  }
  
  @Test
  public void test_one_choose_one_combinations_generated()
  {
    List<String> strings = Arrays.asList("A");
    
    Combinator<String> combinator = new Combinator<>(strings, 1);
    List<String> combination;
    List<List<String>> combinations = new ArrayList<>();
    
    while ((combination = combinator.nextCombination()) != null)
    {
      combinations.add(Arrays.asList(combination.toArray(new String[combination.size()])));
    }
    
    assertEquals("Incorrect number of combinations generated", 1, combinations.size());
    
    assertListsContainsList(combinations, Arrays.asList("A"));
  }
  
	@Test
	public void test_five_choose_three_combinations_generated()
	{
		List<String> strings = Arrays.asList("A", "B", "C", "D", "E");
		
		Combinator<String> combinator = new Combinator<>(strings, 3);
		List<String> combination;
		List<List<String>> combinations = new ArrayList<>();
		
		while ((combination = combinator.nextCombination()) != null)
		{
			combinations.add(Arrays.asList(combination.toArray(new String[combination.size()])));
		}
		
		assertEquals("Incorrect number of combinations generated", 10, combinations.size());
		
		assertListsContainsList(combinations, Arrays.asList("A", "B", "C"));
    assertListsContainsList(combinations, Arrays.asList("A", "B", "D"));
    assertListsContainsList(combinations, Arrays.asList("A", "B", "E"));
    assertListsContainsList(combinations, Arrays.asList("A", "C", "D"));
    assertListsContainsList(combinations, Arrays.asList("A", "C", "E"));
    assertListsContainsList(combinations, Arrays.asList("A", "D", "E"));
    assertListsContainsList(combinations, Arrays.asList("B", "C", "D"));
    assertListsContainsList(combinations, Arrays.asList("B", "C", "E"));
    assertListsContainsList(combinations, Arrays.asList("B", "D", "E"));
    assertListsContainsList(combinations, Arrays.asList("C", "D", "E"));
	}
}
