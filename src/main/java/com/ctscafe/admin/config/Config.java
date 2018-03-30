package com.ctscafe.admin.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
       @Bean
       public RestTemplate restTemplate() {
//    	final String username = "653569";
//        final String password = "";
//        final String proxyUrl = "proxy.cognizant.com";
//        final int port = 6050;
//
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials( 
//                new AuthScope(proxyUrl, port), 
//                new UsernamePasswordCredentials(username, password));
//
//        HttpHost myProxy = new HttpHost(proxyUrl, port);
// 
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//  
//        HttpClientBuilder clientBuilder = HttpClientBuilder.create(); 
//        clientBuilder
//           .setProxy(myProxy)
//           .setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy())
//           .setDefaultCredentialsProvider(credsProvider) 
//           .disableCookieManagement(); 
//        CloseableHttpClient httpClient = clientBuilder.build();
//        factory.setHttpClient(httpClient);
//        return new RestTemplate(factory);
    	   return new RestTemplate();
       }
}
