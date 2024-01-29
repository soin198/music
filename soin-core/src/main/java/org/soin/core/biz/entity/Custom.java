package org.soin.core.biz.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.biz.common.BaseEntity;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:11
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Custom extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6176882420433792109L;


}
