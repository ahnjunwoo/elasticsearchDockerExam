package com.codegun.elasticsearch.config;

import com.codegun.elasticsearch.service.SampleDataSet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@Configuration
public class ElasticConfig {
    @Bean
    @ConditionalOnProperty("initial-import.enabled")
    public SampleDataSet dataSet() {
        return new SampleDataSet();
    }
}
