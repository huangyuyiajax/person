package com.app.service.ipml;

import com.app.dao.UserDao;
import com.app.model.*;
import com.app.service.UserService;
import com.app.util.CodeStringConverter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangyuyi
 */
@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectUserList(){
        return userDao.selectUserList();
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Synchronized
    public void saveUser(User user) throws Exception{
        userDao.insertUser(user);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Synchronized
    public void saveBill(User user) throws Exception{
        if(user.getBillId()==null&&StringUtils.isNotEmpty(user.getTest())){
            Bill bill = new Bill();
            bill.setTest(user.getTest());
            bill.setUserId(user.getId());
            userDao.insertBill(bill);
        }else {
            if(StringUtils.isEmpty(user.getTest())){
                Bill bill = new Bill();
                bill.setId(user.getBillId());
                userDao.deletBill(bill);
            }else {
                Bill bill = new Bill();
                bill.setTest(user.getTest());
                bill.setId(user.getBillId());
                userDao.updateBill(bill);
            }
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Synchronized
    public void saveLottery(Lottery lottery) throws Exception{
        if(lottery==null){
            throw new Exception("没有数据");
        }
        userDao.clearData();
        userDao.insertLottery(lottery);
        List<User> list = selectUserList();
        for(User user:list){
            String test = user.getTest();
            if(StringUtils.isNotEmpty(test)){
                Order order = new Order();
                order.setUserId(user.getId());
                order.setLotteryId(lottery.getId());
                userDao.insertOrder(order);
                List<String[]> results = CodeStringConverter.processCodeString(test);
                for (String[] bet : results) {
                    OrderDetil orderDetil = new OrderDetil();
                    orderDetil.setOrderId(order.getId());
                    orderDetil.setCode(bet[0]);
                    orderDetil.setPrice(new BigDecimal(bet[1]));
                    userDao.insertOrderDetil(orderDetil);
                }
            }
        }

    }

    @Override
    public List<Report> selectReport(){
        List<Report> list = userDao.selectReport();
        list.forEach(e-> {
            List<OrderDetil> orderDetils = userDao.selectOrderDetilByOrderId(e.getId());
            orderDetils.forEach(orderDetil -> {
                orderDetil.setName(CodeStringConverter.getShangxiaoValueJson(orderDetil.getCode()));
            });
            e.setList(orderDetils);
            List<OrderDetil> orderDetils2 = userDao.selectOrderDetilByOrderId2(e.getId());
            orderDetils2.forEach(orderDetil -> {
                orderDetil.setName(CodeStringConverter.getShangxiaoValueJson(orderDetil.getCode()));
            });
            e.setList2(orderDetils2);
        });
        return list;
    }


}
