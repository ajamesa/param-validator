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

package scd.str;

import org.catdou.validate.constant.ParamValidatorConstant;
import org.junit.Test;

/**
 * @author James
 */
public class StringFormat {

    @Test
    public void testFormat() {
        String format = String.format(ParamValidatorConstant.ERROR_MSG, "rule1", "name", "chengdu");
        System.out.println(format);
    }
}
