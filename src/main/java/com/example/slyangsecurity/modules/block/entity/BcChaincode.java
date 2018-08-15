package com.example.slyangsecurity.modules.block.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 区块链_链码
 * </p>
 *
 * @author author slyang
 * @since 2018-08-14
 */
public class BcChaincode extends Model<BcChaincode> {

    private static final long serialVersionUID = 1L;

    /**
     * 链码名
     */
    @TableField("name")
    private String name;
    @TableField("path")
    private String path;
    @TableField("version")
    private String version;
    @TableField("proposal_wait_time")
    private Integer proposalWaitTime;
    @TableField("invoke_wait_time")
    private Integer invokeWaitTime;
    @TableField("channel_id")
    private Integer channelId;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getProposalWaitTime() {
        return proposalWaitTime;
    }

    public void setProposalWaitTime(Integer proposalWaitTime) {
        this.proposalWaitTime = proposalWaitTime;
    }

    public Integer getInvokeWaitTime() {
        return invokeWaitTime;
    }

    public void setInvokeWaitTime(Integer invokeWaitTime) {
        this.invokeWaitTime = invokeWaitTime;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BcChaincode{" +
        ", name=" + name +
        ", path=" + path +
        ", version=" + version +
        ", proposalWaitTime=" + proposalWaitTime +
        ", invokeWaitTime=" + invokeWaitTime +
        ", channelId=" + channelId +
        ", id=" + id +
        "}";
    }
}
