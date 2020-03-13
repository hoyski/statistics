package com.hoyski.permutation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math.util.MathUtils;
import org.junit.Test;

import com.hoyski.statistics.Permutator;

public class PermutatorTest extends StatisticsTest
{
	@Test(expected = IllegalArgumentException.class)
	public void test_exception_thrown_for_empty_list()
	{
		new Permutator<String>(new ArrayList<String>());
	}
	
	@Test
	public void test_permutations_of_one_element_list()
	{
		List<String> strings = Arrays.asList("A");
		
		List<List<String>> permutations = new ArrayList<>();
		
		Permutator<String> permutator = new Permutator<>(strings);
		
		while (permutator.nextPermutation() != null)
		{
			// Since Permutator works in place on the List with which it's constructed, need to make a 
			// deep copy after every permutation
			List<String> permutation = Arrays.asList(strings.toArray(new String[strings.size()]));
			permutations.add(permutation);
		}
		
		assertEquals("Incorrect number of permutations generated", 1, permutations.size());

		assertListsContainsList(permutations, Arrays.asList("A"));
	}

	@Test
	public void test_permutations_of_three_element_list()
	{
		List<String> strings = Arrays.asList("A", "B", "C");
		
		List<List<String>> permutations = new ArrayList<>();
		
		Permutator<String> permutator = new Permutator<>(strings);
		
		while (permutator.nextPermutation() != null)
		{
			// Since Permutator works in place on the List with which it's constructed, need to make a 
			// deep copy after every permutation
			List<String> permutation = Arrays.asList(strings.toArray(new String[strings.size()]));
			permutations.add(permutation);
		}
		
		assertEquals("Incorrect number of permutations generated", 6, permutations.size());

		assertListsContainsList(permutations, Arrays.asList("A", "B", "C"));
		assertListsContainsList(permutations, Arrays.asList("A", "C", "B"));
		assertListsContainsList(permutations, Arrays.asList("B", "A", "C"));
		assertListsContainsList(permutations, Arrays.asList("B", "C", "A"));
		assertListsContainsList(permutations, Arrays.asList("C", "A", "B"));
		assertListsContainsList(permutations, Arrays.asList("C", "B", "A"));
	}
	

	@Test
	public void test_permutations_of_reset_three_element_list()
	{
		List<String> strings = Arrays.asList("A", "B", "C");
		
		List<List<String>> permutations = new ArrayList<>();
		
		Permutator<String> permutator = new Permutator<>(strings);

		// Generate all of the permutations
		while (permutator.nextPermutation() != null)
		{
		}
		
		permutator.reset();

		// Now generate them again storing the results
		while (permutator.nextPermutation() != null)
		{
			// Since Permutator works in place on the List with which it's constructed, need to make a 
			// deep copy after every permutation
			List<String> permutation = Arrays.asList(strings.toArray(new String[strings.size()]));
			permutations.add(permutation);
		}
		
		assertEquals("Incorrect number of permutations generated", 6, permutations.size());

		assertListsContainsList(permutations, Arrays.asList("A", "B", "C"));
		assertListsContainsList(permutations, Arrays.asList("A", "C", "B"));
		assertListsContainsList(permutations, Arrays.asList("B", "A", "C"));
		assertListsContainsList(permutations, Arrays.asList("B", "C", "A"));
		assertListsContainsList(permutations, Arrays.asList("C", "A", "B"));
		assertListsContainsList(permutations, Arrays.asList("C", "B", "A"));
	}
	
	@Test
	public void test_num_permutations_of_a_large_list()
	{
		List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		
		// Number of expected permutations is the factorial of the size of the list
		long expectedNumPermutations = MathUtils.factorial(strings.size());
		
		long numPermutations = 0;
		
		Permutator<String> permutator = new Permutator<>(strings);
		
		while (permutator.nextPermutation() != null)
		{
			numPermutations++;
		}
		
		assertEquals("Incorrect number of permutations generated", expectedNumPermutations, numPermutations);
	}

}
