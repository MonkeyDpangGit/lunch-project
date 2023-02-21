package com.lunch.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.lunch.common.constants.ExecutorConstants;
import com.lunch.common.dto.PageDTO;
import com.lunch.common.enums.IErrorEnum;
import com.lunch.common.enums.SysErrorEnum;
import com.lunch.common.exception.SysException;
import com.lunch.common.executor.IExecutor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ExecutorService
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: ExecutorService
 */
@Component
public class ExecutorService {

    private static final Logger log = LoggerFactory.getLogger(ExecutorService.class);

    @Value("${executor.trace.enable:true}")
    private Boolean traceEnable;
    @Value("${executor.trace.expired:3000}")
    private Integer traceExpired;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("projectValidator")
    private Validator validator;

    /**
     * 执行指定executor方法
     *
     * @param input 输入对象
     * @return
     */
    public Map execute(String action, String requestId, Map input) {

        long beginTime = System.currentTimeMillis();
        Map result = null;

        try {
            // 检查参数
            if (StringUtils.isBlank(action)) {
                throw new Exception("'action' parameter required!");
            }
            input = input == null ? Maps.newHashMap() : input;
            requestId = StringUtils.isBlank(requestId) ? UUID.randomUUID().toString() : requestId;

            // 获取指定的executor
            String firstLetter = action.substring(0, 1).toUpperCase();
            String restLetter = action.substring(1);
            String realActionStr = firstLetter + restLetter;
            IExecutor executor = (IExecutor) applicationContext.getBean(realActionStr);
            if (executor == null) {
                throw new Exception("cannot find executor: " + action);
            }

            // 获得接口定义的execute方法
            Method method = null;
            int methodCount = 0;
            for (Method item : executor.getClass().getMethods()) {
                if ("execute".equals(item.getName()) && !item.isSynthetic()) {
                    method = item;
                    methodCount++;
                }
            }
            if (methodCount != 1) {
                throw new Exception("too many execute method");
            }

            // 处理输入参数
            Class<?> paramType = method.getParameterTypes()[0];
            Object inputObj = JSONObject.parseObject(JSON.toJSONString(input), paramType);

            // 检查输入参数
            checkParam(inputObj);

            // 执行处理逻辑
            Object outputObj = executor.execute(inputObj);

            // 处理输出参数
            Map outMap = JSONObject.parseObject(JSON.toJSONString(outputObj, SerializerFeature.WriteMapNullValue));
            result = Maps.newHashMap();
            result.put(ExecutorConstants.REQUEST_ID, requestId);
            result.put(ExecutorConstants.RESPONSE, outMap);

            // 日志跟踪
            long consumeTime = System.currentTimeMillis() - beginTime;
            if (traceEnable || consumeTime > traceExpired) {

                log.info("[input] [action]{}, [requestId]{}, [param]{}", action, requestId, input.toString());
                log.info("[output] [action]{}, [requestId]{}, [param]{}", action, requestId, result.toString());
                log.info("[summary] [action]{}, [requestId]{}, [consume]{}", action, requestId, consumeTime);
            }

        } catch (Exception e) {
            result = errorResponse(requestId, e);

            log.error("[input] [action]{}, [requestId]{}, [param]{}", action, requestId, input.toString());
            log.error("[output] [action]{}, [requestId]{}, [param]{}", action, requestId, result.toString());

            long consumeTime = System.currentTimeMillis() - beginTime;
            if (e instanceof SysException || e instanceof IllegalArgumentException) {
                // 业务错误，不打印堆栈
                log.error("[summary] [action]{}, [requestId]{}, [consume]{}", action, requestId, consumeTime);
            } else {
                log.error("[summary] [action]{}, [requestId]{}, [consume]{}", action, requestId, consumeTime, e);
            }
        }
        return result;
    }

    /**
     * 处理错误返回
     *
     * @param requestId 请求id
     * @param e 异常类
     * @return map
     */
    private Map errorResponse(String requestId, Exception e) {

        Map errorMap = Maps.newHashMap();

        if (e instanceof SysException) {
            SysException exception = (SysException) e;
            errorMap.put(ExecutorConstants.CODE, exception.getErrorEnum().getErrorCode());
            String errorMessage = exception.getMessage();
            errorMap.put(ExecutorConstants.MESSAGE, errorMessage);
        } else if (e instanceof IllegalArgumentException) {
            IErrorEnum errorEnum = SysErrorEnum.ILLEGAL_PARAMETER;
            errorMap.put(ExecutorConstants.CODE, errorEnum.getErrorCode());
            String errorMessage = new SysException(errorEnum, e.getMessage()).getMessage().replace(", %s", "");
            errorMap.put(ExecutorConstants.MESSAGE, errorMessage);
        } else {
            IErrorEnum errorEnum = SysErrorEnum.UNKNOWN_EXCEPTION;
            errorMap.put(ExecutorConstants.CODE, errorEnum.getErrorCode());
            String errorMessage = new SysException(errorEnum, e.getMessage()).getMessage().replace(", %s", "");
            errorMap.put(ExecutorConstants.MESSAGE, errorMessage);
        }

        Map result = Maps.newHashMap();
        result.put(ExecutorConstants.REQUEST_ID, requestId);
        result.put(ExecutorConstants.ERROR, errorMap);
        return result;
    }

    /**
     * 参数校验
     *
     * @param t 范型参数
     * @param <T>
     */
    private <T> void checkParam(T t) {
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations.size() > 0) {
            String message = "";
            for (ConstraintViolation<T> violation : violations) {
                // todo multi-language
                String propertyName = violation.getPropertyPath().toString();
                propertyName = violation.getMessage();

                // todo multi-annotation
                Class anotationType = violation.getConstraintDescriptor().getAnnotation().annotationType();
                if (anotationType == NotBlank.class || anotationType == NotEmpty.class
                        || anotationType == NotNull.class) {
                    message = "'" + propertyName + "'不能为空";
                }

                break;
            }
            throw new IllegalArgumentException(message);
        }
        if (t instanceof PageDTO) {
            PageDTO pageDTO = (PageDTO) t;
            Integer offset = pageDTO.getOffset();
            Integer limit = pageDTO.getLimit();
            if ((offset == null && limit != null) || (limit == null && offset != null)) {
                throw new IllegalArgumentException("'Offset' and 'Limit' should attend at the same time");
            } else if (offset != null && limit != null) {
                if (offset < 0) {
                    throw new IllegalArgumentException("'Offset' cannot be less then zero");
                }
                if (limit <= 0) {
                    throw new IllegalArgumentException("'Limit' should be greater then zero");
                }
            }
        }
    }

}
