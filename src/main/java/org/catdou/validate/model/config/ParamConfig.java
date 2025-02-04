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

package org.catdou.validate.model.config;

import org.catdou.validate.cache.ValidatorCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author James
 */
public class ParamConfig {
    private CommonConfig commonConfig;

    private List<UrlRuleBean> urlRuleBeanList;

    private Map<String, List<UrlRuleBean>> methodUrlMap = new HashMap<>();

    private ValidatorCache validatorCache;

    public void init() {
        methodUrlMap = urlRuleBeanList.stream().collect(Collectors.groupingBy(UrlRuleBean::getMethod));
    }

    public CommonConfig getCommonConfig() {
        return commonConfig;
    }

    public void setCommonConfig(CommonConfig commonConfig) {
        this.commonConfig = commonConfig;
    }

    public List<UrlRuleBean> getUrlRuleBeanList() {
        return urlRuleBeanList;
    }

    public void setUrlRuleBeanList(List<UrlRuleBean> urlRuleBeanList) {
        this.urlRuleBeanList = urlRuleBeanList;
    }

    public Map<String, List<UrlRuleBean>> getMethodUrlMap() {
        return methodUrlMap;
    }

    public ValidatorCache getValidatorCache() {
        return validatorCache;
    }

    public void setValidatorCache(ValidatorCache validatorCache) {
        this.validatorCache = validatorCache;
    }
}
