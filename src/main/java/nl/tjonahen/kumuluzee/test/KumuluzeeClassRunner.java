/*
 * Copyright (C) 2016 Philippe Tjon - A - Hen, philippe@tjonahen.nl
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.tjonahen.kumuluzee.test;

import com.kumuluz.ee.EeApplication;
import com.kumuluz.ee.common.config.EeConfig;
import com.kumuluz.ee.common.config.PersistenceConfig;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * A BlockJUnit4ClassRunner, enables correct starting of a kumuluzee applicatie, uses the KumuluzeeTestContext to get the application context.
 * @author Philippe Tjon - A - Hen, philippe@tjonahen.nl
 */
public class KumuluzeeClassRunner extends BlockJUnit4ClassRunner {

    public KumuluzeeClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
        final KumuluzeeTestContext testContext = klass.getAnnotation(KumuluzeeTestContext.class);
        if (testContext == null) {
            throw new IllegalStateException("Missing KumuluzeeTestContext annotation.");
        }

        final EeConfig eeConfig = new EeConfig();
        final PersistenceConfig persistenceConfig = eeConfig.getPersistenceConfigs().get(0);
        persistenceConfig.setDriver(testContext.driver());
        persistenceConfig.setUrl(testContext.url());
        persistenceConfig.setUsername(testContext.username());
        persistenceConfig.setPassword(testContext.password());
        persistenceConfig.setUnitName(testContext.unitname());

        eeConfig.getServerConfig().setPort(testContext.port());

        final EeApplication application = new EeApplication(eeConfig);
    }
}
