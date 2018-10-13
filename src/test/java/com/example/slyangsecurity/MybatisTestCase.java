package com.example.slyangsecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.slyangsecurity.modules.block.dao.BcChaincodeMapper;
import com.example.slyangsecurity.modules.block.dao.TestTableMapper;
import com.example.slyangsecurity.modules.block.entity.BcChaincode;
import com.example.slyangsecurity.modules.block.entity.TestTable;
import com.example.slyangsecurity.modules.block.service.BcChaincodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTestCase {

// case in CURD
// https://gitee.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-core/src/test/java/com/baomidou/mybatisplus/core/test/WrapperTest.java

    @Autowired
    BcChaincodeService bcChaincodeService;
    @Autowired
    BcChaincodeMapper bcChaincodeMapper;

    @Autowired
    TestTableMapper testTableMapper;

    @Test
    public void helloInsert() {

        BcChaincode bcChaincode = new BcChaincode();
        bcChaincode.setChannelId(666);
        bcChaincode.setVersion("1.3");
        bcChaincode.setPath("ffsdffdsfs");
        bcChaincode.setName("fdfdf");

        //bcChaincode.insert();
        bcChaincodeMapper.insert(bcChaincode);
    }

    @Test
    public void helloSelect() {
        // 查询所有
        List<BcChaincode> list = bcChaincodeMapper.selectList(null);

        // 按条件查询
        QueryWrapper<BcChaincode> wrappe2r = new QueryWrapper<>();
        wrappe2r.eq("version", "1.3");
        wrappe2r.isNotNull("name");
        List<BcChaincode> list2 = bcChaincodeMapper.selectList(wrappe2r);

        // 按条件查询 lambda
        LambdaQueryWrapper<BcChaincode> wrappe3r = new QueryWrapper<BcChaincode>().lambda();
        wrappe3r.eq(BcChaincode::getVersion, "1.3");
        wrappe3r.isNotNull(BcChaincode::getName);

        List<BcChaincode> list3 = bcChaincodeMapper.selectList(wrappe3r);

        // 分页查询
        IPage<BcChaincode> page4 = bcChaincodeMapper.selectPage(new Page<>(1, 10), wrappe3r);

    }

    @Test
    public void helloSelectUnSimple() {

        // OrderBY  last
        LambdaQueryWrapper<BcChaincode> wrappe3r = new QueryWrapper<BcChaincode>().lambda();
        wrappe3r.eq(BcChaincode::getVersion, "1.3");
        wrappe3r.isNotNull(BcChaincode::getName);
        wrappe3r.orderByAsc(BcChaincode::getName);
        wrappe3r.last("limit 1");

        List<BcChaincode> list1 = bcChaincodeMapper.selectList(wrappe3r);

        // or ()   and ()
        LambdaQueryWrapper<BcChaincode> wrappe4r = new QueryWrapper<BcChaincode>().lambda();
        wrappe4r.eq(BcChaincode::getVersion, "1.3");
        wrappe4r.or(c -> c.isNotNull(BcChaincode::getName).isNotNull(BcChaincode::getPath));
        List<BcChaincode> list2 = bcChaincodeMapper.selectList(wrappe4r);

        // apply
        LambdaQueryWrapper<BcChaincode> wrappe5r = new QueryWrapper<BcChaincode>().lambda();
        wrappe5r.apply("1=1"); // 自定义拼接SQL

        List<BcChaincode> list3 = bcChaincodeMapper.selectList(wrappe5r);

        // 自定义返回对象
        LambdaQueryWrapper<BcChaincode> wrappe6r = new QueryWrapper<BcChaincode>().lambda();
        wrappe6r.select(BcChaincode::getName, BcChaincode::getId);
        wrappe6r.eq(BcChaincode::getVersion, "1.3");

        List<Map<String, Object>> list4 = bcChaincodeMapper.selectMaps(wrappe6r);

    }

    @Test
    @Transactional
    public void helloTransactions() {

        BcChaincode bcChaincode = new BcChaincode();
        bcChaincode.setChannelId(666);
        bcChaincode.setVersion("1.32dsvs222");
        bcChaincode.setPath("ffsdffdsfs");
        bcChaincode.setName("fdfdf");
        bcChaincodeMapper.insert(bcChaincode);

        BcChaincode bcChaincode2 = new BcChaincode();
        bcChaincode2.setChannelId(66444);
        bcChaincode2.setVersion("1.32dsvs222");
        bcChaincode2.setPath("ffsdffdsfs");
        bcChaincode2.setName("fdfdf");
        bcChaincodeMapper.insert(bcChaincode2);

        throw new RuntimeException("fdsfdsf");
    }

    @Test
    public void helloPrimarykey() {

        TestTable testTable = new TestTable().setTt("fdsfdsf");
        testTableMapper.insert(testTable);
    }

    @Test
    public void helloUpdate() {

        BcChaincode chaincode = bcChaincodeMapper.selectById("1");
        bcChaincodeMapper.updateById(chaincode);

        // UPDATE bc_chaincode SET name=?, path=?, version=?, channel_id=?, date_test=? WHERE id=?
        //==> Parameters: fdfdf(String), ffsdffdsfs(String), 1.3(String), 666(Integer), null, 1(Integer)
        //  UPDATE bc_chaincode SET name='fdfdf', path='ffsdffdsfs', version='1.3', channel_id=666, date_test=null WHERE id=1


        LambdaUpdateWrapper<BcChaincode> wrappe6r = new UpdateWrapper<BcChaincode>().lambda();
        wrappe6r.eq(BcChaincode::getVersion, "1.35");

        BcChaincode chaincode2 = new BcChaincode();
        chaincode2.setVersion("1.36");
        bcChaincodeMapper.update(chaincode2, wrappe6r);

        // UPDATE bc_chaincode SET version=?, date_test=? WHERE version = ?
        // Parameters: 1.36(String), null, 1.35(String)
        // UPDATE bc_chaincode SET version='1.36', date_test=null WHERE version = '1.35'


        BcChaincode chaincode3 = new BcChaincode();
        LambdaUpdateWrapper<BcChaincode> updateWrapper = new UpdateWrapper<BcChaincode>().lambda();
        updateWrapper.set(BcChaincode::getChannelId, null).eq(BcChaincode::getId, 1);

        bcChaincodeMapper.update(chaincode3, updateWrapper);

        // UPDATE bc_chaincode SET date_test=?, channel_id=? WHERE id = ?
        // Parameters: null, null, 1(Integer)
        // UPDATE bc_chaincode SET date_test=null, channel_id=null WHERE id = 1
    }

    @Test
    public void helloDel(){

        bcChaincodeMapper.deleteById(2);
        // DELETE FROM bc_chaincode WHERE id=?
        // Parameters: 1(Integer)
        // DELETE FROM bc_chaincode WHERE id=1

        LambdaQueryWrapper<BcChaincode> wrappe3r = new QueryWrapper<BcChaincode>().lambda();
        wrappe3r.eq(BcChaincode::getVersion, "1.3");
        bcChaincodeMapper.delete(wrappe3r);
        // DELETE FROM bc_chaincode WHERE version = ?
        // Parameters: 1.3(String)
        // DELETE FROM bc_chaincode WHERE version = '1.3'

        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("version","1.4");
        bcChaincodeMapper.deleteByMap(objectMap);
        // DELETE FROM bc_chaincode WHERE version = '1.4'
    }

    @Test
    public void caseLock(){ // 乐观锁 悲观锁

        //  乐观锁 
        BcChaincode chaincode = bcChaincodeMapper.selectById(3);

        LambdaUpdateWrapper<BcChaincode> wrappe4r = new UpdateWrapper<BcChaincode>().lambda();

        wrappe4r.set(BcChaincode::getDateTest,new Date())
                .eq(BcChaincode::getId, chaincode.getId())
                .eq(BcChaincode::getDateTest, chaincode.getDateTest());

        int result = bcChaincodeMapper.update(new BcChaincode(), wrappe4r);

        //  悲观锁

    }


}