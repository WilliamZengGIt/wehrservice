package com.ilong.wehrservice.utils;

import com.ilong.wehrservice.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * TOOD
 *
 * @author long
 * @date 2020-07-05 22:05
 */
public class HrUtils {

    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


}
