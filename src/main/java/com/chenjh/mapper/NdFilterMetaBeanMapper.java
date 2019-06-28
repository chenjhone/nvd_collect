package com.chenjh.mapper;

import com.chenjh.domain.nvd.NdFilterMetaBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NdFilterMetaBeanMapper {
    /**
     * 保存
     * @param ndFilterMetaBean
     *
     * */
    int saveNdFilterMeta(NdFilterMetaBean ndFilterMetaBean);
    /**
     * 修改
     * @param ndFilterMetaBean
     *
     * */
    int updateNdFilterMeta(@Param("bean") NdFilterMetaBean ndFilterMetaBean);
    /**
     * 删除
     * @param ndFilterMetaBean
     *
     * */
    int deleteNdFilterMeta(@Param("bean") NdFilterMetaBean ndFilterMetaBean);
    /**
     *
     * @param ndFilterMetaBean
     *
     * */
    List<NdFilterMetaBean> listNdFilterMeta(NdFilterMetaBean ndFilterMetaBean);

    /**
     *
     * @param feed
     * @param shaDense
     *
     * */
    NdFilterMetaBean findNdFilterMeta(@Param("feed") String feed,@Param("shaDense") String shaDense);
}
