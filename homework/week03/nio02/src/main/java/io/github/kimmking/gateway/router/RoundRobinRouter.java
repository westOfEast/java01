package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinRouter implements HttpEndpointRouter  {
    private AtomicInteger nextServerCyclicCounter;

    public RoundRobinRouter(){
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    @Override
    public String route(List<String> endpoints) {
        //长度
        int serverCount = endpoints.size();
        // 如果等于 0 return
        if ((serverCount == 0)) {
            return null;
        }

        int nextServerIndex = incrementAndGetModulo(serverCount);
        //获取下标
        return endpoints.get(nextServerIndex);
    }

    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }
}
