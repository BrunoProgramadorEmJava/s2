A aplicação roda utilizando o JBoss 6.4

Será necessário configurar o datasource no standalone.xml conforme mostrado abaixo:

    <subsystem xmlns="urn:jboss:domain:datasources:1.2">
        <datasources>
            <datasource jta="true" jndi-name="java:/jdbc/s2-ds" pool-name="s2" enabled="true" use-java-context="true" use-ccm="true">
                <connection-url>jdbc:mysql://localhost:3306/s2</connection-url>
                <driver>com.mysql</driver>
                <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
                <pool>
                    <min-pool-size>5</min-pool-size>
                    <max-pool-size>30</max-pool-size>
                    <prefill>true</prefill>
                    <use-strict-min>false</use-strict-min>
                    <flush-strategy>FailingConnectionOnly</flush-strategy>
                </pool>
                <security>
                    <user-name>application</user-name>
                    <password>application</password>
                </security>
                <statement>
                    <prepared-statement-cache-size>32</prepared-statement-cache-size>
                </statement>
            </datasource>
            <drivers>
                <driver name="com.mysql" module="com.mysql">
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
                </driver>
            </drivers>
        </datasources>
    </subsystem>

Baixar o arquivo mysql-connector-java-5.1.36-bin.jar do site https://dev.mysql.com/downloads/connector/j/5.1.html
e colocar na pasta JBOSS_HOME\modules\com\mysql\main. Nesta mesma pasta criar o arquivo module.xml com o seguinte conteudo
<?xml version="1.0" encoding="UTF-8"?>

<module xmlns="urn:jboss:module:1.0" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-java-5.1.36-bin.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
    </dependencies>
</module>

Os testes unitários utilizam o H2 in memory Database.

