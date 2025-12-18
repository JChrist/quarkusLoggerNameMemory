package dev.jchrist;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class ExampleResource {
    private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        for (int i = 0; i <10_000_000; i++) {
            LoggerFactory.getLogger("test." + i);

            if (i % 1000 == 0) {
                final long pretotal = Runtime.getRuntime().totalMemory();
                final long premax = Runtime.getRuntime().maxMemory();
                final long prefree = Runtime.getRuntime().freeMemory();

                System.gc();

                final long total = Runtime.getRuntime().totalMemory();
                final long max = Runtime.getRuntime().maxMemory();
                final long free = Runtime.getRuntime().freeMemory();
                logger.info("did another 1000:{}|PRE|total:{}|free:{}|used:{}|max:{}|POST|total:{}|free:{}|used:{}|max:{}",
                        i, pretotal, prefree, (pretotal - prefree), premax,
                        total, free, (total - free), max);
            }
        }
        return "Hello from Quarkus REST";
    }
}
