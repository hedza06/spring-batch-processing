package com.springdemo.batch.config;

import com.springdemo.batch.dao.ProductCSVMapper;
import com.springdemo.batch.entities.Product;
import com.springdemo.batch.processors.ProductItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
@Description("Product Job Configuration")
public class ProductJobConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;


    @Bean
    public Job productJob()
    {
        return jobBuilders
            .get("productJob")
            .start(taskStep())
            .next(chunkStep())
            .build();
    }

    @Bean
    public Step taskStep()
    {
        return stepBuilders
            .get("taskStep")
            .tasklet(tasklet())
            .build();
    }

    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> RepeatStatus.FINISHED;
    }

    @Bean
    public Step chunkStep()
    {
        return stepBuilders.get("chunkStep")
            .<ProductCSVMapper, Product>chunk(10)
            .reader(reader())
            .processor(productCSVProcessor())
            .writer(productCSVWriter())
            .build();
    }

    @Bean
    public FlatFileItemReader<ProductCSVMapper> reader()
    {
        FlatFileItemReader<ProductCSVMapper> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("products.csv"));
        reader.setLinesToSkip(1); // skip file headers
        reader.setLineMapper(new DefaultLineMapper<ProductCSVMapper>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "description", "productType", "isMain", "createdAt");
                setDelimiter(";");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ProductCSVMapper>() {{
                setTargetType(ProductCSVMapper.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<ProductCSVMapper, Product> productCSVProcessor() {
        return new ProductItemProcessor();
    }

    @Bean
    public ItemWriter<Product> productCSVWriter()
    {
        JpaItemWriter<Product> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);

        return itemWriter;
    }
}
