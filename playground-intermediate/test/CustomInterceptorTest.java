import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CustomInterceptorTest {
    CustomInterceptor interceptor = new CustomInterceptor();
    @Test
    public void testGetAvg() {
        assertEquals(0, interceptor.getAvg());
        interceptor.responseCounter = 10;
        interceptor.responseTime = 100;
        assertEquals(10, interceptor.getAvg());
    }
}