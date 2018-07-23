package io.dyno.mvp.config;

import io.ipfs.api.IPFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IPFSConfig {

    private static final Logger log = LoggerFactory.getLogger(IPFSConfig.class);

    @Value("${ipfs.node.ip}")
    private String NODE_IP;

    @Bean(name = "ipfs")
    public IPFS ipfs() {
        try {
            return new IPFS("/ip4/" + NODE_IP + "/tcp/5001");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

}
