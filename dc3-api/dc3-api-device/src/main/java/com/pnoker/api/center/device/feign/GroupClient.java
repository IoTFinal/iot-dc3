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
import com.pnoker.api.center.device.hystrix.GroupClientHystrix;
import com.pnoker.common.bean.R;
import com.pnoker.common.constant.Common;
import com.pnoker.common.dto.GroupDto;
import com.pnoker.common.model.Dic;
import com.pnoker.common.model.Group;
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
 * <p>设备分组 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = Common.Service.DC3_DEVICE_GROUP_URL_PREFIX, name = Common.Service.DC3_DEVICE, fallbackFactory = GroupClientHystrix.class)
public interface GroupClient {

    /**
     * 新增 Group 记录
     *
     * @param group
     * @return Group
     */
    @PostMapping("/add")
    R<Group> add(@Validated(Insert.class) @RequestBody Group group);

    /**
     * 根据 ID 删除 Group
     *
     * @param id groupId
     * @return Boolean
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@PathVariable(value = "id") Long id);

    /**
     * 修改 Group 记录
     *
     * @param group
     * @return Group
     */
    @PostMapping("/update")
    R<Group> update(@Validated(Update.class) @RequestBody Group group);

    /**
     * 根据 ID 查询 Group
     *
     * @param id
     * @return Group
     */
    @GetMapping("/id/{id}")
    R<Group> selectById(@PathVariable(value = "id") Long id);

    /**
     * 根据 Name 查询 Group
     *
     * @param name
     * @return Group
     */
    @GetMapping("/name/{name}")
    R<Group> selectByName(@PathVariable(value = "name") String name);

    /**
     * 分页查询 Group
     *
     * @param groupDto
     * @return Page<Group>
     */
    @PostMapping("/list")
    R<Page<Group>> list(@RequestBody(required = false) GroupDto groupDto);

    /**
     * 查询 Group 字典
     *
     * @return List<Group>
     */
    @GetMapping("/dictionary")
    R<List<Dic>> dictionary();
}
