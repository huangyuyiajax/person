package com.app.service;

import com.app.model.Lottery;
import com.app.model.Report;
import com.app.model.User;

import java.util.List;
/**
 * @description: TODO
 * @author huangyuyi
 * @date 2023/4/21 10:49
 * @version 1.0
 */

@SuppressWarnings("ALL")
public interface UserService {
    List<User> selectUserList();
    void saveUser(User user) throws Exception;
    void saveBill(User user) throws Exception;
    void saveLottery(Lottery lottery) throws Exception;
    List<Report> selectReport();

}
