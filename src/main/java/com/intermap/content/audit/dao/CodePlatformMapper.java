package com.intermap.content.audit.dao;

import com.intermap.content.audit.entity.CodePlatform;

public interface CodePlatformMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_platform
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_platform
     *
     * @mbg.generated
     */
    int insert(CodePlatform record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_platform
     *
     * @mbg.generated
     */
    int insertSelective(CodePlatform record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_platform
     *
     * @mbg.generated
     */
    CodePlatform selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_platform
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CodePlatform record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table code_platform
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CodePlatform record);
}