package com.hoyski.permutation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class StatisticsTest
{
	/**
	 * Asserts that {@code lists} contains at least one List whose every element, in order,
	 * is equal to the corresponding element in {@code expectedList}
	 */
	protected <T> void assertListsContainsList(List<List<T>> lists, List<T> expectedList)
	{
		boolean found = false;
		
		for (List<? extends Object> list : lists)
		{
			assertEquals("List " + list + " has different length than expected list " + expectedList, 
					list.size(), expectedList.size());
			
			boolean allMatch = true;
			
			for (int i = 0; i < expectedList.size(); ++i)
			{
				if (!expectedList.get(i).equals(list.get(i)))
				{
					allMatch = false;
				}
			}
	
			if (allMatch)
			{
				found = true;
			}
		}
		
		assertTrue("List " + expectedList + " not found in lists", found);
	}

}