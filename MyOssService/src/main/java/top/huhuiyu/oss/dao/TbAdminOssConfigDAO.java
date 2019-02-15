package top.huhuiyu.oss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.oss.entity.TbAdminOssConfig;

/**
 * TbAdminOssConfig表的dao
 * 
 * @author 胡辉煜
 */
@Mapper
public interface TbAdminOssConfigDAO {
  /**
   * 查询全部TbAdminOssConfig
   *
   * @return TbAdminOssConfig的信息
   * @throws Exception
   *                   处理发生异常
   */
  List<TbAdminOssConfig> queryAll() throws Exception;

  /**
   * 按照主键查询TbAdminOssConfig
   *
   * @param tbAdminOssConfig主键信息
   * @return 主键查询TbAdminOssConfig的结果
   * @throws Exception
   *                   处理发生异常
   */
  TbAdminOssConfig queryByKey(TbAdminOssConfig tbAdminOssConfig) throws Exception;

  /**
   * 添加TbAdminOssConfig信息
   *
   * @param tbAdminOssConfig信息
   * @return 添加tbAdminOssConfig信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int add(TbAdminOssConfig tbAdminOssConfig) throws Exception;

  /**
   * 修改TbAdminOssConfig信息
   *
   * @param tbAdminOssConfig信息
   * @return 修改tbAdminOssConfig信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int update(TbAdminOssConfig tbAdminOssConfig) throws Exception;

  /**
   * 删除TbAdminOssConfig信息
   *
   * @param tbAdminOssConfig信息
   * @return 删除tbAdminOssConfig信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int delete(TbAdminOssConfig tbAdminOssConfig) throws Exception;

}
