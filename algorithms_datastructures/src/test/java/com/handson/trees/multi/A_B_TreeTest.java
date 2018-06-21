/**
 * 
 */
package com.handson.trees.multi;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author sveera
 *
 */
public class A_B_TreeTest {

	@Nested
	class A_B_Tree_Invalid_ConfigurationTest {

		@Test
		public void test_A_B_TreeWithInvalidMinimumChildLimitAndExpectException() {
			assertThrows(MinChildrenSizeShouldBeTwo.class,
					() -> new A_B_Tree<Integer, Integer>((key1, key2) -> key1.equals(key2) ? 0 : key1 > key2 ? -1 : 1,
							1, 3));
		}

		@Test
		public void test_A_B_TreeWithInvalidMinMaxChildRealtionLimitsAndExpectException() {
			assertThrows(InvalidMinMaxChildrenLimits.class,
					() -> new A_B_Tree<Integer, Integer>((key1, key2) -> key1.equals(key2) ? 0 : key1 > key2 ? -1 : 1,
							10, 15));
		}

	}

	@Nested
	class Two_Three_TreeTest extends Nested_A_B_TreeTest {
		public Two_Three_TreeTest() {
			super(2, 3);
		}
	}

	@Nested
	@Disabled
	class Two_Four_TreeTest extends Nested_A_B_TreeTest {
		public Two_Four_TreeTest() {
			super(2, 4);
		}
	}

	@Nested
	class Two_Five_TreeTest extends Nested_A_B_TreeTest {
		public Two_Five_TreeTest() {
			super(2, 5);
		}
	}

	@Nested
	class Three_Six_TreeTest extends Nested_A_B_TreeTest {
		public Three_Six_TreeTest() {
			super(3, 6);
		}
	}

	@Nested
	class Fifty_Hundred_TreeTest extends Nested_A_B_TreeTest {
		public Fifty_Hundred_TreeTest() {
			super(50, 100);
		}
	}

	@Nested
	class Two_Hundred_Six_Hundred_TreeTest extends Nested_A_B_TreeTest {
		public Two_Hundred_Six_Hundred_TreeTest() {
			super(200, 600);
		}
	}

}
