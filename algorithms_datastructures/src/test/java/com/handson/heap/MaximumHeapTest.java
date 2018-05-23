/**
 * 
 */
package com.handson.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.handson.sorting.QuickSorting;

/**
 * @author sveera
 *
 */
public class MaximumHeapTest {

	private MaximumHeap<Integer> maximumHeap;

	@BeforeEach
	public void setUp() throws Exception {
		maximumHeap = new MaximumHeap<>();
	}

	@Test
	public void testInEmptyHeap_getMaximum_ExpectNull() {
		assertNull(maximumHeap.maximum());
	}

	@Test
	public void testInSingleElementHeap_getMaximum_ExpectSingleValue() {
		maximumHeap.insert(2, 2);
		assertEquals(2, maximumHeap.maximum().priority);
	}

	@Test
	public void testInTwoElementHeap_getMaximum() {
		maximumHeap.insert(3, 3);
		maximumHeap.insert(2, 2);
		assertEquals(3, maximumHeap.maximum().priority);
	}

	@Test
	public void testInEmptyHeap_delete_Maximum_ExpectNull() {
		assertNull(maximumHeap.delete_maximum());
	}

	@Test
	public void testInTwoElementHeap_deleteMaximum() {
		maximumHeap.insert(2, 2);
		maximumHeap.insert(3, 3);
		assertEquals(3, maximumHeap.delete_maximum().priority);
		assertEquals(2, maximumHeap.delete_maximum().priority);
		assertNull(maximumHeap.maximum());
	}

	@Test
	public void testInThreeElementHeap_deleteMaximum() {
		maximumHeap.insert(4, 4);
		maximumHeap.insert(3, 3);
		maximumHeap.insert(2, 2);
		assertEquals(4, maximumHeap.delete_maximum().priority);
		assertEquals(3, maximumHeap.delete_maximum().priority);
		assertEquals(2, maximumHeap.delete_maximum().priority);
		assertNull(maximumHeap.maximum());
	}

	@Test
	public void testInsertArrayOfIncreasingOrderAndPerformDeleteMaximumElementOfAllElements() {
		Integer[] heapElements = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25 };

		for (int i = 0; i < heapElements.length; i++) {
			maximumHeap.insert(heapElements[i], heapElements[i]);
			assertEquals(heapElements[i].intValue(), maximumHeap.maximum().priority);
		}
		for (int i = heapElements.length - 1; i >= 0; i--)
			assertEquals(heapElements[i].intValue(), maximumHeap.delete_maximum().priority);
	}

	@Test
	public void testInsertArrayWithDuplicatesAndPerformDeleteMaximumElementOfAllElements() {
		QuickSorting quickSorting = new QuickSorting();
		Integer[] heapElements = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
				22, 23, 24, 25 };
		for (int i = 0; i < heapElements.length; i++) {
			maximumHeap.insert(heapElements[i], heapElements[i]);
			assertEquals(heapElements[i > heapElements.length / 2 - 1 ? heapElements.length / 2 - 1 : i].intValue(),
					maximumHeap.maximum().priority);
		}
		quickSorting.sort(heapElements);
		for (int i = heapElements.length - 1; i >= 0; i--)
			assertEquals(heapElements[i].intValue(), maximumHeap.delete_maximum().priority);
	}

	@RepeatedTest(1)
	public void test_Random_Insert_getMaximum() {
		QuickSorting quickSorting = new QuickSorting();
		Random random = new Random();
		int randomLengthCount = 5000;
		Integer[] keysInserted = new Integer[randomLengthCount];
		for (int i = 0; i < keysInserted.length; i++) {
			keysInserted[i] = random.nextInt();
			maximumHeap.insert(keysInserted[i], keysInserted[i]);
			Integer[] insertedListArray = Arrays.copyOfRange(keysInserted, 0, i + 1);
			quickSorting.sort(insertedListArray);
			assertEquals(insertedListArray[insertedListArray.length - 1].intValue(), maximumHeap.maximum().priority);
		}
	}

	@RepeatedTest(1)
	public void test_Random_Insert_DeleteMaximum() {
		QuickSorting quickSorting = new QuickSorting();
		Random random = new Random();
		int randomLengthCount = 5000;
		Integer[] keysInserted = new Integer[randomLengthCount];
		for (int i = 0; i < keysInserted.length; i++) {
			keysInserted[i] = random.nextInt();
			maximumHeap.insert(keysInserted[i], keysInserted[i]);
		}
		quickSorting.sort(keysInserted);
		// logger.debug("Sorted elements " + Arrays.deepToString(keysInserted));
		for (int i = keysInserted.length - 1; i >= 0; i--)
			assertEquals(keysInserted[i].intValue(), maximumHeap.delete_maximum().priority);
		assertNull(maximumHeap.maximum());
	}
}
