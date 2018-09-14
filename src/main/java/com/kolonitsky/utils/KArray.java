package com.kolonitsky.utils;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public class KArray {

	public static boolean contains(String[] source, String value) {
		for (int i = 0; i < source.length; i++)
			if (source[i].equals(value))
				return true;
		return false;
	}
}
