import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class SampleClientTest {
    CustomInterceptor interceptor = new CustomInterceptor();

    @Test
    public void readToListTest() {
        PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
        List<String> temp = new ArrayList();
        for (int i = 0; i < 20; i++) {
            writer.println(i);
            temp.add(Integer.toString(i));
        }
        writer.close();

        assertEquals(temp.size(), readToList("test.txt").size());
        assertEquals(temp, readToList("test.txt"));
    }
}