package hello;

import hello.dao.UserMapper;
import hello.entity.RankItem;
import hello.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RankService rankService;

    @RequestMapping("/")
    @ResponseBody
    public Object index() {
        return userMapper.getUserById(1);
    }

    @RequestMapping("/rank")
    @ResponseBody
    public Object rank() {
        return rankService.doService();
    }

    @RequestMapping("/rank2")
    public ModelAndView rank2() {
        List<RankItem> items = rankService.doService();
        HashMap<String,Object> model = new HashMap<>();
        model.put("items",items);
        // 自动去 templates目录找 index.ftl
        return new ModelAndView("index",model);
    }



    @RequestMapping("/search")
    public String search(@RequestParam String q) {
        //  localhost:8080/search?q=123 时
        System.out.println(q);
        return "search query word is:" + q;
    }

    @RequestMapping("/search2")
    public String search2(@RequestParam String q ,
                          @RequestParam(value="charset",required = false) String charset) {

        return "search query word is:" + q + ",and charset is :" + charset;
    }







}