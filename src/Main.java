public class Main {
    public static void main(String[] args) {

        //Implemented simple main to display basic functionality

        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

        String input = "1,3,6,      7,8,12,13,14,15, 21,22,23,24,31";
        
        System.out.println("Input: " + input);

        var collected = summarizer.collect(input);
        System.out.println("Collected: " + collected);

        String summary = summarizer.summarizeCollection(collected);
        System.out.println("Summary: " + summary);
    }
}