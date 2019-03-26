# 说明
1. 数据库连接信息在application.properties文件中进行配置
2. Mybatis自动生成插件配置信息在generatorConfig.xml中进行配置.需要将classPathEntry和jdbcConnection根据实际情况进行修改.
3. 数据库目前涉及4个表:
   * data_record_2019xxxx 数据实体表
   * data_resource        数据内容表
   * code_platform        基础数据-客户表
   * code_app             基础数据-客户产品表
   其中data_record_2019xxxx表名不固定,为按天区分的表.
4. 因为目前审核通过\不通过\待审核的状态未确定,所以待需求明确后,需要修改ConstantUtil.java类中相关字段的值.
5. 





