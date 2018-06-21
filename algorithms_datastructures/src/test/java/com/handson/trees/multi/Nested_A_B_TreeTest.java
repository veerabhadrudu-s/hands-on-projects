/**
 * 
 */
package com.handson.trees.multi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import com.handson.sorting.QuickSorting;
import com.handson.trees.Comparator;

/**
 * @author sveera
 *
 */
public abstract class Nested_A_B_TreeTest {
	private final QuickSorting quickSorting = new QuickSorting();
	// private final Logger logger = LoggerFactory.getLogger(getClass());
	private final int a, b;
	private A_B_TreeSpy<Integer, String> a_b_Tree;

	public Nested_A_B_TreeTest(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@BeforeEach
	public void setUp() throws Exception {
		a_b_Tree = new A_B_TreeSpy<>((key1, key2) -> key1.equals(key2) ? 0 : key1 > key2 ? -1 : 1, a, b);
	}

	@RepeatedTest(10)
	@DisplayName("test insert and search operations using random numbers")
	public void testInsertAndSearchOperationsUsingRandomNumbers(RepetitionInfo repetitionInfo) {
		Random random = new Random();
		int randomLengthCount = 4000 + repetitionInfo.getCurrentRepetition();
		Integer[] keysToBeInserted = new Integer[randomLengthCount];
		for (int i = 0; i < keysToBeInserted.length; i++)
			keysToBeInserted[i] = random.nextInt();
		insertAndAssert(a_b_Tree, keysToBeInserted);
	}

	@RepeatedTest(1)
	@DisplayName("test insert and delete operations using random numbers")
	public void testInsertAndDeleteOperationsUsingRandomNumbers(RepetitionInfo repetitionInfo) {
		Random random = new Random();
		int randomLengthCount = 1500 + repetitionInfo.getCurrentRepetition();
		Integer[] keysToBeInserted = new Integer[randomLengthCount];
		for (int i = 0; i < keysToBeInserted.length; i++)
			keysToBeInserted[i] = random.nextInt();
		insertAndAssert(a_b_Tree, keysToBeInserted);
		// logger.debug("Keys Inserted " + Arrays.deepToString(keysToBeInserted));
		deleteAndAssert(a_b_Tree, keysToBeInserted, Arrays.copyOf(keysToBeInserted, keysToBeInserted.length));
	}

	@RepeatedTest(1)
	@DisplayName("test insert and delete operations using random numbers arranged in assending order")
	public void testInsertAndDeleteOperationsUsingRandomNumbersArrangedInAssendingOrder(RepetitionInfo repetitionInfo) {
		Random random = new Random();
		int randomLengthCount = 1500 + repetitionInfo.getCurrentRepetition();
		Integer[] keysToBeInserted = new Integer[randomLengthCount];
		for (int i = 0; i < keysToBeInserted.length; i++)
			keysToBeInserted[i] = random.nextInt();
		insertAndAssert(a_b_Tree, keysToBeInserted);
		Integer[] keysToBeDeleted = Arrays.copyOf(keysToBeInserted, keysToBeInserted.length);
		quickSorting.sort(keysToBeDeleted);
		deleteAndAssert(a_b_Tree, keysToBeInserted, keysToBeDeleted);
	}

	private void insertAndAssert(A_B_TreeSpy<Integer, String> a_b_Tree, Integer[] keysToBeInserted) {
		insertKeys(a_b_Tree, keysToBeInserted);
		searchKeys(a_b_Tree, keysToBeInserted);
		quickSorting.sort(keysToBeInserted);
		assertEquals(createCsvString(keysToBeInserted), a_b_Tree.getInOrderTraversalValues());
	}

	private void deleteAndAssert(A_B_TreeSpy<Integer, String> a_b_Tree, Integer[] keysAlreadyInserted,
			Integer[] keysToBeDeleted) {
		SortedSet<Integer> keysInserted = new TreeSet<>();
		for (Integer keyAlreadyInserted : keysAlreadyInserted)
			keysInserted.add(keyAlreadyInserted);
		for (int i = 0; i < keysToBeDeleted.length; i++) {
			// logger.debug("Key to be deleted is " + keysToBeDeleted[i]);
			assertEquals(String.valueOf(keysToBeDeleted[i]), a_b_Tree.delete(keysToBeDeleted[i]));
			keysInserted.remove(keysToBeDeleted[i]);
			assertEquals(createCsvString(keysInserted), a_b_Tree.getInOrderTraversalValues());
		}
	}

	private String createCsvString(Integer[] sortedValues) {
		Set<Integer> distinctValues = new LinkedHashSet<>();
		for (Integer keyToBeInserted : sortedValues)
			distinctValues.add(keyToBeInserted);
		String csvString = "";
		for (Integer distinctValue : distinctValues)
			csvString += distinctValue + ",";
		return !csvString.isEmpty() ? csvString.substring(0, csvString.length() - 1) : null;
	}

	private String createCsvString(SortedSet<Integer> sortedSet) {
		Set<Integer> distinctValues = new LinkedHashSet<>();
		for (Integer keyToBeInserted : sortedSet)
			distinctValues.add(keyToBeInserted);
		String csvString = "";
		for (Integer distinctValue : distinctValues)
			csvString += distinctValue + ",";
		return !csvString.isEmpty() ? csvString.substring(0, csvString.length() - 1) : null;
	}

	private void searchKeys(A_B_TreeSpy<Integer, String> a_b_Tree, Integer[] keysToBeInserted) {
		for (Integer keyToBeInserted : keysToBeInserted)
			assertEquals(String.valueOf(keyToBeInserted), a_b_Tree.search(keyToBeInserted));
	}

	private void insertKeys(A_B_TreeSpy<Integer, String> a_b_Tree, Integer[] keysToBeInserted) {
		for (Integer keyToBeInserted : keysToBeInserted)
			a_b_Tree.insert(keyToBeInserted, String.valueOf(keyToBeInserted));
	}

	private class A_B_TreeSpy<K, V> extends A_B_Tree<K, V> {

		private final A_B_Tree_InorderTraversal a_b_Tree_InorderTraversal;
		private final A_B_Tree_Validator a_b_Tree_Validator;

		public A_B_TreeSpy(Comparator<K> comparator, int minimumChildren, int maxChildren) {
			super(comparator, minimumChildren, maxChildren);
			a_b_Tree_InorderTraversal = new A_B_Tree_InorderTraversal();
			a_b_Tree_Validator = new A_B_Tree_Validator(minimumChildren - 1);
		}

		public String getInOrderTraversalValues() {
			a_b_Tree_Validator.validate(rootNode);
			return a_b_Tree_InorderTraversal.travel(rootNode);
		}
	}
}
