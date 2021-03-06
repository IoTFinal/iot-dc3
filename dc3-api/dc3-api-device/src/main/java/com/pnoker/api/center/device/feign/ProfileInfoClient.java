/*
 * Copyright 2019 Pnoker. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pnoker.api.center.device.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pnoker.api.center.device.hystrix.ProfileInfoClientHystrix;
import com.pnoker.common.bean.R;
import com.pnoker.common.constant.Common;
import com.pnoker.common.dto.ProfileInfoDto;
import com.pnoker.common.model.Dic;
import com.pnoker.common.model.ProfileInfo;
import com.pnoker.common.valid.Insert;
import com.pnoker.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>驱动属性配置信息 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = Common.Service.DC3_DEVICE_PROFILE_INFO_URL_PREFIX, name = Common.Service.DC3_DEVICE, fallbackFactory = ProfileInfoClientHystrix.class)
public interface ProfileInfoClient {

    /**
     * 新增 ProfileInfo 记录
     *
     * @param profileInfo
     * @return ProfileInfo
     */
    @PostMapping("/add")
    R<ProfileInfo> add(@Validated(Insert.class) @RequestBody ProfileInfo profileInfo);

    /**
     * 根据 ID 删除 ProfileInfo
     *
     * @param id profileInfoId
     * @return Boolean
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@PathVariable(value = "id") Long id);

    /**
     * 修改 ProfileInfo 记录
     *
     * @param profileInfo
     * @return ProfileInfo
     */
    @PostMapping("/update")
    R<ProfileInfo> update(@Validated(Update.class) @RequestBody ProfileInfo profileInfo);

    /**
     * 根据 ID 查询 ProfileInfo
     *
     * @param id
     * @return ProfileInfo
     */
    @GetMapping("/id/{id}")
    R<ProfileInfo> selectById(@PathVariable(value = "id") Long id);

    /**
     * 根据 Name 查询 ProfileInfo
     *
     * @param name
     * @return ProfileInfo
     */
    @GetMapping("/name/{name}")
    R<ProfileInfo> selectByName(@PathVariable(value = "name") String name);

    /**
     * 分页查询 ProfileInfo
     *
     * @param profileInfoDto
     * @return Page<ProfileInfo>
     */
    @PostMapping("/list")
    R<Page<ProfileInfo>> list(@RequestBody(required = false) ProfileInfoDto profileInfoDto);

    /**
     * 查询 ProfileInfo 字典
     *
     * @return List<ProfileInfo>
     */
    @GetMapping("/dictionary")
    R<List<Dic>> dictionary();
}
