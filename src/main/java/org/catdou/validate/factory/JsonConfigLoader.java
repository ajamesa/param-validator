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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.catdou.validate.exception.ConfigException;
import org.catdou.validate.exception.ParseException;
import org.catdou.validate.model.config.CommonConfig;
import org.catdou.validate.model.config.ParamConfig;
import org.catdou.validate.model.config.UrlRuleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author James
 */
public class JsonConfigLoader implements ParamConfigLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConfigLoader.class);

    private static final String COMMON_NAME = "validate_common_config.json";

    private static final String CHECK_RULE_NAME = "validate_rule_";

    @Override
    public ParamConfig loadParamConfig(String path) {
        try {
            return loadByPathMatchingResource(path);
        } catch (IOException e) {
            throw new ParseException("load param json config error", e);
        }
    }

    public ParamConfig loadByPathMatchingResource(String path) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(path);
        List<Resource> resourceList = Arrays.stream(resources).filter(resource -> COMMON_NAME.equals(resource.getFilename()))
                .collect(Collectors.toList());
        if (resourceList.size() != 1) {
            throw new ConfigException(COMMON_NAME + " config error");
        }
        String commonJson = readStr(resourceList.get(0).getFile());
        CommonConfig commonConfig = JSONObject.parseObject(commonJson, CommonConfig.class);
        LOGGER.info("load common config json success");
        List<UrlRuleBean> allRuleBeanList = new ArrayList<>();
        for (Resource resource : resources) {
            String fileName = resource.getFilename();
            if (!COMMON_NAME.equals(fileName) && StringUtils.hasText(fileName) && fileName.startsWith(CHECK_RULE_NAME)) {
                String ruleJson = readStr(resource.getFile());
                List<UrlRuleBean> ruleBeanList = JSONArray.parseArray(ruleJson, UrlRuleBean.class);
                allRuleBeanList.addAll(ruleBeanList);
            }
        }
        LOGGER.info("load rule config json success");
        ParamConfig paramConfig = new ParamConfig();
        paramConfig.setCommonConfig(commonConfig);
        paramConfig.setUrlRuleBeanList(allRuleBeanList);
        return paramConfig;
    }

    public String readStr(File commonFile) {
        try {
            return FileUtils.readFileToString(commonFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ConfigException("read json str error ", e);
        }
    }
}
