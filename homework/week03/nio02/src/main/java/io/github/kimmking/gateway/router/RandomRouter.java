package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomRouter implements HttpEndpointRouter {

    @Override
    public  String route(List<String> endpoints) {
        int serverCount = endpoints.size();
        // 如果等于 0 return
        if ((serverCount == 0)) {
            return null;
        }

        Random random = new Random();
        int index = random.nextInt(endpoints.size());
        return endpoints.get(index);
    }
}
