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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Test context for starting a correct KumuluZEE application.
 * @author Philippe Tjon - A - Hen, philippe@tjonahen.nl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface KumuluzeeTestContext {

    /**
     * 
     * @return portnumber; 
     */
    public int port() default 8080;
    /**
     * 
     * @return database driver class, default is org.h2.Driver 
     */
    public String driver() default "org.h2.Driver";
    /**
     * 
     * @return database url, default is jdbc:h2:mem:kumuluzee_test 
     */
    public String url() default "jdbc:h2:mem:kumuluzee_test";
    /**
     * 
     * @return database username, default sa 
     */
    public String username() default "sa";
    /**
     * 
     * @return database user password, default is no password or empty
     */
    public String password() default "";
    /**
     * 
     * @return persistence unit name 
     */
    public String unitname();

}
