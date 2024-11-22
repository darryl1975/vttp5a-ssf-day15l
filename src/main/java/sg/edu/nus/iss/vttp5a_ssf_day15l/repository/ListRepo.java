package sg.edu.nus.iss.vttp5a_ssf_day15l.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5a_ssf_day15l.utility.Util;

@Repository
public class ListRepo {

    @Autowired
    @Qualifier(Util.template02)
    RedisTemplate<String, Object> template;

    // slide 30, slide 34
    public void leftPush(String key, String value) {
        template.opsForList().leftPush(key, value);
    }
    
    public void rightPush(String key, String value) {
        template.opsForList().rightPush(key, value);
    }

    // slide 30
    public void leftPop(String key) {
        template.opsForList().leftPop(key, 1);
    }
    
    public void rightPop(String key) {
        template.opsForList().rightPush(key, 1);
    }

    // slide 32
    public String get(String key, Integer index) {
        return template.opsForList().index(key, index).toString();
    }

    // slide 33
    public Long size(String key) {
        return template.opsForList().size(key);
    }

    public List<Object> getList(String key) {
        List<Object> list = template.opsForList().range(key, 0, -1);

        return list;
    }

    public Boolean deleteItem(String key, String valueToDelete) {
        Boolean isDeleted = false;

        Long iFound = template.opsForList().indexOf(key, valueToDelete);

        if (iFound >= 0) {
            template.opsForList().remove(key, 1, valueToDelete);
            isDeleted = true;
        }

        return isDeleted;
    }
}
