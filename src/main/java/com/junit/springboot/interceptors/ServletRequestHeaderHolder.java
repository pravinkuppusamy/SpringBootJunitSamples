package com.junit.springboot.interceptors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServletRequestHeaderHolder {

    private String requesterName;

    private String tenantId;

}
