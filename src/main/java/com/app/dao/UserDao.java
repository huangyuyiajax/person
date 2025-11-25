package com.app.dao;

import com.app.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @description: TODO
 * @author huangyuyi
 * @date 2023/4/21 10:49
 * @version 1.0
 */

@Repository
public interface UserDao {

    List<User> selectUserList();
    Integer insertUser(User user);
    Integer insertBill(Bill bill);
    Integer insertLottery(Lottery lottery);
    Integer insertOrder(Order order);
    Integer insertOrderDetil(OrderDetil orderDetil);
    Integer updateBill(Bill bill);
    Integer deletBill(Bill bill);
    Integer clearData();
    List<Report> selectReport();
    List<OrderDetil> selectOrderDetilByOrderId(Integer orderId);
    List<OrderDetil> selectOrderDetilByOrderId2(Integer orderId);
}
