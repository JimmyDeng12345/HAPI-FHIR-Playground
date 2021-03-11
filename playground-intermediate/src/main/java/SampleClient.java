import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;

import java.io.*;
import java.util.*;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

public class SampleClient {

    public static void main(String[] theArgs) {
        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        CustomInterceptor customInterceptor = new CustomInterceptor();
        client.registerInterceptor(customInterceptor);
        //Store the names into a List of Strings
        List<String> nameList = readToList("playground-intermediate/src/main/resources/names.txt");

        //main loop
        for (int i = 1; i <= 3; i++) {
            if (i == 3) {
                        client.search()
                        .forResource("Patient")
                        .where(Patient.FAMILY.matches().values(nameList))
                        .returnBundle(Bundle.class)
                        .cacheControl(new CacheControlDirective().setNoCache(true))
                        .execute();
            }else{ 
                        client.search()
                        .forResource("Patient")
                        .where(Patient.FAMILY.matches().values(nameList))
                        .returnBundle(Bundle.class)
                        .execute();
            }
            System.out.println("Run #" + i + " Average response time = " + customInterceptor.getAvg() + "ms");
        }
    }
    
    public static List<String> readToList(String path){
        List<String> nameList = new ArrayList<String>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            String name = br.readLine();
            while (name != null) {
                nameList.add(name);
                name = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameList;
    }
}

