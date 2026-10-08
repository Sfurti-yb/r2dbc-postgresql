package com.yugabyte;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.UniformLoadBalancerConnectionStrategy;
import io.r2dbc.postgresql.TopologyAwareLoadBalancerConnectionStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple unit test to verify Yugabyte-specific configuration can be created.
 * This test ensures that YugabyteDB-specific properties (loadBalanceHosts,
 * ybServersRefreshInterval, topologyKeys) and connection strategies are
 * present and can be used.
 */
public class YugabyteConfigurationTest {

    @Test
    public void testLoadBalanceConfigurationCanBeBuilt() {
        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
            .addHost("127.0.0.1")
            .username("yugabyte")
            .password("yugabyte")
            .database("yugabyte")
            .loadBalanceHosts(true)
            .ybServersRefreshInterval(300)
            .build();

        assertNotNull(config);
    }

    @Test
    public void testTopologyAwareConfigurationCanBeBuilt() {
        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
            .addHost("127.0.0.1")
            .username("yugabyte")
            .password("yugabyte")
            .database("yugabyte")
            .loadBalanceHosts(true)
            .topologyKeys("cloud.region.zone")
            .build();

        assertNotNull(config);
    }

    @Test
    public void testConnectionFactoryWithLoadBalance() {
        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
            .addHost("127.0.0.1")
            .username("yugabyte")
            .password("yugabyte")
            .database("yugabyte")
            .loadBalanceHosts(true)
            .build();

        PostgresqlConnectionFactory factory = new PostgresqlConnectionFactory(config);
        assertNotNull(factory);
    }

    @Test
    public void testUniformLoadBalancerStrategyExists() {
        // Verify that UniformLoadBalancerConnectionStrategy class exists and can be referenced
        assertNotNull(UniformLoadBalancerConnectionStrategy.class);
    }

    @Test
    public void testTopologyAwareLoadBalancerStrategyExists() {
        // Verify that TopologyAwareLoadBalancerConnectionStrategy class exists and can be referenced
        assertNotNull(TopologyAwareLoadBalancerConnectionStrategy.class);
    }
}
