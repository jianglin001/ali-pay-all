package com.example.jianglin.servlce;

import com.example.jianglin.dto.AliPayRequestDTO;
import com.example.jianglin.dto.AliPayResponseDTO;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-25 15:50
 */
public interface AliPayService {
    AliPayResponseDTO aliPayAndroidApp(AliPayRequestDTO aliPayRequestDTO);
}
