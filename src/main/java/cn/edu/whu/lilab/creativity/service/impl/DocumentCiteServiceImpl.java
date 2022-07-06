package cn.edu.whu.lilab.creativity.service.impl;

import cn.edu.whu.lilab.creativity.domain.DocumentCite;
import cn.edu.whu.lilab.creativity.dto.CiteRelationPaperDto;
import cn.edu.whu.lilab.creativity.enums.OrderType;
import cn.edu.whu.lilab.creativity.mapper.DocumentCiteMapper;
import cn.edu.whu.lilab.creativity.service.DocumentCiteService;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service
public class DocumentCiteServiceImpl extends ServiceImpl<DocumentCiteMapper, DocumentCite> implements DocumentCiteService {

    @Autowired
    private DocumentCiteMapper documentCiteMapper;

    /**
     * 分页获取指定PMID的参考文献列表，按指定类型排序
     *
     * @param pmid
     * @param page
     * @param orderType
     * @return
     */
    @Override
    public Page<CiteRelationPaperDto> findRefById(String pmid, Page<CiteRelationPaperDto> page, String orderType) {

        switch (orderType) {
            case "year":
                page.addOrder(OrderItem.desc(OrderType.PUBLICATION_DATE.getCode()));
                break;
            case "cite_count":
                page.addOrder(OrderItem.desc(OrderType.CITE_COUNT.getCode()));
                break;
            default: //默认创新性指数排序
                page.addOrder(OrderItem.desc(OrderType.CREATIVITY_INDEX.getCode()));
        }

        return documentCiteMapper.getRefListById(page, pmid);
    }

    /**
     * 分页获取指定PMID的引证文献列表，按指定类型排序
     *
     * @param pmid
     * @param page
     * @param orderType
     * @return
     */
    @Override
    public Page<CiteRelationPaperDto> findCitingById(String pmid, Page<CiteRelationPaperDto> page, String orderType) {
        switch (orderType) {
            case "year":
                page.addOrder(OrderItem.desc(OrderType.PUBLICATION_DATE.getCode()));
                break;
            case "cite_count":
                page.addOrder(OrderItem.desc(OrderType.CITE_COUNT.getCode()));
                break;
            default: //默认创新性指数排序
                page.addOrder(OrderItem.desc(OrderType.CREATIVITY_INDEX.getCode()));
        }

        return documentCiteMapper.getCitingListById(page, pmid);
    }
}





