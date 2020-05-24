package info.lab.subscriptionservice.configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

@RibbonClient(name="retriever-service")
public class RibbonBConfiguration {
    @Bean
    public IRule loadBlancingRule() {
        //return new RandomRule();
        return new RoundRobinRule();
    }
}
