package io.dyno.mvp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Configuration
public class Web3jConfig {

    private static final Logger log = LoggerFactory.getLogger(Web3j.class);

    @Value("${eth.node.url}")
    private String NODE_URL;

    @Bean(name = "web3j")
    Web3j web3j() throws IOException {
        final Web3j web3j = Web3j.build(new HttpService(
                NODE_URL));
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        return web3j;
    }

}