package com.example.recommendersystem.service;

import com.example.recommendersystem.dao.UserListMapper;
import com.example.recommendersystem.dao.UserUserlistMapper;
import com.example.recommendersystem.dao.UsercMapper;
import com.example.recommendersystem.entity.UserList;
import com.example.recommendersystem.entity.UserUserlist;
import com.example.recommendersystem.entity.Userc;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserListService {
    @Autowired
    private UserListMapper userListMapper;
    @Autowired
    private UserUserlistMapper userUserlistMapper;
    @Autowired
    private UsercMapper usercMapper;

    @Transactional
    public void addUserList(String listName, Date date, List<String> ids){

        UserList userList = new UserList();
        userList.setName(listName);
        userList.setCreateTime(date);
        //建立用户集
        userListMapper.addUserList(userList);
        //取用户集id
        int listId = userList.getId();
        userUserlistMapper.addUserUserList(listId,ids);


//        //用户id集合中的每一个id和用户集id对应，存入用户用户集关联表中
//        for (String id:ids){
//            UserUserlist userUserlist = new UserUserlist();
//            userUserlist.setUserListId(listId);
//            userUserlist.setUserId(id);
//            userUserlistMapper.addUserUserList(userUserlist);
//        }
    }

    @Transactional
    public void createListByInfo(String name, String phone, List<Integer> serviceTypes,
                                 List<Integer> userTypes, String listName, Date date){
        //根据条件筛选用户
        List<Userc> usercList = usercMapper.getUsercList(name, phone, serviceTypes, userTypes,null,null);

        //如果筛选出的用户集合为空，不建立用户集
        if (CollectionUtils.isEmpty(usercList)){
            return;
        }

        //把所得用户id存入集合
        List<String> ids = new ArrayList<String>();
        for (Userc user:usercList){
            String id = user.getId();
            ids.add(id);
        }
        //复用前面根据id集合建立用户集的方法
        addUserList(listName,date,ids);
    }

    public PageResult getUserList(String listName, Date date1, Date date2, Integer page, Integer pageSize){
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setPageSize(pageSize);
        List<UserList> userLists = userListMapper.findUserLists(listName,date1,date2,(page-1)*pageSize,pageSize);
        int userListCount = userListMapper.countList(listName,date1,date2);
        pageResult.setResultList(userLists);
        pageResult.setMaxRow(userListCount%pageSize>0?userListCount/pageSize+1:userListCount/pageSize);
        pageResult.setCount(userListCount);

        return pageResult;
    }
}
