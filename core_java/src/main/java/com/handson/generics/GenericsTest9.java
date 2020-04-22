/**
 * 
 */
package com.handson.generics;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * @author veera
 *
 */
public class GenericsTest9 {

	public static void main(String[] args) {

		findMinUsingComparableDefinedInTypeArgumentOrUsingComparableDefinedInParentOfTypeArgument(
				Collections.<String>emptyList());
		findMinUsingComparableDefinedOnlyInTypeArgument(Collections.<String>emptyList());
		findMinUsingComparableDefinedInTypeArgumentOrUsingComparableDefinedInParentOfTypeArgument(
				Collections.<LocalDate>emptyList());
		// Below line will no get compiled as Comparable is implemented in
		// ChronoLocalDate Interface which is extended by LocalDate.
		// findMinUsingComparableDefinedOnlyInTypeArgument(Collections.<LocalDate>emptyList());
	}

	// This method allows to findMin Object using Comparable implemented in Type T
	// or Comparable implemented in parent/super class of T
	public static <T extends Comparable<? super T>> void findMinUsingComparableDefinedInTypeArgumentOrUsingComparableDefinedInParentOfTypeArgument(
			List<T> elementsMinToBeFound) {
		System.out.println(elementsMinToBeFound);
	}

	// This method allows to findMin Object using Comparable implemented in Type T
	// only.
	public static <T extends Comparable<T>> void findMinUsingComparableDefinedOnlyInTypeArgument(
			List<T> elementsMinToBeFound) {
		System.out.println(elementsMinToBeFound);
	}

}
