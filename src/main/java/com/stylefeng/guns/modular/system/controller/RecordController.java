package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.annotion.log.BussinessLog;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.constant.Dict;
import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.constant.state.ManagerStatus;
import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.dao.RecordMapper;
import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.Record;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.transfer.UserDto;
import com.stylefeng.guns.modular.system.warpper.UserWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2018年09月29日 下午11:20:17
 */
@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {

    private static String PREFIX = "/system/record/";

    @Resource
    private GunsProperties gunsProperties;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RecordMapper recordMapper;

    /**
     * 跳转到查看记录列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "record.html";
    }

    /**
     * 跳转到添加页面
     */
    @RequestMapping("/record_add")
    public String addView() {
        return PREFIX + "record_add.html";
    }



    /**
     * 跳转到产品编辑页面@PathVariable Integer id,@PathVariable Integer userId,
     * ,@RequestParam("userId")Integer userId,
     */
    @Permission
    @RequestMapping("/record_edit/{id}/{userId}")
    public String userEdit(@PathVariable Integer id,@PathVariable Integer userId,Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //assertAuth(userId);
        //User user = userMapper.selectByPrimaryKey(userId);

        Record record = recordMapper.selectRecordById(id);
        model.addAttribute(record);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(record.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(record.getDeptid()));
        LogObjectHolder.me().set(record);
        return PREFIX + "record_edit.html";
    }




    /**
     * 查询产品列表
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String account, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid) {
        Integer userId = ShiroKit.getUser().getId();
        //User user = userMapper.selectByPrimaryKey(userId);
        //DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope(user));
        //dataScope
        List<Map<String, Object>> users = recordMapper.selectRecordInfo( account, beginTime, endTime, deptid,userId);
        return new UserWarpper(users).warp();
    }

    /**
     * 添加产品记录
     */
    @RequestMapping("/add")
    @Permission
    @ResponseBody
    public Tip add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        User theUser = userMapper.getByAccount(user.getAccount());

        if(theUser == null){

            throw new BussinessException(BizExceptionEnum.NO_THIS_USER);            //没有此用户
        }

        //User userInfo = new User();         //用户信息

        // 完善账号信息
        //user.setSalt(ShiroKit.getRandomSalt(5));
        //user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        //user.setStatus(ManagerStatus.OK.getCode());
        //user.setCreatetime(new Date());

        //userInfo =  UserFactory.createUser(user);           //转换成用户对象

        //userMapper.insertUserInfo(userInfo);

        user.setId(theUser.getId());        //用户编号

        insertRecordInfo(user);             //用户投资记录

        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改记录", key = "account", dict = Dict.UserDict)
    @ResponseBody
    public Tip edit(@Valid UserDto user, BindingResult result) throws NoPermissionException {

        if (result.hasErrors()) {

            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        //User userInfo = new User();
        //查询用户信息
        //User info = userMapper.get(user.getId());
        //userInfo = UserFactory.createUser(user);

        //userInfo.setId(user.getUserId());           //用户编号

        // 判断账号是否重复
        User theUser = userMapper.getByAccount(user.getAccount());

        if(theUser == null){

            throw new BussinessException(BizExceptionEnum.NO_THIS_USER);            //没有此用户
        }

        //user.setId(theUser.getId());        //用户编号

        modifyRecordInfo(user);             //用户投资记录

        //if (ShiroKit.hasRole(Const.ADMIN_NAME)) {

            //userMapper.modifyUserInfo(userInfo);

            //return SUCCESS_TIP;
       // } else {
            //assertAuth(user.getId());
          //  ShiroUser shiroUser = ShiroKit.getUser();
          //  if (shiroUser.getId().equals(user.getId())) {
                //userInfo = UserFactory.createUser(user);
              //  userMapper.modifyUserInfo(userInfo);
                //return SUCCESS_TIP;
          ///  } else {
                //throw new BussinessException(BizExceptionEnum.NO_PERMITION);
           // }
        //}

        //修改用户记录
       // modifyRecordInfo(user);

        return SUCCESS_TIP;
    }

    /**
     * 删除产品记录
     */
    @RequestMapping("/delete")
    @Permission
    @ResponseBody
    public Tip delete(@RequestParam Integer id,@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {

            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
       // if (userId.equals(Const.ADMIN_ID)) {
         //   throw new BussinessException(BizExceptionEnum.CANT_DELETE_ADMIN);
        //}
        //assertAuth(userId);
        //userMapper.setStatus(userId, ManagerStatus.DELETED.getCode());


        recordMapper.deleteRecordInfo(id);

        return SUCCESS_TIP;
    }



    /**
     * 添加用户信息
     */
    @RequestMapping(method = RequestMethod.POST, path = "/insertUser")
    @ResponseBody
    private Tip addUserInfo(User user) {

        Integer num = userMapper.insertUserInfo(user);

        if(num == 0){

            throw new BussinessException(BizExceptionEnum.USER_INSERT_ERROR);
        }
        return SUCCESS_TIP;
    }


    /**
     * 修改用户信息
     */
    @RequestMapping(method = RequestMethod.POST, path = "/modifyUser")
    @ResponseBody
    private Tip modifyUserInfo(User user) {

        Integer num = userMapper.modifyUserInfo(user);

        if(num == 0){

            throw new BussinessException(BizExceptionEnum.USER_MODIFY_ERROR);
        }
        return SUCCESS_TIP;
    }


    /**
     * 用户投资记录
     * @param user 用户对象
     * @param user
     */
    public void insertRecordInfo(UserDto user){

        Record record = new Record();

        //用户存在
        record.setUserId(user.getId());                     //用户编号
        record.setVersion(user.getVersion());               //保留字段
        record.setManagement(user.getManagement());         //管理费
        record.setEmployeeNum(user.getEmployeeNum());       //管理分成
        record.setMechanism(user.getMechanism());           //客户净含量
        record.setSalesagency(user.getSalesagency());       //销售机构
        record.setCreatetime(user.getCreatetime());         //开始时间
        record.setUpdatetime(user.getUpdatetime());         //修改时间
        record.setNums(user.getNums());                     //截止日期数量
        record.setCustomerType(user.getCustomerType());     //客户类型
        record.setEmail(user.getEmail());                   //产品代码
        record.setDeptid(user.getDeptid());                 //部门编号
        record.setSex(user.getSex());                       //产品类别

        recordMapper.insertRecordInfo(record);

    }


    /**
     * 用户投资记录
     * @param user 用户对象
     * @param user
     */
    public void modifyRecordInfo(UserDto user){

        Record record = new Record();

        //用户存在
        record.setId(user.getId());                         //编号
        record.setUserId(user.getUserId());                 //用户编号
        record.setVersion(user.getVersion());               //保留字段
        record.setManagement(user.getManagement());         //管理费
        record.setEmployeeNum(user.getEmployeeNum());       //管理分成
        record.setMechanism(user.getMechanism());           //客户净含量
        record.setSalesagency(user.getSalesagency());       //销售机构
        record.setCreatetime(user.getCreatetime());         //开始时间
        record.setUpdatetime(user.getUpdatetime());         //修改时间
        record.setNums(user.getNums());                     //截止日期数量
        record.setCustomerType(user.getCustomerType());     //客户类型
        record.setEmail(user.getEmail());                   //产品代码
        record.setDeptid(user.getDeptid());                 //部门编号
        record.setSex(user.getSex());                       //产品类别

        recordMapper.modifyRecordInfo(record);

    }




}
