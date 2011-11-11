import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static org.apache.commons.lang3.StringUtils.isBlank

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

def catalinaBase = System.getProperty("catalina.base")
def test         = isBlank(catalinaBase)

def (path, basename) = !test ? ["${catalinaBase}/logs", "my-web-app"     ]
                             : ["./logs",               "my-web-app-test"]

appender("FILE", RollingFileAppender) {
    file = "${path}/${basename}.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${path}/${basename}.%d.log"
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%date|%-30.30thread|%-30.30logger{30}|%-5level|%message%n"
    }
}

if (test) {
    logger("org.hibernate.SQL",          DEBUG)
    logger("org.hibernate.tool.hbm2ddl", DEBUG)
}

root(INFO, ["FILE"])
