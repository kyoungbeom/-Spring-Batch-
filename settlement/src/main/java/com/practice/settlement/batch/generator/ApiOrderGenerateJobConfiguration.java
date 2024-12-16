//package com.practice.settlement.batch.generator;
//
//import com.practice.settlement.domain.ApiOrder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.job.DefaultJobParametersValidator;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.PathResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class ApiOrderGenerateJobConfiguration {
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job apiOrderGenerateJob(Step step) {
//        return new JobBuilder("apiOrderGenerateJob", jobRepository)
//                .start(step)
//                .incrementer(new RunIdIncrementer())
//                .validator(new DefaultJobParametersValidator(
//                        new String[]{"targetDate", "totalCount"}, new String[0]
//                        )
//                )
//                .build();
//    }
//
//    @Bean
//    public Step apiOrderGenerateStep(
//            ApiOrderGenerateReader apiOrderGenerateReader,
//            ApiOrderGenerateProcessor apiOrderGenerateProcessor
//    ) {
//        return new StepBuilder("apiOrderGenerateStep", jobRepository)
//                // chunkSize를 설정할때는 성능과 안정성 둘을 고려하여 적절한 값을 선택해야한다.
//                // chunkSize가 커지면 성능은 좋아지겠지만, 그만큼 에러가 발생했을대 rollback 해야하는 데이터가 많아진다.
//                .<Boolean, ApiOrder>chunk(1000, platformTransactionManager)
//                .reader(apiOrderGenerateReader)
//                .processor(apiOrderGenerateProcessor)
//                .writer(apiOrderFlatFileItemWriter(null))
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemWriter<ApiOrder> apiOrderFlatFileItemWriter(
//            @Value("#{jobParameters['targetDate']}") String targetDate
//    ) {
//        final String fileName = targetDate + "_api_orders.csv";
//
//        return new FlatFileItemWriterBuilder<ApiOrder>()
//                .name("apiOrderGenerator")
//                .resource(new PathResource("src/main/resources/datas/" + fileName))
//                .delimited().delimiter(",")
//                .names("id", "customerId", "url", "state", "createdAt")
//                .headerCallback(writer -> writer.write("id,customerId,url,state,createdAt"))
//                .build();
//    }
//
//}
