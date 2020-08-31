package com.tuli.traffic.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.tuli.traffic.respository")
@Slf4j
public class ESConfig extends AbstractElasticsearchConfiguration {

    @Value("#{'${es.hostAndPort}'.split(',')}")
    public List<String> hostAndPortList;
    @Value("${es.connectTimeout:3000}")
    private int connectTimeout = -1;
    @Value("${es.socketTimeout:3000}")
    private int socketTimeout = -1;
    @Value("${es.connectionRequestTimeout:1000}")
    private int connectionRequestTimeout = -1;

    @Value("${es.username}")
    private String username;
    @Value("${es.password}")
    private String password;

    @Value("${es.clusterHost}")
    private String clusterHost;
    @Value("${es.clusterPort}")
    private int clusterPort;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        boolean isLocalhost = clusterHost.contains("localhost");
        
        String[] array = new String[hostAndPortList.size()];
        ClientConfiguration clientConfiguration = null;
        if (isLocalhost) {            
            clientConfiguration = ClientConfiguration.builder().connectedTo(hostAndPortList.toArray(array))
            .build();
            return RestClients.create(clientConfiguration).rest();
        } else {
            clientConfiguration = ClientConfiguration.builder().connectedTo(hostAndPortList.toArray(array))
            .withConnectTimeout(Duration.ofSeconds(connectTimeout))
            .withSocketTimeout(Duration.ofSeconds(socketTimeout))
            .withBasicAuth(username, password).build();            
            return RestClients.create(clientConfiguration).rest();
        }
    }

    public void setConnectTimeOutConfig(RestClientBuilder builder) {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(connectTimeout);
                builder.setSocketTimeout(socketTimeout);
                builder.setConnectionRequestTimeout(500);
                builder.setAuthenticationEnabled(true);
                return builder;
            }
        });

    }

}