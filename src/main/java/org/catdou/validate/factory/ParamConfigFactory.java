/*
 * Copyright 2021 catdou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.catdou.validate.factory;


import org.catdou.validate.enums.LoaderType;
import org.catdou.validate.exception.ConfigException;

/**
 * @author James
 */
public class ParamConfigFactory {

    public ParamConfigLoader createParamConfigLoader(String type) {
        if (LoaderType.JSON.name().equalsIgnoreCase(type)) {
            return new JsonConfigLoader();
        } else if (LoaderType.XML.name().equalsIgnoreCase(type)) {
            return new XmlConfigLoader();
        } else {
            throw new ConfigException("unknown param file load type " + type);
        }
    }
}
