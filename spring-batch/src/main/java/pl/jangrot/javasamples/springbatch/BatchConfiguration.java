package pl.jangrot.javasamples.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcPagingItemReader<Person> reader() throws Exception {
        JdbcPagingItemReader<Person> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setQueryProvider(queryProvider());
        reader.setRowMapper(rowMapper());
        return reader;
    }

    @Bean
    public PersonProcessor processor() {
        return new PersonProcessor();
    }

    @Bean
    public FlatFileItemWriter<Person> writer() {
        FlatFileItemWriter<Person> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("import_from_person.csv"));
        writer.setLineAggregator(lineAggregator());
        return writer;
    }

    @Bean
    public Job importPersonJob() throws Exception {
        return jobBuilderFactory.get("importPersonJob")
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() throws Exception {
        return stepBuilderFactory.get("step")
                .<Person, Person>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    private RowMapper<Person> rowMapper() {
        return (rs, rowNum) -> {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setSurname(rs.getString("surname"));
            return person;
        };
    }


    private PagingQueryProvider queryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean providerFactoryBean = new SqlPagingQueryProviderFactoryBean();
        providerFactoryBean.setSelectClause("select id, name, surname");
        providerFactoryBean.setFromClause("from person");
        providerFactoryBean.setSortKey("id");
        providerFactoryBean.setDataSource(dataSource);
        return providerFactoryBean.getObject();
    }

    private DelimitedLineAggregator lineAggregator() {
        DelimitedLineAggregator delimitedLineAggregator = new DelimitedLineAggregator();
        delimitedLineAggregator.setDelimiter(",");
        BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"name", "surname"});
        delimitedLineAggregator.setFieldExtractor(fieldExtractor);
        return delimitedLineAggregator;
    }
}