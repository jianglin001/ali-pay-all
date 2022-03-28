package com.example.jianglin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jianglin
 * @version 1.0
 * @date 2022-03-26 17:40
 */
@Getter
@Setter
public class AliPayCallBackParams {
    private String notify_type;
    private String notify_id;
    private String notify_time;
    private String trade_no;
    private String out_trade_no;
    private String seller_id;
    private String trade_status;
    private String total_amount;
    private String receipt_amount;
    private String buyer_pay_amount;
    private String point_amount;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AliPayCallBackParams{");
        sb.append("notify_type='").append(notify_type).append('\'');
        sb.append(", notify_id='").append(notify_id).append('\'');
        sb.append(", notify_time='").append(notify_time).append('\'');
        sb.append(", trade_no='").append(trade_no).append('\'');
        sb.append(", out_trade_no='").append(out_trade_no).append('\'');
        sb.append(", seller_id='").append(seller_id).append('\'');
        sb.append(", trade_status='").append(trade_status).append('\'');
        sb.append(", total_amount='").append(total_amount).append('\'');
        sb.append(", receipt_amount='").append(receipt_amount).append('\'');
        sb.append(", buyer_pay_amount='").append(buyer_pay_amount).append('\'');
        sb.append(", point_amount='").append(point_amount).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
