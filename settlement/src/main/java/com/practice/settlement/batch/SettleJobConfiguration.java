package com.practice.settlement.batch;

import com.practice.settlement.batch.support.DateFormatJobParametersValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class SettleJobConfiguration {

    private final JobRepository jobRepository;

    // 일일 정산 배치
    @Bean
    public Job settleJob(
            Step preSettleDetailStep,
            Step settleDetailStep,
            Step settleGroupStep
    ) {
        return new JobBuilder("settleJob", jobRepository)
                .validator(new DateFormatJobParametersValidator(new String[]{"targetDate"}))
                .start(preSettleDetailStep)
                .next(settleDetailStep)
                // 주간 정산을 실행하는 날이면
                .next(isFridayDecider())
                // 주간 정산을 실시
                .on("COMPLETED").to(settleGroupStep)
                .build()
                .build();
    }

    // 주간 정산 배치
    public JobExecutionDecider isFridayDecider() {
        return new JobExecutionDecider() {
            @Override
            public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                final String targetDate = stepExecution.getJobParameters().getString("targetDate");
                final LocalDate date = LocalDate.parse(targetDate, formatter);

                if(date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    return new FlowExecutionStatus("NOOP");
                }

                return FlowExecutionStatus.COMPLETED;
            }
        };
    }

}
