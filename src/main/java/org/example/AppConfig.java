package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig.java
 * Spring configuration class that enables component scanning.
 *
 * @author Nyevu Chea (Reg: SCT221-0595/2024)
 */
@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {
}