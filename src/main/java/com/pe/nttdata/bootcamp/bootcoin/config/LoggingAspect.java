package com.pe.nttdata.bootcamp.bootcoin.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.nttdata.bootcamp.bootcoin.commons.AspectEnum;
import com.pe.nttdata.bootcamp.bootcoin.dto.AppProcesoLogDto;
import com.pe.nttdata.bootcamp.bootcoin.util.DateUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *Implement LoggingAspect. <br/>
 *<b>Class</b>: {@link LoggingAspect}<br/>
 *<b>Copyright</b>: &Copy; 2024 NTTDATA Per&uacute;. <br/>
 *<b>Company</b>: NTTDATA del Per&uacute;. <br/>
 *
 *@author NTTDATA Per&uacute;. (EVE) <br/>
 *<u>Developed by</u>: <br/>
 *<ul>
 *<li>Hugo Oliveros Monti</li>
 *</ul>
 *<u>Changes</u>:<br/>
 *<ul>
 *<li>Mar. 7, 2024 (acronym) Creation class.</li>
 *</ul>
 *@version 1.0
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Value("${user.aplicationName}")
  private String aplicatiobName;

  private final List<Object> listaParametros = new ArrayList<>();

  private AppProcesoLogDto logSystem = AppProcesoLogDto.builder().build();

  /**
   * <p/>
   * Capture parameter of controller endpoint.
   *
   * @param joinPoint {@link ProceedingJoinPoint}
   * @see ProceedingJoinPoint
   */
  @Around(value = "execution(* com.pe.nttdata.bootcamp.bootcoin22.expose.web..*(..))")
  public Object aroundAdvice(ProceedingJoinPoint joinPoint) {

    listaParametros.clear();
    logSystem = AppProcesoLogDto.builder().build();

    try {
      Arrays.asList(joinPoint.getArgs()).forEach(req -> {
        if (!(req instanceof BindingResult
                || req instanceof UriComponentsBuilder)) {
          listaParametros.add(req);
        }
      });

      if (!listaParametros.isEmpty()) {
        ObjectMapper mapperEntrada = new ObjectMapper();
        mapperEntrada.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String parametros = mapperEntrada
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(listaParametros);
        if (parametros.length() >= 3950) {
          parametros = parametros.substring(0, 3950);
        }


        logSystem.setParametroEntrada(String.format("\u001B[33m%s\u001B[0m",parametros));
      }

    } catch (Exception e) {
      log.error("\u001B[31m Log logExecutionTime - exception  {} \u001B[0m", e.getMessage());
      log.error("\u001B[31m Log logExecutionTime - exception  list parameter: {} \u001B[0m", listaParametros);
      logSystem.setParametroEntrada(null);
    }

    logSystem.setFechaInicioEjecucion(new java.util.Date());
    logSystem.setModulo(aplicatiobName);
    try {

      if (joinPoint.proceed() instanceof Mono) { //if Mono
        return Mono.from(((Mono<?>) joinPoint.proceed())
                .doOnNext(response -> getProcees(joinPoint, response)));

      } else { //if Flux
        return Flux.from(((Flux<?>) joinPoint.proceed())
                .doOnNext(response -> getProcees(joinPoint, response)));

      }

    } catch (Exception e) {
      log.error("\u001B[31m Log logExecutionTime - exception {} \u001B[0m,", e.getMessage());
    } catch (Throwable e) {
      log.error("\u001B[31m Log logExecutionTime - ** endProcess Error {} ** \u001B[0m", logSystem.toString());
    }

    return null;

  }


  private void getProcees(ProceedingJoinPoint joinPoint, Object response) {
    final long start = System.currentTimeMillis();

    final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    final Method method = signature.getMethod();
    logSystem.setClaseProgramacion(signature.getDeclaringType().getSimpleName());
    logSystem.setMetodoProgramacion(method.getName());
    String claseHija = joinPoint.getTarget().getClass().getSimpleName();
    if (Optional.ofNullable(claseHija).isPresent()) {
      logSystem.setClaseProgramacion(claseHija);
    }
    logSystem.setDescripcionEstadoEjecucion(AspectEnum.MENSAJEOK.value());
    logSystem.setEstadoEjecucion(AspectEnum.EXITO.getValue());
    logSystem.setFechaFinEjecucion(DateUtils.obtenerFechaHoraActual());

    final long executionTime = System.currentTimeMillis() - start;
    logSystem.setDuracionMs((int) executionTime);
    logSystem.setResultadoSalida(String.format("\u001B[32m%s\u001B[0m",response.toString()));
    log.info("\u001B[36mLog Execution=>\u001B[36m{} \u001B[33m** \u001B[0m",
            logSystem.toString().replaceAll("AppProcesoLogDto",""));

  }


}
