package com.xdclass.redis.domain;

import java.util.Date;

/**
 *
 **/
public class RedPacketRecord {
    private Integer id;

    private Integer amount;

    private String nickName;

    private String imgUrl;

    private Integer uid;

    private Long redPacketId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Long redPacketId) {
        this.redPacketId = redPacketId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RedPacketRecord{" +
                "id=" + id +
                ", amount=" + amount +
                ", nickName='" + nickName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", uid=" + uid +
                ", redPacketId=" + redPacketId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
