package ec.edu.utpl.aa.manageoer.groovy;

import ec.edu.utpl.aa.manageoer.framework.adapters.input.rest.PersonalOerManagementAdapter;
import io.helidon.common.LogConfig;
import io.helidon.common.reactive.Single;
import io.helidon.config.Config;
import io.helidon.health.HealthSupport;
import io.helidon.health.checks.HealthChecks;
import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.metrics.MetricsSupport;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;

public final class App {

    private App(){}

    public static void main(final String[] args) {
        startServer();
    }

    static Single<WebServer> startServer() {

        LogConfig.configureRuntime();

        Config config = Config.create();

        WebServer server = WebServer.builder(createRouting())
                .config(config.get("server"))
                .addMediaSupport(JsonpSupport.create())
                .build();

        Single<WebServer> webserver = server.start();

        webserver.thenAccept(ws -> {
                    System.out.println("WEB server is up! http://127.0.0.1:" + ws.port() + "/");
                    ws.whenShutdown().thenRun(() -> System.out.println("WEB server is DOWN. Good bye!"));
                })
                .exceptionallyAccept(t -> {
                    System.err.println("Startup failed: " + t.getMessage());
                    t.printStackTrace(System.err);
                });

        return webserver;
    }

    private static Routing createRouting() {
        MetricsSupport metrics = MetricsSupport.create();
        HealthSupport health = HealthSupport.builder()
                .addLiveness(HealthChecks.healthChecks())
                .build();

        return Routing.builder()
                .register(health)
                .register(metrics)
                .register("/", new PersonalOerManagementAdapter())
                .build();
    }
}
