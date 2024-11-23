package co.pitam;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class MicrosHelloWorld extends Simulation {
    HttpProtocolBuilder req= http.baseUrl("http://localhost:8080/");

    ScenarioBuilder myscenario= CoreDsl.scenario("micros")
            .exec(http("")
                    .get("")
                    .check(
                            status().is(200),
                            status().not(500)
                    )
            );

    {
        setUp(myscenario.injectOpen(
//                atOnceUsers(350),
                rampUsers(5000).during(60) // Ramp up to 10,000 users over 120 seconds (2 minutes)
        ).protocols(req));
    }
}
