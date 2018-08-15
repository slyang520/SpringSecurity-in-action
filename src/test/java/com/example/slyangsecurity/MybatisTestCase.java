package com.example.slyangsecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public void helloUpdate() {
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

}