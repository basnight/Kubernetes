import otelSDK from './tracing';
import { ValidationPipe } from '@nestjs/common';
import { NestFactory } from '@nestjs/core';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';

async function bootstrap() {
  // Start SDK before nestjs factory create
  await otelSDK.start();
  
  const app = await NestFactory.create(AppModule);
  app.useGlobalPipes(new ValidationPipe());

  const config = new DocumentBuilder()
    .setTitle('Checkout service')
    .setDescription('The checkout API')
    .setVersion('1.0')
    .addTag('checkout')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api', app, document);

  // Starts listening for shutdown hooks
  app.enableShutdownHooks();

  const port = process.env.PORT || 8080

  await app.listen(port);
}
bootstrap();
