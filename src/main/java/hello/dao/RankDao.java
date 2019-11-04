package hello.dao;

import hello.entity.RankItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankDao {
    @Autowired
    private SqlSession sqlsession;
    // 排行榜
    public List<RankItem> getRank(){
        // 这里进行数据库查询

        return sqlsession.selectList("MyMapper.selectRank");
    }
}
