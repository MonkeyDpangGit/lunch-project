package com.lunch.common.executor;

/**
 * IExecutor
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: IExecutor
 */
public interface IExecutor<Input, Output> {

    /**
     * 执行
     *
     * @param input the input
     * @return output output
     * @throws Exception the exception
     */
    Output execute(Input input) throws Exception;
}
