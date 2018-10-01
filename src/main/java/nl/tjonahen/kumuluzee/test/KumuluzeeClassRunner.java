package nl.tjonahen.kumuluzee.test;

import com.kumuluz.ee.EeApplication;
import com.kumuluz.ee.common.config.EeConfig;
import com.kumuluz.ee.common.config.PersistenceConfig;
import com.kumuluz.ee.common.config.ServerConfig;
import com.kumuluz.ee.common.config.ServerConnectorConfig;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * A BlockJUnit4ClassRunner, enables correct starting of a kumuluzee applicatie,
 * uses the KumuluzeeTestContext to get the application context.
 *
 * @author Philippe Tjon - A - Hen, philippe@tjonahen.nl
 */
public class KumuluzeeClassRunner extends BlockJUnit4ClassRunner {
    
    public KumuluzeeClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
        final KumuluzeeTestContext testContext = klass.getAnnotation(KumuluzeeTestContext.class);
        if (testContext == null) {
            throw new IllegalStateException("Missing KumuluzeeTestContext annotation.");
        }
        
        EeConfig.Builder eeConfigBuilder = new EeConfig.Builder();
        if (!"".equals(testContext.unitname())) {
            eeConfigBuilder.persistenceConfig(new PersistenceConfig.Builder()
                                    .password(testContext.password())
            .url(testContext.url())
            .unitName(testContext.unitname())
            .username(testContext.username()));
//            persistenceConfig.setDriver(testContext.driver());
//            persistenceConfig.setUrl(testContext.url());
//            persistenceConfig.setUsername(testContext.username());
//            persistenceConfig.setPassword(testContext.password());
//            persistenceConfig.setUnitName(testContext.unitname());
        }
        eeConfigBuilder.server(new ServerConfig.Builder().http(new ServerConnectorConfig.Builder().port(testContext.port())));
//        eeConfig.getServer().getHttp().setPort(testContext.port());
        EeConfig.initialize(eeConfigBuilder.build());
        final EeApplication application = new EeApplication(EeConfig.getInstance());
    }
}
