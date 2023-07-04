package io.beanie.frontendapi.config

//@Configuration
//class WebConfig {
//    @Bean
//    fun webClient(): WebClient {
//        val httpClient = HttpClient.create()
//            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5_000)
//            .responseTimeout(Duration.ofMillis(5_000))
//            .doOnConnected { conn ->
//                conn.addHandlerLast(ReadTimeoutHandler(5_000, TimeUnit.MILLISECONDS))
//                    .addHandlerLast(WriteTimeoutHandler(5_000, TimeUnit.MILLISECONDS))
//            }
//
//        return WebClient.builder()
//            .clientConnector(ReactorClientHttpConnector(httpClient))
//            .build()
//    }
//}