package com.intermap.content.audit.dao;

import com.intermap.content.audit.entity.DataRecord;

public interface DataRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_record_20190318
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long dataId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_record_20190318
     *
     * @mbg.generated
     */
    int insert(DataRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_record_20190318
     *
     * @mbg.generated
     */
    int insertSelective(DataRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_record_20190318
     *
     * @mbg.generated
     */
    DataRecord selectByPrimaryKey(Long dataId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_record_20190318
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DataRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_record_20190318
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DataRecord record);
}