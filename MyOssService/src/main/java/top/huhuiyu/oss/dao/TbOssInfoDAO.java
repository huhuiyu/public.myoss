package top.huhuiyu.oss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.oss.entity.TbOssInfo;

/**
 * TbOssInfo表的dao
 * 
 * @author 胡辉煜
 */
@Mapper
public interface TbOssInfoDAO {

  /**
   * 查询ocid的记录数量
   * 
   * @param ossInfo
   *                查询参数
   * @return ocid的记录数量
   * @throws Exception
   *                   处理发生异常
   */
  int queryCountByOcid(TbOssInfo ossInfo) throws Exception;

  /**
   * 查询全部TbOssInfo
   *
   * @param ossInfo
   *                查询参数
   * @return TbOssInfo的信息
   * @throws Exception
   *                   处理发生异常
   */
  List<TbOssInfo> queryAll(TbOssInfo ossInfo) throws Exception;

  /**
   * 按照主键查询TbOssInfo
   *
   * @param tbOssInfo主键信息
   * @return 主键查询TbOssInfo的结果
   * @throws Exception
   *                   处理发生异常
   */
  TbOssInfo queryByKey(TbOssInfo tbOssInfo) throws Exception;

  /**
   * 按照objectName查询TbOssInfo
   * 
   * @param tbOssInfo
   *                     查询参数
   * @param objectName信息
   * @return objectName查询TbOssInfo的结果
   * @throws Exception
   *                   处理发生异常
   */
  TbOssInfo queryByObjectName(TbOssInfo tbOssInfo) throws Exception;

  /**
   * 添加TbOssInfo信息
   *
   * @param tbOssInfo信息
   * @return 添加tbOssInfo信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int add(TbOssInfo tbOssInfo) throws Exception;

  /**
   * 修改TbOssInfo信息
   *
   * @param tbOssInfo信息
   * @return 修改tbOssInfo信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int update(TbOssInfo tbOssInfo) throws Exception;

  /**
   * 删除TbOssInfo信息
   *
   * @param tbOssInfo信息
   * @return 删除tbOssInfo信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int delete(TbOssInfo tbOssInfo) throws Exception;

}
