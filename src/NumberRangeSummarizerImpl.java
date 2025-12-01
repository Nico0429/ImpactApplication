import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{

    /*
    ASSUMPTIONS MADE
    -No missing commas
    -No invalid characters
    -No floats
    -No negative signs with spaces
    -No duplicates
     */
    @Override
    public Collection<Integer> collect(String input) {
        //Check if input is empty, return empty list.
        if(input == null || input.isBlank()){
            return Collections.emptyList();
        }

        //Use Stream API to return sorted list.
        return  Arrays.stream(input.split(",")).
                map(String::trim).
                filter(s->!s.isEmpty()).
                map(Integer::parseInt).
                sorted().
                collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> numbers = new ArrayList<>(input);
        StringBuilder result = new StringBuilder();
        int start = numbers.getFirst();
        int  prev = start;


        //Iterate through numbers
        for (int i = 1;i<numbers.size();i++){
            int current = numbers.get(i);
            //there is a jump, i.e. number/s skipped
            if(current != prev+1){
                appendRange(result,start,prev);
                result.append(",");
                start = current;
            }
            prev = current;
        }

        appendRange(result,start,prev);
        return result.toString();
    }

    //Simple helper method to prevent reusing code
    private void appendRange(StringBuilder result, int start, int prev) {
        if (start == prev) {
            result.append(start);
        } else {
            result.append(start).append("-").append(prev);
        }
    }
}
