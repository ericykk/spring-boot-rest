package com.eric.base.core;

/**
 * 描述:定义统一响应结果
 *
 * @author eric
 * @create 2018-06-20 下午2:51
 */
public class JsonResult {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Object data;

    private Result result;

    public JsonResult success() {
        this.result = new Result(true, OK);
        return this;
    }

    public JsonResult success(Object data) {
        this.result = new Result(true, OK);
        this.data = data;
        return this;
    }

    public JsonResult fail() {
        this.result = new Result(false, ERROR);
        return this;
    }


    public JsonResult fail(String message) {
        this.result = new Result(false, message);
        return this;
    }


    public Object getData() {
        return data;
    }


    public Result getResult() {
        return result;
    }


    public class Result {


        private boolean success;

        private String message;


        public Result(boolean success) {
            this.success = success;
        }


        public Result(boolean success, String message) {
            this.success = success;
            this.message = message;

        }

        public boolean isSuccess() {
            return success;

        }

        public String getMessage() {
            return message;

        }
    }
}
