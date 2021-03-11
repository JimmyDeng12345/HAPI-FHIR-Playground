import java.io.IOException;

import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;

@Interceptor
public class CustomInterceptor implements IClientInterceptor{
    int responseCounter = 0;
    long responseTime = 0;
    @Override
    public void interceptRequest(IHttpRequest theRequest) {
        // TODO Auto-generated method stub
    }

    @Override
    public void interceptResponse(IHttpResponse theResponse) throws IOException {
        responseCounter++;
        responseTime += theResponse.getRequestStopWatch().getMillis();		
    }

    public long getAvg(){
        long avg = responseCounter == 0 ? 0 : responseTime/responseCounter;
        responseCounter = 0;
        responseTime = 0;
        return avg;
    }
}

