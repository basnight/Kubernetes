import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import configuration from './config/configuration';
import { AppController } from './app.controller';
import { ConfigModule } from '@nestjs/config';
import { TerminusModule } from '@nestjs/terminus';
import { CheckoutModule } from './checkout/checkout.module';
import { LoggerMiddleware } from './middleware/logger.middleware';
import { PrometheusModule } from '@willsoto/nestjs-prometheus';
import { OpenTelemetryModule } from 'nestjs-otel';

const OpenTelemetryModuleConfig = OpenTelemetryModule.forRoot({
  
});

@Module({
  imports: [
    ConfigModule.forRoot({
      load: [configuration]
    }),
    TerminusModule,
    PrometheusModule.register(),
    CheckoutModule,
    OpenTelemetryModuleConfig,
  ],
  controllers: [AppController],
  providers: [],
})
export class AppModule implements NestModule {
  configure(consumer: MiddlewareConsumer) {
    consumer.apply(LoggerMiddleware)
      .exclude('health')
      .forRoutes('*');
  }
}
