package co.pitam;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class MicrosHelloWorld extends Simulation {
    HttpProtocolBuilder req= http.baseUrl("http://localhost:8000/netty-demo");

    ScenarioBuilder myscenario= CoreDsl.scenario("micros")
            .exec(http("/onepost")
                    .get("/onepost")
                    .check(
                            status().is(200),
                            status().not(500)
                    )
            );

    {
        setUp(myscenario.injectOpen(
                rampUsers(300*10*100).during(60*20)
        ).protocols(req));
    }
}
