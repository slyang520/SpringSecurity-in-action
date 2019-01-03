package com.example.slyangsecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.example.slyangsecurity.modules.block.dao.BcChaincodeMapper;
import com.example.slyangsecurity.modules.block.dao.TestTableMapper;
import com.example.slyangsecurity.modules.block.entity.BcChaincode;
import com.example.slyangsecurity.modules.block.entity.TestTable;
import com.example.slyangsecurity.modules.block.entity.UserRole;
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
    public void helloSelectCase() {

        List<UserRole> userRoles= bcChaincodeMapper.seltMuitTable();

        Map<String,Object> para = new HashMap<>();
        para.put("int_test",3);   // 数字类型
        para.put("str_test","3");   // 数字类型
        bcChaincodeMapper.seltSt(para);

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


    /**
     * 事务详解
     *
     * 1. 作用范围
     * 标注在类前：标示类中所有方法都进行事务处理
     * 标注在接口、实现类的方法前：标示方法进行事务处理
     *
     * 2. 传播行为
     *   2.1 @Transactional(propagation=Propagation.REQUIRED)  如果有事务,那么加入事务,没有的话新建一个(默认情况)
     *   2.2 @Transactional(propagation=Propagation.NOT_SUPPORTED)	容器不为这个方法开启事务
     *   2.3 @Transactional(propagation=Propagation.REQUIRES_NEW)   不管是否存在事务，都创建一个新的事务，原来的挂起，新的执行完毕，继续执行老的事务
     *   2.4 @Transactional(propagation=Propagation.MANDATORY) 必须在一个已有的事务中执行，否则抛出异常
     *   2.5 @Transactional(propagation=Propagation.NEVER)	必须在一个没有的事务中执行，否则抛出异常(与Propagation.MANDATORY相反)
     *   2.6 @Transactional(propagation=Propagation.SUPPORTS)  如果其他bean调用这个方法，在其他bean中声明事务，那就用事务。如果其他bean没有声明事务，那就不用事务
     *
     * 3. 事务隔离级别
     * todo
     *   3.1 @Transactional(isolation = Isolation.READ_UNCOMMITTED)	 读取未提交数据(会出现脏读， 不可重复读)
     *   3.2 @Transactional(isolation = Isolation.READ_COMMITTED) 读取已提交数据(会出现不可重复读和幻读)
     *   3.3 @Transactional(isolation = Isolation.REPEATABLE_READ)	可重复读(会出现幻读)
     *   3.4 @Transactional(isolation = Isolation.SERIALIZABLE)	 串行化
     *
     * 名词释义：
     * 脏读 ，脏读就是指当一个事务正在访问数据，并且对数据进行了修改未提及 也可以看。
     * 不可重复读，是指在数据库访问中，一个事务范围内两个相同的查询却返回了不同数据。
     * 幻读，
     *
     * MYSQL：可重复读
     */
    @Test
    @Transactional
    public void helloTransactionsDetail() {

        // CASE:1
        // A的隔离级别设置为 Read Uncommitted  (读未提交)
        // B更新了一条记录，但是没有提交

        // RESULT:
        // 事务A 可以查询出未提交记录。造成脏读现象。未提交读是最低的隔离级别



        // CASE:2
        // A的隔离级别设置为 Read Committed    (已提交读)
        // B更新了一条记录，但是没有提交
        // B更新了一条记录，提交提交

        // RESULT:
        // 事务A 只可以查询已提交记录。 B更新了一条记录未提交 A查询不到,B更新了一条记录已提交提 A查询得到



        // CASE:3
        // 将A的隔离级别设置为Repeatable Read  (可重复读)
        
        // RESULT:
        // 



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

    /**
     * 逻辑删除测试（注意一定要有默认值）
     * UPDATE bc_chaincode SET deleted=1 WHERE id=? AND deleted=0
     * bcChaincodeMapper.deleteById(4);
     */
    @Test
    public void helloDel() {

        // UPDATE bc_chaincode SET deleted=1 WHERE id=? AND deleted=0
        bcChaincodeMapper.deleteById(4);
        // DELETE FROM bc_chaincode WHERE id=?
        // Parameters: 1(Integer)
        // DELETE FROM bc_chaincode WHERE id=1

        LambdaQueryWrapper<BcChaincode> wrappe3r = new QueryWrapper<BcChaincode>().lambda();
        wrappe3r.eq(BcChaincode::getVersion, "1.3");
        bcChaincodeMapper.delete(wrappe3r);
        // DELETE FROM bc_chaincode WHERE version = ?
        // Parameters: 1.3(String)
        // DELETE FROM bc_chaincode WHERE version = '1.3'

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("version", "1.4");
        bcChaincodeMapper.deleteByMap(objectMap);
        // DELETE FROM bc_chaincode WHERE version = '1.4'
    }

    @Test
    public void caseLock() { // 乐观锁 悲观锁

        //  乐观锁
        BcChaincode chaincode = bcChaincodeMapper.selectById(3);

        LambdaUpdateWrapper<BcChaincode> wrappe4r = new UpdateWrapper<BcChaincode>().lambda();

        wrappe4r.set(BcChaincode::getDateTest, new Date())
                .eq(BcChaincode::getId, chaincode.getId())
                .eq(BcChaincode::getDateTest, chaincode.getDateTest());

        int result = bcChaincodeMapper.update(new BcChaincode(), wrappe4r);

        //  悲观锁

    }

    @Test
    public void idWork() {

        System.out.println(IdWorker.getIdStr());
        System.out.println(IdWorker.getIdStr());
        System.out.println(IdWorker.get32UUID());
        System.out.println(IdWorker.get32UUID());

    }

    // map-underscore-to-camel-case: true
    @Test
    public void mapUnderScoreToCamelCase() {

        // 对象下划线转驼峰  map-underscore-to-camel-case=true
        // channel_id, date_test
        // 自动转换对象的  channelId  dateTest
        // (map类型不会自动转换)
        List<BcChaincode> test3 = bcChaincodeMapper.selectListTest();


        // 测试map 类型转换(test_type testType ) (不生效 要配置 MybatisMapWrapperFactory)
        Map<String, Object> test2 = bcChaincodeMapper.seletMapTest();
        List<Map<String, Object>> object = SqlRunner.DEFAULT.selectList("select 1 as test_type");

    }


}