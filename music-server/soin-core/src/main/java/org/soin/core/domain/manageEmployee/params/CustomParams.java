package org.soin.core.domain.manageEmployee.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseParams;

import java.io.Serializable;

/**
 * 客户查询
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-03-04 19:56
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = 7494049788021241305L;

}
