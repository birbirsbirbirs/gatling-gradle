package co.pitam;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class MicrosHelloWorld extends Simulation {
    HttpProtocolBuilder req= http.baseUrl("http://127.0.0.1:51048");

    ScenarioBuilder myscenario= CoreDsl.scenario("micros")
            .exec(http("helloworld/hero")
                    .get("/helloworld/hero")
                    .check(
                            status().is(200),
                            status().not(500)
                    )
            ).pause(3)
            .exec(http("helloworld")
                    .get("/helloworld")
                    .check(
                            status().is(200),
                            status().not(500)
                    )
            ).pause(3);

    {
        setUp(myscenario.injectOpen(
//                atOnceUsers(20)
                rampUsers(100000).during(60*60*2) // Ramp up to 10,000 users over 120 seconds (2 minutes)
        ).protocols(req));
    }
}
