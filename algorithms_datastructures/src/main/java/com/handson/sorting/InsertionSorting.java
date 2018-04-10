/**
 * 
 */
package com.handson.sorting;

import static java.util.Arrays.deepToString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handson.comparator.Comparator;

/**
 * @author sveera
 *
 */
public class InsertionSorting {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void sort(Integer... valuesTobeSorted) {
		logger.debug("Values to be sorted" + deepToString(valuesTobeSorted));
		for (int i = 0; i < valuesTobeSorted.length; i++) {
			for (int j = i; j > 0; j--)
				if (valuesTobeSorted[j] < valuesTobeSorted[j - 1])
					swapTwoValues(j - 1, j, valuesTobeSorted);
				else
					break;
			logger.debug(
					"Current order of array " + deepToString(valuesTobeSorted) + " after " + (i + 1) + " iteration");
		}
	}

	public void sortAscending(Integer... valuesTobeSorted) {
		sortWithComparator((value1, value2) -> value1 < value2, valuesTobeSorted);
	}

	public void sortDescending(Integer... valuesTobeSorted) {
		sortWithComparator((value1, value2) -> value1 > value2, valuesTobeSorted);
	}

	private void sortWithComparator(Comparator comparator, Integer... valuesTobeSorted) {
		for (int i = 0; i < valuesTobeSorted.length; i++)
			for (int j = i; j > 0; j--)
				if (comparator.compare(valuesTobeSorted[j].intValue(), valuesTobeSorted[j - 1].intValue()))
					swapTwoValues(j - 1, j, valuesTobeSorted);
				else
					break;
	}

	private void swapTwoValues(int fromIndex, int toIndex, Integer... valuesTobeSorted) {
		logger.debug("Swapping elements of 2 indexes " + fromIndex + " " + toIndex);
		valuesTobeSorted[toIndex] += valuesTobeSorted[fromIndex];
		valuesTobeSorted[fromIndex] = valuesTobeSorted[toIndex] - valuesTobeSorted[fromIndex];
		valuesTobeSorted[toIndex] = valuesTobeSorted[toIndex] - valuesTobeSorted[fromIndex];
	}

}
