package io.dyno.mvp.config;

import io.ipfs.api.IPFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;

@Configuration
public class IPFSConfig {

    private static final Logger log = LoggerFactory.getLogger(IPFSConfig.class);

    @Value("${ipfs.node.url}")
    private String NODE_URL;

    @Bean(name = "ipfs")
    public IPFS ipfs() {
        try {
            return new IPFS("/ip4/127.0.0.1/tcp/5001");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

}
