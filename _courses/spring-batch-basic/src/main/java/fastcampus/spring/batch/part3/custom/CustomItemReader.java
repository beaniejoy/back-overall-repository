package fastcampus.spring.batch.part3.custom;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

// ListItemReader 와 같은 기능을 한다.
public class CustomItemReader<T> implements ItemReader<T> {

    private final List<T> items;

    public CustomItemReader(List<T> items) {
        this.items = new ArrayList<>(items);    // copy
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!items.isEmpty()) {
            return items.remove(0);
        }

        // chunk 반복의 끝
        return null;
    }
}
