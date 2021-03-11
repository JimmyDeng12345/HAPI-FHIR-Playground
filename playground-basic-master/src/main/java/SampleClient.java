import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

import java.util.*;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;

public class SampleClient {

    public static void main(String[] theArgs) {

        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));
        
        // Search for Patient resources
        Bundle response = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("SMITH")) //.sort(new SortSpec(Patient.GIVEN.getParamName()))
                .returnBundle(Bundle.class)
                .execute();
        
        List<BundleEntryComponent> list = response.getEntry();
        List<Patient> patientList = new ArrayList<Patient>();
        // loop through entries and store them as patients into patientList
        for (int i = 0; i < list.size(); i++) {
            Patient patient = (Patient) list.get(i).getResource();
            patientList.add(patient);
        }
        // Sort by 
        Collections.sort(patientList, 
            (o1, o2) -> o1.getName().get(0).getGivenAsSingleString().compareTo(o2.getName().get(0).getGivenAsSingleString()));
        
        for (Patient p : patientList) {
            System.out.print(p.getName().get(0).getGivenAsSingleString() + " " + p.getName().get(0).getFamily());
            // if has birthday, print it
            if(p.hasBirthDate())
                System.out.println(" " + p.getBirthDate());
            else
                System.out.println();
        }
    }
}