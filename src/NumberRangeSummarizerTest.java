import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;


///Below are my Unit tests to make sure the code works correctly, all tests were passed.
class NumberRangeSummarizerTest {

    NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

    @Test
    void testCollect() {
        List<Integer> result = (List<Integer>) summarizer.collect("1, 3, 2");
        assertEquals(List.of(1, 2, 3), result);
    }

    @Test
    void testSummarizeCollection() {
        List<Integer> input = List.of(1, 3, 6, 7, 8, 12, 13, 14, 15);
        String summary = summarizer.summarizeCollection(input);
        assertEquals("1,3,6-8,12-15", summary);
    }

    @Test
    void testEmptyInput() {
        assertEquals("", summarizer.summarizeCollection(List.of()));
    }

    @Test
    void testSingleValue() {
        assertEquals("5", summarizer.summarizeCollection(List.of(5)));
    }

    @Test
    void testOneLargeRange() {
        List<Integer> input = List.of(1,2,3,4,5);
        assertEquals("1-5", summarizer.summarizeCollection(input));
    }

    @Test
    void testHandleSpaces(){
        Collection<Integer> result = summarizer.collect("1,  4,   2");
        assertEquals(List.of(1, 2, 4), result);
    }

    @Test
    void testSummarizeNonSequential() {
        List<Integer> numbers = List.of(10, 20, 30);
        assertEquals("10,20,30", summarizer.summarizeCollection(numbers));
    }

    @Test
    void testUnsorted(){
        List<Integer> result = (List<Integer>) summarizer.collect("1, 3, 7, 2");
        assertEquals("1-3,7", summarizer.summarizeCollection(result));
    }

    @Test
    void testNegativeNumbers() {
        List<Integer> result = (List<Integer>) summarizer.collect("-3, -1, -2");
        assertEquals(List.of(-3, -2, -1), result);
        assertEquals("-3--1", summarizer.summarizeCollection(result));
    }

    @Test
    void testMixedNegativePositive() {
        List<Integer> result = (List<Integer>) summarizer.collect("-1, 0, 1");
        assertEquals(List.of(-1, 0, 1), result);
        assertEquals("-1-1", summarizer.summarizeCollection(result));
    }

    @Test
    void testLargeNumbers() {
        List<Integer> input = List.of(999999998, 999999999, 1000000000);
        assertEquals("999999998-1000000000", summarizer.summarizeCollection(input));
    }

    @Test
    void testCollectOnlySpaces() {
        Collection<Integer> result = summarizer.collect("    ");
        assertEquals(List.of(), result);
    }

    @Test
    void testCollectNull() {
        Collection<Integer> result = summarizer.collect(null);
        assertEquals(List.of(), result);
    }

    @Test
    void testSummarizeNull() {
        assertEquals("", summarizer.summarizeCollection(null));
    }

    @Test
    void testMixedSinglesAndRanges() {
        List<Integer> input = List.of(1,2,3,5,7,8,9);
        assertEquals("1-3,5,7-9", summarizer.summarizeCollection(input));
    }


    @Test
    void testTrailingComma() {
        List<Integer> result = (List<Integer>) summarizer.collect("1,2,3,");
        assertEquals(List.of(1,2,3), result);
    }

    @Test
    void testLargeGaps() {
        List<Integer> input = List.of(1,100,200,300);
        assertEquals("1,100,200,300", summarizer.summarizeCollection(input));
    }

}
