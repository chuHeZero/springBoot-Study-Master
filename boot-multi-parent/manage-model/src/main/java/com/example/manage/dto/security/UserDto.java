/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.manage.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author abs
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private Boolean enabled;

    @JsonIgnore
    private Boolean isAdmin = false;

    private List<Integer> roles;

}
