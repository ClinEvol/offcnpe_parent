package com.ujiuye.job;

import com.ujiuye.utils.util.RedisConstant;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Set;

@Component
public class CleanImagesJob {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Scheduled(cron = "0 05 11 ? * *")
    public void cleanImagesJob() {
        Set<String> difference = redisTemplate.opsForSet().difference(RedisConstant.SETMEAL_PIC_UPLOAD, RedisConstant.SETMEAL_PIC_DB);
        if(difference!=null && difference.size()>0){//有垃圾图片
            for (String img : difference) {
                File file=new File("D:/upload/"+img);
                file.delete();
            }
        }
        redisTemplate.delete(RedisConstant.SETMEAL_PIC_UPLOAD);
        redisTemplate.delete(RedisConstant.SETMEAL_PIC_DB);
    }

}
