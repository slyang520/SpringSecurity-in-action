package com.example.slyangsecurity.modules.block.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.slyangsecurity.modules.block.entity.BcChaincode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 区块链_链码 Mapper 接口
 * </p>
 *
 * @author author slyang
 * @since 2018-08-14
 */
@Repository
public interface BcChaincodeMapper extends BaseMapper<BcChaincode> {

}
