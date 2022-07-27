package id.co.nds.gadai_2022_07_04.schedulers;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import id.co.nds.gadai_2022_07_04.repos.ParamRepo;
import id.co.nds.gadai_2022_07_04.services.TrxCicilanTetapService;

// @Component
public class CronScheduler {
    @Autowired
    private ParamRepo paramRepo;

    @Autowired
   TrxCicilanTetapService cicilanService;


    static final Logger logger = LogManager.getLogger(CronScheduler.class);
    Integer counterB = 0;

    // @Scheduled(cron = "0 0 * * *") //every 10 seconds
    public void cronSchedule() throws Exception {
        Integer counterA = 0;
        logger.debug("Start Cron at " + Calendar.getInstance().getTime());
        logger.info("Counter-A: " + counterA);
        logger.info("Counter-B: " + counterB);
        counterA++;
        counterB++;
        cicilanService.doCheckCicStatus();
        cicilanService.doHitungDenda();

    }

    // @Scheduled(cron = "${param.scheduler.cron}") //every 10 seconds
    public void cronParamSchedule() throws Exception {
        Integer counterA = 0;
        logger.debug("Start CronScheduler at " + Calendar.getInstance().getTime());
        logger.info("Counter-A: " + counterA);
        logger.info("Counter-B: " + counterB);
        counterA++;
        counterB++;
        cicilanService.doCheckCicStatus();
        cicilanService.doHitungDenda();

    }

}