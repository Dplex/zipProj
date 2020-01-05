package com.example.zip.core.aop

import com.example.zip.`interface`.controller.ApartController
import com.example.zip.core.type.ServiceConst
import com.example.zip.util.GsonUtil
import com.example.zip.util.LoggerUtil
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.apache.commons.lang3.time.DurationFormatUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.util.MultiValueMap
import java.lang.Exception
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis
//
@Configuration
@Aspect
class PointCut(
//    @Autowired private val headerCheckComponent: HeaderCheckComponent
    ) {
//    @Around("@annotation(headerCheck) && args(transaction, headers, ..)")
//    fun headerCheck(proceedingJoinPoint: ProceedingJoinPoint,
//                    transaction: Transaction,
//                    headerCheck: HeaderCheck,
//                    headers: MultiValueMap<String, String>): Any? {
//        headerCheckComponent.errorChk(headers, headerCheck.url)
//        transaction._userId = headers.getFirst("x-smgl-id") ?: "AP"
//        transaction.traceId = RandomUtil.generatedUuid()
//        return proceedingJoinPoint.proceed()
//    }
    @Around("execution (public * com.example.zip.interface.controller.*.*(..))")
    fun controllerMethodLogging(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        return aopFunc(proceedingJoinPoint, ServiceConst.LogType.API)
    }
    @Around("execution(public * com.example.zip.service.*.*(..))")
    fun serviceMethodLogging(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        return aopFunc(proceedingJoinPoint, ServiceConst.LogType.SERVICE)
    }
    @Around("execution(public * com.example.zip.repo.mapper.*.*(..))")
    fun repositoryMethodLogging(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        return aopFunc(proceedingJoinPoint, ServiceConst.LogType.DB)
    }

    private fun aopFunc(
        proceedingJoinPoint: ProceedingJoinPoint, logType: ServiceConst.LogType, additionalMessage: String? = null
    ):Any? {
        var retVal: Any? = null
        val latency = measureTimeMillis {
            retVal = proceedingJoinPoint.proceed()
        }
        splitTransaction(proceedingJoinPoint.args).run {
            val transaction = first ?: Transaction()
            transaction.copy(
                logType = logType,
                latency = DurationFormatUtils.formatDurationHMS(latency),
                message =
                JsonObject().apply {
                    addProperty("method", proceedingJoinPoint.signature.toShortString())
                    addProperty("param", GsonUtil.toJson(this@run.second))
                    retVal?.also { this.addProperty("result", it.toString()) }
                    transaction.arg1?.also { this.addProperty("arg1", it.toString()) }
                    transaction.arg2?.also { this.addProperty("arg2", it.toString()) }
                    additionalMessage?.also { this.addProperty("additional", additionalMessage) }
                }
            ).also {
                aopLogging(it)
            }
        }
        return retVal
    }

    private fun splitTransaction(args: Array<Any>): Pair<Transaction?, List<Any>> {
        return args.firstOrNull { it is Transaction } as Transaction? to
            args.filter { it !is Transaction }
    }
    private fun aopLogging(transaction: Transaction) {
        LoggerUtil.transactionLog(transaction.apply {
            dt = LocalDateTime.now().toString()
            thread = Thread.currentThread().name
        })
    }
}