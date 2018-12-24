package com.example.slyangsecurity.modules.block.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.slyangsecurity.modules.block.entity.BcChaincode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    Map<String,Object> seletMapTest();

    List<BcChaincode> selectListTest();

}
