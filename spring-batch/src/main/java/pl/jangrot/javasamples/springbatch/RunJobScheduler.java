package pl.jangrot.javasamples.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RunJobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunJobScheduler.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    private static Long RUN_ID = 1L;

    @Scheduled(fixedRate = 15000)
    public void runJob() {
        try {
            jobLauncher.run(job, jobParameters());
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }
    }

    private JobParameters jobParameters() {
        return new JobParametersBuilder().addLong("run.id", RUN_ID++).toJobParameters();
    }
}
