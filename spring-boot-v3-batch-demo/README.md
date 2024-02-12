# Spring Batch 5 for Boot v3

<br>

## DataSource

- multi datasource config

spring batch에 대한 datasource bean은 @Primary로 설정필요  
data 관련(jpa, jdbc) datasource 따로 설정

<br>

## Doc

### JpaPagingItemReader

```kotlin
private fun itemReader(): ItemReader<Cafe> {
    return JpaPagingItemReaderBuilder<Cafe>()
        .name("itemReader")
        .entityManagerFactory(entityManagerFactory)
        .queryString("SELECT c FROM Cafe c")
        .pageSize(100)
        .build()
        .also {
            it.afterPropertiesSet()
        }
    }
```
전체 데이터를 offset, limit을 사용해 조회  
(전체 데이터를 limit 100개 씩 조회) < **성능에 안 좋을 것 같아서 사용에 주의** 