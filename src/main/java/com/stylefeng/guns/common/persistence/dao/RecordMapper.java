package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.Record;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.core.datascope.DataScope;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户投资记录
 */
public interface RecordMapper extends Mapper<User> {



    /**
     * 根据条件查询用户列表
     *@Param("dataScope") DataScope dataScope,
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectRecordInfo( @Param("name") String name, @Param("beginTime") String beginTime,
                                          @Param("endTime") String endTime, @Param("deptid") Integer deptid,
                                                @Param("id") Integer id,@Param("sex") Integer sex);

    /** 添加用户投资记录 **/
    Integer insertRecordInfo(Record record);

    /**
     * 查询单条用户投资记录
     * @param id
     * @return
     */
    Record selectRecordById(@Param("id")Integer id);

    /**
     * 修改用户投资记录
     * @param record
     * @return
     */
    Integer modifyRecordInfo(Record record);

    /**
     * 删除记录
     * @param id
     * @return
     */
    Integer deleteRecordInfo(@Param("id")Integer id);


}
