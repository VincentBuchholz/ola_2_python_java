package dk.vv;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Path("/api/")
public class PredictionResource {

    @GET
    @Path("prediction/{index}")
    @Produces(MediaType.TEXT_PLAIN)
    public String predict(@PathParam("index") int index) throws IOException {
        Process p = Runtime.getRuntime().exec(String.format("python3 /Users/vincentbuchholz/Desktop/SoftSem2/Python/OLA_2/python-implementation-quarkus/src/main/resources/python/breast_cancer_prediction.py --file /Users/vincentbuchholz/Desktop/SoftSem2/Python/OLA_2/python-implementation-quarkus/src/main/resources/python/cancer.csv --index %d",index));

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        String pred = stdInput.readLine().equals("1")? "do": "do not";

        return String.format("It looks like you %s have cancer",pred);
    }
}
