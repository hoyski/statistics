# statistics
This repo provides two simple classes: Permutator and Combinator. 

## `com.hoyski.statistics.Permutator`

Permutator generates all permutations of the List with which it is constructed. The implementation is non-recursive so it can safely be used on large Lists. The implementation is based on the pseudo-code in the [Wikipedia page describing Heap's algorithm.](https://en.wikipedia.org/wiki/Heap%27s_algorithm) 

The implementation is destructive. Each call to `nextPermutation()` modifies the List with which the instance was constructed.

## `com.hoyski.statistics.Combinator`

Combinator generates all the combinations that can be chosen from the List with which it is constructed.

The implementation uses a single List instance that is repopulated on each call to `nextCombination()` so it can safely be used with large Lists and large numbers of combinations without excessive garbage collection.
