package com.example.slyangsecurity.modules.block.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 区块链_链码
 * </p>
 *
 * @author author slyang
 * @since 2018-09-22
 */
@TableName("bc_chaincode")
public class BcChaincode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 链码名
     */
    @TableField("name")
    private String name;

    /**
     * 路径
     */
    @TableField("path")
    private String path;

    /**
     * 版本
     */
    @TableField("version")
    private String version;

    /**
     * 时间
     */
    @TableField("proposal_wait_time")
    private Integer proposalWaitTime;

    /**
     * 时间
     */
    @TableField("invoke_wait_time")
    private Integer invokeWaitTime;

    @TableField("channel_id")
    private Integer channelId;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 时间测试
     */
    @TableField(value = "date_test",fill = FieldFill.INSERT_UPDATE)
    private Date dateTest;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;
    

    public String getName() {
        return name;
    }

    public BcChaincode setName(String name) {
        this.name = name;
        return this;
    }
    public String getPath() {
        return path;
    }

    public BcChaincode setPath(String path) {
        this.path = path;
        return this;
    }
    public String getVersion() {
        return version;
    }

    public BcChaincode setVersion(String version) {
        this.version = version;
        return this;
    }
    public Integer getProposalWaitTime() {
        return proposalWaitTime;
    }

    public BcChaincode setProposalWaitTime(Integer proposalWaitTime) {
        this.proposalWaitTime = proposalWaitTime;
        return this;
    }
    public Integer getInvokeWaitTime() {
        return invokeWaitTime;
    }

    public BcChaincode setInvokeWaitTime(Integer invokeWaitTime) {
        this.invokeWaitTime = invokeWaitTime;
        return this;
    }
    public Integer getChannelId() {
        return channelId;
    }

    public BcChaincode setChannelId(Integer channelId) {
        this.channelId = channelId;
        return this;
    }
    public Integer getId() {
        return id;
    }

    public BcChaincode setId(Integer id) {
        this.id = id;
        return this;
    }
    public Date getDateTest() {
        return dateTest;
    }

    public BcChaincode setDateTest(Date dateTest) {
        this.dateTest = dateTest;
        return this;
    }

    @Override
    public String toString() {
        return "BcChaincode{" +
                "name=" + name +
                ", path=" + path +
                ", version=" + version +
                ", proposalWaitTime=" + proposalWaitTime +
                ", invokeWaitTime=" + invokeWaitTime +
                ", channelId=" + channelId +
                ", id=" + id +
                ", dateTest=" + dateTest +
                "}";
    }
}
