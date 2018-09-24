package com.kolonitsky.kttp;

/**
 * @author Alexey Kolonitsky &lt;alexey.s.kolonitsky@gmail.com&gt;
 */
public interface KTTPHandler {
	void kttpError(String message);
	void kttpOutput(String message);
}
