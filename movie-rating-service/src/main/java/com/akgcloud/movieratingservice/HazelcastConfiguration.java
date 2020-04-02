package com.akgcloud.movieratingservice;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@Configuration
public class HazelcastConfiguration {
	
	@Bean
	public Config hazelCastConfig() {
		return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("ratings-cache")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(300));
	}
	
    @Bean
    HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
        HazelcastInstance hazelcastInstance = com.hazelcast.core.Hazelcast.newHazelcastInstance(hazelCastConfig);
//        System.out.println("TAG 3");
        return hazelcastInstance;
    }

    @Bean
    CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
//        System.out.println("TAG 4");
        return new HazelcastCacheManager(hazelcastInstance);
    }

}
