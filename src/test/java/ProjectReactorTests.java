import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProjectReactorTests {

    public <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }

    @Test
    public void testAppendBoomError() {
        Flux<String> source = Flux.just("thing1", "thing2");

        StepVerifier.create(
            appendBoomError(source))
                .expectNext("thing1")
                .expectNext("thing2")
                .expectErrorMessage("boom")
                .verify();
    }
}
