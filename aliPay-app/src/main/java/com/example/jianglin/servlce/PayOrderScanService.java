package com.example.jianglin.servlce;

import com.example.jianglin.dto.BaseResponseDTO;
import com.example.jianglin.entity.AliPayCallBackParams;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 17:57
 * 处理回调
 * 轮询扫描定时任务
 */
public interface PayOrderScanService {

    BaseResponseDTO payOrderScanCallback(AliPayCallBackParams aliPayCallBackParams);
}
