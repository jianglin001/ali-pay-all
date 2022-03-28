package com.example.jianglin.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * pay_order
 * 
 * @author jianglin
 * @version 1.0.0 2022-03-26
 */
@Getter
@Setter
@NoArgsConstructor
public class PayOrder implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 4131412346549339537L;

    /** id */
    private Integer id;

    /** 订单号 */
    private String orderNo;

    /** 渠道订单号 */
    private String channelTradeNo;

    /** 商户订单请求号 */
    private String merchantRequestId;

    /** 商品名称*/
    private String goodsName;
    /** 商品类型 */
    private String goodsType;

    /** DOING:待付款,SUCCESS:已付款,FAIL:支付失败；REFUN_SUCCESS:已退款； */
    private String orderStatus;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 支付金额 */
    private BigDecimal payAmount;

    /** 支付方式： 1.支付宝 2.微信 */
    private String payType;

    /** 购买时间 */
    private Date payTime;

    /** 银行名称 */
    private String bankName;

    /** 银行卡号 */
    private String bankAccount;

    /** 支行名称 */
    private String bankBranchName;

    /** createTime */
    private Date createTime;

    /** updateTime */
    private Date updateTime;

    /** 回调地址 */
    private String returnUrl;

    /** 支付回调地址 */
    private String nofityUrl;

    @Builder
    public PayOrder(Integer id, String orderNo, String channelTradeNo, String merchantRequestId, String goodsName, String goodsType, String orderStatus, BigDecimal orderAmount, BigDecimal payAmount, String payType, Date payTime, String bankName, String bankAccount, String bankBranchName, Date createTime, Date updateTime, String returnUrl, String nofityUrl) {
        this.id = id;
        this.orderNo = orderNo;
        this.channelTradeNo = channelTradeNo;
        this.merchantRequestId = merchantRequestId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.payAmount = payAmount;
        this.payType = payType;
        this.payTime = payTime;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.bankBranchName = bankBranchName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.returnUrl = returnUrl;
        this.nofityUrl = nofityUrl;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PayOrder{");
        sb.append("id=").append(id);
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append(", channelTradeNo='").append(channelTradeNo).append('\'');
        sb.append(", merchantRequestId='").append(merchantRequestId).append('\'');
        sb.append(", goodsName='").append(goodsName).append('\'');
        sb.append(", goodsType='").append(goodsType).append('\'');
        sb.append(", orderStatus='").append(orderStatus).append('\'');
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", payType='").append(payType).append('\'');
        sb.append(", payTime=").append(payTime);
        sb.append(", bankName='").append(bankName).append('\'');
        sb.append(", bankAccount='").append(bankAccount).append('\'');
        sb.append(", bankBranchName='").append(bankBranchName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", returnUrl='").append(returnUrl).append('\'');
        sb.append(", nofityUrl='").append(nofityUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}