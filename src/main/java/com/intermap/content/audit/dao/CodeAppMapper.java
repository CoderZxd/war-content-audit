package com.intermap.content.audit.dao;

import com.intermap.content.audit.entity.CodeApp;

public interface CodeAppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_app
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_app
     *
     * @mbg.generated
     */
    int insert(CodeApp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_app
     *
     * @mbg.generated
     */
    int insertSelective(CodeApp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_app
     *
     * @mbg.generated
     */
    CodeApp selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_app
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CodeApp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_app
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CodeApp record);
}