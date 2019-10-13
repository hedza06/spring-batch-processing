# Spring Batch Processing
Spring Batch is a lightweight framework designed to enable robust batch processing.
It provides advanced features that support high performance batch jobs.

Here is the flow of batch processing:  
![alt text](add link here)

## Integration with Spring Boot
Integration with Spring Boot framework is very intuitive. You just need to add
new dependency to your `pom.xml` like this:  
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-batch</artifactId>
</dependency>
```
and to add annotation `@EnableBatchProcessing` on your main class to enable features 
that comes with this framework (library).

## Spring Boot YAML Configuration
After adding **spring-boot-starter-batch** as your dependency you should 
set config param in your `application.yml` => `spring.batch.initialize-schema: always`
which will initialize batch schema on project startup.

## DB Schema Explanation
Spring batch comes with initial schema which contains 6 tables:
- BATCH_JOB_EXECUTION
- BATCH_JOB_EXECUTION_CONTEXT
- BATCH_JOB_EXECUTION_PARAMS
- BATCH_JOB_INSTANCE
- BATCH_STEP_EXECUTION
- BATCH_STEP_EXECUTION_CONTEXT

### ER Diagram of batch schema
![alt text](add link here)

### BATCH_JOB_EXECUTION
The BATCH_JOB_EXECUTION table holds all information relevant to the JobExecution object. 
Every time a Job is run there will always be a new `JobExecution`.

### BATCH_JOB_EXECUTION_CONTEXT
The BATCH_STEP_EXECUTION_CONTEXT table holds all information relevant to an Step's `ExecutionContext`. 
There is exactly one `ExecutionContext` per `StepExecution`, and it contains all of the data that needs to persisted 
for a particular step execution. This data typically represents the state that must be retrieved after a failure so 
that a `JobInstance` can start from where it left off.

### BATCH_JOB_EXECUTION_PARAMS
The BATCH_JOB_EXECUTION_PARAMS table holds all information relevant to the `JobParameters` object.
It contains 0 or more key/value pairs passed to a Job and serve as a record of the parameters a job was run with. 
For each parameter that contributes to the generation of a job's identity, the IDENTIFYING flag is set to true. 
It should be noted that the table has been denormalized.

### BATCH_JOB_INSTANCE
The BATCH_JOB_INSTANCE table holds all information relevant to a `JobInstance`, 
and serves as the top of the overall hierarchy.

### BATCH_STEP_EXECUTION
The BATCH_STEP_EXECUTION table holds all information relevant to the `StepExecution` object. 
This table is very similar in many ways to the BATCH_JOB_EXECUTION table and there will always be at least one 
entry per Step for each `JobExecution` created.

### BATCH_STEP_EXECUTION_CONTEXT
The BATCH_JOB_EXECUTION_CONTEXT table holds all information relevant to an Job's ExecutionContext. There is exactly 
one Job ExecutionContext per `JobExecution`, and it contains all of the job-level data that is needed for a particular 
job execution. This data typically represents the state that must be retrieved after a failure so that a `JobInstance` 
can start from where it left off.

## Contribution
If someone is interesting in contribution please contact me on e-mail ```hedzaprog@gmail.com```. 
There will be more interesting things to come for Spring Boot especially.

## Author
Heril Muratović   
Software Engineer  
<br>
**Mobile**: +38269657962  
**E-mail**: hedzaprog@gmail.com  
**Skype**: hedza06  
**Twitter**: hedzakirk  
**LinkedIn**: https://www.linkedin.com/in/heril-muratovi%C4%87-021097132/  
**StackOverflow**: https://stackoverflow.com/users/4078505/heril-muratovic