package com.stylefeng.guns.common.persistence.model;

import javax.persistence.Table;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Table(name = "sys_user")
public class User extends Base {

    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String  avatar;
    /**
     * 账号
     */
    private String  account;
    /**
     * 密码
     */
    private String  password;
    /**
     * md5密码盐
     */
    private String  salt;
    /**
     * 名字
     */
    private String  name;
    /**
     * 生日
     */
    private String    birthday;
    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;
    /**
     * 电子邮件
     */
    private String  email;
    /**
     * 电话
     */
    private String  phone;
    /**
     * 角色id
     */
    private String  roleid;
    /**
     * 部门id
     */
    private Integer deptid;
    /**
     * 状态(1：启用  2：冻结  3：删除）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 保留字段
     */
    private Integer version;

    /**
     * 管理费
     */
    private String management;

    /**
     * 管理分成
     */
    private String employeeNum;

    /**
     * 客户净含量
     */
    private String mechanism;

    /**
     * 销售机构
     */
    private String salesagency;

    /**
     * 修改时间
     */
    private String updatetime;

    private String customerType;    //客户类型

    private String nums;			//截止日期数量



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar=" + avatar +
                ", account=" + account +
                ", password=" + password +
                ", salt=" + salt +
                ", name=" + name +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", email=" + email +
                ", phone=" + phone +
                ", roleid=" + roleid +
                ", deptid=" + deptid +
                ", status=" + status +
                ", createtime=" + createtime +
                ", version=" + version +
                ", management=" + management +
                ", employeeNum=" + employeeNum +
                ", mechanism=" + mechanism +
                ", salesagency=" + salesagency +
                ", updatetime=" + updatetime +
                ", customerType=" + customerType +
                "}";
    }
}
