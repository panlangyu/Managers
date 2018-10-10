package com.stylefeng.guns.common.persistence.model;


import javax.persistence.Table;

/**
 * <p>
 * 用户记录
 * </p>
 *
 * @author stylefeng
 * @since 2018-09-28
 */
@Table(name = "sys_record")
public class Record extends Base{


    private static final long serialVersionUID = 1L;

    //private Integer id;                 //编号

    private Integer userId;             //用户编号

    private String avatar;              //头像

    private String account;             //账号

    private String password;            //密码

    private String salt;                //md5密码盐

    private String name;                //名字

    private String phone;               //电话

    private String birthday;            //出生日期

    private String roleid;              //角色编号

    private Integer deptid;             //部门编号

    private Integer version;            //保留字段

    private String management;          //管理费

    private String employeeNum;         //管理分成

    private String mechanism;           //客户净含量

    private String salesagency;         //销售机构

    private String createtime;          //开始时间

    private String updatetime;          //修改时间

    private String nums;                //截止日期数量

    private String customerType;        //客户类型

    private String email;               //产品代码

    private int sex;                    //产品类别

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getSalesagency() {
        return salesagency;
    }

    public void setSalesagency(String salesagency) {
        this.salesagency = salesagency;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
